package com.ecommerce.admin.controller;

import com.ecommerce.library.dto.AdminDto;
import com.ecommerce.library.model.Admin;
import com.ecommerce.library.service.impl.AdminServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }


    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("adminDto", new AdminDto());
        return "register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model){
        return "forgot-password";
    }

    @PostMapping("/register-new")
    public String addNewAdmin(
            @ModelAttribute("adminDto")AdminDto adminDto,
            BindingResult result,
            Model model,
            HttpSession session
    ){
        try {
            if(result.hasErrors()){
                model.addAttribute("adminDto",adminDto);
                return "register";
            }
            String username = adminDto.getUsername();
            Admin admin = adminService.findByUsername(username);

            model.addAttribute("adminDto",adminDto);

            if(admin != null){
                session.setAttribute("message","Your email has been registered");
                return "register";
            }
            if(adminDto.getPassword().equals((adminDto.getRepeatPassword()))){
                adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
                adminService.save(adminDto);
                session.setAttribute("message","Register successfully");
            }else{
                session.setAttribute("message", "The passwords didn't match");
                return "register";
            }

        }catch (Exception e){
            e.printStackTrace();
            session.setAttribute("message", "Error: Cannot register. Please try again.");
        }

        return "register";
    }
}
