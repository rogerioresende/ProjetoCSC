package com.projetocsc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projetocsc.facade.TccFacade;
import com.projetocsc.model.Alunos;
import com.projetocsc.model.Tcc;

@Controller
public class TccController {

	TccFacade tccFacade;
	
	@RequestMapping(value = "/listarTccAdm", method = RequestMethod.GET)
	public ModelAndView listTccAdm() {
		ModelAndView mv = new ModelAndView("tcc/listarTccAdm");
		tccFacade = new TccFacade();
		List<Tcc> tccs = new ArrayList<Tcc>();
		tccs = tccFacade.listarTcc();
		mv.addObject("tccs", tccs);
		return mv;
	}
	
	@RequestMapping(value = "/listarTccUsuario", method = RequestMethod.GET)
	public ModelAndView listTccUsuario() {
		ModelAndView mv = new ModelAndView("tcc/listarTccUsuario");
		tccFacade = new TccFacade();
		List<Tcc> tccs = new ArrayList<Tcc>();
		tccs = tccFacade.listarTcc();
		mv.addObject("tccs", tccs);
		return mv;
	}
	
	@RequestMapping(value= "/cadastrarTccAdm", method = RequestMethod.GET)
	public ModelAndView cadastroTccAdm() {
		ModelAndView mv = new ModelAndView("tcc/cadastroTcc");
		boolean adm = true;
		mv.addObject("adm", adm);
		return mv;
	}
	@RequestMapping(value= "/cadastrarTccUsuario", method = RequestMethod.GET)
	public ModelAndView cadastroTccUsuario() {
		ModelAndView mv = new ModelAndView("tcc/cadastroTcc");
		boolean adm = false;
		mv.addObject("adm", adm);
		return mv;
	}
	@RequestMapping(value= "/cadastrarTccAdm", method = RequestMethod.POST)
	public String cadastrarTccAdm(Tcc tcc, Alunos alunos, RedirectAttributes attributes){
		tccFacade = new TccFacade();
		tccFacade.cadastrarTcc(tcc, alunos);
		attributes.addFlashAttribute("mensagem", "TCC salvo com sucesso");
		return "redirect:/listarTccAdm";
	}
	@RequestMapping(value= "/cadastrarTccUsuario", method = RequestMethod.POST)
	public String cadastrarTccUsuario(Tcc tcc, Alunos alunos, RedirectAttributes attributes){
		tccFacade = new TccFacade();
		tccFacade.cadastrarTcc(tcc, alunos);
		 attributes.addFlashAttribute("mensagem", "TCC salvo com sucesso");
		return "redirect:/listarTccUsuario";
	}
	
	@RequestMapping(value = "/editarTccAdm/{id}", method = RequestMethod.GET)
	public ModelAndView editarTccAdm(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("tcc/editarTccAdm");
		
		tccFacade = new TccFacade();
		Tcc tcc = new Tcc();
		Alunos alunos = new Alunos();
		
		tcc = tccFacade.listarTcc(id);
		alunos = tccFacade.listarAlunos(tcc);
		mv.addObject("tcc", tcc);
		mv.addObject("alunos", alunos);
		return mv; 
	}
	
	@RequestMapping(value = "/editarTccUsuario/{id}", method = RequestMethod.GET)
	public ModelAndView editarTccUsuario(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("tcc/editarTccUsuario");
		
		tccFacade = new TccFacade();
		Tcc tcc = new Tcc();
		Alunos alunos = new Alunos();
		
		tcc = tccFacade.listarTcc(id);
		alunos = tccFacade.listarAlunos(tcc);
		mv.addObject("tcc", tcc);
		mv.addObject("alunos", alunos);
		return mv; 
	}
	
	@RequestMapping(value= "/editarTccAdm/{id}", method = RequestMethod.POST)
	public String updateTccAdm(Tcc tcc, Alunos alunos, RedirectAttributes attributes) {
		tccFacade = new TccFacade();
		tccFacade.updateTcc(tcc, alunos);
		attributes.addFlashAttribute("mensagem", "TCC atualizado com sucesso");
		return "redirect:/listarTccAdm";
	}
	
	@RequestMapping(value= "/editarTccUsuario/{id}", method = RequestMethod.POST)
	public String updateTccUsuario(Tcc tcc, Alunos alunos, RedirectAttributes attributes) {
		tccFacade = new TccFacade();
		tccFacade.updateTcc(tcc, alunos);
		attributes.addFlashAttribute("mensagem", "TCC atualizado com sucesso");
		return "redirect:/listarTccUsuario";
	}
	
	@RequestMapping(value = "/excluirTccAdm/{id}", method = RequestMethod.GET)
	public String excluirTccAdm(@PathVariable("id") int id, RedirectAttributes attributes) {
		tccFacade = new TccFacade();
	    tccFacade.excluirTcc(id);
	    attributes.addFlashAttribute("mensagem", "TCC excluído com sucesso");
		return "redirect:/listarTccAdm";
	}
	
	@RequestMapping(value = "/excluirTccUsuario/{id}", method = RequestMethod.GET)
	public String excluirTccUsuario(@PathVariable("id") int id, RedirectAttributes attributes) {
		tccFacade = new TccFacade();
	    tccFacade.excluirTcc(id);
	    attributes.addFlashAttribute("mensagem", "TCC excluído com sucesso");
		return "redirect:/listarTccUsuario";
	}
	
	@RequestMapping(value = "/pesquisarTccAdm", method = RequestMethod.POST)
	public ModelAndView listarTccAdm(String orientador, String professor, String curso, String anoSemestre) {
		ModelAndView mv = new ModelAndView("tcc/listarTccAdm");
		tccFacade = new TccFacade();
		List<Tcc> tccs = new ArrayList<Tcc>();
		tccs = tccFacade.listarTcc(orientador, professor, curso, anoSemestre);
		mv.addObject("tccs", tccs);
		return mv;
	}
}
