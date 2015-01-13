package com.lzy.block.console.common;

import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.ObjectUtils;

import com.google.common.collect.Maps;

/**
 * 流程定义缓存
 *
 */
public class ProcessDefinitionCache {

    private static Map<String, ProcessDefinition> map = Maps.newConcurrentMap();

    private static Map<String, List<ActivityImpl>> activities = Maps.newConcurrentMap();

    private static Map<String, ActivityImpl> singleActivity = Maps.newConcurrentMap();

    public static ProcessDefinition get(String processDefinitionId,RepositoryService repositoryService) {
        ProcessDefinition processDefinition = map.get(processDefinitionId);
        if (processDefinition == null) {
            processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processDefinitionId);
            if (processDefinition != null) {
                put(processDefinitionId, processDefinition);
            }
        }
        return processDefinition;
    }

    public static void put(String processDefinitionId, ProcessDefinition processDefinition) {
        map.put(processDefinitionId, processDefinition);
        ProcessDefinitionEntity pde = (ProcessDefinitionEntity) processDefinition;
        activities.put(processDefinitionId, pde.getActivities());
        for (ActivityImpl activityImpl : pde.getActivities()) {
            singleActivity.put(processDefinitionId + "_" + activityImpl.getId(), activityImpl);
        }
    }

    
    
    public static ActivityImpl getActivity(String processDefinitionId, String activityId,RepositoryService repositoryService) {
        ProcessDefinition processDefinition = get(processDefinitionId, repositoryService);
        if (processDefinition != null) {
            ActivityImpl activityImpl = singleActivity.get(processDefinitionId + "_" + activityId);
            if (activityImpl != null) {
                return activityImpl;
            }
        }
        return null;
    }

    public static String getActivityName(String processDefinitionId, String activityId,RepositoryService repositoryService) {
        ActivityImpl activity = getActivity(processDefinitionId, activityId,repositoryService);
        if (activity != null) {
            return ObjectUtils.toString(activity.getProperty("name"));
        }
        return null;
    }
}
