/**  
* @Title: ChannelServiceImpl.java
* @Package com.lzy.block.core.service.channel.impl
* @author 李志勇  
* @date 2014年11月26日 上午10:11:07
* @version V1.0  
*/ 
package com.lzy.block.core.service.channel.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lzy.block.api.common.Pagination;
import com.lzy.block.api.model.channel.ChannelModel;
import com.lzy.block.core.dao.channel.ChannelMapper;
import com.lzy.block.core.service.channel.IChannelService;

/**
 * @ClassName: ChannelServiceImpl
 * @Description: 栏目 
 * @author 李志勇
 * @date 2014年11月26日 上午10:11:07
 *
 */
@Service
public class ChannelServiceImpl implements IChannelService{
	@Resource
	private ChannelMapper channelMapper;
	

	@Override
	public int insert(ChannelModel o) throws Exception {
		 
		return channelMapper.insert(o);
	}

	@Override
	public int updateByPrimaryKeySelective(ChannelModel o) throws Exception {
		return channelMapper.updateByPrimaryKeySelective(o);
	}

	@Override
	public void deleteByPrimaryKey(Integer id) throws Exception {
		channelMapper.deleteByPrimaryKey(id);
	}

	@Override
	public ChannelModel selectByPrimaryKey(Integer id) throws Exception {
		return channelMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ChannelModel> getAll(ChannelModel o) throws Exception {
		return channelMapper.getAll(o);
	}

	@Override
	public ChannelModel selectModel(ChannelModel o) {
		return channelMapper.selectModel(o);
	}

	@Override
	public Pagination<ChannelModel> getPagination(ChannelModel o,
			PageBounds pageBounds) {
		
		long recordCount=channelMapper.getCount(o);
		List<ChannelModel> recordList=new ArrayList<ChannelModel>();
		if(recordCount>0){
			recordList= channelMapper.getAll(o, pageBounds);
		}
		Pagination<ChannelModel> pagination=new Pagination<ChannelModel>(pageBounds.getPage(),pageBounds.getLimit(),recordCount,recordList);
		return pagination;
	}

}
