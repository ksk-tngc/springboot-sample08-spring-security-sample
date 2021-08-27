package com.example.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ログインページのコントローラ。
 */
@Controller
@RequestMapping("/")
public class LoginController {

	/**
	 * トップページ。
	 */
	@GetMapping("/")
	public String index() {
		return "redirect:/login";
	}

	/**
	 * ログインページ。
	 */
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}

	/**
	 * ログイン成功時のページ。
	 */
	@GetMapping("/login/success")
	public String getSuccess(Model model, Principal principal) {
		model.addAttribute("userId", principal.getName()); // Spring Securityで現在ログインしているユーザIDを取得
		return "login-success";
	}

}
