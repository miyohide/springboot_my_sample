package mysample.webapp.basic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mysample.webapp.basic.config.Constant;

@Controller
@RequestMapping("/country")  // ベースとなるURL
public class CountryController {
	@Autowired
	private Constant constant;
	
	@Autowired
	private CountryFormValidator countryFormValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(countryFormValidator);
	}
	
	// classに設定したRequestMappingの下に設定するURL
	@RequestMapping("/input")
	public String input(CountryForm countryForm, Model model) {
		model.addAttribute("continentList", constant.CONTINENT_LIST);
		return "country/input";
	}
	
	@RequestMapping("/input/back")
	public String inputBack(CountryForm countryForm, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("countryForm", countryForm);
		return "redirect:/country/input";
	}
	
	@RequestMapping("/confirm")
	public String confirm(@Validated CountryForm countryForm,
			 BindingResult bindingResult,
			 Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("continentList", constant.CONTINENT_LIST);
			return "country/input";
		}
		return "country/confirm";
	}
	
	@RequestMapping("/update")
	public String update() {
		return "redirect:/country/complete";
	}
	
	@RequestMapping("/complete")
	public String complete() {
		return "country/complete";
	}

}
