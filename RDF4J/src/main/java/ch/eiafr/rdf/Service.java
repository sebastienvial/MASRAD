package ch.eiafr.rdf;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.repository.Repository;

public class Service {
	
	public static IRI iService;
	public static IRI type;
	public static IRI price;
	public static IRI opIs_proposed;
	
	public Service(Repository rep,String namespace) {
		ValueFactory f = rep.getValueFactory();
		iService = f.createIRI(namespace, "service");
		type = f.createIRI(namespace, "type");
		price = f.createIRI(namespace, "price");
		
		opIs_proposed = f.createIRI(namespace, "is_proposed");		
	}

}
