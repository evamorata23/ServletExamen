package model.assembler;

import javax.servlet.http.HttpServletRequest;

import model.Country;

public class CountryAssembler {
	public static Country assemblePaisFrom(HttpServletRequest req) {
		Country c = new Country();
		String country = req.getParameter("country");
		
		c.setCountry(country);
		//Nos devolvera un idioma que necesitamos para pais
		LanguageAssembler la = new LanguageAssembler();
		la.assembleLanguageFrom(req);
		
		c.setCountry(country);
		
		return c;
	}
}
