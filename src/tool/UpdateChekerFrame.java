package tool;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UpdateChekerFrame extends JFrame {
    // テーブルの見出し設定delArray
    private static String[] COLUMN_NAMES = { "チェック", "対象", "タイプ", "状態" };
    private static DefaultTableModel TABLEMODEL = new DefaultTableModel(
        COLUMN_NAMES, Csv.CSVDATA.size());
    private static ArrayList<String> DELARRAY = new ArrayList<String>();

    static void watching() {
        UpdateChekerFrame frame = new UpdateChekerFrame("監視対象管理");
        frame.setVisible(true);
    }

    UpdateChekerFrame(String title) {
        UpdateChekerTable table = new UpdateChekerTable(TABLEMODEL);
        for (int i = 0; i < Csv.CSVDATA.size(); i++) {
            // ファイル拡張子取得
            int point = Csv.CSVDATA.get(i).lastIndexOf(".");
            String SUFFIX = null;
            if (point != -1) {
                SUFFIX = Csv.CSVDATA.get(i).substring(point + 1);
            }
            TABLEMODEL.setValueAt(SUFFIX, i, 2);
            TABLEMODEL.setValueAt(Csv.CSVDATA.get(i), i, 1);
            TABLEMODEL.setValueAt(true, i, 0);
        }
        // スクロールバー作成
        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(600, 250));
        // ボタン作成
        JButton addbutton = new JButton("追加");
        JButton delbutton = new JButton("削除");
        // 追加ボタン処理(再読み込み処理必須)
        ActionListener aladd = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser filechooser = new JFileChooser();
                int selected = filechooser.showOpenDialog(null);
                if (selected == JFileChooser.APPROVE_OPTION) {
                    File file = filechooser.getSelectedFile();
                    System.out.println(file.getAbsolutePath());
                    try {
                        FileWriter fw = new FileWriter(Csv.sCSV_FILE_PATH, true);
                        PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
                        pw.print(file.getAbsolutePath());
                        pw.print(",");
                        pw.println();
                        pw.close();
                    } catch (IOException ex) {
                        // 例外時処理
                        ex.printStackTrace();
                    }
                }
                // 設定ファイル再読み込み
                Csv.Csvload();
                FileTask.BEFORE_FILE_SIZE = new int[Csv.CSVDATA.size()];
                FileTask.AFTER_FILE_SIZE = new int[Csv.CSVDATA.size()];
                TABLEMODEL = new DefaultTableModel(COLUMN_NAMES, Csv.CSVDATA.size());
                for (int i = 0; i < Csv.CSVDATA.size(); i++) {
                 // ファイル拡張子取得
                    int point = Csv.CSVDATA.get(i).lastIndexOf(".");
                    String SUFFIX = null;
                    if (point != -1) {
                        SUFFIX = Csv.CSVDATA.get(i).substring(point + 1);
                    }
                    TABLEMODEL.setValueAt(SUFFIX, i, 2);
                    TABLEMODEL.setValueAt(Csv.CSVDATA.get(i), i, 1);
                    TABLEMODEL.setValueAt(true, i, 0);
                }
            }
        };
        // 削除ボタン処理(再読み込み処理必須)
        ActionListener aldel = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < Csv.CSVDATA.size(); i++) {
                    System.out.println(TABLEMODEL.getValueAt(i, 0));
                    String TableBoolean = TABLEMODEL.getValueAt(i, 0).toString();
                    if (TableBoolean.equals("false")) {
                        DELARRAY.add(TABLEMODEL.getValueAt(i, 1).toString());
                    }
                    try {
                        FileWriter fw = new FileWriter(Csv.sCSV_FILE_PATH,
                                false);
                        PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
                        for (int j = 0; j < DELARRAY.size(); j++) {
                            pw.print(DELARRAY.get(j));
                            pw.print(",");
                        }
                        pw.println();
                        pw.close();
                    } catch (IOException ex) {
                        // 例外時処理
                        ex.printStackTrace();
                    }
                }
                // 設定ファイル再読み込み
                Csv.Csvload();
                FileTask.BEFORE_FILE_SIZE = new int[Csv.CSVDATA.size()];
                FileTask.AFTER_FILE_SIZE = new int[Csv.CSVDATA.size()];
                TABLEMODEL = new DefaultTableModel(COLUMN_NAMES, Csv.CSVDATA.size());
                for (int i = 0; i < Csv.CSVDATA.size(); i++) {
                 // ファイル拡張子取得
                    int point = Csv.CSVDATA.get(i).lastIndexOf(".");
                    String SUFFIX = null;
                    if (point != -1) {
                        SUFFIX = Csv.CSVDATA.get(i).substring(point + 1);
                    }
                    TABLEMODEL.setValueAt(SUFFIX, i, 2);
                    TABLEMODEL.setValueAt(Csv.CSVDATA.get(i), i, 1);
                    TABLEMODEL.setValueAt(true, i, 0);
                }
            }
        };
        addbutton.addActionListener(aladd);
        delbutton.addActionListener(aldel);
        // フレーム表示位置
        setBounds(600, 300, 600, 600);
        // ×を押した時の処理
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // パネル作成
        Panel contentPane1 = new Panel();
        Panel contentPane2 = new Panel();
        // パネルのレイアウト
        setLayout(new GridLayout(2, 1));
        add(contentPane1);
        add(contentPane2);
        contentPane1.setLayout(new FlowLayout());
        contentPane2.setLayout(new FlowLayout());
        // ボタン、テーブルの配置
        contentPane1.add(sp);
        contentPane2.add(delbutton);
        contentPane2.add(addbutton);
    }
}
