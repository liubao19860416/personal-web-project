package base.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import base.conf.MyContances;
/**
 * Date时间格式自动转换器
 * 
 * @author Liubao
 * @2014年11月29日
 *
 */
public class DateConverter implements Converter<String, Date> {

    public Date convert(String source) {
        if (source != null) {
            // 如果从浏览器传入字符串不等于null开始转换
            source = source.trim();
            // 去除前后空格
            if (source.equals("")) {
                source = null;
            }
            if (source != null) {
                // 去除空格后不为空则开始转换
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MyContances.DEFAULE_DATE_FORMAT);
                try {
                    return simpleDateFormat.parse(source);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return null;
                }
            }

        }
        return null;

    }
}
