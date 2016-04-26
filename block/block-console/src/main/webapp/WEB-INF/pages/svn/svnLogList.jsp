<%@ page language="java" contentType="text/html; charset=UTF-8"%>

	<div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north'"
            style="height:50px;border-top-style: none;border-left-style: none;border-right-style: none;">
  
            <div class="well-small" style="margin-left: 5px;margin-top: 5px">
                <span class="badge">提示</span>SVN日志管理集成了SVN，通过SVNKit查看系统开发版本的进度。
            </div>
        </div>
        <div data-options="region:'west',split: true," style="width:700px">
                  <table id="dg" title="SVN更新日志"></table>
        </div>  
        <div data-options="region:'center',split: true," title="受影响的文件、目录">
			<table>
				<tr>
					<td id="logfile"></td>
				</tr>
			</table>
		</div>
    </div>
 <!-- -->
 
 <script	src="${basePath}/resources/js/svn/svnLogList.js?jsVersion=${jsVersion}"></script>
 
