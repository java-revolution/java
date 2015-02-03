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

import javax.imageio.ImageIO;

public class TaskTray {
    // private static Timer TIMER;
    static TrayIcon ICON;
    // タスクトレイに表示する画像
    private static String ICON_PATH = "res/pic/tray_icon.png";
    // // TASK_TYPE
    // static TimerTask TASK_TYPE_FILE;
    // static TimerTask TASK_TYPE_DIR;
    // static TimerTask TASK_TYPE_RSS;
    // static TimerTask TASK_TYPE_VSS;
    // // CHECK_CYCLE
    // private static final long CHECK_CYCLE_FILE = 5000;
    // private static final long CHECK_CYCLE_DIR = 5000;
    // private static final long CHECK_CYCLE_RSS = 5000;
    // private static final long CHECK_CYCLE_VSS = 5000;
    final CheckerController oCheckerController = new CheckerController();

    void TaskTrayMake() throws InterruptedException {
        try {
            // 監視対象の読み込み
            Csv.Csvload();
            // メニューアイテムの作成
            MenuItem watchingItem = new MenuItem("監視対象一覧");
            final MenuItem stopItem = new MenuItem("一時停止");
            final MenuItem restartItem = new MenuItem("再開");
            MenuItem exitItem = new MenuItem("終了");
            restartItem.setEnabled(false);
            // アイコンの作成
            SystemTray tray = SystemTray.getSystemTray();
            ICON = new TrayIcon(ImageIO.read(new File(ICON_PATH)));
            tray.add(ICON);
            // 監視対象一覧
            watchingItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    UpdateChekerFrame.watching();
                }
            });
            // 一時停止メニュー
            stopItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ICON.displayMessage("お知らせ", "一時停止しました", MessageType.INFO);
                    System.out.println("一時停止します");
                    // TASK_TYPE_FILE.cancel();
                    // TASK_TYPE_FILE = null;
                    oCheckerController.stop();
                    stopItem.setEnabled(false);
                    restartItem.setEnabled(true);
                }
            });
            // 再開
            restartItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ICON.displayMessage("お知らせ", "再開しました", MessageType.INFO);
                    // // if (TASK_TYPE_FILE == null) {
                    // TASK_TYPE_FILE = new Run();
                    // }
                    System.out.println("再開します");
                    // // TIMER.schedule(TASK_TYPE_FILE, 0, CHECK_CYCLE_FILE);
                    oCheckerController.start();
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

            ICON.setPopupMenu(menu);

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
        // // TaskTray TT = new TaskTray();
        // TT.timerperiod();
        oCheckerController.start();
    }

    // void timerperiod() throws InterruptedException {
    //
    // TaskTray.TASK_TYPE_FILE = new Run();
    // TIMER = new Timer();
    //
    // TIMER.schedule(TaskTray.TASK_TYPE_FILE, 0, TaskTray.CHECK_CYCLE_FILE);
    // }
}
