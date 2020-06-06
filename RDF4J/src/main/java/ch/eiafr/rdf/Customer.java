package ch.eiafr.rdf;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.repository.Repository;

public class Customer {
	
	public static IRI iCustomer;
	public static IRI idCustomer;
	public static IRI name;
	public static IRI opOrdered;
	public static IRI opRequest;
	
	public Customer(Repository rep,String namespace) {
		ValueFactory f = rep.getValueFactory();
		iCustomer = f.createIRI(namespace, "customer");
		idCustomer = f.createIRI(namespace, "idCustomer");
		name = f.createIRI(namespace, "name");

		opOrdered = f.createIRI(namespace, "ordered");
		opRequest = f.createIRI(namespace, "request");		
	}

}
