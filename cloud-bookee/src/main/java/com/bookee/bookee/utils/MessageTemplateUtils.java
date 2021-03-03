package com.bookee.bookee.utils;


import com.bookee.bookee.entity.UserEo;
import net.sf.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageTemplateUtils {
    public static void main(String[] args) {
        UserEo user = new UserEo();
        user.setId("1511111");
        user.setName("hhhh");
        String str = "id,name";
        //ystem.out.println(MessageTemplateUtils.replaceParamsByEntity(str, user));

        String strMessage = "id为${id}的用户名${name}";
        System.out.println(MessageTemplateUtils.replaceParamsByEntity(strMessage,user));
    }
    public static String replaceParamsByEntity(String content, Object obj) {
        // 利用正则+反射的方式将字符串中的占位字符替换为实际变量值
        // 参数名必须为字母或者下划线
        String regex = "(\\$\\{[a-zA-Z_]+\\})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            // 找出 ${参数名} 格式的字符串
            String str = matcher.group();
            String paramName = str.substring(2, str.length() - 1);
            content = content.replace(str, getValueByReflect(obj, paramName).toString());
        }
        List<String> contentList = Arrays.asList(content.split(","));
        Map<String,String> map = new HashMap<String,String>();
        if(contentList.size() > 0){
            for(int i=0;i<contentList.size();i++){
                String className = contentList.get(i);
                if(!getValueByReflect(obj, className).toString().equals("")){
                    map.put(className,getValueByReflect(obj, className).toString());
                }
            }
        }
        if(!map.isEmpty()){
            JSONObject object = JSONObject.fromObject(map);
            content = object.toString().replace("\"", "\\\"");
        }
        return content;
    }

    public static Object getValueByReflect(Object dto, String name) {
        Method[] m = dto.getClass().getMethods();
        for (int i = 0; i < m.length; i++) {
            if (("get" + name).equalsIgnoreCase(m[i].getName())) {
                try {
                    Object obj = m[i].invoke(dto);
                    Type type = m[i].getGenericReturnType();
                    // 处理日期类型的返回值
                    if (type.toString().equals("class java.util.Date")) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = (Date) obj;
                        return sdf.format(date);
                    }
                    return obj;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
}
