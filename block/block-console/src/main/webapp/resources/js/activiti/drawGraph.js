$.fn.outerHTML = function(){
 
    return (!this.length) ? this : (this[0].outerHTML || (
      function(el){
          var div = document.createElement('div');
          div.appendChild(el.cloneNode(true));
          var contents = div.innerHTML;
          div = null;
          return contents;
    })(this[0]));
 
};

function graphTrace(activiti,options) {
	
       var _defaults = {
            srcEle: activiti,
            processInstanceId: activiti.attr('processInstanceId'),
            processDefinitionId: activiti.attr('processDefId')
        };
        var opts = $.extend(true, _defaults, options); 
        
        // 获取图片资源
        var imageUrl = basePath+'/activiti/resource/read.do?processDefinitionId='+opts.processDefinitionId+'&resourceType=image';
        $.getJSON(basePath+'/activiti/process/allTrace.do?processInstanceId=' + opts.processInstanceId, function(res) {
        	
        	var infos=res.infos;
//        	console.log(infos);
        var positionHtml = "";

        // 生成图片
        var maxX =0;
        var maxY =0;
        var minX=document.documentElement.clientWidth * 0.95;
        var minY=document.documentElement.clientHeight * 0.95;
        var varsArray = new Array();
        $.each(infos, function(i, v) {
        	 
        	if(maxX<v.x ){
        		maxX = v.x ;
        	}
        	
        	if(minX>v.x ){
        		minX = v.x ;
        	}
        	
        	if(maxY<v.y ){
        		maxY = v.y ;
        	}
        	
        	if(minY>v.y ){
        		minY = v.y ;
        	}
        	
            var $positionDiv = $('<div/>', {
                'class': 'activity-attr'
            }).css({
                position: 'absolute',
                left: (v.x - 1),
                top: (v.y - 1),
                width: (v.width - 2),
                height: (v.height - 2),
                backgroundColor: 'black',
                opacity: 0,
                zIndex: $.fn.qtip.zindex - 1
            });

            // 节点边框
            var $border = $('<div/>', {
                'class': 'activity-attr-border'
            }).css({
                position: 'absolute',
                left: (v.x + 44 ),
                top: (v.y - 25),
                width: (v.width - 4),
                height: (v.height - 3),
                zIndex: $.fn.qtip.zindex - 2
            });

            if (v.currentActiviti) {
                $border.addClass('ui-corner-all-12').css({
                    border: '3px solid red'
                });
            }
            positionHtml += $positionDiv.outerHTML() + $border.outerHTML();
            varsArray[varsArray.length] = v.vars;
        });

        if ($('#workflowTraceDialog').length == 0) {
            $('<div/>', {
                id: 'workflowTraceDialog',
                title: '查看流程（按ESC键可以关闭）',
                html: "<div><img src='" + imageUrl + "' style='position:absolute; left:0px; top:0px; padding-top:50px; padding-left:50px;' />" +
                "<div id='processImageBorder'>" +
                positionHtml +
                "</div>" +
                "</div>"
            }).appendTo('body');
        } else {
            $('#workflowTraceDialog img').attr('src', imageUrl);
            $('#workflowTraceDialog #processImageBorder').html(positionHtml);
        }

        // 设置每个节点的data
        $('#workflowTraceDialog .activity-attr').each(function(i, v) {
            $(this).data('vars', varsArray[i]);
        });

        // 打开对话框
        $('#workflowTraceDialog').dialog({
            modal: true,
            resizable: false,
            dragable: false,
            open: function() {
                $('#workflowTraceDialog').dialog('option', 'title', '查看流程（按ESC键可以关闭）');
                $('#workflowTraceDialog').css('padding', '0.2em');
                $('#workflowTraceDialog .ui-accordion-content').css('padding', '0.2em').height($('#workflowTraceDialog').height() - 75);

                // 此处用于显示每个节点的信息，如果不需要可以删除
                $('.activity-attr').qtip({
                    content: function() {
                        var vars = $(this).data('vars');
                        var tipContent = "<table class='need-border'>";
                        $.each(vars, function(varKey, varValue) {
                            if (varValue) {
                                tipContent += "<tr><td class='label'>" + varKey + "</td><td>" + varValue + "<td/></tr>";
                            }
                        });
                        tipContent += "</table>";
                        return tipContent;
                    },
                    position: {
                        at: 'bottom left',
                        adjust: {
                            x: 3
                        }
                    }
                });
                // end qtip
            },
            close: function() {
                $('#workflowTraceDialog').remove();
            },
            width: maxX-minX+200,
            height: maxY-minY+200
        });

    });
}