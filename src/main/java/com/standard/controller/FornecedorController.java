package com.standard.controller;

import com.standard.domain.Fornecedor;
import com.standard.service.fornecedor.FornecedorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(FornecedorController.BASE_URL)
public class FornecedorController {

	public static final String BASE_URL = "/api/v1/fornecedor";
	
	private FornecedorService fornecedorService;

	public FornecedorController(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}

	@GetMapping({"/all"})
	@ResponseStatus(HttpStatus.OK)
	public List<Fornecedor> consultar(){
		return fornecedorService.consultar();
	}

	@GetMapping({"/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public Fornecedor consultarByCodigo(@PathVariable Long id){
		return fornecedorService.consultarByCodigo(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Fornecedor incluir(@RequestBody Fornecedor obj){
		return fornecedorService.incluir(obj);
	}

	@DeleteMapping({"/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id){
		fornecedorService.excluir(id);
		 
	}

	@PutMapping({"/{id}"})
	public Fornecedor alterar(@PathVariable Long id, @RequestBody Fornecedor obj){
		return fornecedorService.alterar(id, obj);
	}

	/*// Set a form validator
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(fornecedorValidator);
	}

	@RequestMapping(value = "/abrirFornecedor", method = { RequestMethod.GET, RequestMethod.POST })
	public String abrir(ModelMap model) {
		model.addAttribute("fornecedorForm", new Fornecedor());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW;
	}

	@RequestMapping(value = "/incluirFornecedor", method = { RequestMethod.GET, RequestMethod.POST })
	public String incluir(@ModelAttribute("fornecedorForm") @Validated Fornecedor fornecedor, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {

		fornecedorService.incluir(fornecedor);
		model.addAttribute("list", fornecedorService.consultar());
		model.addAttribute("fornecedorForm", new Fornecedor());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return "redirect:"+VIEW_COLSULTA;
	}

	@RequestMapping(value = "/abrirAlterarFornecedor", method = { RequestMethod.GET, RequestMethod.POST })
	public String abrirAlterar(@ModelAttribute("fornecedorForm") Fornecedor fornecedor, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {
		Fornecedor retorno = fornecedorService.consultarByCodigo(fornecedor);
		model.addAttribute("fornecedorForm", retorno);
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW;
	}

	@RequestMapping(value = "/consultarFornecedor", method = { RequestMethod.GET, RequestMethod.POST })
	public String consultarFornecedor(@ModelAttribute("fornecedorForm") Fornecedor Fornecedor, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {
		model.addAttribute("list", fornecedorService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}

	@RequestMapping(value = "/alterarFornecedor", method = { RequestMethod.GET, RequestMethod.POST })
	public String alterar(@ModelAttribute("fornecedorForm") @Validated Fornecedor fornecedor, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {
		fornecedorService.alterar(fornecedor);
		model.addAttribute("mensagem", message.getMessage("global.alteracao", null, Locale.US));
		model.addAttribute("list", fornecedorService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return "redirect:"+VIEW_COLSULTA;
	}

	@RequestMapping(value = "/excluirFornecedor", method = { RequestMethod.GET, RequestMethod.POST })
	public String excluir(@ModelAttribute("fornecedorForm") @Validated Fornecedor fornecedor, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {
		fornecedorService.excluir(fornecedor);
		model.addAttribute("mensagem", message.getMessage("global.exclusao", null, Locale.US));
		model.addAttribute("list", fornecedorService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return "redirect:"+VIEW_COLSULTA;
	}

	public List<BreadCrumb> breadCrumbList() {
		List<String> msg = new ArrayList<String>();
		msg.add("menu.cadastro");
		msg.add("menu.cadastro.fornecedor");
		return Util.breadCrumbList(message, msg);
	}*/
}
