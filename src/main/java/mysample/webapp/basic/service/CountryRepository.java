package mysample.webapp.basic.service;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import mysample.webapp.basic.domain.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, String>, JpaSpecificationExecutor<Country> {

}
