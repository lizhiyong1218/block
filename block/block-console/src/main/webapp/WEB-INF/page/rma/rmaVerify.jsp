<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script src="${basePath}/resources/js/rma/rmaVerify.js?jsVersion=${jsVersion}"></script>

<div  class="easyui-panel" data-options="title:'审核信息',plain:true,fitWidth:true,cls:'mt20'" >
        	  <div data-options="region:'center',border:false">
        <div class="pd10">
        	<form id="rmaDetailForm" method="post">
            <div class="easyui-panel" data-options="title:'基本信息',plain:true,fitWidth:true,cls:'mt20'" >
                <div class="fl">
                    <table class="form-tb">
                       <col width=120 />
                       <col width="300" />
                       <tbody>
                        <tr>
                            <th>编号：</th>
                            <td>
                            	<div id="rmaNo"></div>
                            </td>
                            
                            <th>状态：</th>
                            <td>
                            <div id="state"></div>
							</td>
                        </tr>
                        
                        <tr>
                            <th>创建人：</th>
                            <td>
                            	<div id="createBy"></div>
                            </td>
                        </tr>
                      
                        <tr>
                            <th>下单日期：</th>
                            <td>
                            	<div id="createTime"></div>
                            </td>
                            
                             <th>计划退货日期：</th>
                            <td>
                            	<div id="planCloseDate"></div>
                            </td>
                        </tr>
                       
                        <tr>
                            <th>运输方式：</th>
                            <td>
                            	<div  id="shipVip"></div>
                            </td>
                        </tr>  
                         </tbody>                                                                  
                    </table>
                </div>
            </div>
            </form>
        </div>
        </div>
</div>