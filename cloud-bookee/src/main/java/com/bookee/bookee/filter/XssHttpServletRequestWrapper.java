package com.bookee.bookee.filter;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 对数组参数进行特殊字符过滤
	 */
	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		if (value == null) {
			return null;
		}
		return cleanXSS(value);
	}

	/**
	 * 获取attribute,特殊字符过滤
	 */
	@Override
	public Object getAttribute(String name) {
		Object value = super.getAttribute(name);
		if (value != null && value instanceof String) {
			cleanXSS((String) value);
		}
		return value;
	}

	/**
	 * 对请求头部进行特殊字符过滤
	 */
	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		if (value == null) {
			return null;
		}
		return cleanXSS(value);
	}
	
	private String cleanXSS(String value) {
		if (value != null) {
			// 推荐使用ESAPI库来避免脚本攻击,value = ESAPI.encoder().canonicalize(value);
			// 避免空字符串
//			value = value.replaceAll(" ", "");
			// 避免script 标签
			Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");
			// 避免src形式的表达式
			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			// 删除单个的 </script> 标签
			scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");
			// 删除单个的<script ...> 标签
			scriptPattern = Pattern.compile("<script(.*?)>",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			// 避免 eval(...) 形式表达式
//			scriptPattern = Pattern.compile("eval\\((.*?)\\)",
//					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
//			value = scriptPattern.matcher(value).replaceAll("");
			// 避免 e­xpression(...) 表达式
			scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			// 避免 javascript: 表达式
			scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");
			// 避免 vbscript:表达式
			scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");
			// 避免 onload= 表达式
			scriptPattern = Pattern.compile("onload(.*?)=",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			// 过滤数据库关键词
			scriptPattern = Pattern.compile("xss|iframe|prompt|src|href|alert|svg|onload|onmouseover|onerror|onfocus|exec |insert |select |delete |update |select [*]|select[*]");
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("[<]|[>]|\\|[-]{2}|[']|[&]|[(]|[)]|(%28)|(%29)|(%3C)|(%3E)");
			value = scriptPattern.matcher(value).replaceAll("");
		}
		return value;
	}
}