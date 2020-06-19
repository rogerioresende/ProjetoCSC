package com.projetocsc.facade;

import java.sql.SQLException;
import java.util.List;

import com.projetocsc.model.Alunos;
import com.projetocsc.model.Tcc;
import com.projetocsc.model.DAO.TccDAO;

public class TccFacade {

	TccDAO tccDAO = new TccDAO();
	
	
	public List<Tcc> listarTcc(){
		return tccDAO.listarTcc();
	}
	
	public boolean cadastrarTcc(Tcc tcc, Alunos alunos) {

		boolean criado = false;
		if(alunos.getAluno1() != null && alunos.getAluno2() != null && alunos.getAluno3() != null) {
			if(!alunos.getAluno1().equals("")) {
				tcc.setAlunos(alunos.getAluno1() + "/" + alunos.getMatricula1());
			}
			if(!alunos.getAluno2().equals("") && alunos.getAluno3().equals("")) {
				tcc.setAlunos(tcc.getAlunos() + " e " + alunos.getAluno2() + "/" + alunos.getMatricula2());
			}
			if(!alunos.getAluno2().equals("") && !alunos.getAluno3().equals("")) {
				tcc.setAlunos(tcc.getAlunos() + ", " + alunos.getAluno2() + "/" + alunos.getMatricula2() +
						" e " +  alunos.getAluno3() + "/" + alunos.getMatricula3());
			}
		}
		try {
			criado = tccDAO.criarTcc(tcc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return criado;
	}
	
	public Tcc listarTcc(int id){
		return tccDAO.listarTcc(id);
	}
	
	public Alunos listarAlunos(Tcc tcc) {
		String[] aux = tcc.getAlunos().split(", |\\/|\\ e ");
		Alunos alunos = new Alunos();
		if(aux.length >= 2) {
			alunos.setAluno1(aux[0]);
			alunos.setMatricula1(aux[1]);
		}
		if(aux.length >= 4) {
			alunos.setAluno2(aux[2]);
			alunos.setMatricula2(aux[3]);
		}
		if(aux.length == 6) {
			alunos.setAluno3(aux[4]);
			alunos.setMatricula3(aux[5]);	
		}
		return alunos;	
	}
	
	public boolean updateTcc(Tcc tcc, Alunos alunos){
		
		boolean update = false;
		
		if(alunos.getAluno1() != null && alunos.getAluno2() != null && alunos.getAluno3() != null) {
			if(!alunos.getAluno1().equals("")) {
				tcc.setAlunos(alunos.getAluno1() + "/" + alunos.getMatricula1());
			}
			if(!alunos.getAluno2().equals("") && alunos.getAluno3().equals("")) {
				tcc.setAlunos(tcc.getAlunos() + " e " + alunos.getAluno2() + "/" + alunos.getMatricula2());
			}
			if(!alunos.getAluno2().equals("") && !alunos.getAluno3().equals("")) {
				tcc.setAlunos(tcc.getAlunos() + ", " + alunos.getAluno2() + "/" + alunos.getMatricula2() +
						" e " +  alunos.getAluno3() + "/" + alunos.getMatricula3());
			}
		}
		
		try {
			update = tccDAO.updateTcc(tcc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return update;
	}
	
	public boolean excluirTcc(int id) {
		
		boolean excluido = false;
		
		try {
			excluido = tccDAO.excluirTcc(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excluido;
	}
	
	public List<Tcc> listarTcc(String orientador, String professor, String curso, String anoSemestre){
		
		if(!orientador.equals("")) {
			return tccDAO.listarTcc("orientador", orientador);
		}
		if(!professor.equals("")) {
			return tccDAO.listarTcc("professor", professor);
		}
		if(!curso.equals("")) {
			return tccDAO.listarTcc("curso", curso);
		}
		if(!anoSemestre.equals("")) {
			return tccDAO.listarTcc("anoSemestre", anoSemestre);
		}
		return tccDAO.listarTcc();
	}
	
}
