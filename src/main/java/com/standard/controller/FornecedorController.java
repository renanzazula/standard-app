package com.standard.controller;

import com.standard.domain.Fornecedor;
import com.standard.service.fornecedor.FornecedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/fornecedor")
public class FornecedorController {

	private FornecedorService fornecedorService;

	public FornecedorController(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}

	@GetMapping({"/all"})
	public ResponseEntity<List<Fornecedor>> consultar(){
		return new ResponseEntity<>(fornecedorService.consultar(), HttpStatus.OK);
	}

	@GetMapping({"/{id}"})
	public ResponseEntity<Fornecedor> consultarByCodigo(@PathVariable Integer id){
		return new ResponseEntity<>(fornecedorService.consultarByCodigo(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Fornecedor> incluir(@RequestBody Fornecedor obj){
		return new ResponseEntity<>(fornecedorService.incluir(obj), HttpStatus.CREATED);
	}

	@DeleteMapping({"/{id}"})
	public ResponseEntity<Fornecedor> delete(@PathVariable Integer id){
		fornecedorService.excluir(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping({"/{id}"})
	public ResponseEntity<Fornecedor> alterar(@PathVariable Integer id, @RequestBody Fornecedor obj){
		return new ResponseEntity<>(fornecedorService.alterar(id, obj), HttpStatus.OK);
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
