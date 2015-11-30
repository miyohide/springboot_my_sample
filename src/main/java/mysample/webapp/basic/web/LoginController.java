package mysample.webapp.basic.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	// "/"にアクセスしたら、loginページを返す
    @RequestMapping("/")
    public String index() {
        return "login";
    }
}
