package com.tec.os.controller;

import java.util.List;
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

import com.tec.os.model.Guia;
import com.tec.os.model.Procedimento;
import com.tec.os.repository.GuiaRepository;
import com.tec.os.repository.ProcedimentoRepository;
import com.tec.os.repository.filter.GuiaFilter;

@Controller
@RequestMapping("/guias")
public class GuiasController {

	@Autowired
	private ProcedimentoRepository procedimentos;

	@Autowired
	private GuiaRepository guias;

	public List<Procedimento> adicionarProcedimento(String descricao) {
		return this.procedimentos.findAll();
	}

	@GetMapping("/novo")
	public ModelAndView novo(Guia guia) {
		ModelAndView mv = new ModelAndView("guia/cadastro-guia");
		mv.addObject("procedimentos", procedimentos.findAll());
		mv.addObject(guia);
		return mv;
	}

	

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Guia guia,  BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(guia);
		}
		guias.save(guia);

		attributes.addFlashAttribute("mensagem", "Guia salvo com sucesso!");
		return new ModelAndView("redirect:/guias");
	}

	@GetMapping
	public ModelAndView pesquisar(GuiaFilter guiaFilter) {
		ModelAndView mv = new ModelAndView("guia/pesquisa-guia");
		mv.addObject("guias", guias.findByNome(Optional.ofNullable(guiaFilter.getNome()).orElse("%")));

		return mv;
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Guia guia = guias.findOne(codigo);
		return novo(guia);
	}

	@DeleteMapping("/{codigo}")
	public String apagar(@PathVariable Long codigo, RedirectAttributes attributes) {
		guias.delete(codigo);
		attributes.addFlashAttribute("mensagem", "Guia removido com sucesso");
		return "redirect:/guias";
	}

}
