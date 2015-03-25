package com.wyd.server.dispatcher;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyd.server.service.ConfigService;
import com.wyd.server.service.ServerInfo;
import com.wyd.server.service.ServiceManager;
public class DispatcherServlet extends HttpServlet {
	/*
	 * 玩家ip 申请
	 * 
	*/
    private static final String CONTENT_TYPE     = "text/html;charset=utf-8";
    private static final long   serialVersionUID = 110325631288123751L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	Map<String, Object> serverData = new HashMap<String, Object>();
    	String area = req.getParameter("area");
        String group = req.getParameter("group");
        String channel = req.getParameter("channel");
        String serverid = req.getParameter("serverid");
        String versionString = req.getParameter("version");
        
        if (null == area) area = "CN";
        if (null == versionString) versionString = "1.0.0";
        if (null == channel) channel = "1000";
        if (serverid == null) serverid = "0";
        
        if (!ServiceManager.getManager().getConfigService().exisArea(area)) {
            area = ServiceManager.getManager().getConfiguration().getString("defaultArea");
        }
        group = channel+"_"+group;
        if (!ServiceManager.getManager().getConfigService().exisGroup(area,group)) {
            group = ServiceManager.getManager().getConfiguration().getString("defaultGroup");
        }
        ServerInfo serverInfo = null;
        if (ServiceManager.getManager().getVersionService().isTestVersion(versionString) 
        		|| ServiceManager.getManager().getVersionService().isTestChannel(channel)) {
            serverInfo = ServiceManager.getManager().getServerListService().getTestServerInfo(area,group);
        }
        if (serverInfo == null) {
        	ConfigService sss = ServiceManager.getManager().getConfigService();
            if (ServiceManager.getManager().getConfigService().exisMachine(area, group, Integer.parseInt( serverid))) {
                serverInfo = ServiceManager.getManager().getServerListService().getServerInfoMap().get(area).get(group).get(Integer.parseInt( serverid));
            } else {
                serverInfo = ServiceManager.getManager().getServerListService().getServerInfo(area, group);//根据几率获取一个服
            }
        }
        
        if(serverInfo != null){
        	serverData = ServiceManager.getManager().getUserInfoService().getLineInfo(serverInfo, versionString, channel);	
        }else{
        	serverData.put("msg", ServiceManager.getManager().getConfiguration().getString("busyMessage"));
        }
        
        String sendStr = JSON.toJSONString(serverData);
        resp.setContentType(CONTENT_TYPE);
        resp.setStatus(200);
        ServletOutputStream out = resp.getOutputStream();
        OutputStreamWriter os = new OutputStreamWriter(out, "utf-8");
        os.write(sendStr);
        os.flush();
        os.close();
    }
}
