package mysample.webapp.basic.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/country")  // ベースとなるURL
public class CountryController {
	// classに設定したRequestMappingの下に設定するURL
	@RequestMapping("/input")
	public String input() {
		return "country/input";
	}
	
	@RequestMapping("/confirm")
	public String confirm() {
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
