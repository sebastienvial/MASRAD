package ch.eiafr.rdf;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.rdf4j.common.iteration.Iterations;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.LinkedHashModel;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.model.vocabulary.XMLSchema;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryResult;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.sail.memory.MemoryStore;

public class MiniProjet {
	
	static File dataDir = new File("C:\\temp\\myRepository\\Ontologie");
	static String namespace = "http://MiniProjet-Ontologie/";
	static Repository rep;
	static ValueFactory f;
	static RepositoryConnection conn;
	
	static void buildOntology(Repository rep) {
		
		new Parts(rep,namespace);
		new Product(rep,namespace);	
		new Customer(rep,namespace);	
		new Order(rep,namespace);	
		new Service(rep,namespace);	
		new Person(rep,namespace);
		new FST(rep,namespace);	
		new Organisation(rep,namespace);
		new Company(rep,namespace);	
		
	}
	
		
	static IRI createIndividualsParts(String name, Integer criticity, Double price) {
		
		IRI individu = f.createIRI(namespace, name);
		try {
		
			conn.add(individu, RDF.TYPE, Parts.iParts);
			conn.add(individu, Parts.partsNumber, f.createLiteral(name));
			conn.add(individu, Parts.criticity, f.createLiteral(criticity));
			conn.add(individu, Parts.price, f.createLiteral(price));
			
		}finally {
			
		}
		
		return individu;
	}
	
	static IRI createIndividualsProduct(String name, String description) {
		
		IRI individu = f.createIRI(namespace, name);
		
		conn.add(individu, RDF.TYPE, Product.iProduct);
		conn.add(individu, Product.productNumber, f.createLiteral(name));
		conn.add(individu, Product.description, f.createLiteral(description));
		
		return individu;
	}
	
	static IRI createIndividualsCustomer(String name, Integer idCustomer, String nameCustomer) {
	
		IRI individu = f.createIRI(namespace, name);
		
		conn.add(individu, RDF.TYPE, Customer.iCustomer);
		conn.add(individu, Customer.iCustomer, f.createLiteral(idCustomer));
		conn.add(individu, Customer.name, f.createLiteral(nameCustomer));
		
		return individu;
	}
	
	static IRI createIndividualsService(String name, String type, Double price) {
	
		IRI individu = f.createIRI(namespace, name);
		
		conn.add(individu, RDF.TYPE, Service.iService);
		conn.add(individu, Service.type, f.createLiteral(type));
		conn.add(individu, Service.price, f.createLiteral(price));
		
		return individu;
	}
	
	static IRI createIndividualsOrder(String name, String orderNumber, String creationDate) throws ParseException {
	
		IRI individu = f.createIRI(namespace, name);
		
		Date creaDate = new SimpleDateFormat("yyyy/MM/dd").parse(creationDate);
		
		conn.add(individu, RDF.TYPE, Order.iOrder);
		conn.add(individu, Order.orderNumber, f.createLiteral(orderNumber));
		conn.add(individu, Order.creationDate, f.createLiteral(creaDate));
		
		return individu;
	}
	
	static IRI createIndividualsFST(String name, Integer idPersonnel, String nameFST) {
	
		IRI individu = f.createIRI(namespace, name);
		
		conn.add(individu, RDF.TYPE, FST.iFST);
		conn.add(individu, FST.idPersonnel, f.createLiteral(idPersonnel));
		conn.add(individu, FST.name, f.createLiteral(nameFST));
		
		return individu;
	}
	
	static IRI createIndividualsCompany(String name, String nameCompany, String location) {
	
		IRI individu = f.createIRI(namespace, name);
		
		conn.add(individu, RDF.TYPE, Company.iCompany);
		conn.add(individu, Company.name, f.createLiteral(nameCompany));
		conn.add(individu, Company.location, f.createLiteral(location));
		
		return individu;
	}
	
