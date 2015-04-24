/**  
 * @Title: HelloTag.java
 * @Package com.lzy.block.console.tag
 * @author 李志勇  
 * @date 2015年4月17日 上午10:19:38
 * @version V1.0  
 */
package com.lzy.block.console.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @ClassName: HelloTag
 * @Description: HelloTag
 * @author 李志勇
 * @date 2015年4月17日 上午10:19:38
 * 
 */
public class HelloTag extends TagSupport {

	private static final long serialVersionUID = -2546310303206307962L;

	private String name;
	private String age;

	public String getName() {
		return name;
	}

	public String getAge() {
		return age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = super.pageContext.getOut();
		try {
			out.println("<h3>姓名：" + name + "</h3>");
			out.println("<h3>年龄：" + age + "</h3>");
		} catch (Exception e) {
		}
		return TagSupport.SKIP_BODY;
	}

}
