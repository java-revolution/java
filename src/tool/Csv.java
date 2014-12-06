package tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Csv {
	static ArrayList<String> dir = new ArrayList<String>();
	static ArrayList<String[]> csvData = new ArrayList<String[]>();
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
				csvData.add(cols);
			}
			br.close();
			// 読み込みデータの表示
			// ******************************
			// １．csvDataの数だけ繰り返し
			// ******************************
			for(String[]row:csvData){
				// ******************************
				// ２．csvDataの数だけ繰り返し
				// ******************************
				// １．と２．で２回繰り返しているため、csvDataの数×csvDataの数分繰り返している。
				for(int i = 0;i < csvData.size();i++){
					dir.add(row[i]);
					//dir.add(row[1]);
					//System.out.println("　: " + row[0]);
					//System.out.println("　: " + row[1]);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();	
		}
	}
}


