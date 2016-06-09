package com.ml.dataQuality;

import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DQ_Coursera {

	public static int PRETTY_PRINT_INDENT_FACTOR = 4;

	public static void main(String[] args) throws Exception {
		
		
		
		DQ_Coursera dqCoursera = new DQ_Coursera();
		dqCoursera.getNewDataFromCoursera();
		//dqCoursera.makeChangesToJSONForEvaluation();
		//dqCoursera.checkQualityOfDataFromCoursera();
		
	}
	
	@SuppressWarnings("unchecked")
	private void makeChangesToJSONForEvaluation() throws Exception{

		
		JSONParser parser = new JSONParser(); 
		Object obj= parser.parse(new FileReader("D:\\Project\\Coursera\\coursera.json"));
		JSONObject inner = (JSONObject) obj;
		JSONArray jArrayForElements = (JSONArray)inner.get("elements");
		double count=0;
		for(Object o: jArrayForElements){
			
			if(Math.random() < 0.9){
				count++;
				JSONObject course = (JSONObject) o;
				
				course.put("name", "This is a change in name for course ID"+ course.get("id").toString());
				course.put("shortDescription", "<div>This course gives a broad overview of contraceptive methods and explores issues that influence contraceptive choices today. <\\//div>\n<div><\\/div>\\n<div>We will discuss the mechanism of action, effectiveness, risk/benefit, side effects and contraindications for each contraceptive method, as well as ask some questions about contraceptive decision making. What are some of the factors that influence contraception use and decision making?   Are there specific cultural, ethnic, social and environmental factors?  We will also look at the relationship between contraception use and risk of acquiring Sexually Transmitted Infections (STIs). <\\/div>\\n<div><\\/div>"+ course.get("id").toString());
				course.put("courseFormat", "<div>This course gives a broad overview of contraceptive methods and explores issues that influence contraceptive choices today. <\\//div>\n<div><\\/div>\\n<div>We will discuss the mechanism of action, effectiveness, risk/benefit, side effects and contraindications for each contraceptive method, as well as ask some questions about contraceptive decision making. What are some of the factors that influence contraception use and decision making?   Are there specific cultural, ethnic, social and environmental factors?  We will also look at the relationship between contraception use and risk of acquiring Sexually Transmitted Infections (STIs). <\\/div>\\n<div><\\/div>"+ course.get("id").toString());
				course.put("courseSyllabus", "<div>This course gives a broad overview of contraceptive methods and explores issues that influence contraceptive choices today. <\\//div>\n<div><\\/div>\\n<div>We will discuss the mechanism of action, effectiveness, risk/benefit, side effects and contraindications for each contraceptive method, as well as ask some questions about contraceptive decision making. What are some of the factors that influence contraception use and decision making?   Are there specific cultural, ethnic, social and environmental factors?  We will also look at the relationship between contraception use and risk of acquiring Sexually Transmitted Infections (STIs). <\\/div>\\n<div><\\/div>"+ course.get("id").toString());
				course.put("language", "<div>This course gives a broad overview of contraceptive methods and explores issues that influence contraceptive choices today. <\\//div>\n<div><\\/div>\\n<div>We will discuss the mechanism of action, effectiveness, risk/benefit, side effects and contraindications for each contraceptive method, as well as ask some questions about contraceptive decision making. What are some of the factors that influence contraception use and decision making?   Are there specific cultural, ethnic, social and environmental factors?  We will also look at the relationship between contraception use and risk of acquiring Sexually Transmitted Infections (STIs). <\\/div>\\n<div><\\/div>"+ course.get("id").toString());
				course.put("recommendedBackground", "<div>This course gives a broad overview of contraceptive methods and explores issues that influence contraceptive choices today. <\\//div>\n<div><\\/div>\\n<div>We will discuss the mechanism of action, effectiveness, risk/benefit, side effects and contraindications for each contraceptive method, as well as ask some questions about contraceptive decision making. What are some of the factors that influence contraception use and decision making?   Are there specific cultural, ethnic, social and environmental factors?  We will also look at the relationship between contraception use and risk of acquiring Sexually Transmitted Infections (STIs). <\\/div>\\n<div><\\/div>"+ course.get("id").toString());
				course.put("aboutTheCourse", "<div>This course gives a broad overview of contraceptive methods and explores issues that influence contraceptive choices today. <\\//div>\n<div><\\/div>\\n<div>We will discuss the mechanism of action, effectiveness, risk/benefit, side effects and contraindications for each contraceptive method, as well as ask some questions about contraceptive decision making. What are some of the factors that influence contraception use and decision making?   Are there specific cultural, ethnic, social and environmental factors?  We will also look at the relationship between contraception use and risk of acquiring Sexually Transmitted Infections (STIs). <\\/div>\\n<div><\\/div>"+ course.get("id").toString());
			}
		}
		System.out.println(count);
		
		inner.put("elements", jArrayForElements);
		FileWriter fw = new FileWriter("D:\\Project\\Coursera\\coursera_90.json");
		fw.write(inner.toString());
		fw.flush();
		fw.close();
		
		/*org.json.JSONObject jsonObject = new org.json.JSONObject(IOUtils.toString(new FileReader("D:\\Project\\Coursera\\coursera_10.json")));
		String jsonPrettyPrintString = jsonObject.toString(PRETTY_PRINT_INDENT_FACTOR);
		fw = new FileWriter("D:\\Project\\Coursera\\coursera_10.json");
		fw.write(jsonPrettyPrintString.toString());
		fw.flush();
		fw.close();*/
		
	}

	private void checkQualityOfDataFromCoursera() throws Exception {
		
		/*
		 * Loading new JSON file into HashMap
		 * 
		*/
		
		JSONParser parser = new JSONParser(); 

		Object obj= parser.parse(new FileReader("D:\\Project\\Coursera\\coursera_90.json"));

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
		System.out.println(count);

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
			
			
			Object obj= parser.parse(new FileReader("D:\\Project\\Coursera\\coursera.json"));
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
					Map<String,String> dummyMap = new HashMap<>();
					dummyMap.put("dummy", "dummy");
					changeInJsonMap.put(courseID, dummyMap);
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
			
			
			Object obj= parser.parse(new FileReader("D:\\Project\\Coursera\\coursera.json"));
			JSONObject inner = (JSONObject) obj;
			JSONArray jArrayForElements = (JSONArray)inner.get("elements");
			int locationCount = 0;
			ArrayList<Integer> locationList = new ArrayList<Integer>();
			Map<String,String> dummyMap = new HashMap<>();
			dummyMap.put("dummy", "dummy");
			for(Object o: jArrayForElements){
				JSONObject course = (JSONObject) o;
				String courseID = course.get("id").toString();

				if(mapForChangesToBeMadeInOriginalJson.get(courseID)==dummyMap){ //Delete course from JSON file
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
			FileWriter fw = new FileWriter("D:\\Project\\Coursera\\coursera_90_changed.json");
			fw.write(inner.toString());
			fw.flush();
			fw.close();

			/*org.json.JSONObject jsonObject = new org.json.JSONObject(IOUtils.toString(new FileReader("D:\\Project\\Coursera\\coursera_10_changed.json")));
			String jsonPrettyPrintString = jsonObject.toString(PRETTY_PRINT_INDENT_FACTOR);
			fw = new FileWriter("D:\\Project\\Coursera\\coursera_10_changed.json");
			fw.write(jsonPrettyPrintString.toString());
			fw.flush();
			fw.close();*/
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	@SuppressWarnings("static-access")
	public void getNewDataFromCoursera(){
		try {

			org.json.JSONObject jsonObject = new org.json.JSONObject(IOUtils.toString(new URL("https://api.coursera.org/api/catalog.v1/courses?fields=id,startDate,name,shortDescription,language,courseFormat,courseSyllabus,recommendedBackground,aboutTheCourse&includes=instructors,categories")));
			Thread t=null;
			long startTime = System.currentTimeMillis();
			String jsonPrettyPrintString = jsonObject.toString(PRETTY_PRINT_INDENT_FACTOR);
			FileWriter fw = new FileWriter("D:\\Project\\Coursera\\coursera.json");
			fw.write(jsonPrettyPrintString.toString());
			fw.flush();
			fw.close();
			t.sleep(425);
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("\n\n"+totalTime);
		} catch (Exception je) {
			System.out.println(je.toString());
		}
	}


}