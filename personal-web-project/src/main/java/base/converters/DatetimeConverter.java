package base.converters;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.core.convert.converter.Converter;

import base.conf.MyContances;
/**
 * Timestamp时间格式自动转换器
 * 
 * @author Liubao
 * @2014年11月29日
 *
 */
public class DatetimeConverter implements Converter<String, Timestamp> {

    public Timestamp convert(String source) {

        if (source != null) {
            source = source.trim();
            if (source.equals("")) {
                source = null;
            }
            if (source != null) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MyContances.DEFAULE_TIMESTAMP_FORMAT);
                try {
                    Timestamp timestamp = new Timestamp(simpleDateFormat.parse(source).getTime());
                    return timestamp;
                } catch (ParseException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return null;

    }
}
