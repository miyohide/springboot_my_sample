package mysample.webapp.basic.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mysample.webapp.basic.domain.Country;
import mysample.webapp.basic.web.CountryForm;
import mysample.webapp.basic.web.CountryListForm;

import static mysample.webapp.basic.service.CountrySpecifications.*;

@Service
public class CountryService {
	@Autowired
	private CountryRepository countryRepository;
	
    public Page<Country> findCountry(CountryListForm countryListForm, Pageable pageable) {
        return countryRepository.findAll(
                Specifications
                        .where(codeContains(countryListForm.getCode()))
                        .and(nameContains(countryListForm.getName()))
                        .and(continentContains(countryListForm.getContinent()))
                        .and(localNameContains(countryListForm.getLocalName()))
                , pageable);
    }

	@Transactional
	public void save(CountryForm countryForm) {
		Country country = new Country();
		BeanUtils.copyProperties(countryForm, country);
		countryRepository.save(country);
	}
}
