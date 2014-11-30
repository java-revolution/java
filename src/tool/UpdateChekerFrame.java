package tool;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class UpdateChekerFrame extends JFrame{
	static void watching(){
		UpdateChekerFrame frame = new UpdateChekerFrame("監視対象管理");
		frame.setVisible(true);
	}
	
	UpdateChekerFrame(String title){
		//テーブルデータ作成
		String[][] tabledata = {
			    {Csv.Csvload().get(0), Csv.Csvload().get(2), "txt", "監視中"},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""}
		};
		String[] columnNames = {"名前", "対象", "タイプ", "状態"};
		
		JTable table = new JTable(tabledata,columnNames);
		
		//スクロールバー作成
		JScrollPane sp = new JScrollPane(table);
	    sp.setPreferredSize(new Dimension(250, 90));
	    
	    //ボタン作成
	    JButton addbutton = new JButton("追加");
	    JButton delbutton = new JButton("削除");
	    
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
