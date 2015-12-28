package mysample.webapp.basic.web;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CountryForm {
	@NotBlank
	@Size(max = 3, message = "{error.size.max}")
	private String code;
	
	@NotBlank
	@Size(max = 52, message = "{error.size.max}")
	private String name;
	
	@NotBlank
	@Pattern(regexp = "^(Asia|Europe|North America|Africa|Oceania|Antarctica|South America)$", message = "{countryListForm.continent.pattern}")
	private String continent;
	
	@NotBlank
	@Size(max = 26, message = "{error.size.max}")
	private String region;
	
	@NotNull
	@Digits(integer=8, fraction=2, message = "{error.digits.integerandfraction}")
	private BigDecimal surfaceArea;
	
	@NotNull
	@Digits(integer=11, fraction=0, message = "{error.digits.integeronly}")
	private BigDecimal population;
	
	@NotBlank
	@Size(max = 45, message = "{error.size.max}")
	private String localName;

	@NotBlank
	@Size(max = 45, message = "{error.size.max}")
	private String governmentForm;
	
	@NotBlank
	@Size(max = 2, message = "{error.size.max}")
	private String code2;
}
