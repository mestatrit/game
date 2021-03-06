package com.wyd.server.handler.server;
import org.apache.log4j.Logger;

import com.wyd.empire.protocol.data.server.UpdateServerInfo;
import com.wyd.protocol.data.AbstractData;
import com.wyd.protocol.handler.IDataHandler;
import com.wyd.server.dispatcher.DispatchSession;
import com.wyd.server.service.ServiceManager;

/**
 * 类 <code>SendAddressHandler</code>处理登录服务器信息相关Handler
 * 
 * @see com.sumsharp.protocol.handler.IDataHandler
 * @since JDK 1.6
 */
public class UpdateServerInfoHandler implements IDataHandler {
	Logger log;

	public UpdateServerInfoHandler() {
		this.log = Logger.getLogger(UpdateServerInfoHandler.class);
	}

	public AbstractData handle(AbstractData data) throws Exception {
		UpdateServerInfo address = (UpdateServerInfo) data;
		DispatchSession session = (DispatchSession) data.getHandlerSource();
		System.out.println(address.getArea() + " " + address.getGroup() + " " + address.getMachineId());
		if (ServiceManager.getManager().getConfigService().exisMachine(address.getArea(), address.getGroup(), address.getMachineId())) {
			session.setId(address.getLine());
			session.setArea(address.getArea());
			session.setGroup(address.getGroup());
			session.setServerId(address.getMachineId());
			session.setAddress(address.getAddress());

			System.out.println("地区:" + address.getArea() + ", 组:" + address.getGroup() + ", 线:" + address.getLine() + ", 机器id:"
					+ address.getMachineId());
			ServiceManager.getManager().getLineService().addServer(address);
			this.log.info("Server [" + address.getArea() + "] [" + address.getGroup() + "] connected");
		} else {
			this.log.info("Server [" + address.getArea() + "] [" + address.getGroup() + "] connect fail");
			session.close();
		}
		return null;
	}
}
