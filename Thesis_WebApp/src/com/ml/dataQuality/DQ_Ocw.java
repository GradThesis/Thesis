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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DQ_Ocw {

	public static int PRETTY_PRINT_INDENT_FACTOR = 4;

	public static void main(String[] args) {

		DQ_Ocw ocw = new DQ_Ocw();
		//ocw.getNewDataFromOCW();
		ocw.checkQualityOfDataFromOCW();
	}

	public void checkQualityOfDataFromOCW() {
		
		/*
		 * Loading new JSON file into HashMap
		 * 
		*/
		
		try{
			JSONParser parser = new JSONParser(); 

			Object obj= parser.parse(new FileReader("D:\\ocw.json"));

			org.json.simple.JSONObject inner = (org.json.simple.JSONObject) obj;
			org.json.simple.JSONArray jArrayForElements = (org.json.simple.JSONArray)inner.get("rows");
			Map<String,ArrayList<String>> courseMap = new HashMap<String,ArrayList<String>>();
			
			int count=0;
			
			for(Object o: jArrayForElements){
				JSONObject course = (JSONObject) o;

				String courseID = course.get("URL Hash").toString();
				courseMap.put(courseID, null);
				ArrayList<String> value = new ArrayList<String>();
				
				String name = (String) course.get("Title");
				value.add(name);
				courseMap.put(courseID, value);

				String shortDescription = (String) course.get("Description");
				value.add(shortDescription);
				courseMap.put(courseID, value);
				
				String language = (String) course.get("Language");
				value.add(language);
				courseMap.put(courseID, value);
				
				String Provider = (String) course.get("Provider");
				value.add(Provider);
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
			
			
			Object obj= parser.parse(new FileReader("D:\\ocw.json"));
			JSONObject inner = (JSONObject) obj;
			JSONArray jArrayForElements = (JSONArray)inner.get("rows");

			/*
			 *  For each old JSON element, compare it with the new JSON file which is already loaded. 
			*/

			for(Object o: jArrayForElements){
				JSONObject course = (JSONObject) o;
				Map<String,String> courseDetailsChangeMap = new HashMap<String,String>();
				String courseID = course.get("URL Hash").toString();
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
					String name = (String) course.get("Title");
					if(courseMap.get(courseID).get(arrayListIterator++).equals(name)){}
					else{
						countOfCoursesWithDetailsChanged++;
						courseDetailsChangeMap.put("Title", courseMap.get(courseID).get(arrayListIterator-1));
					}
					String shortDescription = (String) course.get("Description");
					if(courseMap.get(courseID).get(arrayListIterator++).equals(shortDescription)){}
					else{
						countOfCoursesWithDetailsChanged++;
						courseDetailsChangeMap.put("Description", courseMap.get(courseID).get(arrayListIterator-1));
					}
					String language = (String) course.get("Language");
					if(courseMap.get(courseID).get(arrayListIterator++).equals(language)){}
					else{
						countOfCoursesWithDetailsChanged++;
						courseDetailsChangeMap.put("Language", courseMap.get(courseID).get(arrayListIterator-1));
					}
					String Provider = (String) course.get("Provider");
					if(courseMap.get(courseID).get(arrayListIterator++).equals(Provider)){}
					else{
						countOfCoursesWithDetailsChanged++;
						courseDetailsChangeMap.put("Provider", courseMap.get(courseID).get(arrayListIterator-1));
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
	private static void changeOriginalJSONAccToScore(Map<String, Map<String, String>> mapForChangesToBeMadeInOriginalJson, int score) {

		try{
			JSONParser parser = new JSONParser(); 

			/*  Now, update the original JSON file with the changes.
			 * 
			*/
			
			
			Object obj= parser.parse(new FileReader("D:\\ocw.json"));
			JSONObject inner = (JSONObject) obj;
			JSONArray jArrayForElements = (JSONArray)inner.get("rows");
			int locationCount = 0;
			ArrayList<Integer> locationList = new ArrayList<Integer>();
			Map<String,String> dummyMap = new HashMap<>();
			dummyMap.put("dummy", "dummy");
			for(Object o: jArrayForElements){
				JSONObject course = (JSONObject) o;
				String courseID = course.get("URL Hash").toString();

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
			inner.put("rows", finalJsonArray);
			FileWriter fw = new FileWriter("D:\\ocw_updated.json");
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
