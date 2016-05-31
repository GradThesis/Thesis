package com.ml.dataQuality;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DQ_Coursera {

	public static void main(String[] args) throws Exception {

		JSONParser parser = new JSONParser(); 

		Object obj= parser.parse(new FileReader("D:\\courses.json"));

		JSONObject inner = (JSONObject) obj;
		JSONArray jArrayForElements = (JSONArray)inner.get("elements");
		Map<String,ArrayList<String>> courseMap = new HashMap<String,ArrayList<String>>();
		int count=0;
		for(Object o: jArrayForElements){
			JSONObject course = (JSONObject) o;

			String courseID = course.get("id").toString();
			courseMap.put(courseID, null);
			ArrayList<String> value = new ArrayList<String>();

			String name = (String) course.get("name");
			//			System.out.println(name);
			value.add(name);
			courseMap.put(courseID, value);

			String shortDescription = (String) course.get("shortDescription");
			//			System.out.println(shortDescription);
			value.add(shortDescription);
			courseMap.put(courseID, value);

			String courseFormat = (String) course.get("courseFormat");
			//			System.out.println(courseFormat);
			value.add(courseFormat);
			courseMap.put(courseID, value);

			String courseSyllabus = (String) course.get("courseSyllabus");
			//			System.out.println(courseSyllabus);
			value.add(courseSyllabus);
			courseMap.put(courseID, value);


			String language = (String) course.get("language");
			//			System.out.println(language);
			value.add(language);
			courseMap.put(courseID, value);

			String recommendedBackground = (String) course.get("recommendedBackground");
			//			System.out.println(recommendedBackground);
			value.add(recommendedBackground);
			courseMap.put(courseID, value);

			String aboutTheCourse = (String) course.get("aboutTheCourse");
			//			System.out.println(aboutTheCourse);
			value.add(aboutTheCourse);
			courseMap.put(courseID, value);

			count++;
		}
//		System.out.println(count);

		JSONObject links =  (JSONObject) inner.get("linked");
		JSONArray instructorArray = (JSONArray) links.get("instructors");
		Map<String,ArrayList<String>> instructorMap = new HashMap<String,ArrayList<String>>();

		for(Object o: instructorArray){
			JSONObject instructor = (JSONObject) o;

			String instructorID = instructor.get("id").toString();
			instructorMap.put(instructorID, null);
			ArrayList<String> value = new ArrayList<String>();

			String lastName = (String) instructor.get("lastName");
			//			System.out.println(lastName);
			value.add(lastName);
			instructorMap.put(instructorID, value);

			String firstName = (String) instructor.get("firstName");
			//			System.out.println(firstName);
			value.add(firstName);
			instructorMap.put(instructorID, value);

		}

		JSONArray categoryArray = (JSONArray) links.get("categories");
		Map<String,ArrayList<String>> categoryMap = new HashMap<String,ArrayList<String>>();

		for(Object o: categoryArray){
			JSONObject category = (JSONObject) o;

			String categoryID = category.get("id").toString();
			categoryMap.put(categoryID, null);
			ArrayList<String> value = new ArrayList<String>();

			String nameOfCategory = (String) category.get("name");
			//			System.out.println(nameOfCategory);
			value.add(nameOfCategory);
			categoryMap.put(categoryID, value);

		}

		System.out.println(getScore(courseMap,instructorMap,categoryMap));




	}

	public static int getScore(Map<String,ArrayList<String>> courseMap, Map<String,ArrayList<String>> instructorMap, Map<String,ArrayList<String>> categoryMap){

		
		int countOfCoursesDeleted=0;
		int countOfCoursesWithDetailsChanged=0;
		try{
			JSONParser parser = new JSONParser(); 

			Object obj= parser.parse(new FileReader("D:\\courses0.json"));
			JSONObject inner = (JSONObject) obj;
			JSONArray jArrayForElements = (JSONArray)inner.get("elements");
			
			for(Object o: jArrayForElements){
				JSONObject course = (JSONObject) o;
				String courseID = course.get("id").toString();
				if(courseMap.get(courseID)==null){
					//This is a course which has been removed since the last run
					countOfCoursesDeleted++;
				}
				else{
					int arrayListIterator=0;
					String name = (String) course.get("name");
					if(courseMap.get(courseID).get(arrayListIterator++).equals(name)){
						continue;
					}
					else{
						countOfCoursesWithDetailsChanged++;
					}
					String shortDescription = (String) course.get("shortDescription");
					if(courseMap.get(courseID).get(arrayListIterator++).equals(shortDescription)){
						continue;
					}
					else{
						countOfCoursesWithDetailsChanged++;
					}
					String courseFormat = (String) course.get("courseFormat");
					if(courseMap.get(courseID).get(arrayListIterator++).equals(courseFormat)){
						continue;
					}
					else{
						countOfCoursesWithDetailsChanged++;
					}
					String courseSyllabus = (String) course.get("courseSyllabus");
					if(courseMap.get(courseID).get(arrayListIterator++).equals(courseSyllabus)){
						continue;
					}
					else{
						countOfCoursesWithDetailsChanged++;
					}
					String language = (String) course.get("language");
					if(courseMap.get(courseID).get(arrayListIterator++).equals(language)){
						continue;
					}
					else{
						countOfCoursesWithDetailsChanged++;
					}
					String recommendedBackground = (String) course.get("recommendedBackground");
					if(courseMap.get(courseID).get(arrayListIterator++).equals(recommendedBackground)){
						continue;
					}
					else{
						countOfCoursesWithDetailsChanged++;
					}
					String aboutTheCourse = (String) course.get("aboutTheCourse");
					if(courseMap.get(courseID).get(arrayListIterator++).equals(aboutTheCourse)){
						continue;
					}
					else{
						countOfCoursesWithDetailsChanged++;
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return countOfCoursesWithDetailsChanged+countOfCoursesDeleted;

	}



}