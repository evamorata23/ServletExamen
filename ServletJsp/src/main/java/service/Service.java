package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Form;
import model.Language;
import model.assembler.FormAssembler;
import repository.Repository;

public class Service {
	private Repository repository = new Repository();
	
	public Form assembleFormFromRequest(HttpServletRequest req) {
		return FormAssembler.assembleUserFrom(req);
	}
	
	public void insert(Form form) {
		repository.insert(form);
	}
	
	public void delete(String language) {
		int i=repository.findLanguageId(language);
		repository.deleteLanguage(i);
		repository.deleteCountry(i);
	}
	
	public List<Form> listAllForms() {
		return repository.searchAll();
	}
	
	public List<Language> listAllLang() {
		return repository.searchLanguage();
	}
	
}

