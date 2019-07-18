package com.example.MLB;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MLBController {
	@CrossOrigin
	@PostMapping("/getAllCocktail")
	public @ResponseBody String getCocktailByAlcohol(@RequestBody Map<String, String> payload) {
		String alcoholic = payload.get("alcoholic");
		String country = payload.get("country");
		String price = payload.get("price");
		String smoothness = payload.get("smoothness");
		String query=null;
		if(country.equals("Any") && price.equals("Any") && smoothness.equals("Any")  ){
			query=
					"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" +
							"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" +
							"PREFIX abc: <http://localhost/owl/liquorOntology.owl#>\r\n" +
							"\r\n" +
							"SELECT distinct ?cocktail ?basealcohol ?secondaryalcohol ?straw ?deco ?glass ?softner\r\n" +
							"WHERE {\r\n" +
							"  ?alcoholclass rdfs:subClassOf* abc:" +alcoholic+ ".\r\n" +

							"?cocktail abc:hasBaseAlcohol ?basealcohol.\r\n"+
							"?cocktail abc:hasSecondaryAlcohol ?secondaryalcohol.\r\n"+
							"?cocktail abc:hasSoftner ?softner.\r\n"+
							" ?cocktail abc:hasStraw ?straw.\r\n"+
							"   ?cocktail abc:hasDecoration ?deco.\r\n"+
							" ?cocktail abc:isServedIn ?glass.\r\n"+
							"}";

		}
		else{ query=
				        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" +
						"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" +
						"PREFIX abc: <http://localhost/owl/liquorOntology.owl#>\r\n" +
						"\r\n" +
						"SELECT distinct ?cocktail ?basealcohol ?secondaryalcohol ?straw ?deco ?glass ?softner\r\n" +
						"WHERE {\r\n" +
						"  ?alcoholclass rdfs:subClassOf* abc:" +alcoholic+ ".\r\n" +
						"?basealcohol rdf:type ?alcoholclass.\r\n" +
						"?cocktail abc:hasOrigin abc:" +country+".\r\n"+
								"?cocktail abc:hasPrice abc:" +price+".\r\n"+
								"?cocktail abc:hasSmoothness abc:" +smoothness+".\r\n"+

								"?cocktail abc:hasBaseAlcohol ?basealcohol.\r\n"+
								"?cocktail abc:hasSecondaryAlcohol ?secondaryalcohol.\r\n"+
								"?cocktail abc:hasSoftner ?softner.\r\n"+
								" ?cocktail abc:hasStraw ?straw.\r\n"+
								"   ?cocktail abc:hasDecoration ?deco.\r\n"+
								" ?cocktail abc:isServedIn ?glass.\r\n"+
						"}";

	}
		return OwlHelper.OwlToJson(query);}
}
