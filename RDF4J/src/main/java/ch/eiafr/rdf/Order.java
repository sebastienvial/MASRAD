package ch.eiafr.rdf;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;

public class Order {
	
	public static IRI iOrder;
	public static IRI orderNumber;
	public static IRI creationDate;
	public static IRI opNeed;
	
	public Order(Repository rep,String namespace) {
		ValueFactory f = rep.getValueFactory();
		RepositoryConnection conn = rep.getConnection();
		iOrder = f.createIRI(namespace, "order");
		orderNumber = f.createIRI(namespace, "orderNumber");
		creationDate = f.createIRI(namespace, "creationDate");
		
		opNeed = f.createIRI(namespace, "need");
		
		try {
			conn.add(iOrder, RDF.TYPE, RDFS.CLASS);
			conn.add(opNeed, RDF.TYPE, RDF.PREDICATE);
			conn.add(orderNumber, RDF.TYPE, RDF.PROPERTY);
			conn.add(creationDate, RDF.TYPE, RDF.PROPERTY);
		} finally {
			conn.close();
		}		
	}

}
