package com.example.MLB;
import java.io.ByteArrayOutputStream;


import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.util.FileManager;

public class OwlHelper {
	private static OwlHelper owlHelper;
	private static Model model;
	private static InfModel infmodel;
	private OwlHelper(){
		FileManager.get().addLocatorClassLoader(OwlHelper.class.getClassLoader());
		model=FileManager.get().loadModel("C:\\Users\\DELL\\Desktop\\Ontology Project\\BackEnd\\TvShow\\src\\main\\java\\com\\example\\MLB\\cocktailfusuki4.rdf");
		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
		reasoner = reasoner.bindSchema(model);
		infmodel = ModelFactory.createInfModel(reasoner,model);
	}

	public static OwlHelper getInstance()
	{
		if (owlHelper == null)
			owlHelper = new OwlHelper();

		return owlHelper;
	}

	public static String OwlToJson(String sparqlQuery ) {
		
		String r = null;
		//JSONObject re;
		
		//FileManager.get().addLocatorClassLoader(OwlHelper.class.getClassLoader());
		//Model model=FileManager.get().loadModel("C:\\Users\\sheha\\Desktop\\Ontology\\TvShow\\TvShow\\src\\main\\java\\com\\example\\MLB\\onto.rdf");


		String queryString = sparqlQuery;

		/* "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
		"PREFIX foaf:<http://xmlns.com/foaf/0.1/>"+
				"SELECT * WHERE {  " +
		         "?person foaf:name ?x."+
				//"?person foaf:knows ?person2."+
		        // "?person2 foaf:name ?y."+
				//"FILTER(?y=\"Sasan\")" +
				" }" ; */
		
		
		  Query query=QueryFactory.create(queryString);
		  QueryExecution quexec=QueryExecutionFactory.create(query,model);
		  try {
			  ResultSet results=quexec.execSelect();
			  
			  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			  ResultSetFormatter.outputAsJSON(outputStream, results);

			  String json = new String(outputStream.toByteArray());

			 r=json;
			  
		  }catch(Exception e) {
			  System.out.println(e);
			  
		  }
			  finally {
				  
				  quexec.close();
				 
			  }
		  return r;
	}

}
