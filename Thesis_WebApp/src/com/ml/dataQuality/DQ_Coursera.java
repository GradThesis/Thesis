package com.ml.dataQuality;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DQ_Coursera {

	public static void main(String[] args) throws Exception {
		
		DQ_Coursera dqCoursera = new DQ_Coursera();
		dqCoursera.checkQualityOfDataFromCoursera();

	}

	private void checkQualityOfDataFromCoursera() throws Exception {
		
		/*
		 * Loading new JSON file into HashMap
		 * 
		*/
		
		JSONParser parser = new JSONParser(); 

		Object obj= parser.parse(new FileReader("D:\\courses_new.json"));

		JSONObject inner = (JSONObject) obj;
		JSONArray jArrayForElements = (JSONArray)inner.get("elements");
		
		/*
		 * Loading courses
		 * 
		*/
		
		Map<String,ArrayList<String>> courseMap = new HashMap<String,ArrayList<String>>();
		int count=0;
		for(Object o: jArrayForElements){
			JSONObject course = (JSONObject) o;

			String courseID = course.get("id").toString();
			courseMap.put(courseID, null);
			ArrayList<String> value = new ArrayList<String>();

			String name = (String) course.get("name");
			value.add(name);
			courseMap.put(courseID, value);

			String shortDescription = (String) course.get("shortDescription");
			value.add(shortDescription);
			courseMap.put(courseID, value);

			String courseFormat = (String) course.get("courseFormat");
			value.add(courseFormat);
			courseMap.put(courseID, value);

			String courseSyllabus = (String) course.get("courseSyllabus");
			value.add(courseSyllabus);
			courseMap.put(courseID, value);


			String language = (String) course.get("language");
			value.add(language);
			courseMap.put(courseID, value);

			String recommendedBackground = (String) course.get("recommendedBackground");
			value.add(recommendedBackground);
			courseMap.put(courseID, value);

			String aboutTheCourse = (String) course.get("aboutTheCourse");
			value.add(aboutTheCourse);
			courseMap.put(courseID, value);

			count++;
		}
		//		System.out.println(count);

		JSONObject links =  (JSONObject) inner.get("linked");
		JSONArray instructorArray = (JSONArray) links.get("instructors");
		
		/*
		 * Loading instructors
		 * */
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

		/*
		 * Loading categories
		 * */
		JSONArray categoryArray = (JSONArray) links.get("categories");
		Map<String,ArrayList<String>> categoryMap = new HashMap<String,ArrayList<String>>();

		for(Object o: categoryArray){
			JSONObject category = (JSONObject) o;

			String categoryID = category.get("id").toString();
			categoryMap.put(categoryID, null);
			ArrayList<String> value = new ArrayList<String>();

			String nameOfCategory = (String) category.get("name");
			value.add(nameOfCategory);
			categoryMap.put(categoryID, value);

		}

		
		/*
		 * call to get the score of Coursera
		 * */
		getScore(courseMap,instructorMap,categoryMap);


		
	}

	public static void getScore(Map<String,ArrayList<String>> courseMap, Map<String,ArrayList<String>> instructorMap, Map<String,ArrayList<String>> categoryMap){


		int countOfCoursesDeleted=0;
		int countOfCoursesWithDetailsChanged=0;
		Map<String,Map<String,String>> changeInJsonMap = new HashMap<String,Map<String,String>>();

		try{
			JSONParser parser = new JSONParser(); 

			/*
			 *	Loading the old JSON data. 
			*/
			
			
			Object obj= parser.parse(new FileReader("D:\\courses_old.json"));
			JSONObject inner = (JSONObject) obj;
			JSONArray jArrayForElements = (JSONArray)inner.get("elements");

			/*
			 *  For each old JSON element, compare it with the new JSON file which is already loaded. 
			*/

			for(Object o: jArrayForElements){
				JSONObject course = (JSONObject) o;
				Map<String,String> courseDetailsChangeMap = new HashMap<String,String>();
				String courseID = course.get("id").toString();
				if(courseMap.get(courseID)==null){
					//This is a course which has been removed since the last run
					countOfCoursesDeleted++;
					changeInJsonMap.put(courseID, null);
					//delete this course from the original JSON
				}
				else{
					int arrayListIterator=0;
					String name = (String) course.get("name");
					if(courseMap.get(courseID).get(arrayListIterator++).equals(name)){}
					else{
						countOfCoursesWithDetailsChanged++;
						courseDetailsChangeMap.put("name", courseMap.get(courseID).get(arrayListIterator-1));
					}
					String shortDescription = (String) course.get("shortDescription");
					if(courseMap.get(courseID).get(arrayListIterator++).equals(shortDescription)){}
					else{
						countOfCoursesWithDetailsChanged++;
						courseDetailsChangeMap.put("shortDescription", courseMap.get(courseID).get(arrayListIterator-1));
					}
					String courseFormat = (String) course.get("courseFormat");
					if(courseMap.get(courseID).get(arrayListIterator++).equals(courseFormat)){}
					else{
						countOfCoursesWithDetailsChanged++;
						courseDetailsChangeMap.put("courseFormat", courseMap.get(courseID).get(arrayListIterator-1));
					}
					String courseSyllabus = (String) course.get("courseSyllabus");
					if(courseMap.get(courseID).get(arrayListIterator++).equals(courseSyllabus)){}
					else{
						countOfCoursesWithDetailsChanged++;
						courseDetailsChangeMap.put("courseSyllabus", courseMap.get(courseID).get(arrayListIterator-1));
					}
					String language = (String) course.get("language");
					if(courseMap.get(courseID).get(arrayListIterator++).equals(language)){}
					else{
						countOfCoursesWithDetailsChanged++;
						courseDetailsChangeMap.put("language", courseMap.get(courseID).get(arrayListIterator-1));
					}
					String recommendedBackground = (String) course.get("recommendedBackground");
					if(courseMap.get(courseID).get(arrayListIterator++).equals(recommendedBackground)){}
					else{
						countOfCoursesWithDetailsChanged++;
						courseDetailsChangeMap.put("recommendedBackground", courseMap.get(courseID).get(arrayListIterator-1));
					}
					String aboutTheCourse = (String) course.get("aboutTheCourse");
					if(courseMap.get(courseID).get(arrayListIterator++).equals(aboutTheCourse)){}
					else{
						countOfCoursesWithDetailsChanged++;
						courseDetailsChangeMap.put("aboutTheCourse", courseMap.get(courseID).get(arrayListIterator-1));
					}
					//if(!courseDetailsChangeMap.isEmpty())
					changeInJsonMap.put(courseID, courseDetailsChangeMap);
				}

			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		changeOriginalJSONAccToScore(changeInJsonMap,(countOfCoursesDeleted+(countOfCoursesWithDetailsChanged/2)));
	}

	@SuppressWarnings("unchecked")
	private static void changeOriginalJSONAccToScore(Map<String, Map<String, String>> mapForChangesToBeMadeInOriginalJson, int score) {

		try{
			JSONParser parser = new JSONParser(); 

			/*  Now, update the original JSON file with the changes.
			 * 
			*/
			
			
			Object obj= parser.parse(new FileReader("D:\\courses.json"));
			JSONObject inner = (JSONObject) obj;
			JSONArray jArrayForElements = (JSONArray)inner.get("elements");
			int locationCount = 0;
			ArrayList<Integer> locationList = new ArrayList<Integer>();
			for(Object o: jArrayForElements){
				JSONObject course = (JSONObject) o;
				String courseID = course.get("id").toString();

				if(mapForChangesToBeMadeInOriginalJson.get(courseID)==null){ //Delete course from JSON file
					locationList.add(locationCount);
				}
				else{
					Map<String,String> innerChangesMap = mapForChangesToBeMadeInOriginalJson.get(courseID);
					for (Map.Entry<String, String> entry : innerChangesMap.entrySet()) {
						String key = entry.getKey().toString();
						String value = entry.getValue().toString();
						course.put(key, value);
					}
				}

				locationCount++;
			}
			ArrayList<String> list1 = new ArrayList<String>();
			int len = jArrayForElements.size();
			if (jArrayForElements != null) { 
				for (int i=0;i<len;i++){ 
					list1.add(jArrayForElements.get(i).toString());
				} 
			}
			
			for(int i=0;i<locationList.size();i++){
				int loc = locationList.get(i);
				list1.remove(loc);
			}
			org.json.JSONArray finalJsonArray = new org.json.JSONArray(list1);
			inner.put("elements", finalJsonArray);
			FileWriter fw = new FileWriter("D:\\courses_old.json");
			fw.write(inner.toString());
			fw.flush();
			fw.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}



}