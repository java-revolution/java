package tool;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

public class UpdateChekerFrame extends JFrame{
	static void watching(){
		UpdateChekerFrame frame = new UpdateChekerFrame("監視対象管理");
		frame.setVisible(true);
	}
	
	UpdateChekerFrame(String title){
		String[][] tabledata = {
			    {Csv.Csvload().get(0), "test", "txt", "�Ď���"},
			    {"", "", "", ""},
			    {"", "", "", ""},
			    {"", "", "", ""}};
		String[] columnNames = {"監視", "対象", "タイプ", "状態"};
		
		JTable table = new JTable(tabledata,columnNames);
		setBounds(600, 300, 400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.add(table,BorderLayout.NORTH);
		
		//ImageIcon icon = new ImageIcon("./icon.png");
	    //setIconImage(icon.getImage());
	}
}
