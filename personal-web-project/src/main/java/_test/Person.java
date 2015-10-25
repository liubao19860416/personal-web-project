package _test;

import java.io.Serializable;
import java.util.Date;

/**
 * 测试实体bean
 * 
 * @author Liubao
 */
public class Person implements Serializable{
    private static final long serialVersionUID = 3903804282914420790L;
    
    private Long pid;
    private String pname;
    private String password;
    private Date birthday;
    private String description;

    private Son son;// 对象属性，测试是否能转为Json格式

    public Person(String pname, Long pid) {
        super();
        this.pid = pid;
        this.pname = pname;
    }

    public Son getSon() {
        return son;
    }

    public void setSon(Son son) {
        this.son = son;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person() {
        super();
    }

    public Person(Long pid, String pname, String password, Date birthday) {
        super();
        this.pid = pid;
        this.pname = pname;
        this.password = password;
        this.birthday = birthday;

    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person [pid=" + pid + ", pname=" + pname + ", password="
                + password + ", birthday=" + birthday + ", description="
                + description + ", son=" + son + "]";
    }

}
