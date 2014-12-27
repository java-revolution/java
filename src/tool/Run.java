package tool;

import java.awt.TrayIcon;
import java.io.File;
import java.util.Date;
import java.util.TimerTask;

public class Run extends TimerTask {
	//一定間隔で繰り返し行う処理
	public void run() {
		for(int i = 0;i < Csv.csvData.size();i++){
			int Size[] = new int[Csv.csvData.size()];
			int FirstSize[] = new int[Csv.csvData.size()];
		
			File CheckFile = new File(Csv.csvData.get(i));
			Size[i]=((int)CheckFile.length());
			System.out.print("ファイルサイズ"+i+"："+Size[i]);
			System.out.println("タスク実行：" + new Date());
		
	        if(FirstSize[i] == 0){
	        //	MainUpdateCheker.icon.displayMessage("メッセージ", "ファイルの大きさが0です", TrayIcon.MessageType.WARNING);
	        	FirstSize[i]=Size[i];
	        }
	            	
	        if(FirstSize[i] == Size[i]){
	        //	MainUpdateCheker.icon.displayMessage("メッセージ", "ファイルの大きさが同じです", TrayIcon.MessageType.WARNING);
	        	FirstSize[i]=Size[i];
	        }
	            	
	        else if (FirstSize[i] != Size[i]) {
	        	TaskTray.ICON.displayMessage("メッセージ", "ファイルが更新されました", TrayIcon.MessageType.INFO);
	        	FirstSize[i]=Size[i];
	        }
	        else{
	        	System.out.print("ファイルサイズ取得失敗");
	        }
		}
	}
}

