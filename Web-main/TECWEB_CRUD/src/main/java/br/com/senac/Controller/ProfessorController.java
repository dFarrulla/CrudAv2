package br.com.senac.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.com.senac.entity.Professor;
import br.com.senac.service.ProfessorService;

@Controller
@RequestMapping("professor")
public class ProfessorController {
    
    @Autowired
    private ProfessorService service;

    @GetMapping("listar")
    public ModelAndView listarTodosProfessores(){
        ModelAndView mv = new ModelAndView("professor/paginaListaProfessores");
        mv.addObject("professores", service.buscarTodosProfessors());

        return mv;
    }

    @PostMapping("/salvar") 
    public ModelAndView salvarProfessor(Professor Professor){
        service.salvar(Professor);
        return listarTodosProfessores();
    }

    @GetMapping("/cadastrar") 
    public ModelAndView cadastrarProfessor(){
        ModelAndView mv = new ModelAndView("Professor/cadastrarProfessor");
        mv.addObject("professor", new Professor());
        return mv;
    }

    

    @PostMapping("/alterar") 
    public RedirectView alterarProfessor(Professor professor) {
        service.salvarAlteracao(professor);
        return (RedirectView) new RedirectView("listar");
    }

    @GetMapping("/alterar/{id}/{nome}") 
    public ModelAndView alterarProfessor(@PathVariable("id") Integer id, @PathVariable("nome") String nome) {
        ModelAndView mv = new ModelAndView("professor/alterarProfessor");
        Professor professor = new Professor();
        professor.setId(id);
        professor.setNome(nome);
        mv.addObject("professor", professor);
        return mv;
    }

    

    @GetMapping("/deletar/{id}") 
    public RedirectView deletarProfessor(@PathVariable("id") Integer id) {
        service.deletarProfessorId(id);
        return (RedirectView) new RedirectView("/professor/listar");
    }

}