	static void createIndividuals(Repository rep) throws ParseException {
		// Création des individus Parts
		IRI BSA1234 = createIndividualsParts("BSA1234", 1, 10.0);
		IRI BSA5678 = createIndividualsParts("BSA5678", 3, 30.0);
		IRI BSA9876 = createIndividualsParts("BSA9876", 1, 50.5);
		
		//Créations des individus Product
		IRI SP0533  = createIndividualsProduct("SP0533", "SPrintera 106 PER");
		IRI SP0534  = createIndividualsProduct("SP0534", "Masterfoild 254");
		IRI PCR0356 = createIndividualsProduct("PCR0356", "VisionFoild");

		//Création des individus Customer
		IRI Cus1111 = createIndividualsCustomer("Cus1111", 1111, "Rieken AG");
		IRI Cus2222 = createIndividualsCustomer("Cus2222", 2222, "Imprimeurs du futur");
		IRI Cus3333 = createIndividualsCustomer("Cus3333", 3333, "GrosJean");
		
		//Création des individus Service
		IRI Srv88 = createIndividualsService("Srv88", "Dépannage", 150.0);
		IRI Srv99 = createIndividualsService("Srv99", "Maintenance", 100.0);		
		
		//Création des individus Order
		IRI O2001 = createIndividualsOrder("O2001", "O2001", "2020/01/01");
		IRI O2002 = createIndividualsOrder("O2002", "O2002", "2020/01/02");
		
		//Création des individus FST
		IRI FST01 = createIndividualsFST("FST01", 16625, "Sylvain Blanc");
		IRI FST02 = createIndividualsFST("FST02", 18625, "Alain Couturier");
		
		//Création des individus Company
		IRI Cpn01 = createIndividualsCompany("Cpn01", "Bobst Mex", "Suisse");
		IRI Cpn02 = createIndividualsCompany("Cpn02", "Bobst Lyon", "France");
		
		try {
			
			conn.add(BSA1234,Parts.opPart_of, SP0533);
			conn.add(BSA1234,Parts.opPart_of, SP0534);
			conn.add(BSA5678,Parts.opPart_of, SP0533);
			conn.add(BSA9876,Parts.opPart_of, SP0534);
			conn.add(BSA9876,Parts.opPart_of, PCR0356);
			
			conn.add(BSA9876,Parts.opPart_of, O2001);
			conn.add(BSA5678,Parts.opPart_of, O2002);
			
			conn.add(PCR0356,  Product.opBelongs_to, Cus1111);
			conn.add(SP0533,  Product.opBelongs_to, Cus3333);
			conn.add(SP0534,  Product.opBelongs_to, Cus2222);
		
			conn.add(Cpn01,  Company.opServes, Cus1111);
			conn.add(Cpn02,  Company.opServes, Cus2222);
			conn.add(Cpn02,  Company.opServes, Cus3333);
			
			conn.add(FST01, FST.opTravel_for, Cpn01);
			conn.add(FST02, FST.opTravel_for, Cpn02);
		
			conn.add(Srv99, Service.opIs_proposed, Cus3333);
			conn.add(Cus2222, Customer.opRequest, Srv88);
			
			conn.add(O2002, Order.opNeed, FST02);
		
			conn.add(Cus2222, Customer.opOrdered, O2002);
			conn.add(Cus1111, Customer.opOrdered, O2001);
			
		 } finally {
			//conn.close(); 
		 }
		
		
		
	} 
	
