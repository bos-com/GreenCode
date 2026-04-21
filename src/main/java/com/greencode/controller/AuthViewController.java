package com.greencode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthViewController {

    @GetMapping("/auth/reset-request")
    public String resetRequest() {
        return "auth/reset-request";
    }

    @GetMapping("/auth/reset-validate")
    public String resetValidate() {
        return "auth/reset-validate";
    }

    @GetMapping("/auth/reset-password")
    public String resetPassword() {
        return "auth/reset-password";
    }
}
