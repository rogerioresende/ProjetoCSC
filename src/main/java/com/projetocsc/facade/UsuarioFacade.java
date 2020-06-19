package com.projetocsc.facade;

import java.sql.SQLException;
import java.util.List;

import com.projetocsc.model.Usuario;
import com.projetocsc.model.DAO.UsuarioDAO;

public class UsuarioFacade {

	UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	public List<Usuario> listarUsuario(){
		return usuarioDAO.listarUsuario();
	}
	
	public boolean criarUsuario(Usuario usuario){
		boolean criado = false;
		try {
			criado = usuarioDAO.criarUsuario(usuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return criado;
	}
	
	public Usuario listarUsuario(int id){
		return usuarioDAO.listarUsuario(id);
	}
	
	public boolean updateUsuario(Usuario usuario){
		boolean update = false;
		try {
			update = usuarioDAO.updateUsuario(usuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return update;
	}
	
	public boolean excluirUsuario(int id) {
		boolean excluido = false;
		try {
			usuarioDAO.excluirUsuario(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excluido;
	}
	
	public List<Usuario> listarUsuario(String nome, String login){
		
		if(!nome.equals("") && login.equals("")) {
			return usuarioDAO.listarUsuario("nome", nome);
		}
		if(nome.equals("") && !login.equals("")) {
			return usuarioDAO.listarUsuario("login", login);
		}
		return usuarioDAO.listarUsuario();
	}

}
