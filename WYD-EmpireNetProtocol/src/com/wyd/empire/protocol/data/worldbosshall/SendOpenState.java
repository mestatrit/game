package com.wyd.empire.protocol.data.worldbosshall;

import com.wyd.empire.protocol.Protocol;
import com.wyd.protocol.data.AbstractData;
/**
 * 返回开启状态
 * @author zengxc
 *
 */
public class SendOpenState extends AbstractData {
	private boolean state;//
	public boolean getState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public SendOpenState(int sessionId, int serial) {
        super(Protocol.MAIN_WORLDBOSSHALL, Protocol.WORLDBOSSHALL_SendOpenState, sessionId, serial);
    }
	public SendOpenState(){
		super(Protocol.MAIN_WORLDBOSSHALL, Protocol.WORLDBOSSHALL_SendOpenState);
	}

}
