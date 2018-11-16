package com.standard.controller;


import com.standard.domain.Dominio;
import com.standard.service.dominio.DominioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(DominioController.BASE_URL)
public class DominioController {

	public static final String BASE_URL = "/api/v1/dominio";

	private final DominioService dominioService;

	public DominioController(DominioService dominioService) {
		this.dominioService = dominioService;
	}

	@GetMapping({"/all"})
    @ResponseStatus(HttpStatus.OK)
	public List<Dominio> consultar(){
        return dominioService.consultar();
	}

	@GetMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
    public Dominio  consultarByCodigo(@PathVariable Long codigo){
	 return dominioService.consultarByCodigo(codigo);
	}

	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
	public Dominio incluir(@RequestBody Dominio dominio){
		return dominioService.incluir(dominio);
	}

	@DeleteMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long codigo){
		dominioService.excluir(codigo);
	}

	@PutMapping({"/{codigo}"})
    @ResponseStatus(HttpStatus.OK)
	public Dominio alterar(@PathVariable Long codigo, @RequestBody Dominio dominio){
		return dominioService.alterar(codigo, dominio);
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
