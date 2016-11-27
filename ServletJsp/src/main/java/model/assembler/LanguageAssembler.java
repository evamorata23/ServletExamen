package model.assembler;

import javax.servlet.http.HttpServletRequest;

import model.Language;
import model.assembler.*;

public class LanguageAssembler {
	public static Language assembleLanguageFrom(HttpServletRequest req) {
		Language language = new Language();
		String language1 = req.getParameter("idioma");
		String language2 = req.getParameter("course");
		
		if(language1 == "0"){
			language.setLanguage(language2);
		}else{
			language.setLanguage(language1);
		}
		
		return language;
	}
}
