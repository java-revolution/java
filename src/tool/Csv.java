package tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Csv {
	static ArrayList<String> dir = new ArrayList<String>();
	static ArrayList<String> csvData = new ArrayList<String>();
	//一時的に追加
	static String sCSV_FILE_PATH = "./res/data/test.csv";
	
	static void Csvload(){
		String filename = sCSV_FILE_PATH;
		File file = new File(filename);
		dir.clear();
		
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			String line;
	 
			while ( ( line = br.readLine()) != null ) {
				String[] cols = line.split(",");
				for(String row:cols){
					csvData.add(row);
				}
			}
			System.out.println(csvData);
			br.close();
			
		} catch(Exception e) {
			e.printStackTrace();	
		}
	}
}


