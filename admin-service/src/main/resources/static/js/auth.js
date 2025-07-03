/**
 * 认证工具类
 * 提供JWT令牌管理、请求拦截和身份验证功能
 */

// 添加请求拦截器，统一在请求头中添加token
function setupTokenInterceptor() {
    // 保存原始的fetch函数
    const originalFetch = window.fetch;

    // 重写fetch函数
    window.fetch = function(resource, config = {}) {
        // 获取token
        const token = localStorage.getItem('admin_token');

        // 如果token存在，添加到请求头
        if (token) {
            // 初始化headers对象
            if (!config.headers) {
                config.headers = {};
            }

            // 添加Authorization头
            config.headers['Authorization'] = 'Bearer ' + token;
        }

        // 调用原始fetch函数
        return originalFetch(resource, config)
            .then(response => {
                // 如果返回401未授权，重定向到登录页面
                if (response.status === 401) {
                    localStorage.removeItem('admin_token');
                    window.location.href = '/admin/login';
                    return Promise.reject('未登录或登录已过期');
                }
                return response;
            });
    };

    // 为XMLHttpRequest添加拦截
    (function() {
        const originalOpen = XMLHttpRequest.prototype.open;
        XMLHttpRequest.prototype.open = function() {
            this.addEventListener('readystatechange', function() {
                if (this.readyState === 1) { // OPENED
                    const token = localStorage.getItem('admin_token');
                    if (token) {
                        this.setRequestHeader('Authorization', 'Bearer ' + token);
                    }
                }
                if (this.readyState === 4 && this.status === 401) { // DONE with 401
                    localStorage.removeItem('admin_token');
                    window.location.href = '/admin/login';
                }
            });
            originalOpen.apply(this, arguments);
        };
    })();

    // 如果使用axios
    if (window.axios) {
        axios.interceptors.request.use(config => {
            const token = localStorage.getItem('admin_token');
            if (token) {
                config.headers['Authorization'] = 'Bearer ' + token;
            }
            return config;
        });

        axios.interceptors.response.use(
            response => response,
            error => {
                if (error.response && error.response.status === 401) {
                    localStorage.removeItem('admin_token');
                    window.location.href = '/admin/login';
                }
                return Promise.reject(error);
            }
        );
    }
}

// 检查用户是否已登录
function checkAuth() {
    const token = localStorage.getItem('admin_token');
    if (!token && !window.location.pathname.includes('/admin/login')) {
        window.location.href = '/admin/login';
        return false;
    }
    return true;
}

// 退出登录
function logout() {
    fetch('/admin/logout', {
        method: 'POST'
    })
        .then(() => {
            localStorage.removeItem('admin_token');
            window.location.href = '/admin/login';
        })
        .catch(err => {
            console.error('退出登录失败:', err);
            // 即使API调用失败，也清除本地token并重定向
            localStorage.removeItem('admin_token');
            window.location.href = '/admin/login';
        });
}

// 令牌刷新机制
function setupTokenRefresh() {
    // 每10分钟检查一次令牌
    setInterval(() => {
        const token = localStorage.getItem('admin_token');
        if (!token) return;

        try {
            // 解析令牌获取过期时间
            const payload = JSON.parse(atob(token.split('.')[1]));
            const expirationTime = payload.exp * 1000; // 转换为毫秒
            const currentTime = Date.now();

            // 如果令牌将在30分钟内过期，刷新它
            if (expirationTime - currentTime < 30 * 60 * 1000) {
                fetch('/admin/refresh-token', {
                    method: 'POST',
                    headers: {
                        'Authorization': 'Bearer ' + token
                    }
                })
                    .then(res => res.json())
                    .then(data => {
                        if (data.succeed && data.data && data.data.token) {
                            localStorage.setItem('admin_token', data.data.token);
                            console.log('令牌已刷新');
                        }
                    })
                    .catch(err => console.error('刷新令牌失败:', err));
            }
        } catch (e) {
            console.error('解析令牌失败:', e);
            // 如果令牌格式错误，清除它
            localStorage.removeItem('admin_token');
        }
    }, 10 * 60 * 1000); // 10分钟检查一次
}

// 获取当前登录用户信息
function getCurrentUser() {
    const token = localStorage.getItem('admin_token');
    if (!token) return null;

    try {
        const payload = JSON.parse(atob(token.split('.')[1]));
        return payload.sub; // 用户名
    } catch (e) {
        console.error('解析令牌失败:', e);
        return null;
    }
}

// 初始化
document.addEventListener('DOMContentLoaded', function() {
    setupTokenInterceptor();
    setupTokenRefresh();

    // 在非登录页面检查登录状态
    if (!window.location.pathname.includes('/admin/login')) {
        checkAuth();
    }

    // 为所有退出登录按钮绑定事件
    document.querySelectorAll('.logout-btn').forEach(btn => {
        btn.addEventListener('click', function(e) {
            e.preventDefault();
            logout();
        });
    });
});

// 导出API，允许其他脚本使用这些函数
window.authAPI = {
    checkAuth,
    logout,
    getCurrentUser
};
