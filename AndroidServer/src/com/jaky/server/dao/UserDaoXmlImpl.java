package com.jaky.server.dao;

import com.jaky.server.bean.User;
import com.jaky.server.utils.XmlUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDaoXmlImpl implements UserDao {

    public boolean insert(User user){
        boolean res = false;
        try {
            Document document = XmlUtils.getDocument();
            Element root = document.getRootElement();
            Element userNode = root.addElement("user");
            userNode.setAttributeValue("id", user.getId());
            userNode.setAttributeValue("username", user.getUsername());
            userNode.setAttributeValue("password", user.getPassword());
            userNode.setAttributeValue("email", user.getEmail());
            userNode.setAttributeValue("birthday", user.getBirthday().toLocaleString());
            XmlUtils.write2Xml(document);
            res = true;
        } catch (Exception e) {
            res = false;
            throw new RuntimeException(e);
        } finally {
            return res;
        }
    }

    public User query(String username) {
        try {
            Document document = XmlUtils.getDocument();
            Element ele = (Element) document.selectSingleNode("//user[@username='"+username+"']");
            if(ele == null){
                return null;
            }
            User user = new User();
            user.setId(ele.attributeValue("id"));
            user.setEmail(ele.attributeValue("email"));
            user.setUsername(ele.attributeValue("username"));
            user.setPassword(ele.attributeValue("password"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String birthday = ele.attributeValue("birthday");
            Date date = sdf.parse(birthday);
            user.setBirthday(date);
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User query(String username, String password){
        try {
            Document document = XmlUtils.getDocument();
            Element ele = (Element) document.selectSingleNode("//user[@username='"+username+"' and @password='"+password+"']");
            if(ele == null){
                return null;
            }
            User user = new User();
            user.setId(ele.attributeValue("id"));
            user.setEmail(ele.attributeValue("email"));
            user.setUsername(ele.attributeValue("username"));
            user.setPassword(ele.attributeValue("password"));
            String birthday = ele.attributeValue("birthday");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            user.setBirthday(sdf.parse(birthday));
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
