package tool.checker;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class RssChecker implements Checker {

	private final static DateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");

	private Map<String, String> oUpdateHistory;

	/**
	 * Constructor
	 * 
	 * @param oUpdateHistory
	 */
	public RssChecker(Map<String, String> oUpdateHistory) {
		super();
		this.oUpdateHistory = oUpdateHistory;
	}

	@Override
	public boolean Check(String sURL) throws Exception {
		// sURL = "https://github.com/mimicman.atom";

		// ******************************
		// RSS読み込み
		// ******************************
		URL feedUrl = new URL(sURL);
		SyndFeed feed = new SyndFeedInput().build(new XmlReader(feedUrl));

		Date newUpdate = null;
		for (Object obj : feed.getEntries()) {
			SyndEntry entry = (SyndEntry) obj;
			// // 記事のタイトル
			// System.out.println(entry.getTitle());

			if (newUpdate == null
					|| newUpdate.compareTo(entry.getPublishedDate()) < 0) {
				newUpdate = entry.getPublishedDate();
			}

			// // 記事本文のURI
			// System.out.println(entry.getUri());
			// // 記事の抜粋
			// System.out.println(entry.getDescription());
		}

		// ******************************
		// 更新有無確認
		// ******************************
		boolean isUpdate = false;
		if (newUpdate == null) {

			System.out.println(sURL + "の情報を取得できませんでした。");
		} else {

			String sDate = oUpdateHistory.get(sURL);
			Calendar newCalendar = Calendar.getInstance();
			newCalendar.setTime(newUpdate);
			if (sDate == null) {

				System.out.println("初回読み込み");
				oUpdateHistory.put(sURL, SDF.format(newUpdate));
			} else {

				String sUpdateDate = SDF.format(newUpdate);

				// 更新があった場合
				if (new BigDecimal(sDate)
						.compareTo(new BigDecimal(sUpdateDate)) < 0) {

					System.out.println("更新がありました。");
					oUpdateHistory.put(sURL, sUpdateDate);
					isUpdate = true;
				}
			}
		}
		return isUpdate;
	}
}