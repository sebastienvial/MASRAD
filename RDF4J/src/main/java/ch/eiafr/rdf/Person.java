package ch.eiafr.rdf;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;

public class Person {
	
	public static IRI iPerson;
	public static IRI opWork_for;
	
	public Person(Repository rep,String namespace) {
		ValueFactory f = rep.getValueFactory();
		RepositoryConnection conn = rep.getConnection();
		
		iPerson = f.createIRI(namespace, "Person");
		opWork_for = f.createIRI(namespace, "work_for");	
		
		try {
			conn.add(iPerson, RDF.TYPE, RDFS.CLASS);
			conn.add(opWork_for, RDF.TYPE, RDF.PREDICATE);
		} finally {
			conn.close();
		}		
	}

}
