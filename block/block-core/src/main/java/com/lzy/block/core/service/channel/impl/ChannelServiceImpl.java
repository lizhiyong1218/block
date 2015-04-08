/**  
* @Title: ChannelServiceImpl.java
* @Package com.lzy.block.core.service.channel.impl
* @author 李志勇  
* @date 2014年11月26日 上午10:11:07
* @version V1.0  
*/ 
package com.lzy.block.core.service.channel.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lzy.block.api.model.channel.ChannelModel;
import com.lzy.block.core.dao.base.BaseMapper;
import com.lzy.block.core.dao.channel.ChannelMapper;
import com.lzy.block.core.service.base.impl.BaseServiceImpl;
import com.lzy.block.core.service.channel.IChannelService;

/**
 * @ClassName: ChannelServiceImpl
 * @Description: 栏目 
 * @author 李志勇
 * @date 2014年11月26日 上午10:11:07
 *
 */
@Service
public class ChannelServiceImpl extends BaseServiceImpl<ChannelModel> implements IChannelService{
	@Resource
	private ChannelMapper channelMapper;
	
	@Override
	protected BaseMapper<ChannelModel> getMapper() {
		return channelMapper;
	}

}
