package com.tec.os.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tec.os.model.Procedimento;
import com.tec.os.repository.ProcedimentoRepository;
import com.tec.os.repository.filter.ProcedimentoFilter;

@Controller
@RequestMapping("/procedimentos")
public class ProcedimentosController {

	@Autowired
	private ProcedimentoRepository procedimentos;

	@GetMapping("/novo")
	public ModelAndView novo(Procedimento procedimento) {
		ModelAndView mv = new ModelAndView("Procedimento/cadastro-procedimento");
		mv.addObject(procedimento);
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Procedimento procedimento, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(procedimento);
		}
		procedimentos.save(procedimento);
		attributes.addFlashAttribute("mensagem", "Procedimento salvo com sucesso!");
		return new ModelAndView("redirect:/procedimentos");
	}

	@GetMapping
	public ModelAndView pesquisar(ProcedimentoFilter procedimentoFilter) {
		ModelAndView mv = new ModelAndView("Procedimento/pesquisa-procedimento");
		mv.addObject("procedimentos", procedimentos.findByDescricao(Optional.ofNullable(procedimentoFilter.getDescricao()).orElse("%")));
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		Procedimento procedimento = procedimentos.findOne(id);
		return novo(procedimento);
	}

	@DeleteMapping("/{id}")
	public String apagar(@PathVariable Long id, RedirectAttributes attributes) {
		procedimentos.delete(id);
		attributes.addFlashAttribute("mensagem", "Procedimento removido com sucesso");
		return "redirect:/procedimentos";
	}

}
