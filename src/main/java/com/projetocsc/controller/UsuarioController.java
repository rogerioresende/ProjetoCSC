package com.projetocsc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projetocsc.facade.UsuarioFacade;
import com.projetocsc.model.Usuario;

@Controller
public class UsuarioController {
	
	UsuarioFacade usuarioFacade;

	@RequestMapping(value= "/listarUsuario", method = RequestMethod.GET)
	public ModelAndView listUsuario() {
		
		ModelAndView mv = new ModelAndView("usuario/listarUsuario");
		usuarioFacade = new UsuarioFacade();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = usuarioFacade.listarUsuario();
		mv.addObject("usuarios", usuarios);
		return mv;
	}
	
	@RequestMapping(value= "/cadastrarUsuario", method = RequestMethod.GET)
	public String formUsuario() {
		return "usuario/cadastroUsuario";
	}
	
	@RequestMapping(value= "/cadastrarUsuario", method = RequestMethod.POST)
	public String formUsuario(Usuario usuario, RedirectAttributes attributes) {
		boolean criado = false;
		usuarioFacade = new UsuarioFacade();
		criado = usuarioFacade.criarUsuario(usuario);
		if(criado == true) {
			attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso");
			return "redirect:/listarUsuario";
		}else {
			attributes.addFlashAttribute("mensagem", "Erro ao salvar usuário...login já existe");
			return "redirect:/cadastrarUsuario";
		}
	}
	
	@RequestMapping(value = "/editarUsuario/{id}", method = RequestMethod.GET)
	public ModelAndView editarUsuario(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("usuario/editarUsuario");
		usuarioFacade = new UsuarioFacade();
		Usuario usuario = new Usuario();
		usuario = usuarioFacade.listarUsuario(id);
		mv.addObject("usuario", usuario);
		return mv; 
	}
	
	@RequestMapping(value= "/editarUsuario/{id}", method = RequestMethod.POST)
	public String updateUsuario(Usuario usuario, RedirectAttributes attributes) {
		usuarioFacade = new UsuarioFacade();
		usuarioFacade.updateUsuario(usuario);
		attributes.addFlashAttribute("mensagem", "Usuário atualizado com sucesso");
		return "redirect:/listarUsuario";
	}
	
	@RequestMapping(value = "/excluirUsuario/{id}", method = RequestMethod.GET)
	public String excluir(@PathVariable("id") int id, RedirectAttributes attributes) {
		usuarioFacade = new UsuarioFacade();
	    usuarioFacade.excluirUsuario(id);
	    attributes.addFlashAttribute("mensagem", "Usuário excluído com sucesso");
		return "redirect:/listarUsuario";
	}
	
	@RequestMapping(value = "/pesquisarUsuario", method = RequestMethod.POST)
	public ModelAndView listarUsuario(String nome, String login) {
		ModelAndView mv = new ModelAndView("usuario/listarUsuario");
		usuarioFacade = new UsuarioFacade();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = usuarioFacade.listarUsuario(nome, login);
		mv.addObject("usuarios", usuarios);
		return mv;
	}
}
