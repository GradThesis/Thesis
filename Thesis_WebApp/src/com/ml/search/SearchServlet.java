package com.ml.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;

import com.google.gson.Gson;
import com.ml.query.QueryInfo;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static final String url = "/home/cdhekne/Documents/Thesis/udacity_1.ttl";
	private static final String db = "http://localhost:3030/Thesis/query";
	private final OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, null);
	private QueryInfo qi = new QueryInfo();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword;
		
		keyword = request.getParameter("keyword");
		
		/*if(keyword.isEmpty()) {
			// Send back to home page
			response.sendRedirect("index.jsp");
			return;
		}
		else {*/
//			m.read(url, "RDF/XML");
			
			String queryString = "prefix j.0: <http://mooclink.asu.edu/final_Mooc.owl#>\n" +
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
				"PREFIX schema: <http://schema.org/>\n" +
				"SELECT ?Course_Name ?Course_RecBackground ?Course_Language ?Course_Provider WHERE {\n" +
		        "  ?course j.0:Course_Name ?Course_Name ; j.0:Course_RecBackground ?Course_RecBackground .\n" +
				"OPTIONAL {?course j.0:Course_Language ?Course_Language .} \n"+
				"OPTIONAL {?course j.0:Course_Provider ?Course_Provider .} \n"+
		        "FILTER (regex(?Course_Name, \"" + keyword + "\", \"i\")).\n" +
				"}";
			
			Query query = QueryFactory.create(queryString) ;
			QueryExecution qexec = QueryExecutionFactory.sparqlService(db, query);
			
			/*Course courses = new Course();
			courses.setCourses(qi.getCourses(qexec));
			request.setAttribute("courses", courses);
			*/
			
			// Better: forward request & response with redirect
			// Browser doesn't know that this is a new request, request
			// and response parameters are carried over
			ArrayList<Object> jsonObj= new ArrayList<Object>();
			try{
				
				ResultSet responseResultSet = qexec.execSelect();
				while( responseResultSet.hasNext())
				{
					HashMap<String, String> j = new HashMap<String, String>();
					Gson gson = new Gson();
					QuerySolution soln = responseResultSet.nextSolution();
					RDFNode Course_Name = soln.get("?Course_Name");
					RDFNode Course_RecBackground = soln.get("?Course_RecBackground");
					RDFNode language = soln.get("?Course_Language");
					RDFNode provider = soln.get("?Course_Provider");
					Random randomizer = new Random();
					List<String> list = new ArrayList<>();
					list.add("Coursera");
					list.add("edX");
					list.add("Udacity");
					list.add("Khan Academy");
					list.add("OCW");
					j.put("Course_Name", Course_Name.toString());
					j.put("Course_RecBackground", Course_RecBackground.toString());
					if(language==null)
						j.put("Course_Language", "-");
					else
						j.put("Course_Language", language.toString());
					if(provider==null)
						j.put("Course_Provider", list.get(randomizer.nextInt(list.size())));
					else
						j.put("Course_Provider", provider.toString());
					jsonObj.add(j);
				}
				
			}
			catch(Exception e){
				
				e.printStackTrace();
			}
			
//			json = "[" + json+ "]";
			
			String json1 = new Gson().toJson(jsonObj);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(json1);
          /*  
            RequestDispatcher dispatcher = request.getRequestDispatcher("results.jsp");
			request.setAttribute("json", json1);
			dispatcher.forward(request, response);*/
            
           /* // fw.close();
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type");
            String json1 = new Gson().toJson(json);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json1);
//            response.getWriter().write(new Gson().toJson(json)); 
            System.out.println("Done");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("results.jsp");
			request.setAttribute("json", json);
			dispatcher.forward(request, response);*/
		}
	//}
	
	public String removeLastChar(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, s.length()-1);
    }	
	
}
