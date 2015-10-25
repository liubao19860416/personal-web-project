package base.mail;

/*
 * 存放操作常量的接口
 */
public interface MailConstants {
	
	// 邮件激活几种情况;
	public static final int ACTIVEMAIL_TIMEOUT = 0; // 超时
	public static final int ACTIVEMAIL_OK = 1; // 正常激活
	public static final int ACTIVEMAIL_ISACTIVED = 2; // 已经激活过
	public static final int ACTIVEMAIL_INVALIDATIVE = 3; // 激活码无效
	
	//定义用户激活状态的两个常量,1表示激活,0表示未激活;
	public static final int ACTIVECODE_ISACTIVED = 1; // 账号为激活状态
	public static final int ACTIVECODE_ISNOTACTIVED = 0; // 账号为未激活状态
	

}