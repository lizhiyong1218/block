package com.lzy.block.api.vo.activiti;

public class OrgStructModel {
    private Integer id;

    private String defKey;

    private String taskKey;

    private String orgName;

    private String orgId;

    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDefKey() {
        return defKey;
    }

    public void setDefKey(String defKey) {
        this.defKey = defKey;
    }

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    @Override
    public int hashCode() {
    	return defKey.hashCode()+taskKey.hashCode()+orgId.hashCode()*31;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(obj==null){
    		return false;
    	}
    	if(obj instanceof OrgStructModel){
    		OrgStructModel temp = (OrgStructModel)obj;
    		if(temp.getDefKey().equals(defKey)&&temp.getTaskKey().equals(taskKey)
    		   &&temp.getOrgId().equals(orgId)&&temp.getType().equals(type)){
    			return true;
    		}
    	}
    	return false;
    }
}
















