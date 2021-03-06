package com.wyd.empire.world.session;

import org.apache.mina.core.session.IoSession;

import com.wyd.empire.world.server.service.factory.ServiceManager;
import com.wyd.protocol.data.AbstractData;
import com.wyd.session.Session;
import com.wyd.session.SessionHandler;
import com.wyd.session.SessionRegistry;

/**
 * 类 SessionHandler Session处理类 继承SessionHandler ，由子类负责创建不同类型的Session类<br>
 * 抽象Session处理方法，实现基本处理逻辑
 * 
 * @since JDK 1.6
 */
public abstract class WorldHandler extends SessionHandler {

	/**
	 * 构造函数，初始化<tt>SessionRegistry</tt>值
	 * 
	 * @param registry
	 */
	public WorldHandler(SessionRegistry registry) {
		super(registry);
	}

	/*
	 * 处理dis 转发过来的消息
	 */
	@Override
	public void messageReceived(IoSession ioSession, Object msg) throws Exception {
		AbstractData dataobj = (AbstractData) msg;
		// System.out.println(dataobj.toString());
		Session session = this.registry.getSession(ioSession);
		if (session != null)
			ServiceManager.getManager().getAbstractService().addAbstractInfo(dataobj, session);
	}
}
