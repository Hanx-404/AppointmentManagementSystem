package com.niit.adminservice.controller;

import com.niit.adminservice.dao.AdminRepository;
import com.niit.adminservice.entity.Admin;
import com.niit.adminservice.entity.Result;
import com.niit.adminservice.service.AdminService;
import com.niit.adminservice.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: HeiLong
 * @CreateTime: 2025-06-30)
 */
@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/index")
    public String showIndexPage() {
        return "redirect:/index";
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestParam String username,@RequestParam String password){
        Admin admin = adminService.findByName(username);
        if(admin == null){
            return Result.fail("用户名不存在");
        }
        if(!password.equals(admin.getPassword())){
            return Result.fail("对不起，密码不存在");
        }

        String token = jwtUtil.generateToken(admin.getUsername());
        redisTemplate.opsForValue().set(admin.getUsername(), token, jwtUtil.getExpiration(), TimeUnit.SECONDS);
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("admin", admin);

        return Result.success(data);
    }
    @PostMapping("/logout")
    @ResponseBody
    public Result logout(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        if (StringUtils.hasText(authToken) && authToken.startsWith("Bearer ")) {
            authToken = authToken.substring(7);
            String username = jwtUtil.getUsernameFromToken(authToken);

            // 从Redis中移除token
            redisTemplate.delete(username);
        }

        return Result.success("退出成功");
    }
}
