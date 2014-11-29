package tool;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class UpdateChekerFrame extends JFrame{
	static void watching(){
		UpdateChekerFrame frame = new UpdateChekerFrame("監視対象管理");
		frame.setVisible(true);
	}
	
	UpdateChekerFrame(String title){
		String[][] tabledata = {
			    {Csv.Csvload().get(0), Csv.Csvload().get(2), "txt", "監視中"},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""}};
		String[] columnNames = {"名前", "対象", "タイプ", "状態"};
		
		JTable table = new JTable(tabledata,columnNames);
		
		JScrollPane sp = new JScrollPane(table);
	    sp.setPreferredSize(new Dimension(250, 90));
		
		setBounds(600, 300, 400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.add(sp,BorderLayout.CENTER);
		
		//ImageIcon icon = new ImageIcon("./icon.png");
	    //setIconImage(icon.getImage());
	}
}
