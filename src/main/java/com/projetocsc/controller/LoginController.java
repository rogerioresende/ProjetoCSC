package com.projetocsc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projetocsc.facade.LoginFacade;

@Controller
public class LoginController {
	

	@RequestMapping(value= "/login", method = RequestMethod.GET)
	public String formUsuario() {
		return "login/login";
	}
	
	@RequestMapping(value= "/login", method = RequestMethod.POST)
	public String logar(String login, String senha, RedirectAttributes attributes) {
		
		LoginFacade loginFacade = new LoginFacade();
		boolean loginValido = false;
		boolean adm = false;
		loginValido = loginFacade.logar(login, senha);
		
		if(loginValido) {
			adm = loginFacade.verificarAdm(login);
			if(adm) {
				return "redirect:/listarTccAdm";
			}else {
				return "redirect:/listarTccUsuario";
			}
		}
		attributes.addFlashAttribute("mensagem", "Login ou senha estão incorretos!");
		return "redirect:/login";
	}
}
