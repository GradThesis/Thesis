package rdf;

import java.util.Iterator;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;

/**
 * 
 * Example of Jena ARQ query to Fuseki server for implementation in JSP/Servlet
 * web application.
 * 
 **/
public class QueryTest {
	
	public static void main(String[] args) {
		final String url = "http://sebk.me/mooc_7_1.rdf";
		final OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, null);
		m.read(url, "RDF/XML");
		
		String queryString = "PREFIX mooc: <http://sebk.me/MOOC.owl#>\n"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
				+ "PREFIX schema: <http://schema.org/>\n"
				+ "SELECT * WHERE {\n"
		     	+ "mooc:coursera_category10 mooc:includesCourse ?course\n"
		     	+ "}";
		Query query = QueryFactory.create(queryString) ;
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://sebk.me:3030/ds/query", query);
//		QueryExecution qexec = QueryExecutionFactory.create(query, m) ;
		
		  try {
			  Iterator<QuerySolution> results = qexec.execSelect() ;
			    for ( ; results.hasNext() ; )
			    {
			        QuerySolution soln = results.next() ;
			        RDFNode n = soln.get("course") ; // "x" is a variable in the query
			        // If you need to test the thing returned
			        if ( n.isLiteral() ) {
			            System.out.println(((Literal)n).getLexicalForm()) ;
			        }
			        if ( n.isResource() ) {
			           Resource r = (Resource)n ;
			            if ( ! r.isAnon() )
			            {
			              System.out.println(r.getURI());
			            }
			        }
			    }
		  } finally { qexec.close() ; }
	}
}
