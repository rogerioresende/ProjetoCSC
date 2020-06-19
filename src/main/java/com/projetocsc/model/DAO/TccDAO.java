package com.projetocsc.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.projetocsc.model.Tcc;
import com.projetocsc.model.util.ConnectionDbFactory;

public class TccDAO {
	
	ConnectionDbFactory connectionDbFactory;
	private Connection con;
	private Statement stm;
	private PreparedStatement stmt;
	
	public TccDAO() {
    	connectionDbFactory = new ConnectionDbFactory();
    	con = connectionDbFactory.getConnection();
    }
    
    public List<Tcc> listarTcc(){
  
    	List<Tcc> lista = new ArrayList<Tcc>();
    	ResultSet result;
    	
    	try {
    		
			stm = con.createStatement();
			result = stm.executeQuery("select * from tcc");
			
			while(result.next()) {
				
				Tcc tcc = new Tcc();
				
				tcc.setId(result.getInt("id_tcc"));
				tcc.setTitulo(result.getString("titulo"));
				tcc.setProfessor(result.getString("professor"));
				tcc.setOrientador(result.getString("orientador"));
				tcc.setAnoSemestre(result.getString("anoSemestre"));
				tcc.setTipo(result.getString("tipo"));
				tcc.setCurso(result.getString("curso"));
				tcc.setAlunos(result.getString("alunos"));
				lista.add(tcc);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return lista;
    }
    
    public boolean criarTcc(Tcc tcc) throws SQLException {
    	
    	boolean criado = false;
    	String sqlCriar = "insert into tcc(titulo, professor, orientador, curso, anoSemestre, tipo, alunos)\r\n" + 
    			          "values(?,?,?,?,?,?,?);";
    	
		 try {
	            con.setAutoCommit(false);
	            stmt = con.prepareStatement(sqlCriar);
	            stmt.setString(1, tcc.getTitulo());
	            stmt.setString(2, tcc.getProfessor());
	            stmt.setString(3, tcc.getOrientador());
	            stmt.setString(4, tcc.getCurso());
	            stmt.setString(5, tcc.getAnoSemestre());
	            stmt.setString(6, tcc.getTipo());
	            stmt.setString(7, tcc.getAlunos());
	            stmt.executeUpdate();
	            
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
    
    public Tcc listarTcc(int id){
  	  
    	Tcc tcc = new Tcc();
    	ResultSet result;
    	
    	try {
    		
			stm = con.createStatement();
			result = stm.executeQuery("select * from tcc where id_tcc = " + id + ";");
			
			while(result.next()) {
				tcc.setId(result.getInt("id_tcc"));
				tcc.setTitulo(result.getString("titulo"));
				tcc.setProfessor(result.getString("professor"));
				tcc.setOrientador(result.getString("orientador"));
				tcc.setAnoSemestre(result.getString("anoSemestre"));
				tcc.setTipo(result.getString("tipo"));
				tcc.setCurso(result.getString("curso"));
				tcc.setAlunos(result.getString("alunos"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return tcc;
    }
    
    public boolean updateTcc(Tcc tcc) throws SQLException{
		 boolean update = false;
			
		 try {
	            stmt = con.prepareStatement("update tcc set titulo = '" + tcc.getTitulo() + 
	            		"', professor = '" + tcc.getProfessor() + "', orientador = '"
	            		+ tcc.getOrientador() + "', curso = '" + tcc.getCurso() + 
	            		"', anoSemestre = '" + tcc.getAnoSemestre() + "', tipo = '" + tcc.getTipo() +
	            		"', alunos = '" + tcc.getAlunos() +
	            		"' where id_tcc = " + tcc.getId() + ";");
	            
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
 
	 public boolean excluirTcc(int id) throws SQLException{
	 	
	 	boolean excluir = false;
	 	
	 	try {
	 		
	 		stmt = con.prepareStatement("delete from tcc where id_tcc = " + id + ";");
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
	 
	 public List<Tcc> listarTcc(String buscarPor, String igual){
		  
	    	List<Tcc> lista = new ArrayList<Tcc>();
	    	ResultSet result;
	    	
	    	try {
	    		
				stm = con.createStatement();
				result = stm.executeQuery("select * from tcc where " + buscarPor + " like '" + igual + "%';");
				
				while(result.next()) {
					
					Tcc tcc = new Tcc();
					
					tcc.setId(result.getInt("id_tcc"));
					tcc.setTitulo(result.getString("titulo"));
					tcc.setProfessor(result.getString("professor"));
					tcc.setOrientador(result.getString("orientador"));
					tcc.setAnoSemestre(result.getString("anoSemestre"));
					tcc.setTipo(result.getString("tipo"));
					tcc.setCurso(result.getString("curso"));
					tcc.setAlunos(result.getString("alunos"));
					lista.add(tcc);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	return lista;
	 }
	    
}
