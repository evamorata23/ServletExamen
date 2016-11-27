package model.assembler;

import javax.servlet.http.HttpServletRequest;

import model.Country;
import model.assembler.*;

public class CountryAssembler {
	public static Country assemblePaisFrom(HttpServletRequest req) {
		Country c = new Country();
		String country = req.getParameter("country");
		String language1 = req.getParameter("language1");
		String language2 = req.getParameter("language2");
		
		String prueba = "";
		
		c.setCountry(country);
		if(language1 == "0"){
			c.setLanguage(language2);
		}else{
			c.setLanguage(language1);
		}		
		prueba = c.getCountry();
		return c;
	}
}
