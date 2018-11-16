package com.standard.controller;

import com.standard.domain.Marca;
import com.standard.service.marca.MarcaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MarcaController.BASE_URL)
public class MarcaController {

	public static final String BASE_URL = "/api/v1/marca";
	
	private MarcaService marcaService;

	public MarcaController(MarcaService marcaService) {
		this.marcaService = marcaService;
	}

	@GetMapping({"/all"})
	@ResponseStatus(HttpStatus.OK)
	public List<Marca> consultar(){
		return marcaService.consultar();
	}

	@GetMapping({"/{codigo}"})
	@ResponseStatus(HttpStatus.OK)
	public Marca consultarByCodigo(@PathVariable Long codigo){
		return marcaService.consultarByCodigo(codigo);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Marca incluir(@RequestBody Marca obj){
		return marcaService.incluir(obj);
	}

	@DeleteMapping({"/{codigo}"})
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long codigo){
		marcaService.excluir(codigo);

	}

	@PutMapping({"/{codigo}"})
	@ResponseStatus(HttpStatus.OK)
	public Marca alterar(@PathVariable Long codigo, @RequestBody Marca obj){
		return marcaService.alterar(codigo, obj);
	}

//	private static final String VIEW = "marca";
//	private static final String VIEW_COLSULTA = "consultarMarca";
//
//	@Autowired
//	com.alcarrer.controller.validator.MarcaValidator marcaValidator;
//
//	@Autowired
//	private MessageSource message;
//
//	@Autowired
//	MarcaService marcaService;
//
//	// Set a form validator
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		binder.setValidator(marcaValidator);
//	}
//
//	@RequestMapping(value = "/abrirMarca", method = { RequestMethod.GET, RequestMethod.POST })
//	public String abrir(ModelMap model) {
//		model.addAttribute("marcaForm", new Marca());
//		model.addAttribute("breadCrumbItens", breadCrumbList());
//		return VIEW;
//	}
//
//	@RequestMapping(value = "/abrirAlterarMarca", method = { RequestMethod.GET, RequestMethod.POST })
//	public String abrirAlterar(@ModelAttribute("marcaForm") Marca marca, BindingResult result, Model model,
//			final RedirectAttributes redirectAttributes) {
//		Marca retorno = marcaService.consultarByCodigo(marca);
//		model.addAttribute("marcaForm", retorno);
//		model.addAttribute("breadCrumbItens", breadCrumbList());
//		return VIEW;
//	}
//
//	@RequestMapping(value = "/alterarMarca", method = { RequestMethod.GET, RequestMethod.POST })
//	public String altearar(@ModelAttribute("marcaForm") @Validated Marca marca, BindingResult result, Model model,
//			final RedirectAttributes redirectAttributes) {
//		marcaService.alterar(marca);
//		model.addAttribute("mensagem", message.getMessage("global.alteracao", null, Locale.US));
//		model.addAttribute("list", marcaService.consultar());
//		model.addAttribute("breadCrumbItens", breadCrumbList());
//		return "redirect:"+VIEW_COLSULTA;
//	}
//
//	@RequestMapping(value = "/incluirMarca", method = { RequestMethod.GET, RequestMethod.POST })
//	public String incluir(@ModelAttribute("marcaForm") @Validated Marca marca, BindingResult result, Model model,
//			final RedirectAttributes redirectAttributes) {
//		marcaService.incluir(marca);
//		model.addAttribute("mensagem", message.getMessage("global.inclusao", null, Locale.US));
//		model.addAttribute("list", marcaService.consultar());
//		model.addAttribute("breadCrumbItens", breadCrumbList());
//		return "redirect:"+VIEW_COLSULTA;
//	}
//
//	@RequestMapping(value = "/consultarMarca", method = { RequestMethod.GET, RequestMethod.POST })
//	public String consultarMarca(@ModelAttribute("marcaForm") Marca marca, BindingResult result, Model model,
//			final RedirectAttributes redirectAttributes) {
//		model.addAttribute("list", marcaService.consultar());
//		model.addAttribute("breadCrumbItens", breadCrumbList());
//		return VIEW_COLSULTA;
//	}
//
//	@RequestMapping(value = "/excluirMarca", method = { RequestMethod.GET, RequestMethod.POST })
//	public String excluir(@ModelAttribute("marcaForm") @Validated Marca marca, BindingResult result, Model model,
//			final RedirectAttributes redirectAttributes) {
//		marcaService.excluir(marca);
//		model.addAttribute("mensagem", message.getMessage("global.exclusao", null, Locale.US));
//		model.addAttribute("list", marcaService.consultar());
//		model.addAttribute("breadCrumbItens", breadCrumbList());
//		return "redirect:"+VIEW_COLSULTA;
//	}
//
//	public List<BreadCrumb> breadCrumbList() {
//		List<String> msg = new ArrayList<String>();
//		msg.add("menu.cadastro");
//		msg.add("menu.cadastro.marca");
//		return Util.breadCrumbList(message, msg);
//	}
}
