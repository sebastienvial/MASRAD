package ch.eiafr.rdf;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;

public class FST{
	
	public static IRI iFST;
	public static IRI idPersonnel;
	public static IRI name;
	public static IRI opTravel_for;
	
	public FST(Repository rep,String namespace) {
		ValueFactory f = rep.getValueFactory();
		RepositoryConnection conn = rep.getConnection();
		
		iFST = f.createIRI(namespace, "FTS");
		idPersonnel = f.createIRI(namespace, "idPersonnel");
		name = f.createIRI(namespace, "name");
		
		opTravel_for = f.createIRI(namespace, "travel_for");	
		
		try {
			conn.add(iFST, RDF.TYPE, RDFS.CLASS);
			conn.add(iFST, RDFS.SUBCLASSOF, Person.iPerson);
			conn.add(opTravel_for, RDF.TYPE, RDF.PREDICATE);
			conn.add(opTravel_for, RDFS.SUBPROPERTYOF, Person.opWork_for);
			conn.add(idPersonnel, RDF.TYPE, RDF.PROPERTY);
			conn.add(name, RDF.TYPE, RDF.PROPERTY);
		} finally {
			conn.close();
		}		
	}

}
