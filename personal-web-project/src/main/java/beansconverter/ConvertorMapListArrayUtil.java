package beansconverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
/**
 * Java 集合转换(数组、List、Set、Map相互转换)
 * 
 * @author Liubao
 * @2014年12月7日
 *
 */
public class ConvertorMapListArrayUtil {

    public static void main(String[] args) {
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("A", "ABC");
        map.put("K", "KK");
        map.put("L", "LV");
        
        ConvertorMapListArrayUtil.transFromMap2List(null, null, map);
        
    }

    /**
     * 将map中的key和value分别添加到或创建新的一个list对象
     */
    public static <T> void transFromMap2List(List<String> keyList,List<T> valueList,Map<String ,T> map) {
        // 将Map Key 转化为List
        if(keyList==null||keyList.isEmpty()){
            keyList = new ArrayList<String>(map.keySet());
        }else{
            keyList.addAll(map.keySet());
        }
        System.out.println("keyList:" + keyList);

        // 将Map Key 转化为List
        if(valueList==null||valueList.isEmpty()){
            valueList = new ArrayList<T>(map.values());
        }else{
            valueList.addAll(map.values());
        }
        System.out.println("valueList:" + valueList);

    }

    /**
     * 将map中的key和value分别添加到或创建新的一个TreeSet对象
     */
    public static <T> void transFromMap2Set(Set<String> keySet,Set<T> valueSet,Map<String ,T> map) {
        // 将Map 的键转化为Set
        if(keySet==null||keySet.isEmpty()){
            keySet = new TreeSet<String>(map.keySet());
        }else{
            keySet.addAll(map.keySet());
        }
        System.out.println("keySet:" + keySet);

        // 将Map 的值转化为Set
        if(valueSet==null||valueSet.isEmpty()){
            valueSet = new TreeSet<T>(map.values());
        }else{
            valueSet.addAll(map.values());
        }
        System.out.println("valueSet:" + valueSet);
    }

    /**
     * 将数组中的对象转换为一个Set对象
     */
    public static <T> void transFromArray2Set(T[] arrays,Set<T> valueSet) {
        if(!arrays.getClass().isArray()){
            throw new RuntimeException("您输入的对象不是数组类型！！！");
        }

        // 数组-->Set
        // 将Map 的值转化为Set
        if(valueSet==null||valueSet.isEmpty()){
            valueSet = new TreeSet<T>(Arrays.asList(arrays));
        }else{
            valueSet.addAll(Arrays.asList(arrays));
        }
        System.out.println(valueSet);
    }

    /**
     * 将Set中的对象转换为一个Object[]数组对象
     */
    public static  <T> void transFromSet2Array(Set<T> valueSet,Object[] arrays) {
        // Set-->数组
        if(arrays==null||arrays.length<=0){
            arrays=new Object[valueSet.size()];
        }
        // 将转化后的数组放入已经创建好的对象中
        //arrays=valueSet.toArray();
        valueSet.toArray(arrays);
        System.out.println(Arrays.toString(arrays));
    }

    /**
     * 将List中的对象转换为一个Set对象
     */
    public static <T> void transFromList2Set(List<T> list,Set<T> set) {
        // List-->Set
        if(set==null||set.isEmpty()){
            set= new HashSet<T>(list);
        }else{
            set.addAll(list);
        }
        System.out.println(set);
    }

    /**
     * 将Set中的对象转换为一个List对象
     */
    public static <T> void transFromSet2List(Set<T> set,List<T> list) {
        // 将Map Key 转化为List
        if(list==null||list.isEmpty()){
            list = new ArrayList<T>(set);
        }else{
            list.addAll(set);
        }
        // Set --> List
        System.out.println(list);
    }

    /**
     * 将List中的对象转换为一个Object[]对象
     */
    public static <T> void transFromList2Array(List<T> list,Object[] arrays) {
        // List-->数组
        if(arrays==null||arrays.length<=0){
            arrays=new Object[list.size()];
        }
        // 将转化后的数组放入已经创建好的对象中
        //arrays = list.toArray();
        list.toArray(arrays);
        System.out.println( Arrays.toString(arrays));
    }

    /**
     * 将Array中的对象转换为一个List对象
     */
    public static <T> void transFromArray2List(T[] arrays,List<T> list) {
        // 数组-->List
        if(list==null||list.isEmpty()){
            list = new ArrayList<T>(Arrays.asList(arrays));
        }else{
            list.addAll(Arrays.asList(arrays));
        }
        System.out.println(list);
    }

}
