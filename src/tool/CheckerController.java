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

	public void start() {

		oFileCheckTimer = new Timer();
		oFileCheckTimer.schedule(new FileTask(), 0, CHECK_CYCLE_FILE);
		oRssCheckTimer = new Timer();
		oRssCheckTimer.schedule(new RssTask(), 0, CHECK_CYCLE_RSS);
	}

	public void stop() {

		oFileCheckTimer.cancel();
		oFileCheckTimer = null;
		// oRssCheckTimer.cancel();
		// oRssCheckTimer = null;

	}
}
