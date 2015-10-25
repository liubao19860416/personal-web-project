package beancompare;
import java.io.Serializable;
import java.sql.Timestamp;

import com.saike.grape.util.DatetimeUtils;


/**
 * 车享宝返回的保养券列表VO
 * 
 * @author Liubao
 * @version 3.0
 * 
 */
public class UserCouponCXBVO implements Serializable,
        Comparable<UserCouponCXBVO> {

    private static final long serialVersionUID = 1L;

    private String verifyCode;
    private String couponType;
    private Float amount;
    private String colorValue1;
    private String colorValue2;
    private String summary;
    private String detailDesc;
    private String startDate;
    private String endDate;
    private String couponStatus;
    private String typeCode;
    private String useKmValue;

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getColorValue1() {
        return colorValue1;
    }

    public void setColorValue1(String colorValue1) {
        this.colorValue1 = colorValue1;
    }

    public String getColorValue2() {
        return colorValue2;
    }

    public void setColorValue2(String colorValue2) {
        this.colorValue2 = colorValue2;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getUseKmValue() {
        return useKmValue;
    }

    public void setUseKmValue(String useKmValue) {
        this.useKmValue = useKmValue;
    }

    @Override
    public String toString() {
        return "UserCouponCXBVO [verifyCode=" + verifyCode + ", couponType="
                + couponType + ", amount=" + amount + ", colorValue1="
                + colorValue1 + ", colorValue2=" + colorValue2 + ", summary="
                + summary + ", detailDesc=" + detailDesc + ", startDate="
                + startDate + ", endDate=" + endDate + ", couponStatus="
                + couponStatus + ", typeCode=" + typeCode + ", useKmValue="
                + useKmValue + "]";
    }

    /**
     * 对对象数据排序实现方式1，方式2是使用自定义比较器
     */
    @SuppressWarnings("unused")
    @Override
    public int compareTo(UserCouponCXBVO obj) {
        if(this==null){
            return -1;
        }
        if(obj==null){
            return 1;
        }
        // 1. 获取格式化的float格式的金额和时间类型的开始时间和结束时间
        Float amount0 = this.getAmount();
        String startDateStr0 = this.getStartDate();
        String endDateStr0 = this.getEndDate();
        Float amount1 = obj.getAmount();
        String startDateStr1 = obj.getStartDate();
        String endDateStr1 = obj.getEndDate();
        
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