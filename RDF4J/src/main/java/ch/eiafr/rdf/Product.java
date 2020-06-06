package ch.eiafr.rdf;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;

public class Product {
	
	public static IRI iProduct;
	public static IRI productNumber;
	public static IRI description;
	public static IRI opBelongs_to;
	
	public Product(Repository rep,String namespace) {
		ValueFactory f = rep.getValueFactory();
		RepositoryConnection conn = rep.getConnection();
		
		iProduct = f.createIRI(namespace, "product");
		productNumber = f.createIRI(namespace, "productNumber");
		description = f.createIRI(namespace, "description");
		
		opBelongs_to = f.createIRI(namespace, "belongs_to");	
		
		try {
			conn.add(iProduct, RDF.TYPE, RDFS.CLASS);
			conn.add(opBelongs_to, RDF.TYPE, RDF.PREDICATE);
			conn.add(productNumber, RDF.TYPE, RDF.PROPERTY);
			conn.add(description, RDF.TYPE, RDF.PROPERTY);
		} finally {
			conn.close();
		}
	}

}
