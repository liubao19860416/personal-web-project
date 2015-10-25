package time;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatetimeUtils {

    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final Logger logger = LoggerFactory.getLogger(DatetimeUtils.class);

    private static long ONE_DAY_IN_MILISECONDS = 24 * 60 * 60 * 1000;

    public static Timestamp afterOneWeek(Timestamp datetime) {

        if (datetime == null) {
            throw new IllegalArgumentException("Argument datetime is null!!");
        }

        Calendar c = Calendar.getInstance();
        c.setTime(datetime);
        c.add(Calendar.WEEK_OF_YEAR, 1);
        return new Timestamp(c.getTime().getTime());
    }

    /**
     * 当前日期加减n天后的日期，返回String (yyyy-mm-dd)
     */
    public static Date addNDays(Date date, int n) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.DAY_OF_MONTH, +n);
        System.out.println(df.format(rightNow.getTime()));
        return rightNow.getTime();
    }

    public static long daysBetween(Timestamp datetime1, Timestamp datetime2) {

        Timestamp currentTimestamp = currentTimestamp();

        datetime1 = (datetime1 == null) ? currentTimestamp : datetime1;
        datetime2 = (datetime2 == null) ? currentTimestamp : datetime2;

        long delta = Math.abs(datetime2.getTime() - datetime1.getTime());
        return (long) Math.ceil(delta * 1.0 / ONE_DAY_IN_MILISECONDS);
    }

    public static Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Timestamp parseTimestamp(String timestamp) {

        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_PATTERN);
        try {
            return new Timestamp(sdf.parse(timestamp).getTime());
        } catch (Exception ex) {
            logger.error("\"" + timestamp + "\" is invalid,"
                    + " it should be in pattern " + " \""
                    + DEFAULT_DATETIME_PATTERN + "\"", ex);
        }
        return null;
    }

    public static String formatTimestamp(Timestamp timestamp) {
        return timestamp == null ? "" : new SimpleDateFormat(
                DEFAULT_DATETIME_PATTERN).format(timestamp);
    }

}
