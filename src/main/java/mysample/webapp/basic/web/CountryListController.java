package mysample.webapp.basic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import mysample.webapp.basic.domain.Country;
import mysample.webapp.basic.helper.PagenationHelper;
import mysample.webapp.basic.service.CountryService;
import mysample.webapp.basic.web.CountryListForm;

@Controller
@RequestMapping("/countryList")
public class CountryListController {
	@Autowired
	private CountryService countryService;
	
	private static final int DEFAULT_PAGEBLE_SIZE = 5;
	
	@RequestMapping
	public String index(@Validated CountryListForm countryListForm,
			BindingResult bindingResult,
			@PageableDefault(size = DEFAULT_PAGEBLE_SIZE, page = 0) Pageable pageable,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "countryList";
		}
		
		Page<Country> page = countryService.findCountry(countryListForm, pageable);
		PagenationHelper ph = new PagenationHelper(page);
		
		model.addAttribute("page", page);
		model.addAttribute("ph", ph);
		
		return "countryList";
	}

}
