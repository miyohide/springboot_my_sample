package mysample.webapp.basic.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/countryList")
public class CountryListController {
	@RequestMapping
	public String index(@Validated CountryListForm countryListForm,
			BindingResult bindingResult,
			Model model) {
		// Validationの結果、妥当でなかった時
		if (bindingResult.hasErrors()) {
			return "countryList";
		}
		return "countryList";
	}
}
