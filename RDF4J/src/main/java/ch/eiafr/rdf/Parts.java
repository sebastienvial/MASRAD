package ch.eiafr.rdf;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;

public class Parts {
	
	public static IRI iParts;
	public static IRI partsNumber;
	public static IRI criticity;
	public static IRI price;
	public static IRI opPart_of;
	
	
	public Parts(Repository rep,String namespace) {
		ValueFactory f = rep.getValueFactory();
		RepositoryConnection conn = rep.getConnection();
		
		iParts = f.createIRI(namespace, "parts");
		partsNumber = f.createIRI(namespace, "partsNumber");
		criticity = f.createIRI(namespace, "criticity");
		price = f.createIRI(namespace, "price");		
		
		opPart_of = f.createIRI(namespace, "part_of");	
		
		try {
			conn.add(iParts, RDF.TYPE, RDFS.CLASS);
			conn.add(opPart_of, RDF.TYPE, RDF.PREDICATE);
			conn.add(partsNumber, RDF.TYPE, RDF.PROPERTY);
			conn.add(criticity, RDF.TYPE, RDF.PROPERTY);
			conn.add(price, RDF.TYPE, RDF.PROPERTY);
		} finally {
			conn.close();
		}
		
	}

}
