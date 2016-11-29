package model.assembler;

import javax.servlet.http.HttpServletRequest;

import model.Language;
import model.assembler.*;

public class LanguageAssembler {
	public static Language assembleLanguageFrom(HttpServletRequest req) {
		Language language = new Language();
		String language1 = req.getParameter("language1");
		String language2 = req.getParameter("language2");
		
		if(language1 == ""){
			language.setLanguage(language2);
		}else{
			language.setLanguage(language1);
		}
		
		return language;
	}
}
