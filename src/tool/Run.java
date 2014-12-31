package tool;

import java.awt.TrayIcon;
import java.io.File;
import java.util.Date;
import java.util.TimerTask;

public class Run extends TimerTask {
	//　ファイルサイズを格納する配列
	static int BEFORE_FILE_SIZE[] = new int[Csv.CSVDATA.size()];
	static int AFTER_FILE_SIZE[] = new int[Csv.CSVDATA.size()];
	// 一定間隔で繰り返し行う処理
	public void run() {
		for (int i = 0; i < Csv.CSVDATA.size(); i++) {
			File CHECKFILE = new File(Csv.CSVDATA.get(i));
			BEFORE_FILE_SIZE[i] = ((int) CHECKFILE.length());
			System.out.println("ファイルサイズ" + i + "番目：" + BEFORE_FILE_SIZE[i]
					+ "byte");
			System.out.println("タスク実行：" + new Date());

			if (BEFORE_FILE_SIZE[i] == 0) {
				// TaskTray.ICON.displayMessage("メッセージ", "ファイルの大きさが0です",
				// TrayIcon.MessageType.WARNING);
				AFTER_FILE_SIZE[i] = BEFORE_FILE_SIZE[i];
			}

			if (AFTER_FILE_SIZE[i] == BEFORE_FILE_SIZE[i]) {
				// TaskTray.ICON.displayMessage("メッセージ", "ファイルの大きさが同じです",
				// TrayIcon.MessageType.WARNING);
				AFTER_FILE_SIZE[i] = BEFORE_FILE_SIZE[i];
			}

			else if (AFTER_FILE_SIZE[i] != BEFORE_FILE_SIZE[i]) {
				TaskTray.ICON.displayMessage("メッセージ", Csv.CSVDATA.get(i)
						+ "が更新されました", TrayIcon.MessageType.INFO);
				AFTER_FILE_SIZE[i] = BEFORE_FILE_SIZE[i];
			} else {
				System.out.print("ファイルサイズ取得失敗");
			}
		}
	}
}
