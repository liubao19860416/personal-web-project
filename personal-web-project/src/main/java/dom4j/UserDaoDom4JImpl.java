package dom4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import base.entiy.User;

public class UserDaoDom4JImpl {
	
	@SuppressWarnings("deprecation")
    public void save(User user) {
		try {
			Document document = Dom4JUtil.getDocument();
			Element root = document.getRootElement();
			
			// <user></user>
			Element userElement = DocumentHelper.createElement("user");
			userElement.addAttribute("username", user.getUsername());
			userElement.addAttribute("password", user.getPassword());
			userElement.addAttribute("email", user.getEmail());
			userElement.addAttribute("birthday", user.getBirthday().toLocaleString());
			
			root.add(userElement);
			
			Dom4JUtil.write2xml(document);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public User findUserByUsername(String username) {
		try {
			Document document = Dom4JUtil.getDocument();
			String xpath = "//user[@username='"+username+"']";
			Node node = document.selectSingleNode(xpath);
			if(node==null)
				return null;
			
			User user = new User();
			user.setUsername(node.valueOf("@username"));
			user.setPassword(node.valueOf("@password"));
			user.setEmail(node.valueOf("@email"));
			
			String str = node.valueOf("@birthday");//1980-10-01
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			user.setBirthday(df.parse(str));
			
			return user;
			
		} catch (Exception e) {
			throw new RuntimeException(e);//�쳣ת��
		}
	}

	public User findUser(String username, String password) {
		try {
			Document document = Dom4JUtil.getDocument();
			String xpath = "//user[@username='"+username+"' and @password='"+password+"']";
			Node node = document.selectSingleNode(xpath);
			if(node==null)
				return null;
			
			User user = new User();
			user.setUsername(node.valueOf("@username"));
			user.setPassword(node.valueOf("@password"));
			user.setEmail(node.valueOf("@email"));
			
			String str = node.valueOf("@birthday");//1980-10-01
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			user.setBirthday(df.parse(str));
			
			return user;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
