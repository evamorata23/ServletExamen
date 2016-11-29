package service;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import connection.ConnectionH2;
import connection.ConnectionManager;
import repository.Repository;
import model.*;
import model.assembler.CountryAssembler;
import model.assembler.LanguageAssembler;

public class Service {
	
	
	private Repository repository = new Repository();
	
	public Country assemblePaisFromRequest(HttpServletRequest req) {
		return CountryAssembler.assemblePaisFrom(req);
	}
	
	public Language assembleLanguageFromRequest(HttpServletRequest req) {
		return LanguageAssembler.assembleLanguageFrom(req);
	}
	
	public void insertOrUpdateCountry(Country countryForm) {
		Country countryInDatabase = repository.search(countryForm);
		if(null == countryInDatabase){
			repository.insert(countryForm);
		}else{
			repository.update(countryForm);
		}
	}
	
	public void insertOrUpdateLanguage(Language languageForm) {
		Language languageInDatabase = repository.search(languageForm);
		if(null == languageInDatabase){
			repository.insert(languageForm);
		}else{
			repository.update(languageForm);
		}
	}
	
	public List<Country> searchAllCountry() {
		return repository.searchAllCountry();
	}
	
	public List<Language> searchAllLanguage() {
		return repository.searchAllLanguage();
	}
	
	public List<Country> searchCountriesByLanguage(Language language) {
		return repository.searchCountriesByLanguage(language);
	}
	
	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}
}
