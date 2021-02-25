package com.bookee.bookee.filter;

import io.netty.util.CharsetUtil;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.regex.Pattern;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class RequestSpecialCharacterWrapper extends HttpServletRequestWrapper {

    private final byte[] body;//保存流的字节数组

    private static final Logger logger = LoggerFactory.getLogger(RequestSpecialCharacterWrapper.class);

    public RequestSpecialCharacterWrapper(HttpServletRequest request) throws UnsupportedEncodingException {
        super(request);
        String sessionStream = this.getBodyString(request);//读取流中的参数
        logger.error("sessionStream:"+ sessionStream);
        Pattern scriptPattern = Pattern.compile("dnslog|@type|InetAddress");
        sessionStream = scriptPattern.matcher(sessionStream).replaceAll("");

        JSONObject parameterMap = null;
        try {
            parameterMap = JSON.parseObject(sessionStream);
        } catch (Exception e) {
            logger.error("非正常JSON形式");
        }
        if(parameterMap == null || parameterMap.isEmpty()){
            logger.error("noreplace!");
            if(StringUtils.isNotBlank(sessionStream)){
                body = sessionStream.getBytes("UTF-8");
            }else{
                body = new byte[0];
            }
        }else {
            String fianle = parameterMap.toJSONString();
            //if (StringUtil.isNotBlank(fianle) && fianle != null) {
                fianle = this.filterRequestSpecialCharacter(fianle);
                if (StringUtils.isNotBlank(fianle) && fianle != null) {
                    logger.error("replace-done!");
                    body = fianle.getBytes("UTF-8");
                    logger.error("fianle-end:" + fianle);
                } else {
                    logger.error("replace-none!");
                    body = new byte[0];
                }
            //}
//            else {
//                logger.error("noreplace!");
//                body = parameterMap.toJSONString().getBytes(CharsetUtil.charset("UTF-8"));
//            }

        }

    }
    public final String getBodyString(final ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try (InputStream inputStream = cloneInputStream(request.getInputStream());
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));){
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        catch (IOException e) {
            logger.error("获取request请求体异常！");
        }
        return sb.toString();
    }

    /**
     * Description: 复制输入流</br>
     *
     * @param inputStream
     * @return</br>
     */
    public InputStream cloneInputStream(ServletInputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buffer)) > -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byteArrayOutputStream.flush();
        }
        catch (IOException e) {
            logger.error("克隆流异常！");
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return byteArrayInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                logger.info("");
            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };

    }
    public final String filterRequestSpecialCharacter(String value){
        logger.error("requestUrl:"+this.getRequestURI()+" 校验json数据："+ value);
        if (value != null) {
            Pattern scriptPattern = Pattern.compile("<[\r\n| | ]*script[\r\n| | ]*>(.*?)</[\r\n| | ]*script[\r\n| | ]*>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            scriptPattern = Pattern.compile("src[\r\n| | ]*=[\r\n| | ]*[\\\"|\\\'](.*?)[\\\"|\\\']", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("</[\r\n| | ]*script[\r\n| | ]*>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("<[\r\n| | ]*script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("e-xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("javascript[\r\n| | ]*:[\r\n| | ]*", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("vbscript[\r\n| | ]*:[\r\n| | ]*", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("xss|iframe|prompt|src|href|alert|svg|onload|onmouseover|onerror|onfocus|exec |insert |select |delete |update |select [*]|select[*]");
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("[<]|[>]|\\|[-]{2}|[']|[&]|[(]|[)]|(%28)|(%29)|(%3C)|(%3E)");
            value = scriptPattern.matcher(value).replaceAll("");
        }
        return value;
    }
    public final Boolean isJsonStr(String value){
        try {
            JSONObject parameterMap = JSON.parseObject(value);
            return true;
        }catch (Exception e){
            logger.error("非json字符串！");
            return false;
        }
    }
}
