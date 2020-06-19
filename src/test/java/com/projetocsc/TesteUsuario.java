package com.projetocsc;

import org.junit.Assert;
import org.junit.Test;

import com.projetocsc.facade.UsuarioFacade;
import com.projetocsc.model.Usuario;

public class TesteUsuario {
	
	UsuarioFacade usuarioFacade = new UsuarioFacade();
	
	@Test
	public void cadastroUsuarioValido() {
		
		Usuario usuario = new Usuario();
		usuario.setNome("Fabiana");
		usuario.setAcesso("Administrador");
		usuario.setLogin("fabi");
		usuario.setSenha("fabi123");
		Assert.assertTrue(usuarioFacade.criarUsuario(usuario));
	}

	@Test
	public void cadastroNomeNulo() {
		
		Usuario usuario = new Usuario();
		usuario.setAcesso("Administrador");
		usuario.setLogin("fabi");
		usuario.setSenha("fabi123");
		Assert.assertFalse(usuarioFacade.criarUsuario(usuario));
		
	}

	@Test
	public void cadastroLoginExistente() {
		Usuario usuario = new Usuario();
		usuario.setNome("Admin");
		usuario.setAcesso("Administrador");
		usuario.setLogin("admin");
		usuario.setSenha("admin");
		Assert.assertFalse(usuarioFacade.criarUsuario(usuario));
		
	}
	
}
