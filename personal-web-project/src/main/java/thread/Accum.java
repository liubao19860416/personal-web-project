package thread;
/**
 * 实体bean
 * 
 * @author Liubao
 * @2014年10月31日
 *
 */
public class Accum {
    
    private static Accum a=new Accum();
    private int counter=0;
    
    //空构造函数
    private Accum(){}
    
    public static Accum getAccum(){
        return a;
    }
    
    public void updateCounter(int add){
        this.counter+=add;
    }
    
    public int getCount(){
        return this.counter;
    }
    
    

}
