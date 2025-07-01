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

// 初始化
document.addEventListener('DOMContentLoaded', function() {
    setupTokenInterceptor();

    // 在非登录页面检查登录状态
    if (!window.location.pathname.includes('/admin/login')) {
        checkAuth();
    }

    // 为所有退出登录按钮绑定事件
    document.querySelectorAll('.logout-btn').forEach(btn => {
        btn.addEventListener('click', logout);
    });
});
