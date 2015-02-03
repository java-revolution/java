package tool;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Test {
    public static void main(String srgs[]) {
        // System.out.println(Csv.Csvload());
        try {
            FileWriter fw = new FileWriter(Csv.sCSV_FILE_PATH, false);
            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

            pw.print(1);
            pw.print(",");
            pw.println();
            pw.close();

        } catch (IOException ex) {
            // 例外時処理
            ex.printStackTrace();
        }
    }

}
