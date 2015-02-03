package tool.checker;

public class TestRSS {
    public static void main(String[] sArgs) throws Exception {

        for (int i = 0; true;) {

            new RssChecker().Check("");
            try {
                Thread.sleep(60 * 1000 * 1); // 3000ミリ秒Sleepする
            } catch (InterruptedException e) {
            }
        }
    }
}
