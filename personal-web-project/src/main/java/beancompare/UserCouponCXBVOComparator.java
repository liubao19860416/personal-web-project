package beancompare;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.saike.grape.util.DatetimeUtils;

/**
 * 自定义比较器
 * 对对象数据排序实现方式2，方式1是覆写需要比较对象的compareTo方法即可
 * 
 * @author Liubao
 * @version 3.0
 * 
 */
public class UserCouponCXBVOComparator implements Comparator<UserCouponCXBVO> {
    
    public static void main(String[] args) {
        List<UserCouponCXBVO> couponList = new ArrayList<UserCouponCXBVO>();
        System.err.println(couponList.isEmpty());//true
        
        for(int i=0;i<7;i++){
            UserCouponCXBVO userCouponCXBVO=new UserCouponCXBVO();
            if(i==3){
                userCouponCXBVO.setStartDate("2015-03-12 23:00:00");
                userCouponCXBVO.setEndDate("2015-12-31 22:59:59");
                userCouponCXBVO.setAmount(100.0F);
                userCouponCXBVO.setDetailDesc("顺序为：001");
            }
            if(i==2){
                userCouponCXBVO.setStartDate("2015-03-13 01:00:00");
                userCouponCXBVO.setEndDate("2015-12-31 22:59:59");
                userCouponCXBVO.setAmount(100.0F);
                userCouponCXBVO.setDetailDesc("顺序为：002");
            }
            if(i==5){
                userCouponCXBVO.setStartDate("2015-03-13 01:00:00");
                userCouponCXBVO.setEndDate("2015-12-31 23:59:59");
                userCouponCXBVO.setAmount(300.0f);
                userCouponCXBVO.setDetailDesc("顺序为：003");
            }
            if(i==4){
                userCouponCXBVO.setStartDate("2015-03-13 01:00:00");
                userCouponCXBVO.setEndDate("2015-12-31 23:59:59");
                userCouponCXBVO.setAmount(100.0f);
                userCouponCXBVO.setDetailDesc("顺序为：004");
            }
            if(i==6){
                userCouponCXBVO.setStartDate("2015-06-01 00:00:00");
                userCouponCXBVO.setEndDate("2015-12-31 23:59:59");
                userCouponCXBVO.setAmount(200.00f);
                userCouponCXBVO.setDetailDesc("顺序为：000");
            }
            if(i==0){
                userCouponCXBVO.setStartDate("2015-06-01 00:00:0");
                userCouponCXBVO.setEndDate("2015-12-31 23:59:58");
                userCouponCXBVO.setAmount(100.00f);
                userCouponCXBVO.setDetailDesc("顺序为：0000");
            }
            if(i==1){
                userCouponCXBVO.setStartDate("2015-03-01 00:00:00");
                userCouponCXBVO.setEndDate("2015-12-31 23:59:59");
                userCouponCXBVO.setAmount(200.00f);
                userCouponCXBVO.setDetailDesc("顺序为：000");
            }
            couponList.add(userCouponCXBVO);
        }
        
        for (int i = 0; i < couponList.size(); i++) {
//            System.out.println(JSON.toJSON(couponList.get(i)));
        }
        //方式1
        Collections.sort(couponList);
        //System.out.println("==========================================");
        //方式2
        //Collections.sort(couponList, new UserCouponCXBVOComparator());
        //Comparator<UserCouponCXBVO> reverseOrder = Collections.reverseOrder(new UserCouponCXBVOComparator());
        //Collections.sort(couponList, reverseOrder);
        
        //转换成数组进行排序
//        Arrays.sort(couponList.toArray()) ;
        
        System.out.println("==========================================");
        
        for (int i = 0; i < couponList.size(); i++) {
            System.out.println(JSON.toJSON(couponList.get(i)));
        }
    }
    
    @Override
    public int compare(UserCouponCXBVO cxbvo0, UserCouponCXBVO cxbvo1) {
        if(cxbvo0==null){
            return -1;
        }
        if(cxbvo1==null){
            return 1;
        }
        // 1. 获取格式化的float格式的金额和时间类型的开始时间和结束时间
        Float amount0 = cxbvo0.getAmount();
        String startDateStr0 = cxbvo0.getStartDate();
        String endDateStr0 = cxbvo0.getEndDate();
        Float amount1 = cxbvo1.getAmount();
        String startDateStr1 = cxbvo1.getStartDate();
        String endDateStr1 = cxbvo1.getEndDate();
        
        if(amount0==null){
            return -1;
        }
        if(amount1==null){
            return 1;
        }
        if(startDateStr0==null||!startDateStr0.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")){
            return -1;
        }
        if(startDateStr1==null||!startDateStr1.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")){
            return 1;
        }
        if(endDateStr0==null||!endDateStr0.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")){
            return -1;
        }
        if(endDateStr1==null||!endDateStr1.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")){
            return 1;
        }
        // 2. 首先比较创建时间，按照升序排列；如果相同，则比较结束时间，按照升序排列；
        //DateUtil.parse(startDate0);
        //DatetimeUtils.parseDatetime(startDate0);
        Timestamp startDate0 = DatetimeUtils.parseTimestamp(startDateStr0);
        Timestamp startDate1 = DatetimeUtils.parseTimestamp(startDateStr1);
        Timestamp endDate0 = DatetimeUtils.parseTimestamp(endDateStr0);
        Timestamp endDate1 = DatetimeUtils.parseTimestamp(endDateStr1);
        
        int flag = startDate0.compareTo(startDate1);
        if (flag == 0) {
            flag=endDate0.compareTo(endDate1);
            if(flag == 0){
                flag= amount1.compareTo(amount0);
            }
        } 
        return flag;
    }

}