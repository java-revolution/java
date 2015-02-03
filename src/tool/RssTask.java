package tool;

import java.awt.TrayIcon;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

import tool.checker.RssChecker;

public class RssTask extends TimerTask {
    static int BEFORE_FILE_SIZE[] = new int[Csv.CSVDATA.size()];
    static int AFTER_FILE_SIZE[] = new int[Csv.CSVDATA.size()];

    // 終了時ファイルへ出力し起動時ファイルから読み込む処理未実装
    private final static Map<String, String> UPDATE_HISTORY = new HashMap<String, String>();

    // 一定間隔で繰り返し行う処理
    public void run() {

        RssChecker oRssChecker = new RssChecker(UPDATE_HISTORY);
        // //
        // for (int i = 0; i < Csv.csvData.size(); i++) {
        // // Rssタイプの場合
        // if () {
        // CSVからURLを取得
        String sURL = "https://github.com/mimicman.atom";

        // チェック実行
        boolean isUpdate = false;
        try {
            isUpdate = oRssChecker.Check(sURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 更新があった場合
        if (isUpdate) {
            TaskTray.ICON.displayMessage("メッセージ", sURL + "が更新されました",
                    TrayIcon.MessageType.INFO);
        }
        // }
        // }
    }
}
