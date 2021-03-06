package com.wyd.dispatch;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.wyd.empire.protocol.Protocol;
import com.wyd.protocol.INetData;
public class TimeControlProcessor implements ControlProcessor, Runnable {
	private static TimeControlProcessor controlProcessor = new TimeControlProcessor();
	private static final Logger log = Logger.getLogger(TimeControlProcessor.class);
	public static final short ADMIN_ADDIP = 243;
	public static final short FINITERELOAD = 195;
	private ChannelService channelService;// 通道服务
	private Dispatcher dispatcher;
	private IpdService ipdService;
	// private TrustIpService trustIpService;
	private ConfigMenger configuration;
	private BlockingQueue<INetData> datas = new LinkedBlockingQueue<INetData>();

	private TimeControlProcessor() {
	}
	public static TimeControlProcessor getControlProcessor() {
		return controlProcessor;
	}

	public void start() {
		Thread thread = new Thread(this);
		thread.setName("Control");
		thread.start();
		log.info("TimeControlProcessor Control start.");
	}

	public void setChannelService(ChannelService channelService) {
		this.channelService = channelService;
	}

	public void setConfiguration(ConfigMenger configuration) {
		this.configuration = configuration;
	}
	// public void setTrustIpService(TrustIpService trustIpService) {
	// this.trustIpService = trustIpService;
	// }
	public void setDispatcher(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	public void setIpdService(IpdService ipdService) {
		this.ipdService = ipdService;
	}
	/** 添加执行任务 */
	public void process(INetData data) {
		try {
			datas.put(data);
		} catch (InterruptedException ex1) {
			log.info("TimeControlProcessor process INetData Exception.");
		}
	}

	private void processServerMsg(INetData data) {
		byte type = data.getSubType();
		try {
			switch (type) {
				case Protocol.SERVER_NotifyMaintance : // '\037'
					maintance(data);// 设置服务器状态状态
					break;
				case Protocol.SERVER_NotifyMaxPlayer : // '\031'
					maxPlayer(data);
					break;
				case Protocol.SERVER_BroadCast : // '\027'
					broadcast(data);
					break;
				case Protocol.SERVER_ForceBroadCast : // '\028'
					forceBroadcast(data);
					break;
				case Protocol.SERVER_Kick : // '\029' 提玩家下线
					kick(data);
					break;
				case Protocol.SERVER_ShutDown : // '\030'
					shutdown();
					break;
				case Protocol.SERVER_UpdateServerInfo : // '\091'
					updateServerInfo(data);
					break;
			}
		} catch (Exception ex) {
			log.error(ex, ex);
		}
	}

	/**
	 * 设置服务器状态状态
	 * 
	 * @param data
	 */
	private void maintance(INetData data) {
		try {
			boolean maintance = data.readBoolean();
			if (ipdService != null)
				ipdService.connect(-1, -1, maintance);
			// configuration.setProperty("maintance",
			// Boolean.valueOf(maintance));
			log.info("maintance:" + maintance);
		} catch (Exception e) {
			log.error(e, e);
		}
	}

	private void processChannelMsg(INetData data) {
		byte type = data.getSubType();
		try {
			switch (type) {
				case Protocol.CHAT_SyncChannels :
					syncChannel(data);
					break;
			}
		} catch (Exception ex) {
			log.error(ex, ex);
		}
	}
	/** 聊天处理 */
	private void syncChannel(INetData data) throws Exception {
		int sessionId = data.readInt();
		IoSession session = dispatcher.getSession(sessionId);
		if (session != null) {
			String aChannels[] = data.readStrings();
			String rChannels[] = data.readStrings();
			for (int i = 0; i < aChannels.length; i++) {
				Channel channel = channelService.getAndCreate(aChannels[i]);
				if (channel != null)
					channel.join(session);
			}
			for (int i = 0; i < rChannels.length; i++) {
				Channel channel = channelService.getChannel(rChannels[i]);
				if (channel != null)
					channel.removeSession(session);
			}
		}
	}
	/** 任务处理 */
	protected void process0(INetData data) {
		byte type = data.getType();
		try {
			switch (type) {
				case Protocol.MAIN_SERVER ://服务器间协议
					processServerMsg(data);
					break;
				case Protocol.MAIN_CHAT :
					processChannelMsg(data);
					break;
			}
		} catch (Exception ex) {
			log.error(ex, ex);
		}
	}

	public void run() {
		while (true) {
			try {
				// 检索并移除此队列datas的头部，如果此队列不存在任何元素，则一直等待。
				INetData data = (INetData) datas.take();
				process0(data);
			} catch (InterruptedException ex) {
				log.error(ex, ex);
			}
		}
	}

	// private void clearChannels(INetData data) throws Exception {
	// int sessionId = data.readInt();
	// IoSession session = dispatcher.getSession(sessionId);
	// if (session != null) channelService.clearChannels(session);
	// }
	/** 重新开启服务 */
	private void shutdown() {
		dispatcher.shutdown();
	}

	/**
	 * 广播线上所有用户 此方法调用 SocketDispatcher中的broadcast方法
	 * 
	 * @see com.wyd.dispatch.SocketDispatcher
	 * @param data
	 * @throws Exception
	 */
	private void forceBroadcast(INetData data) throws Exception {
		dispatcher.broadcast(IoBuffer.wrap(data.readBytes()));
	}

	/**
	 * 读取对应通道，向该通道上广播数据
	 * 
	 * @param data
	 * @throws Exception
	 */
	private void broadcast(INetData data) throws Exception {
		Channel channel = channelService.getChannel(data.readString());
		if (channel != null)
			channel.broadcast(IoBuffer.wrap(data.readBytes()));
	}

	/**
	 * 读取客户端返回的游戏人数,服务器状态
	 * 
	 * @param data
	 * @throws Exception
	 */
	private void maxPlayer(INetData data) throws Exception {
		int current = data.readInt();
		int maxPlayer = data.readInt();
		long c = data.readLong();
		log.info((new StringBuilder()).append("SyncTime[").append(System.currentTimeMillis() - c).append("] ONLINE[").append(current)
				.append("] MAX[").append(maxPlayer).append("]").toString());
		if (ipdService != null) {
			ipdService.connect(current, maxPlayer, configuration.getConfiguration().getBoolean("maintance", true));
		}
	}

	/**
	 * 更新服务器信息
	 * 
	 * @param data
	 * @throws Exception
	 */
	private void updateServerInfo(INetData data) throws Exception {
		String area = data.readString();
		int machine = data.readInt();
		data.readInt();
		String version = data.readString();
		String updateurl = data.readString();
		String remark = data.readString();
		String appraisal = data.readString();
		String group = data.readString();
		if (ipdService != null) {
			ipdService.updateServerInfo(area, group, machine, version, updateurl, remark, appraisal);
		}
	}
	/** 踢玩家下线 */
	private void kick(INetData data) throws Exception {
		int sessionId = data.readInt();
		dispatcher.unRegisterClient(sessionId);
	}
}