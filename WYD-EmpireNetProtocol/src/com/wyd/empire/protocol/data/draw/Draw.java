package com.wyd.empire.protocol.data.draw;

import com.wyd.empire.protocol.Protocol;
import com.wyd.protocol.data.AbstractData;

public class Draw extends AbstractData {
	
	private int typeId;   //玩家选择的抽奖类型的物品ID
	
    public Draw(int sessionId, int serial) {
        super(Protocol.MAIN_DRAW, Protocol.DRAW_Draw, sessionId, serial);
    }

    public Draw() {
        super(Protocol.MAIN_DRAW, Protocol.DRAW_Draw);
    }

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
}
