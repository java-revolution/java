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
import javax.swing.table.DefaultTableModel;

public class UpdateChekerFrame extends JFrame {
	// テーブルの見出し設定delArray
	private static String[] COLUMN_NAMES = { "チェック", "対象", "タイプ", "状態" };
	private static DefaultTableModel MODEL = new DefaultTableModel(
			COLUMN_NAMES, Csv.CSVDATA.size());
	private static ArrayList<String> DELARRAY = new ArrayList<String>();

	static void watching() {
		UpdateChekerFrame frame = new UpdateChekerFrame("監視対象管理");
		frame.setVisible(true);
	}

	UpdateChekerFrame(String title) {
		UpdateChekerTable table = new UpdateChekerTable(MODEL);
		for (int i = 0; i < Csv.CSVDATA.size(); i++) {
			MODEL.setValueAt(Csv.CSVDATA.get(i), i, 1);
			MODEL.setValueAt(true, i, 0);
		}
		// スクロールバー作成
		JScrollPane sp = new JScrollPane(table);
		sp.setPreferredSize(new Dimension(250, 90));
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
				MODEL = new DefaultTableModel(COLUMN_NAMES, Csv.CSVDATA.size());
				for (int i = 0; i < Csv.CSVDATA.size(); i++) {
					MODEL.setValueAt(Csv.CSVDATA.get(i), i, 1);
					MODEL.setValueAt(true, i, 0);
				}
			}
		};
		// 削除ボタン処理(再読み込み処理必須)
		ActionListener aldel = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < Csv.CSVDATA.size(); i++) {
					System.out.println(MODEL.getValueAt(i, 0));
					String TableBoolean = MODEL.getValueAt(i, 0).toString();
					if (TableBoolean.equals("false")) {
						DELARRAY.add(MODEL.getValueAt(i, 1).toString());
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
				MODEL = new DefaultTableModel(COLUMN_NAMES, Csv.CSVDATA.size());
				for (int i = 0; i < Csv.CSVDATA.size(); i++) {
					MODEL.setValueAt(Csv.CSVDATA.get(i), i, 1);
					MODEL.setValueAt(true, i, 0);
				}
			}
		};
		addbutton.addActionListener(aladd);
		delbutton.addActionListener(aldel);
		// フレーム表示位置
		setBounds(600, 300, 400, 400);
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
