package com.ml.dataQuality;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DQ_Ocw {

	public static int PRETTY_PRINT_INDENT_FACTOR = 4;

	public static void main(String[] args) {

		DQ_Ocw edx = new DQ_Ocw();
		edx.getNewDataFromOCW();
//		edx.checkQualityOfDataFromEdX();
	}

	public void checkQualityOfDataFromEdX() {
		
		/*
		 * Loading new JSON file into HashMap
		 * 
		*/
		
		try{
			JSONParser parser = new JSONParser(); 

			Object obj= parser.parse(new FileReader("D:\\edx1.json"));

			org.json.simple.JSONObject inner = (org.json.simple.JSONObject) obj;
			org.json.simple.JSONObject rssElements = (org.json.simple.JSONObject)inner.get("rss");
			org.json.simple.JSONObject channelElements = (org.json.simple.JSONObject)rssElements.get("channel");
			org.json.simple.JSONArray jArrayForElements = (org.json.simple.JSONArray)channelElements.get("item");
			Map<String,ArrayList<String>> courseMap = new HashMap<String,ArrayList<String>>();
			
			int count=0;
			
			for(Object o: jArrayForElements){
				JSONObject course = (JSONObject) o;

				String courseID = course.get("course:code").toString();
				courseMap.put(courseID, null);
				ArrayList<String> value = new ArrayList<String>();
				
				String name = (String) course.get("title");
				value.add(name);
				courseMap.put(courseID, value);

				String shortDescription = (String) course.get("description");
				value.add(shortDescription);
				courseMap.put(courseID, value);
				
				String recommendedBackground = (String) course.get("prerequisites");
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

	@SuppressWarnings("unchecked")
	public void getNewDataFromOCW(){
		try {

			FileInputStream io = new FileInputStream(new File("D:\\ocw-courses.xls"));
			
			Workbook wb = WorkbookFactory.create(io);
			Sheet sheet = wb.getSheetAt(0);
			
			org.json.JSONObject json = new org.json.JSONObject();

		    // Iterate through the rows.
			
			org.json.JSONArray rows = new org.json.JSONArray();
			
			Iterator<Row> rowsIT = sheet.rowIterator();
			rowsIT.next();
			ArrayList<String> keyList = new ArrayList<>();
			keyList.add("OCW Link");
			keyList.add("URL Hash");
			keyList.add("Link");
			keyList.add("Provider");
			keyList.add("Language");
			keyList.add("Tags");
			keyList.add("Author");
			keyList.add("Title");
			keyList.add("Description");
			keyList.add("Published");
			keyList.add("Indexed");
			keyList.add("Modified");
			keyList.add("Categories");
			
			
		    while(rowsIT.hasNext())
		    {
		        Row row = rowsIT.next();
		        JSONObject jRow = new JSONObject();

		        org.json.JSONArray cells = new org.json.JSONArray();
		        int i=0;
		        JSONObject jcell = new JSONObject();
		        for ( Iterator<Cell> cellsIT = row.cellIterator(); cellsIT.hasNext(); )
		        {
		            Cell cell = cellsIT.next();
		            if(i>=9 && i<=11)
		            	jcell.put(keyList.get(i++), cell.getNumericCellValue());
		            else
		            	jcell.put(keyList.get(i++), cell.getStringCellValue());
		        }
		        rows.put( jcell );
		    }
			
		    json.put( "rows", rows );
		    String jsonPrettyPrintString = json.toString(PRETTY_PRINT_INDENT_FACTOR);
			FileWriter fw = new FileWriter("D:\\ocw.json");
			fw.write(jsonPrettyPrintString.toString());
			fw.flush();
			fw.close();
		} catch (Exception je) {
			System.out.println(je.toString());
		}
	}

}
