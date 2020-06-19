package com.projetocsc.facade;

import java.util.ArrayList;
import java.util.List;

import com.projetocsc.model.Usuario;

public class LoginFacade {

	
	public boolean logar(String login, String senha) {
		
		UsuarioFacade usuarioFacade = new UsuarioFacade();
        List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = usuarioFacade.listarUsuario();
			
		for(Usuario usuario : usuarios) {
			if(usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
				return true;
			}
		}
		return false;
	}
	
    public boolean verificarAdm(String login) {
		
		UsuarioFacade usuarioFacade = new UsuarioFacade();
        List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = usuarioFacade.listarUsuario();
			
		for(Usuario usuario : usuarios) {
			if(usuario.getLogin().equals(login)) {
				if(usuario.getAcesso().equals("Administrador")) {
					return true;
				}
			}
		}
		return false;
	}
}
