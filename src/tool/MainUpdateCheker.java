package tool;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
//通知テストコミット
//メインクラス

class MainUpdateCheker {
	static TrayIcon icon;
	// TASK_TYPE
	static TimerTask TASK_TYPE_FILE;
	static TimerTask TASK_TYPE_DIR;
	static TimerTask TASK_TYPE_RSS;
	static TimerTask TASK_TYPE_VSS;
	// CHECK_CYCLE
	private static final long CHECK_CYCLE_FILE = 5000;
	private static final long CHECK_CYCLE_DIR = 5000;
	private static final long CHECK_CYCLE_RSS = 5000;
	private static final long CHECK_CYCLE_VSS = 5000;
	static Timer timer;

	public static void main(String args[]) throws InterruptedException {
		try {
			Csv.Csvload();
			// メニューアイテムの作成
			MenuItem watchingItem = new MenuItem("監視対象一覧");
			final MenuItem stopItem = new MenuItem("一時停止");
			final MenuItem restartItem = new MenuItem("再開");
			MenuItem exitItem = new MenuItem("終了");
			restartItem.setEnabled(false);

			// アイコンの作成
			SystemTray tray = SystemTray.getSystemTray();
			icon = new TrayIcon(ImageIO.read(new File("res/pic/tray_icon.png")));
			tray.add(icon);

			// 監視対象一覧
			watchingItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UpdateChekerFrame.watching();
				}
			});

			// 一時停止メニュー
			stopItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					icon.displayMessage("お知らせ", "一時停止しました", MessageType.INFO);
					System.out.println("一時停止します");
					TASK_TYPE_FILE.cancel();
					TASK_TYPE_FILE = null;
					stopItem.setEnabled(false);
					restartItem.setEnabled(true);
				}
			});

			// 再開
			restartItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					icon.displayMessage("お知らせ", "再開しました", MessageType.INFO);
					if (TASK_TYPE_FILE == null) {
						TASK_TYPE_FILE = new Run();
					}
					System.out.println("再開します");
					timer.schedule(TASK_TYPE_FILE, 0, CHECK_CYCLE_FILE);
					stopItem.setEnabled(true);
					restartItem.setEnabled(false);
				}
			});

			// 終了メニュー
			exitItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});

			// ポップアップメニュー
			PopupMenu menu = new PopupMenu();

			// メニューにアイテムを追加
			menu.add(stopItem);
			menu.add(restartItem);
			menu.add(exitItem);
			menu.add(watchingItem);

			icon.setPopupMenu(menu);

		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (AWTException ex) {
			ex.printStackTrace();
		}
		MainUpdateCheker main = new MainUpdateCheker();
		main.timerperiod();
	}

	void timerperiod() throws InterruptedException {

		TASK_TYPE_FILE = new Run();
		timer = new Timer();

		timer.schedule(TASK_TYPE_FILE, 0, CHECK_CYCLE_FILE);
	}
}
