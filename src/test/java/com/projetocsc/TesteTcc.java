package com.projetocsc;

import org.junit.Assert;
import org.junit.Test;

import com.projetocsc.facade.TccFacade;
import com.projetocsc.model.Alunos;
import com.projetocsc.model.Tcc;

public class TesteTcc {

	TccFacade tccFacade = new TccFacade();
	
	@Test
	public void cadastroTccValido() {
		
		Tcc tcc = new Tcc();
		Alunos alunos = new Alunos();
		tcc.setOrientador("Fabiana");
		tcc.setProfessor("Thiago");
		tcc.setTitulo("Segurança da Informação");
		tcc.setTipo("TCC1");
		tcc.setCurso("Sistemas de Informções");
		tcc.setAnoSemestre("2020/2");
		alunos.setAluno1("Paulo Henrique Lopes Gomes");
		alunos.setMatricula1("201200020202");
		alunos.setAluno2("");
		alunos.setMatricula2("");
		alunos.setAluno3("");
		alunos.setMatricula3("");
		Assert.assertTrue(tccFacade.cadastrarTcc(tcc, alunos));
	}

	@Test
	public void cadastroTccNulo() {
		
		Tcc tcc = new Tcc();
		Alunos alunos = new Alunos();
		alunos.setAluno1("Paulo Henrique Lopes Gomes");
		alunos.setMatricula1("201200020202");
		alunos.setAluno2("");
		alunos.setMatricula2("");
		alunos.setAluno3("");
		alunos.setMatricula3("");
		Assert.assertFalse(tccFacade.cadastrarTcc(tcc, alunos));
	}
	
	@Test
	public void cadastroAlunosNulo() {
		
		Tcc tcc = new Tcc();
		Alunos alunos = new Alunos();
		tcc.setOrientador("Fabiana");
		tcc.setProfessor("Thiago");
		tcc.setTitulo("Segurança da Informação");
		tcc.setTipo("TCC1");
		tcc.setCurso("Sistemas de Informções");
		tcc.setAnoSemestre("2020/2");
		Assert.assertFalse(tccFacade.cadastrarTcc(tcc, alunos));
		
	}
	
}
