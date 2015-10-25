package mongodb;

import java.io.Serializable;
import java.sql.Timestamp;

import com.mongodb.BasicDBObject;
/**
 * mongoDB 测试bean
 * @author Liubao
 * @2014年11月14日
 *
 */
public class MongoDBEntiy extends BasicDBObject implements Serializable {

    private static final long serialVersionUID = -5481361667393666119L;

    private Integer id;
    private String name;
    private Float salary;
    private Timestamp startTime;

    public MongoDBEntiy() {
        super();
    }

    public MongoDBEntiy(int size) {
        super(size);
    }

    public MongoDBEntiy(String key, Object value) {
        super(key, value);
    }

    public MongoDBEntiy(Integer id, String name, Float salary,
            Timestamp startTime) {
        super();
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.startTime = startTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

}
