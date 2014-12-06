package tool;

import java.awt.TrayIcon;
import java.io.File;
import java.util.Date;
import java.util.TimerTask;

public class Run extends TimerTask {
	static int Size;
	int FirstSize;
	// ブラウザ上から直接修正
	// エクリプス上から修正（エンコード変更）
	public void run() {
		
		File CheckFile = new File(Csv.dir.get(0));
		Size = (int)CheckFile.length();
		System.out.print(Size);
		System.out.println("タスク実行：" + new Date());
		
	//	for(int i = 0;i<5;i++){
	//		
	//	}
		
	            	if(FirstSize == 0){
	            	//	MainUpdateCheker.icon.displayMessage("メッセージ", "ファイルの大きさが0です", TrayIcon.MessageType.WARNING);
	            		FirstSize = Size;
	            	}
	            	
	            	if(FirstSize == Size){
	            	//	MainUpdateCheker.icon.displayMessage("メッセージ", "ファイルの大きさが同じです", TrayIcon.MessageType.WARNING);
	            		FirstSize = Size;
	            	}
	            	
	            	else if (FirstSize != Size) {
	            		MainUpdateCheker.icon.displayMessage("メッセージ", "ファイルが更新されました", TrayIcon.MessageType.INFO);
	            		FirstSize = Size;
	            	}
	            }					
	}

