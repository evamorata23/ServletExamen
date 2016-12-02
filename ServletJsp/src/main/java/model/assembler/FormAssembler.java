package model.assembler;

import javax.servlet.http.HttpServletRequest;

import model.Form;

public class FormAssembler {

	public static Form assembleUserFrom(HttpServletRequest req) {
		Form form = new Form();
		// Recuperamos los valores del formulario
		String country = req.getParameter("country");
		String language1 = req.getParameter("language1");
		String language2 = req.getParameter("language2");
		// Guardamos pais
		form.setCountry(country);
		// Seleccionamos que idioma (combobox o manual) esta informado
		System.out.println(language1+"/"+language2);
		if (language1.equals("null")){
			System.out.println("Languaje2:"+language2);
			form.setLanguage(language2);
		} else {
			System.out.println("Languaje1:"+language1);
			form.setLanguage(language1);
		}
		
		return form;
	}
}
