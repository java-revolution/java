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

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class UpdateChekerFrame extends JFrame{
	static String[] columnNames = {"チェック", "対象", "タイプ", "状態"};
	static DefaultTableModel model = new DefaultTableModel(columnNames, Csv.csvData.size());
	
	static void watching(){
		UpdateChekerFrame frame = new UpdateChekerFrame("監視対象管理");
		frame.setVisible(true);
	}
	
	UpdateChekerFrame(String title){
		
		UpdateChekerTable table = new UpdateChekerTable(model);
		
		for(int i=0; i < Csv.csvData.size(); i++){
		model.setValueAt(Csv.csvData.get(i),i,1);
		model.setValueAt(true,i,0);
		}		
		
		//スクロールバー作成
		JScrollPane sp = new JScrollPane(table);
	    sp.setPreferredSize(new Dimension(250, 90));
	    
	    //ボタン作成
	    JButton addbutton = new JButton("追加");
	    JButton delbutton = new JButton("削除");
	    
	    //追加ボタン処理
	    ActionListener aladd = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				int selected = filechooser.showOpenDialog(null);
				if(selected == JFileChooser.APPROVE_OPTION){
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
						//例外時処理
						ex.printStackTrace();
			        }
				}
			}
		};
		
		//削除ボタン処理（未完成）
		//メモ：ファイル名を配列に格納しすべてを再書き込み
		ActionListener aldel = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for(int i=0;i < Csv.csvData.size() ;i++){
					System.out.println(model.getValueAt(i,0));
					String TableBoolean = model.getValueAt(i,0).toString();
					if(TableBoolean.equals("false")){
						try {
							FileWriter fw = new FileWriter(Csv.sCSV_FILE_PATH, false);
				            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
				            
				            pw.print(model.getValueAt(i,1));
				            pw.print(",");
				            pw.println();
				            pw.close();
				            
						} catch (IOException ex) {
							//例外時処理
							ex.printStackTrace();
				        }
					}
				}
			}
		};
		  
	    addbutton.addActionListener(aladd);
	    delbutton.addActionListener(aldel);
	    
		//フレーム表示位置
		setBounds(600, 300, 400, 400);
		//×を押した時の処理
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//パネル作成
		Panel contentPane1 = new Panel();
		Panel contentPane2 = new Panel();
		//パネルのレイアウト
		setLayout(new GridLayout(2, 1));
        add(contentPane1);
        add(contentPane2);
        
        contentPane1.setLayout(new FlowLayout());
        contentPane2.setLayout(new FlowLayout());
        
        //ボタン、テーブルの配置
        contentPane1.add(sp);
        contentPane2.add(delbutton);
        contentPane2.add(addbutton);

	}
}
