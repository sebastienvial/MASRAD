package ch.eiafr.rdf;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;

public class Organisation {
	
	public static IRI iOrganisation;
	
	public Organisation(Repository rep,String namespace) {
		ValueFactory f = rep.getValueFactory();
		RepositoryConnection conn = rep.getConnection();
		
		iOrganisation = f.createIRI(namespace, "Organisation");
		
		try {
			conn.add(iOrganisation, RDF.TYPE, RDFS.CLASS);
		} finally {
			conn.close();
		}		
	}

}
