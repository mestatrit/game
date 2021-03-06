package com.wyd.empire.protocol.data.chat;

import com.wyd.empire.protocol.Protocol;
import com.wyd.protocol.data.AbstractData;

public class ChangeChannel extends AbstractData {
	private int    channelId;
	public ChangeChannel(int sessionId, int serial) {
		super(Protocol.MAIN_CHAT, Protocol.CHAT_ChangeChannel, sessionId, serial);
	}

	public ChangeChannel() {
		super(Protocol.MAIN_CHAT, Protocol.CHAT_ChangeChannel);
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
}
