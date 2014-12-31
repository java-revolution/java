package tool;

import java.util.Timer;

public class CheckerController {

	// CHECK_CYCLE
	private static final long CHECK_CYCLE_FILE = 5000;
	private static final long CHECK_CYCLE_RSS = 5000;
	// private static final long CHECK_CYCLE_DIR = 5000;
	// private static final long CHECK_CYCLE_VSS = 5000;

	// TIMER
	private Timer oFileCheckTimer;
	private Timer oRssCheckTimer;

	// 起動時／再開時実行処理
	public void start() {

		oFileCheckTimer = new Timer();
		oFileCheckTimer.schedule(new FileTask(), 0, CHECK_CYCLE_FILE);
		oRssCheckTimer = new Timer();
		oRssCheckTimer.schedule(new RssTask(), 0, CHECK_CYCLE_RSS);
	}

	// 終了時／一時停止時実行処理
	public void stop() {

		oFileCheckTimer.cancel();
		oFileCheckTimer = null;
		// oRssCheckTimer.cancel();
		// oRssCheckTimer = null;

	}
}
