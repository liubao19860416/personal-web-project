package reflect;

interface Info {
}

class Contact implements Info {
    private String address;
    private String telphone;
    private String zipcode;

    public Contact(String address, String telphone, String zipcode) {
        this.setAddress(address);
        this.setTelphone(telphone);
        this.setZipcode(zipcode);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return this.address;
    }

    public String getTelphone() {
        return this.telphone;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public String toString() {
        return "��ϵ��ʽ��" + "\n" + "\t|- ��ϵ�绰��" + this.telphone + "\n"
                + "\t|- ��ϵ��ַ��" + this.address + "\n" + "\t|- �������룺"
                + this.zipcode;
    }
};

/**
 * 实现类2
 * 
 * @author Liubao
 * @2015年3月23日
 * 
 */
class Introduction implements Info {
    private String name;
    private String sex;
    private int age;

    public Introduction(String name, String sex, int age) {
        this.setName(name);
        this.setSex(sex);
        this.setAge(age);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public String getSex() {
        return this.sex;
    }

    public int getAge() {
        return this.age;
    }

    public String toString() {
        return "����Ϣ��" + "\n" + "\t|- ����" + this.name + "\n" + "\t|- �Ա�"
                + this.sex + "\n" + "\t|- ���䣺" + this.age;
    }
};

/**
 * 定义泛型的上限，最大为Info
 * 
 * @author Liubao
 * @2015年3月23日
 * 
 */
class Person<T extends Info> {
    private T info;

    public Person(T info) {
        this.setInfo(info);
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public T getInfo() {
        return this.info;
    }

    public String toString() {
        return this.info.toString();
    }
};

/**
 * 方便组合各种信息
 * 
 * @author Liubao
 * @2015年3月23日
 * 
 */
public class GenericsDemo32 {
    public static void main(String args[]) {
        Person<Contact> per = null;
        per = new Person<Contact>(new Contact("地址", "01051283346", "100088"));
        System.out.println(per);
        
        Person<Introduction> per2 = null ;       
        per2 = new Person<Introduction>(new Introduction("���˻�","��",30)) ;
        System.out.println(per2) ;
    }
};