	static void execQuery1(Repository rep) {
		
		// Liste des ressources de type parts dont le prix est suppérieur à 10 , par ordre descendant
		String queryString= "PREFIX ex: <http://MiniProjet-Ontologie/>" +
							"SELECT ?parts ?price " +
							"WHERE { " +
							 "    ?parts a ex:parts ." +
							 "    ?parts ex:price ?price ." +
							 "    FILTER (?price > 10)" + 
							 "}" +
							 "ORDER BY DESC(?price)";

		TupleQuery query = conn.prepareTupleQuery(queryString);

		try (TupleQueryResult result = query.evaluate()) {
			int i=1;
			System.out.println("Requète 1 : Liste des ressources pièces dont le prix est > 10");
			while (result.hasNext()) {
				BindingSet solution = result.next();
				System.out.println("Parts_" + i + " = " + solution.getValue("parts") + solution.getValue("price") );
				i++;
			}
			System.out.println(" ");
		}
	}
	 
	
		static void execQuery2(Repository rep) {
			
			// Donne le nombre de company localisée en Suisse et en France 
			String queryString= "PREFIX ex: <http://MiniProjet-Ontologie/>" +
								"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" + 
								"SELECT (count (distinct ?x) as ?countCpn) " +
								"WHERE { " +
								 "       {?x a ex:company ." +
								 "        ?x ex:location 'Suisse'}" +
								 "       UNION" +
								 "       {?x ex:location 'France'}" +
								 "}";

			TupleQuery query = conn.prepareTupleQuery(queryString);

			try (TupleQueryResult result = query.evaluate()) {
				System.out.println("Requète 2 : Nombre de sociétés en Suisse et en France");
				while (result.hasNext()) {
					BindingSet solution = result.next();
					System.out.println("Nombre de company = " + solution.getValue("countCpn"));
				}
				System.out.println(" ");
			}
		}
		 
		
		static void execQuery3(Repository rep) {
			
			// Retourne la liste des description machine qui contiennent la pièce BSA1234
			String queryString= "PREFIX ex: <http://MiniProjet-Ontologie/>" +
								"SELECT distinct ?description " +
								"WHERE { " +
								"  ?x ex:partsNumber 'BSA1234' ." +
								"  ?x ex:part_of ?y ." +
								"  ?y ex:description ?description" +
								 "}";

			TupleQuery query = conn.prepareTupleQuery(queryString);

			try (TupleQueryResult result = query.evaluate()) {
				System.out.println("Requète 3 : Les machines qui contiennent la pièces BSA1234");
				while (result.hasNext()) {
					BindingSet solution = result.next();
					System.out.println(solution.getValue("description"));
				};
				System.out.println(" ");
			}
		}
		 
		
		static void execQuery4(Repository rep) {
			
			//Liste les pièces de la machine SP0533 avec leur criticité. 
			String queryString= "PREFIX ex: <http://MiniProjet-Ontologie/>" +
								"SELECT ?pn ?criticity " +
								"WHERE { " +
								 "    ?x ex:criticity ?criticity ." +
								 "    ?x ex:partsNumber ?pn ." + 
								 "    ?x a ex:parts ." +
								 "    ?x ex:part_of ?y . " +
								 "    ?y ex:productNumber 'SP0533'" +
								 "}" ;

			TupleQuery query = conn.prepareTupleQuery(queryString);

			try (TupleQueryResult result = query.evaluate()) {
				System.out.println("Requète 4 : ");
				while (result.hasNext()) {
					BindingSet solution = result.next();
					System.out.println(solution.getValue("pn") + ", Criticity :" + solution.getValue("criticity"));
				}
				System.out.println(" ");
			}
		}
		 
		
		static void execQuery5(Repository rep) {
			
			// Liste des pièces commandées par le client Rieken AG
			String queryString= "PREFIX ex: <http://MiniProjet-Ontologie/>" +
								"SELECT distinct ?pn " +
								"WHERE { " +
								 "    ?parts ex:partsNumber ?pn . " +
								 "    ?parts a ex:parts . " +
								 "    ?parts ex:part_of ?order . " +
								 "    ?customer ex:ordered ?order . " +
								 "    ?customer ex:name 'Rieken AG'" +
								 "}";

			TupleQuery query = conn.prepareTupleQuery(queryString);

			try (TupleQueryResult result = query.evaluate()) {
				System.out.println("Requète 5 : ");
				while (result.hasNext()) {
					BindingSet solution = result.next();
					System.out.println("Parts = " + solution.getValue("pn"));
				}
				System.out.println(" ");
			}
		}
		 
		
		static void execQuery6(Repository rep) {
			
			// Le nom des FST qui ont travaillés sur la machine SP0534
			String queryString= "PREFIX ex: <http://MiniProjet-Ontologie/>" +
								"SELECT distinct ?nameFST " +
								"WHERE { " +
								 "    ?product ex:productNumber 'SP0534' . " +
								 "    ?product ex:belongs_to ?customer . " +
								 "    ?customer ex:ordered ?order ." +
								 "    ?order ex:need ?FST ." +
								 "    ?FST ex:name ?nameFST" +
								 "}";

			TupleQuery query = conn.prepareTupleQuery(queryString);

			try (TupleQueryResult result = query.evaluate()) {
				System.out.println("Requète 6 : ");
				while (result.hasNext()) {
					BindingSet solution = result.next();
					System.out.println(solution.getValue("nameFST"));
				}
				System.out.println(" ");
			}
		}

		static void execQuery7(Repository rep) {
			
			// Donne les types de sercices disponibles et quel client les a demandés
			String queryString= "PREFIX ex: <http://MiniProjet-Ontologie/>" +
								"SELECT distinct ?type ?customer  " +
								"WHERE { " +
								 "    ?x a ex:service . " +
								 "    ?x ex:type ?type ." +
								 "    OPTIONAL {" +
								 "        ?customer ex:request ?x ." +
								 "    }" +
								 "}";

			TupleQuery query = conn.prepareTupleQuery(queryString);

			try (TupleQueryResult result = query.evaluate()) {
				System.out.println("Requète 6 : ");
				while (result.hasNext()) {
					BindingSet solution = result.next();
					System.out.println(solution.getValue("type") + "," + solution.getValue("customer"));
				}
				System.out.println(" ");
			}
		}
	public static void main(String[] args) throws ParseException {
		
		//Initialisation du repository
		rep = new SailRepository(new MemoryStore(dataDir));
		f = rep.getValueFactory();						
		conn = rep.getConnection();
		
		buildOntology(rep);
		createIndividuals(rep);
		execQuery1(rep);
		execQuery2(rep);
		execQuery3(rep);
		execQuery4(rep);
		execQuery5(rep);
		execQuery6(rep);
		execQuery7(rep);
		
		
//		RepositoryResult<Statement> statements = conn.getStatements (null, null, null, true);
//		Model model = Iterations.addAll(statements, new LinkedHashModel());
//		
//		model.setNamespace("rdf", RDF.NAMESPACE);
//		model.setNamespace("rdfs", RDFS.NAMESPACE);
//		model.setNamespace("xsd", XMLSchema.NAMESPACE);
//		model.setNamespace("foaf", FOAF.NAMESPACE);
//			
//		Rio.write(model, System.out, RDFFormat.TURTLE);
//		
//		RepositoryResult<Statement> comptes = conn.getStatements (null, null, null, true);
//	
//		Statement st;
//		while (comptes.hasNext()) {
//			st = comptes.next();
//			//Quels sont les noms de toutes les « Persons »
//			//if ( (st.getPredicate().getLocalName().equals("name"))  )
//				System.out.println(st.getObject());
//			
//		}

	}

}
