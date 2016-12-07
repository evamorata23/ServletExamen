package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Form;
import model.Language;
import model.assembler.FormAssembler;
import repository.RepositoryLanguage;
import repository.RepositoryCountry;

public class Service {
	private RepositoryLanguage repL = new RepositoryLanguage();
	private RepositoryCountry repC = new RepositoryCountry();
	
	public Form assembleFormFromRequest(HttpServletRequest req) {
		return FormAssembler.assembleUserFrom(req);
	}
	
	public void insertLanguage(Form form) {
		repL.insertLanguage(form);
	}
	
	public void insertCountry(Form form) {
		repC.insertCountry(form);
	}
	
	public void delete(String language) {
		int i=repL.findLanguageId(language);
		repL.deleteLanguage(i);
		repC.deleteCountry(i);
	}
	
	public List<Form> listAllForms() {
		return repL.searchAll();
	}
	
	public List<Language> listAllLang() {
		return repL.searchLanguage();
	}
}

