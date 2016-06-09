package com.ml.dataQuality;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DQ_Udacity {

	public static int PRETTY_PRINT_INDENT_FACTOR = 4;

	public static void main(String[] args) throws Exception {

		
		DQ_Udacity udacity = new DQ_Udacity();
		udacity.getNewDataFromUdacity();
		//udacity.makeChangesToJSONForEvaluation();
		//udacity.checkQualityOfDataFromEdX();
		

	}

	@SuppressWarnings("unchecked")
	private void makeChangesToJSONForEvaluation() throws Exception{


		JSONParser parser = new JSONParser(); 
		Object obj= parser.parse(new FileReader("D:\\Project\\Udacity\\udacity.json"));
		JSONObject inner = (JSONObject) obj;
		JSONArray jArrayForElements = (JSONArray)inner.get("courses");
		double count=0;
		for(Object o: jArrayForElements){
			count++;
			if(Math.random() < 0.9){
				JSONObject course = (JSONObject) o;
				course.put("title", "This is a change in name for course ID"+ course.get("key").toString());
				course.put("expected_learning", "<div>This course gives a broad overview of contraceptive methods and explores issues that influence contraceptive choices today. <\\//div>\n<div><\\/div>\\n<div>We will discuss the mechanism of action, effectiveness, risk/benefit, side effects and contraindications for each contraceptive method, as well as ask some questions about contraceptive decision making. What are some of the factors that influence contraception use and decision making?   Are there specific cultural, ethnic, social and environmental factors?  We will also look at the relationship between contraception use and risk of acquiring Sexually Transmitted Infections (STIs). <\\/div>\\n<div><\\/div>"+ course.get("key").toString());
				course.put("required_knowledge", "<div>This course gives a broad overview of contraceptive methods and explores issues that influence contraceptive choices today. <\\//div>\n<div><\\/div>\\n<div>We will discuss the mechanism of action, effectiveness, risk/benefit, side effects and contraindications for each contraceptive method, as well as ask some questions about contraceptive decision making. What are some of the factors that influence contraception use and decision making?   Are there specific cultural, ethnic, social and environmental factors?  We will also look at the relationship between contraception use and risk of acquiring Sexually Transmitted Infections (STIs). <\\/div>\\n<div><\\/div>"+ course.get("key").toString());
			}
		}
		System.out.println(count);
		inner.put("courses", jArrayForElements);
		FileWriter fw = new FileWriter("D:\\Project\\Udacity\\udacity_90.json");
		fw.write(inner.toString());
		fw.flush();
		fw.close();
	}

	public void checkQualityOfDataFromEdX() {

		/*
		 * Loading new JSON file into HashMap
		 * 
		 */

		try{
			JSONParser parser = new JSONParser(); 

			Object obj= parser.parse(new FileReader("D:\\Project\\Udacity\\udacity_90.json"));

			org.json.simple.JSONObject inner = (org.json.simple.JSONObject) obj;
			org.json.simple.JSONArray jArrayForElements = (org.json.simple.JSONArray)inner.get("courses");
			Map<String,ArrayList<String>> courseMap = new HashMap<String,ArrayList<String>>();

			int count=0;

			for(Object o: jArrayForElements){
				JSONObject course = (JSONObject) o;

				String courseID = course.get("key").toString();
				courseMap.put(courseID, null);
				ArrayList<String> value = new ArrayList<String>();

				String name = (String) course.get("title");
				value.add(name);
				courseMap.put(courseID, value);

				String shortDescription = (String) course.get("expected_learning");
				value.add(shortDescription);
				courseMap.put(courseID, value);

				String recommendedBackground = (String) course.get("required_knowledge");
				value.add(recommendedBackground);
				courseMap.put(courseID, value);

				count++;
			}
			System.out.println(count);
			getScore(courseMap);

		}
		catch(Exception e ){e.printStackTrace();}
	}

	public static void getScore(Map<String,ArrayList<String>> courseMap){


		long countOfCoursesDeleted=0;
		long countOfCoursesWithDetailsChanged=0;
		Map<String,Map<String,String>> changeInJsonMap = new HashMap<String,Map<String,String>>();

		try{
			JSONParser parser = new JSONParser(); 

			/*
			 *	Loading the old JSON data. 
			 */


			Object obj= parser.parse(new FileReader("D:\\Project\\Udacity\\udacity.json"));
			org.json.simple.JSONObject inner = (org.json.simple.JSONObject) obj;
			org.json.simple.JSONArray jArrayForElements = (org.json.simple.JSONArray)inner.get("courses");

			/*
			 *  For each old JSON element, compare it with the new JSON file which is already loaded. 
			 */

			for(Object o: jArrayForElements){
				JSONObject course = (JSONObject) o;
				Map<String,String> courseDetailsChangeMap = new HashMap<String,String>();
				String courseID = course.get("key").toString();
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
					String name = (String) course.get("title");
					if(courseMap.get(courseID).get(arrayListIterator++).equals(name)){}
					else{
						countOfCoursesWithDetailsChanged++;
						courseDetailsChangeMap.put("title", courseMap.get(courseID).get(arrayListIterator-1));
					}
					String shortDescription = (String) course.get("expected_learning");
					if(courseMap.get(courseID).get(arrayListIterator++).equals(shortDescription)){}
					else{
						countOfCoursesWithDetailsChanged++;
						courseDetailsChangeMap.put("expected_learning", courseMap.get(courseID).get(arrayListIterator-1));
					}
					String recommendedBackground = (String) course.get("required_knowledge");
					if(courseMap.get(courseID).get(arrayListIterator++).equals(recommendedBackground)){}
					else{
						countOfCoursesWithDetailsChanged++;
						courseDetailsChangeMap.put("required_knowledge", courseMap.get(courseID).get(arrayListIterator-1));
					}
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
	private static void changeOriginalJSONAccToScore(Map<String, Map<String, String>> mapForChangesToBeMadeInOriginalJson, long score) {

		try{
			JSONParser parser = new JSONParser(); 

			/*  Now, update the original JSON file with the changes.
			 * 
			 */


			Object obj= parser.parse(new FileReader("D:\\Project\\Udacity\\udacity.json"));
			JSONObject inner = (JSONObject) obj;
			JSONArray jArrayForElements = (JSONArray)inner.get("courses");
			int locationCount = 0;
			ArrayList<Integer> locationList = new ArrayList<Integer>();
			Map<String,String> dummyMap = new HashMap<>();
			dummyMap.put("dummy", "dummy");
			for(Object o: jArrayForElements){
				JSONObject course = (JSONObject) o;
				String courseID = course.get("key").toString();

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
			inner.put("courses", finalJsonArray);
			FileWriter fw = new FileWriter("D:\\Project\\Udacity\\udacity_90_changed.json");
			fw.write(inner.toString());
			fw.flush();
			fw.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	public void getNewDataFromUdacity(){
		try {

			org.json.JSONObject jsonObject = new org.json.JSONObject(IOUtils.toString(new URL("https://www.udacity.com/public-api/v0/courses")));
			long startTime = System.currentTimeMillis();
			String jsonPrettyPrintString = jsonObject.toString(PRETTY_PRINT_INDENT_FACTOR);
			FileWriter fw = new FileWriter("D:\\Project\\Udacity\\udacity.json");
			fw.write(jsonPrettyPrintString.toString());
			fw.flush();
			fw.close();
			Thread t = null;
			t.sleep(200);
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("\n\n"+totalTime);
		} catch (Exception je) {
			System.out.println(je.toString());
		}
	}

}
