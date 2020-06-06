package ch.eiafr.rdf;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;

public class Company {
	
	public static IRI iCompany;
	public static IRI name;
	public static IRI location;
	public static IRI opServes;
	
	public Company(Repository rep,String namespace) {
		ValueFactory f = rep.getValueFactory();
		RepositoryConnection conn = rep.getConnection();
		
		iCompany = f.createIRI(namespace, "company");
		name = f.createIRI(namespace, "name");
		location = f.createIRI(namespace, "location");
		
		opServes = f.createIRI(namespace, "serves");
		
		try {
			conn.add(iCompany, RDF.TYPE, RDFS.CLASS);
			conn.add(iCompany, RDFS.SUBCLASSOF, Organisation.iOrganisation);
			conn.add(opServes, RDF.TYPE, RDF.PREDICATE);
			conn.add(location, RDF.TYPE, RDF.PROPERTY);
			conn.add(name, RDF.TYPE, RDF.PROPERTY);
		} finally {
			conn.close();
		}		
	}

}
