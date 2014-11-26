package com.lzy.block.api.model.channel;

import java.util.Date;
/**
* 
* @ClassName: ChannelModel
* @Description: 栏目 
* @author 李志勇
* @date 2014年11月26日 上午10:04:57
*
*/
public class ChannelModel {
    private Integer channelId;

    private Integer channelParentId;

    private String channelName;

    private String channelShowname;

    private String channelLogo;

    private String channelDesc;

    private String crateBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String isavailable;

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getChannelParentId() {
        return channelParentId;
    }

    public void setChannelParentId(Integer channelParentId) {
        this.channelParentId = channelParentId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getChannelShowname() {
        return channelShowname;
    }

    public void setChannelShowname(String channelShowname) {
        this.channelShowname = channelShowname == null ? null : channelShowname.trim();
    }

    public String getChannelLogo() {
        return channelLogo;
    }

    public void setChannelLogo(String channelLogo) {
        this.channelLogo = channelLogo == null ? null : channelLogo.trim();
    }

    public String getChannelDesc() {
        return channelDesc;
    }

    public void setChannelDesc(String channelDesc) {
        this.channelDesc = channelDesc == null ? null : channelDesc.trim();
    }

    public String getCrateBy() {
        return crateBy;
    }

    public void setCrateBy(String crateBy) {
        this.crateBy = crateBy == null ? null : crateBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsavailable() {
        return isavailable;
    }

    public void setIsavailable(String isavailable) {
        this.isavailable = isavailable == null ? null : isavailable.trim();
    }
}