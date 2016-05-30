import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DQ {

	public static void main(String[] args) throws Exception {

		JSONParser parser = new JSONParser(); 

		Object obj= parser.parse(new FileReader("D:\\courses.json"));
		
			JSONObject inner = (JSONObject) obj;
			JSONArray jArray = (JSONArray)inner.get("elements");
			int count = 0;
			Map<String,ArrayList<String>> courseMap = new HashMap<String,ArrayList<String>>();
			for(Object o: jArray){
				JSONObject course = (JSONObject) o;

				String courseID = course.get("id").toString();
				courseMap.put(courseID, null);
				ArrayList<String> value = new ArrayList<String>();
				
				String name = (String) course.get("name");
				System.out.println(name);
				value.add(name);
				courseMap.put(courseID, value);
				
				String shortDescription = (String) course.get("shortDescription");
				System.out.println(shortDescription);
				value.add(shortDescription);
				courseMap.put(courseID, value);
				
				String courseFormat = (String) course.get("courseFormat");
				System.out.println(courseFormat);
				value.add(courseFormat);
				courseMap.put(courseID, value);
				
				String courseSyllabus = (String) course.get("courseSyllabus");
				System.out.println(courseSyllabus);
				value.add(courseSyllabus);
				courseMap.put(courseID, value);
				
				
				String language = (String) course.get("language");
				System.out.println(language);
				value.add(language);
				courseMap.put(courseID, value);
				
				String recommendedBackground = (String) course.get("recommendedBackground");
				System.out.println(recommendedBackground);
				value.add(recommendedBackground);
				courseMap.put(courseID, value);
				
				String aboutTheCourse = (String) course.get("aboutTheCourse");
				System.out.println(aboutTheCourse);
				value.add(aboutTheCourse);
				courseMap.put(courseID, value);
				
				
				count++;
			}
			System.out.println("\n\n" + count);
			
		



	}



}