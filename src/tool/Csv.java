package tool;
//テスト変更
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Csv {
	static ArrayList<String> dir = new ArrayList<String>();
	static ArrayList<String[]> csvData = new ArrayList();
	
	static ArrayList<String> Csvload(){
		String filename = "test.csv";
		
		File file = new File(filename);
		try {
				FileInputStream fis = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(fis);
				BufferedReader br = new BufferedReader(isr);
				String line;
		 
				while ( ( line = br.readLine()) != null ) {
					String[] cols = line.split(",");
					csvData.add(cols);
				}
			// 隱ｭ縺ｿ霎ｼ縺ｿ繝�繝ｼ繧ｿ縺ｮ陦ｨ遉ｺ
				for(String[]row:csvData){
					int i = 0;
					dir.add(row[0]);
					System.out.println("縲�: " + row[0]);
					System.out.println("縲�: " + row[1]);
					System.out.println("縲�: " + row[2]);
					System.out.println();
				
				}
			} catch(Exception e) {
				e.printStackTrace();	
			}
		return dir;
	}
}


