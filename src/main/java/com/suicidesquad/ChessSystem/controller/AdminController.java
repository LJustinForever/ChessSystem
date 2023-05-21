package com.suicidesquad.ChessSystem.controller;

import com.suicidesquad.ChessSystem.entity.Admin;
import com.suicidesquad.ChessSystem.entity.User;
import com.suicidesquad.ChessSystem.service.AdminService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.suicidesquad.ChessSystem.utils.Utils.ONE_DAY;

@RestController
@RequestMapping(path = "api/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    @CrossOrigin()
    public List<Admin> getAdmins(){
        return adminService.getAdmins();
    }

    @GetMapping("/{adminId}")
    @CrossOrigin()
    public Admin getAdmin(@PathVariable("adminId") Long adminId){
        return adminService.getAdmin(adminId);
    }

    @PostMapping("/register")
    @CrossOrigin()
    public void registerAdmin(Admin admin){
        adminService.addNewAdmin(admin);
    }

    @PostMapping("/login")
    @ResponseBody
    @CrossOrigin()
    public Map<String, Long> loginUser(@RequestBody Admin admin, HttpServletResponse response) throws NoSuchAlgorithmException {
        String jwtToken = adminService.loginAdmin(admin);

        Cookie cookie = new Cookie("id",jwtToken);
        cookie.setMaxAge(ONE_DAY);
        cookie.setSecure(false);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        Map<String, Long> jsonResponse = new HashMap<>();
        jsonResponse.put("id", adminService.getAdmin(admin.getEmailAddress()).getId());
        return jsonResponse;
    }
}
