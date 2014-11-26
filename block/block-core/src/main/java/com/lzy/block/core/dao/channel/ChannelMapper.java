package com.lzy.block.core.dao.channel;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lzy.block.api.model.channel.ChannelModel;


public interface ChannelMapper {
    int deleteByPrimaryKey(Integer channelId);

    int insert(ChannelModel record);

    int insertSelective(ChannelModel record);

    ChannelModel selectByPrimaryKey(Integer channelId);

    int updateByPrimaryKeySelective(ChannelModel record);

    int updateByPrimaryKey(ChannelModel record);
    
    /**
     * 
    * @Title: selectModel
    * @Description: 查询单个对象
    * @param para
    * @return:     
    * DictionaryModel    
    * @throws
     */
    public ChannelModel selectModel(ChannelModel para);
    
    /**
     * 
    * @Title: getAll
    * @Description: 查询所有对象
    * @param o
    * @return:     
    * List<DictionaryModel>    
    * @throws
     */
    public List<ChannelModel> getAll(ChannelModel o) ;
    
    /**
     * 
    * @Title: getAll
    * @Description: 查询分页集合
    * @param o
    * @param pageBounds
    * @return:     
    * PageList<DictionaryModel>    
    * @throws
     */
    public PageList<ChannelModel> getAll(ChannelModel o,
			PageBounds pageBounds);
    
    /**
     * 
    * @Title: getCount
    * @Description: 查询总数
    * @param record
    * @return:     
    * long    
    * @throws
     */
    public long getCount(ChannelModel record);
}