package com.ml.dataQuality;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.json.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DQ_EdX {

	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	public static String TEST_XML_STRING =
			"<?xml version=\"1.0\" ?><test attrib=\"moretest\">Turn this to JSON</test>";

	public static void main(String[] args) {

		DQ_EdX edx = new DQ_EdX();
//		edx.getNewDataFromEdX();
		edx.checkQualityOfDataFromEdX();
	}

	public void checkQualityOfDataFromEdX() {

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
			
			
		}
		catch(Exception e ){e.printStackTrace();}
	}

	public void getNewDataFromEdX(){
		try {

			InputStream io = new FileInputStream(new File("D:\\rss.xml"));

			org.json.JSONObject xmlJSONObj = XML.toJSONObject(IOUtils.toString(io));

			String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
			System.out.println(jsonPrettyPrintString);
			FileWriter fw = new FileWriter("D:\\edx1.json");
			fw.write(jsonPrettyPrintString.toString());
			fw.flush();
			fw.close();
		} catch (Exception je) {
			System.out.println(je.toString());
		}
	}

}
