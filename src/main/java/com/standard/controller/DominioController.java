package com.standard.controller;


import com.standard.domain.Dominio;
import com.standard.service.dominio.DominioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/dominio")
public class DominioController {

	private final DominioService dominioService;

	public DominioController(DominioService dominioService) {
		this.dominioService = dominioService;
	}

	@GetMapping({"/all"})
	public  ResponseEntity<List<Dominio>> consultar(){
        return new ResponseEntity<>(dominioService.consultar(), HttpStatus.OK);
	}

	@GetMapping({"/{id}"})
	public ResponseEntity<Dominio> consultarByCodigo(@PathVariable Integer id){
	 return new ResponseEntity<>(dominioService.consultarByCodigo(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Dominio> incluir(@RequestBody Dominio dominio){
		return new ResponseEntity<>(dominioService.incluir(dominio), HttpStatus.CREATED);
	}

	@DeleteMapping({"/{id}"})
	public ResponseEntity<Dominio> delete(@PathVariable Integer id){
		dominioService.excluir(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping({"/{id}"})
	public ResponseEntity<Dominio> alterar(@PathVariable Integer id, @RequestBody Dominio dominio){
		return new ResponseEntity<>(dominioService.alterar(id, dominio), HttpStatus.OK);
	}

//	@RequestMapping(value = "/abrirDominio", method = { RequestMethod.GET, RequestMethod.POST })
//	public String abrir(ModelMap model) {
//		model.addAttribute("dominioForm", new Dominio());
//		model.addAttribute("breadCrumbItens", breadCrumbList());
//		return VIEW;
//	}
/*
	@RequestMapping(value = "/abrirAlterarDominio", method = { RequestMethod.GET, RequestMethod.POST })
	public String abrirAlterar(@ModelAttribute("dominioForm") Dominio dominio, BindingResult result, Model model,
							   final RedirectAttributes redirectAttributes) {
		Dominio retorno = dominioService.consultarByCodigo(dominio);
		model.addAttribute("dominioForm", retorno);
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW;
	}

	@RequestMapping(value = "/alterarDominio", method = { RequestMethod.GET, RequestMethod.POST })
	public String altearar(@ModelAttribute("dominioForm") @Validated Dominio dominio, BindingResult result, Model model,
						   final RedirectAttributes redirectAttributes) {
		dominioService.alterar(dominio);
		model.addAttribute("mensagem", message.getMessage("global.alteracao", null, Locale.US));
		model.addAttribute("list", dominioService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return "redirect:"+VIEW_COLSULTA;
	}

	@RequestMapping(value = "/incluirDominio", method = { RequestMethod.GET, RequestMethod.POST })
	public String incluir(@ModelAttribute("dominioForm") @Validated Dominio dominio, BindingResult result, Model model,
						  final RedirectAttributes redirectAttributes) {
		dominioService.incluir(dominio);
		model.addAttribute("mensagem", message.getMessage("global.inclusao", null, Locale.US));
		model.addAttribute("list", dominioService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return "redirect:"+VIEW_COLSULTA;
	}

	@RequestMapping(value = "/consultarDominio", method = { RequestMethod.GET, RequestMethod.POST })
	public String consultarDominio(@ModelAttribute("dominioForm") Dominio dominio, BindingResult result, Model model,
								   final RedirectAttributes redirectAttributes) {
		model.addAttribute("list", dominioService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}

	@RequestMapping(value = "/excluirDominio", method = { RequestMethod.GET, RequestMethod.POST })
	public String excluir(@ModelAttribute("dominioForm") @Validated Dominio dominio, BindingResult result, Model model,
						  final RedirectAttributes redirectAttributes) {
		dominioService.excluir(dominio);
		model.addAttribute("mensagem", message.getMessage("global.exclusao", null, Locale.US));
		model.addAttribute("list", dominioService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return "redirect:"+VIEW_COLSULTA;
	}

	public List<BreadCrumb> breadCrumbList() {
		List<String> msg = new ArrayList<String>();
		msg.add("menu.cadastro");
		msg.add("menu.cadastro.dominio");
		return Util.breadCrumbList(message, msg);
	}
*/

}
