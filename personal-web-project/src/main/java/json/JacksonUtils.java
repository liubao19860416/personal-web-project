package json;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;



public class JacksonUtils {
    
    private final static ObjectMapper ojectMapper = new ObjectMapper();
    
    public static void main(String[] args) {
        String jsonFilePath = "d:\\temp\\";
        String jsonFileName = "order";
        jsonFileName = "header";
        String readJson = readJson(jsonFileName, jsonFilePath);
        System.out.println(readJson);
        Map<String, Object> map = fromJson2Object(Map.class, readJson);
        
        //方式2
        Map map2 = FastJsonUtil.stringToCollect(readJson);
        for (Object key : map2.keySet()) {
            System.out.println(key+"====>>"+map.get(key));
        }
        
        
    }
    
    /**
     * 从json格式文件中读取json字符串
     */
    public static String readJson(String jsonFileName,String jsonFilePath) {  
        String result = null;  
        StringBuilder stringBuilder = new StringBuilder();  
        FileReader fileReader = null;  
        try {  
            File jsonFile = new File(jsonFilePath + jsonFileName + ".json");  
            fileReader = new FileReader(jsonFile);  
            char[] buf = new char[(int) jsonFile.length()];  
            int len = -1;  
            while((len = fileReader.read(buf))!=-1 ){
                result = new String(buf,0,len);  
                stringBuilder.append(result);
            }
        } catch (Exception e) {  
            e.printStackTrace();  
        }  finally{
            try {
                if(fileReader!=null){
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                fileReader=null;
            }  
        }
        return stringBuilder.toString();  
    }  
    
    /**
     * 将任意json格式字符串转换为对象
     */
    public static <T> T fromJson2Object( Class<T> clazz, String jsonStr ) {
        T jsonObj = null;
        if( jsonStr != null && ! "".equals( jsonStr ) ) {
            try {
                jsonObj = ojectMapper.readValue( jsonStr, clazz );
            } catch ( Exception ex ) {
                throw new RuntimeException( ex );
            }
        }
        return jsonObj;
    }

    /**
     * 将任意对象转换为json格式字符串
     */
    public static String fromObject2Json(Object obj) {
        String json = null;
        try {
            json = ojectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
    
}
