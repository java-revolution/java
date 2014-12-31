package tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Csv {
	//　監視対象を格納する配列
	static ArrayList<String> CSVDATA = new ArrayList<String>();
	// 設定ファイルのパス
	static String sCSV_FILE_PATH = "./res/data/test.csv";

	static void Csvload() {
		String filename = sCSV_FILE_PATH;
		File file = new File(filename);
		CSVDATA.clear();
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				String[] cols = line.split(",");
				for (String row : cols) {
					CSVDATA.add(row);
				}
			}
			System.out.println(CSVDATA);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
