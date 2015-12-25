/**
 * 
 */
package com.lzy.block.console.freemarker;

import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * freemarker视图解析器扩展
 * 方便将公共信息获取
 * @author lizhiyong
 *
 */
public class FreeMarkerViewResolverExt extends FreeMarkerViewResolver{
	public String getPrefix() {
		return super.getPrefix();
	}
	@Override
	@SuppressWarnings("rawtypes")
	protected Class requiredViewClass() {
		return FreeMarkerViewExt.class;
	}
}
