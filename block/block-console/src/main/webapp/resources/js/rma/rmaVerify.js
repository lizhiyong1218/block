var RmaBusiness = {
	create : function() {
		var business = {};
		business.init = function(businessId, taskId) {
			/*$.ajax({
				type : "POST",
				url : '../rma/rmaDetailByNo.do',
				data : {
					rmaNo : businessId
				},
				success : function(res) {
					var rma = eval("(" + res + ")");// 转换为json对象
					$("#rmaNo").html(rma.rmaNo);
					$("#state").html(stateFormatter(rma.state));
					$("#createBy").html(rma.createBy);
					$("#createTime").html(dateFormatter(rma.createTime));
					$("#planCloseDate").html(dateFormatter(rma.planCloseDate));
					$("#shipVip").html(shipVipFormatter(rma.shipVip));
					$("#vendorNo").html(rma.vendorNo);
				}
			});*/
		};

		var workflowVerifyConfirmDialog;
		
		business.complete = function(taskId, complete) {
			var rmaNo = $('input[name=businessId]').val();
			$('<div/>',{
				title : '填写备注信息',
				html : "<textarea id='checkDescPass' style='width: 300px; height: 150px;'></textarea>"
			}).dialog({
			modal : true,
			
			onOpen:function(){
				workflowVerifyConfirmDialog = $(this);
		    },
		    
		    onClose:function(){
		    	workflowVerifyConfirmDialog.dialog('destroy');
	        },
			
			buttons : [ {
				text : '通过',
				handler : function() {
					var checkDesc = $('#checkDescPass').val();

					// 设置流程变量
					complete(taskId, [ {
						key : 'checkState',
						value : '1',
						type : 'S'
					}, {
						key : 'checkDesc',
						value : checkDesc,
						type : 'S'
					}, {
						key : 'rmaNo',
						value : rmaNo,
						type : 'S'
					} ]);
					workflowVerifyConfirmDialog.dialog('destroy');
					workflow.close();
					$('#tbList').datagrid("reload"); 
				}
			}, {
				text : '取消',
				handler : function() {
					workflowVerifyConfirmDialog.dialog('destroy');
				}
			} ]
		});
			
			
			
			
		};

		business.revert = function(taskId, complete) {
			$('<div/>',{
					title : '填写驳回理由',
					html : "<textarea id='checkDesc' style='width: 300px; height: 150px;'></textarea>"
				}).dialog({
				modal : true,

				onOpen:function(){
					workflowVerifyConfirmDialog = $(this);
			    },
			    
			    onClose:function(){
			    	workflowVerifyConfirmDialog.dialog('destroy');
		        },
				
				buttons : [ {
					text : '驳回',
					handler : function() {
						var checkDesc = $('#checkDesc').val();
						if (checkDesc == '') {
							alert('请输入驳回理由！');
							return;
						}

						// 设置流程变量
						complete(taskId, [ {
							key : 'checkState',
							value : '0',
							type : 'S'
						}, {
							key : 'reason',
							value : checkDesc,
							type : 'S'
						}, {
							key : 'rmaNo',
							value : rmaNo,
							type : 'S'
						} ]);
						workflowVerifyConfirmDialog.dialog('destroy');
						workflow.close();
						$('#tbList').datagrid("reload"); 
					}
				}, {
					text : '取消',
					handler : function() {
						workflowVerifyConfirmDialog.dialog('destroy');
					}
				} ]
			});
		};

		business.suspend = function(taskId, complete) {

		};
		return business;
	}

};

/**
 * 状态格式化
 * @param val
 * @returns
 *//*
function stateFormatter(val){
	if(val == '0'){
		return '已取消';
	}else if(val == '1'){
		return '已完成';
	}else if(val == '2'){
		return '待供应商换货';
	}else if(val == '3'){
		return '待供应商退款';
	}else if(val == '4'){
		return '部分发货';
	}else if(val == '5'){
		return '待发货';
	}else if(val == '6'){
		return '已驳回';
	}else if(val == '7'){
		return '待审核';
	}else if(val == '8'){
		return '同步失败';
	}else{
		return val;
	}
}
*/
/**
 * 货运方式格式化
 * @param val
 * @returns
 */
/*function shipVipFormatter(val){
	if(val == '0'){
		return '快递';
	}else if(val == '1'){
		return '物流';
	}else if(val == '2'){
		return '上门自提';
	}else{
		return val;
	}
}*/

/**
 * 时间格式化
 * @param val
 * @returns
 */
/*function dateFormatter(val){
	return val.substring(0,10);
	
}*/

var workflow = Workflow.create(RmaBusiness.create());
workflow.buttons.pass.text = '通过审核';
workflow.addPassButton().addRevertButton().init();
