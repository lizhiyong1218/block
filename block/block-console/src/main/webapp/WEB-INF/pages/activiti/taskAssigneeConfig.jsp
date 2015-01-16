<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<script type="text/javascript" src="${basePath}/resources/js/activiti/taskAssigneeConfig.js"></script>

<style type="text/css">
		.drag-item{
			list-style-type:none;
			
			padding:5px;
			border:1px solid #ccc;
			margin:2px;
			width:430px;
			background:#fafafa;
			color:#444;
		}
		.indicator{
			position:absolute;
			font-size:9px;
			width:10px;
			height:10px;
			display:none;
			color:red;
		}
	</style>

<div class="easyui-layout" style="width:965px;height:545px;">   
	<input type="hidden" name="processDefinitionId" id="processDefinitionId" value="${processDefinitionId}" /> 
    
    <div data-options="region:'east',title:'流程任务信息',split:true" style="width:500px;">
		<div class="easyui-accordion" data-options="fit:true,border:false" id="taskAssignAccordion">
		
				<div title="领导审批" id="assignExample" style="width=200px;border:1px solid #ccc;padding:10px;">
					 <ul>
                        <li>
                             <div>任务分配人：</div>
                             <div style="width=200px;border:1px solid #ccc;">
                               <ul  class="taskAssignee" style="width=200px;">
		                         <li class="drag-item">请选择任务分配人(选择组织结构的叶子节点，然后点击选择任务分配人)</li>
	                           </ul>
                            </div>
                            <br>
                        </li>
                        <li>
                             <div>任务候选人：</div>
                             <div style="border:1px solid #ccc;">
                               <ul  class="taskCandidateUser">
		                         <li class="drag-item">请选择任务候选人 (选择组织结构的叶子节点，然后点击选择任务候选人)</li>
	                           </ul>
                            </div>
                             <br>
                        </li>
                        
                        <li>
                            <div >任务候选分组：</div>
                            <div style="border:1px solid #ccc;">
                               <ul class="taskCandidateGroup">
		                         <li class="drag-item">请选择任务候选分组 (选择组织结构的非叶子节点，然后点击选择任务候选分组)</li>
	                           </ul>
                            </div>
                        </li>
                     </ul>
                     <br>
                     <a id="saveOrganizationBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>  
                     <br>
				</div>
				
			</div>
	</div>
	
    <div data-options="region:'west',title:'组织结构',split:true" style="width:250px;">
          <div id="organizationTree"></div>
    </div>   
    
    <div data-options="region:'center',iconCls:'icon-reload',title:'选择'">
    
           <div style="padding-top:160px;padding-left:20px;">
               <a id="selectAssigneeBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">选择任务分配人</a>  
           </div>
           
           <div style="padding-top:30px;padding-left:20px;">
               <a id="selectUserCandidateBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">选择任务候选人</a>  
           </div>
           
           <div style="padding-top:30px;padding-left:20px;">
               <a id="selectOrganizationBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">选择任务候选分组</a>  
           </div>

    </div>   
    

</div>  
