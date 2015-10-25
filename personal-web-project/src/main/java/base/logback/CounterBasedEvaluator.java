package base.logback;

import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.EventEvaluator;
import ch.qos.logback.core.spi.ContextAwareBase;
/**
 * 自定义的触发邮件发送的规则类（默认按照error日志触发1024条发送）
 * 
 * @author Liubao
 * @2014年12月1日
 *
 */
@SuppressWarnings("rawtypes")
public class CounterBasedEvaluator extends ContextAwareBase implements EventEvaluator {

    /**
     * 设置触发发送邮件题型的时候，出现错误记录的条数
     */
    static int LIMIT = 10;
    //static int counter = 0;
    int counter = 0;
    String name;
    boolean started;

    public boolean evaluate(Object event) throws NullPointerException,EvaluationException {
        counter++;
        if (counter == LIMIT) {
            counter = 0;
            return true;
        } else {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStarted() {
        return started;
    }

    public void start() {
        started = true;
    }

    public void stop() {
        started = false;
    }
}
