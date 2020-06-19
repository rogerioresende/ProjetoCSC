package com.projetocsc.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.projetocsc.model.Usuario;
import com.projetocsc.model.util.ConnectionDbFactory;

public class UsuarioDAO {
	
	ConnectionDbFactory connectionDbFactory;
	private Connection con;
	private Statement stm;
	private PreparedStatement stmt;
	
    public UsuarioDAO() {
    	connectionDbFactory = new ConnectionDbFactory();
    	con = connectionDbFactory.getConnection();
    }
    
    public List<Usuario> listarUsuario(){
  
    	List<Usuario> lista = new ArrayList<Usuario>();
    	ResultSet result;
    	
    	try {
    		
			stm = con.createStatement();
			result = stm.executeQuery("select * from usuario");
			
			while(result.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(result.getInt("id_usuario"));
				usuario.setNome(result.getString("nome"));
				usuario.setLogin(result.getString("login"));
				usuario.setSenha(result.getString("senha"));
				usuario.setAcesso(result.getString("acesso"));
				lista.add(usuario);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return lista;
    }
    
    public boolean criarUsuario(Usuario usuario) throws SQLException {
    	
    	boolean criado = false;
    	String sqlCriar = "insert into usuario(nome, login, senha, acesso)\r\n" + 
    			          "values(?,?,?,?);";
    	
		 try {
	            con.setAutoCommit(false);
	            stmt = con.prepareStatement(sqlCriar);
	            stmt.setString(1, usuario.getNome());
	            stmt.setString(2, usuario.getLogin());
	            stmt.setString(3, usuario.getSenha());
	            stmt.setString(4, usuario.getAcesso());
	            stmt.executeUpdate();
	            
	            //Grava as informações se caso de problema os dados não são gravados
	            con.commit();
	            criado = true;
	     
	        } catch (SQLException e) {
	            if (con != null) {
	                try {
	                    System.err.print("Rollback efetuado na transação " + e);
	                    con.rollback();
	                } catch (SQLException e2) {
	                    System.err.print("Erro na transação! " + e2);
	                    criado = false;
	                }
	            }
	        } finally {
	            if (stmt != null) {
	                stmt.close();
	            }
	           con.setAutoCommit(true);
	        }
		return criado;
	}
    
    public Usuario listarUsuario(int id){
  	  
    	Usuario usuario = new Usuario();
    	ResultSet result;
    	
    	try {
    		
			stm = con.createStatement();
			result = stm.executeQuery("select * from usuario where id_usuario = " + id + ";");
			
			while(result.next()) {
				usuario.setId(result.getInt("id_usuario"));
				usuario.setNome(result.getString("nome"));
				usuario.setLogin(result.getString("login"));
				usuario.setSenha(result.getString("senha"));
				usuario.setAcesso(result.getString("acesso"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return usuario;
    }
    
    public boolean updateUsuario(Usuario usuario) throws SQLException{
		 boolean update = false;
			
		 try {
	            stmt = con.prepareStatement("update usuario set nome = '" + usuario.getNome() + 
	            		"', acesso = '" + usuario.getAcesso() + "', login = '"
	            		+ usuario.getLogin() + "', senha = '" + usuario.getSenha() + 
	            		"' where id_usuario = " + usuario.getId() + ";");
	            
	            stmt.executeUpdate();
	            update = true;
	
	        } catch (SQLException e) {
	            if (con != null) {
	                try {
	                    System.err.print("Rollback efetuado na transação " + e);
	                    con.rollback();
	                } catch (SQLException e2) {
	                    System.err.print("Erro na transação! " + e2);
	                    update = false;
	                }
	            }
	        } finally {
	            if (stmt != null) {
	                stmt.close();
	            }
	        }
		 return update;
	}
    
    public boolean excluirUsuario(int id) throws SQLException{
    	
    	boolean excluir = false;
    	
    	try {
    		
    		stmt = con.prepareStatement("delete from usuario where id_usuario = " + id + ";");
    		stmt.executeUpdate();
			excluir = true;
			
		} catch (SQLException e) {
            if (con != null) {
                try {
                    System.err.print("Rollback efetuado na transação " + e);
                    con.rollback();
                } catch (SQLException e2) {
                    System.err.print("Erro na transação! " + e2);
                    excluir = false;
                }
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    	return excluir;
    }
    
    public List<Usuario> listarUsuario(String buscarPor, String igual){
    	  
    	List<Usuario> lista = new ArrayList<Usuario>();
    	ResultSet result;
    	
    	try {
    		
			stm = con.createStatement();
			result = stm.executeQuery("select * from usuario where " + buscarPor + " like '" + igual + "%';");
			
			while(result.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(result.getInt("id_usuario"));
				usuario.setNome(result.getString("nome"));
				usuario.setLogin(result.getString("login"));
				usuario.setSenha(result.getString("senha"));
				usuario.setAcesso(result.getString("acesso"));
				lista.add(usuario);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return lista;
    }
}
