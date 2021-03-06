package com.wyd.empire.world.server.handler.errorlog;

import org.apache.log4j.Logger;

import com.wyd.empire.protocol.data.errorlog.SendLogList;
import com.wyd.empire.world.session.ConnectSession;
import com.wyd.protocol.data.AbstractData;
import com.wyd.protocol.handler.IDataHandler;

/**
 * 获取错误列表
 * 
 * @author Administrator
 * 
 */
public class SendLogListHandler implements IDataHandler {
	Logger log = Logger.getLogger(SendLogListHandler.class);

	@SuppressWarnings("unused")
	public void handle(AbstractData data) throws Exception {
		ConnectSession session = (ConnectSession) data.getHandlerSource();
		SendLogList sendLogList = (SendLogList) data;
		try {

		} catch (Exception ex) {
			log.error(ex, ex);
		}
	}
}
