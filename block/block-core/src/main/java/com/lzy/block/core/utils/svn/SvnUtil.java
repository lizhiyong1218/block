package com.lzy.block.core.utils.svn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import com.lzy.block.api.common.Pagination;
import com.lzy.block.api.model.svn.SvnLogModel;

public class SvnUtil {
	
    // 资源库相关登录配置信息
    public static final String url = "https://192.168.0.250/svn/qfang";
    public static final String username = "lizhiyong";
    public static final String password = "123456";
    public static long lastRevision;//最后更新版本
    public static SVNRepository repository;
    static{
        try {
        	// 版本库初始化
            DAVRepositoryFactory.setup();
        	// 建立一个新的资源库
			repository  = DAVRepositoryFactory.create(SVNURL.parseURIEncoded(url));
			 // 登录确认信息
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username,password);
            repository.setAuthenticationManager(authManager);
            lastRevision  = repository.getLatestRevision();
		} catch (SVNException e) {
			e.printStackTrace();
		}
    }
	
//	public static List<SvnLogModel> getSVNLogs(int page,int pageSize) {
//		
//        // 版本库初始化
//        DAVRepositoryFactory.setup();
//  
//        // 资源库相关登录配置信息
//        String url = "https://192.168.0.250/svn/qfang";
//        String username = "lizhiyong";
//        String password = "123456";
//        long startRevision = 0;// 最初版本
//        long endRevision = -1;// 最近版本
//        // 资源库
//        SVNRepository repository = null;
//        List<SvnLogModel> svns = new ArrayList<SvnLogModel>();
//  
//        try {
//  
//            // 建立一个新的资源库
//            repository = DAVRepositoryFactory.create(SVNURL.parseURIEncoded(url));
//            // 登录确认信息
//            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username,password);
//            repository.setAuthenticationManager(authManager);
//            lastRevision = repository.getLatestRevision();
//            // 存放结果
//            final Collection logEntries = new ArrayList<SVNLogEntry>();
//            startRevision = lastRevision - ((page) * pageSize) + 1;
//            if (startRevision < 0) {
//                startRevision = 0;
//            }
//            endRevision = lastRevision - ((page - 1) * pageSize);
//            repository.log(new String[] { "/" }, logEntries, startRevision, endRevision, true, true);
//            // 遍历
//  
//            for (Iterator entries = logEntries.iterator(); entries.hasNext();) {
//                SVNLogEntry logEntry = (SVNLogEntry) entries.next();
//                SvnLogModel svn = new SvnLogModel();
//                svn.setMessage(logEntry.getMessage());
//                svn.setAuthor(logEntry.getAuthor());
//                svn.setDate(logEntry.getDate());
//                svn.setRevision(logEntry.getRevision());
//                String loge = "";
//                if (logEntry.getChangedPaths().size() > 0) {
//                    Set changedPathsSet = logEntry.getChangedPaths().keySet();
//                    for (Iterator changedPaths = changedPathsSet.iterator(); changedPaths.hasNext();) {
//                        SVNLogEntryPath entryPath = logEntry.getChangedPaths().get(changedPaths.next());
//                        char entrytype = entryPath.getType();
//                        String typeCN = "";
//                        if ("M".equals(String.valueOf(entrytype))) {
//                            typeCN = "修改";
//                        } else if ("A".equals(String.valueOf(entrytype))) {
//                            typeCN = "新增";
//                        } else if ("D".equals(String.valueOf(entrytype))) {
//                            typeCN = "删除";
//                        }
//  
//                        loge += " "
//                                + typeCN
//                                + " "
//                                + entryPath.getPath()
//                                + ((entryPath.getCopyPath() != null) ? " (from " + entryPath.getCopyPath()
//                                        + " revision " + entryPath.getCopyRevision() + ")" : "") + "\n";
//                    }
//                }
//                svn.setLogEntry(loge);
//                svn.setEntryCount(logEntry.getChangedPaths().size());
//                svns.add(svn);
//            }
//        } catch (Exception ex) {
//            System.out.println(ex.toString());
//        }
//        Collections.reverse(svns);
//        return svns;
//    }
	
	public static Pagination<SvnLogModel> getSVNLogPagination(int page,int pageSize) {
        long startRevision = 0;// 分页开始下标版本
        long endRevision = -1;// 分页结束下标版本
        List<SvnLogModel> svns = new ArrayList<SvnLogModel>();
        try {
            // 存放结果
            final Collection<SVNLogEntry> logEntries = new ArrayList<SVNLogEntry>();
            startRevision = lastRevision - ((page) * pageSize) + 1;
            if (startRevision < 0) {
                startRevision = 0;
            }
            endRevision = lastRevision - ((page - 1) * pageSize);
            repository.log(new String[] { "/" }, logEntries, startRevision, endRevision, true, true);
            // 遍历
            SVNLogEntry logEntry;//svn日志对象
            SvnLogModel svn;
            Set<?> changedPathsSet;//受影响的路径集合
            SVNLogEntryPath entryPath;//受影响的路径
            char entrytype;//svn日志类型
            String typeCN = "";//svn日志类型描述
            StringBuffer log;
            for (Iterator<SVNLogEntry> entries = logEntries.iterator(); entries.hasNext();) {
                logEntry = (SVNLogEntry) entries.next();
                svn = new SvnLogModel();
                svn.setMessage(logEntry.getMessage());
                svn.setAuthor(logEntry.getAuthor());
                svn.setDate(logEntry.getDate());
                svn.setRevision(logEntry.getRevision());
                log=new StringBuffer("");
                if (logEntry.getChangedPaths().size() > 0) {
                    changedPathsSet = logEntry.getChangedPaths().keySet();
                    for (Iterator<?> changedPaths = changedPathsSet.iterator(); changedPaths.hasNext();) {
                        entryPath = logEntry.getChangedPaths().get(changedPaths.next());
                        entrytype = entryPath.getType();
                        if ("M".equals(String.valueOf(entrytype))) {
                            typeCN = "修改";
                        } else if ("A".equals(String.valueOf(entrytype))) {
                            typeCN = "新增";
                        } else if ("D".equals(String.valueOf(entrytype))) {
                            typeCN = "删除";
                        }
                        log.append(" ").append(typeCN).append(" ").append(entryPath.getPath()).append((entryPath.getCopyPath() != null) ? " (from " + entryPath.getCopyPath()
                                + " revision " + entryPath.getCopyRevision() + ")" : "").append("\n");
                    }
                }
                svn.setLogEntry(log.toString());
                svn.setEntryCount(logEntry.getChangedPaths().size());
                svns.add(svn);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        Collections.reverse(svns);
        Pagination<SvnLogModel> pm=new Pagination<SvnLogModel>(page, pageSize, lastRevision, svns);
        return pm;
    }
	
	
	public static void main(String[] args) {
		Pagination<SvnLogModel> svnLogPagination = getSVNLogPagination(1, 20);
		System.out.println(svnLogPagination.getRecordCount());
		System.out.println(svnLogPagination.getRecordList());
	}
}
