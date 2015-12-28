package mysample.webapp.basic.web;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CountryFormValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(CountryForm.class);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		CountryForm countryForm = (CountryForm)target;
		
		if (StringUtils.equals(countryForm.getCode(), countryForm.getCode2())) {
			errors.rejectValue("code2", "countryForm.code2.equalCode");
		}
		
        if ((StringUtils.equals(countryForm.getName(), "Japan") || StringUtils.equals(countryForm.getName(), "日本"))
                && (!StringUtils.equals(countryForm.getContinent(), "Asia"))) {
            errors.rejectValue("continent", "countryForm.continent.notAsia");
        }

        if ((StringUtils.equals(countryForm.getContinent(), "Asia"))
                && (!Pattern.matches("^(Eastern Asia|Middle East|Southeast Asia|Southern and Central Asia)$", countryForm.getRegion()))) {
            errors.rejectValue("region", "countryForm.region.notAsiaPattern");
        }
	}

}
