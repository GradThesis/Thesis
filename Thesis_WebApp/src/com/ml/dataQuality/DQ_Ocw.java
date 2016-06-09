package com.ml.dataQuality;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
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

	public static void main(String[] args) throws Exception {

		
		DQ_Ocw ocw = new DQ_Ocw();
		ocw.getNewDataFromOCW();
		//ocw.makeChangesToJSONForEvaluation();
		//ocw.checkQualityOfDataFromOCW();
		
	}


	@SuppressWarnings("unchecked")
	private void makeChangesToJSONForEvaluation() throws Exception{


		JSONParser parser = new JSONParser(); 
		Object obj= parser.parse(new FileReader("D:\\Project\\OCW\\ocw.json"));
		JSONObject inner = (JSONObject) obj;
		JSONArray jArrayForElements = (JSONArray)inner.get("rows");
		double count=0;
		for(Object o: jArrayForElements){

			if(Math.random() < 0.9){
				count++;
				JSONObject course = (JSONObject) o;

				course.put("Title", "This is a change in Title for course ID"+ course.get("URL Hash").toString());
				course.put("Description", "<div>This course gives a broad overview of contraceptive methods and explores issues that influence contraceptive choices today. <\\//div>\n<div><\\/div>\\n<div>We will discuss the mechanism of action, effectiveness, risk/benefit, side effects and contraindications for each contraceptive method, as well as ask some questions about contraceptive decision making. What are some of the factors that influence contraception use and decision making?   Are there specific cultural, ethnic, social and environmental factors?  We will also look at the relationship between contraception use and risk of acquiring Sexually Transmitted Infections (STIs). <\\/div>\\n<div><\\/div>"+ course.get("URL Hash").toString());
				course.put("Language", "<div>This course gives a broad overview of contraceptive methods and explores issues that influence contraceptive choices today. <\\//div>\n<div><\\/div>\\n<div>We will discuss the mechanism of action, effectiveness, risk/benefit, side effects and contraindications for each contraceptive method, as well as ask some questions about contraceptive decision making. What are some of the factors that influence contraception use and decision making?   Are there specific cultural, ethnic, social and environmental factors?  We will also look at the relationship between contraception use and risk of acquiring Sexually Transmitted Infections (STIs). <\\/div>\\n<div><\\/div>"+ course.get("URL Hash").toString());
				course.put("Provider", "<div>This course gives a broad overview of contraceptive methods and explores issues that influence contraceptive choices today. <\\//div>\n<div><\\/div>\\n<div>We will discuss the mechanism of action, effectiveness, risk/benefit, side effects and contraindications for each contraceptive method, as well as ask some questions about contraceptive decision making. What are some of the factors that influence contraception use and decision making?   Are there specific cultural, ethnic, social and environmental factors?  We will also look at the relationship between contraception use and risk of acquiring Sexually Transmitted Infections (STIs). <\\/div>\\n<div><\\/div>"+ course.get("URL Hash").toString());
			}
		}
		System.out.println(count);

		inner.put("elements", jArrayForElements);
		FileWriter fw = new FileWriter("D:\\Project\\OCW\\ocw_90.json");
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

	public void checkQualityOfDataFromOCW() {

		/*
		 * Loading new JSON file into HashMap
		 * 
		 */

		try{
			JSONParser parser = new JSONParser(); 

			Object obj= parser.parse(new FileReader("D:\\Project\\OCW\\ocw_90.json"));

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


			Object obj= parser.parse(new FileReader("D:\\Project\\OCW\\ocw.json"));
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


			Object obj= parser.parse(new FileReader("D:\\Project\\OCW\\ocw.json"));
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
			FileWriter fw = new FileWriter("D:\\Project\\OCW\\ocw_90_changed.json");
			fw.write(inner.toString());
			fw.flush();
			fw.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	@SuppressWarnings({ "unchecked", "static-access" })
	public void getNewDataFromOCW(){
		try {
			Thread t = null;
			URL link = new URL("http://data.oeconsortium.org/dbdump/ocw-courses.xls");
			InputStream in = new BufferedInputStream(link.openStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			while (-1!=(n=in.read(buf)))
			{
				out.write(buf, 0, n);
			}
			out.close();
			in.close();
			long startTime = System.currentTimeMillis();
			byte[] response = out.toByteArray();
			FileOutputStream fos = new FileOutputStream("D:\\Project\\OCW\\ocw.json");
			fos.write(response);
			fos.flush();
			fos.close();


			FileInputStream io = new FileInputStream(new File("D:\\Project\\OCW\\ocw.json"));

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
			FileWriter fw = new FileWriter("D:\\Project\\OCW\\ocw.json");
			fw.write(jsonPrettyPrintString.toString());
			fw.flush();
			fw.close();
			t.sleep(3500);
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("\n\n"+totalTime);
		} catch (Exception je) {
			System.out.println(je.toString());
		}
	}

}
