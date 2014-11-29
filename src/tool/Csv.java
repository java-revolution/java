package tool;
//テスト変更
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Csv {
	
	/** プロジェクトルートからのパス */
	private static final String sCSV_FILE_PATH = "./res/data/test.csv";
	
	static ArrayList<String> dir = new ArrayList<String>();
	static ArrayList<String[]> csvData = new ArrayList<String[]>();
	
	static ArrayList<String> Csvload(){
		String filename = sCSV_FILE_PATH;
		
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
				// 読み込みデータの表示
				for(String[]row:csvData){
					int i = 0;
					dir.add(row[0]);
					System.out.println("　: " + row[0]);
					System.out.println("　: " + row[1]);
					System.out.println("　: " + row[2]);
					System.out.println();
				
				}
			} catch(Exception e) {
				e.printStackTrace();	
			}
		return dir;
	}
}


