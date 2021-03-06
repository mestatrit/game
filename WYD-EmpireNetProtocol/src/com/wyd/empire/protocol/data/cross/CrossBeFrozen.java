package com.wyd.empire.protocol.data.cross;

import com.wyd.empire.protocol.Protocol;
import com.wyd.protocol.data.AbstractData;
/**
 * 玩家被冰冻
 * @author zgq
 *
 */
public class CrossBeFrozen extends AbstractData {
    private int battleId;
    private int[] playerIds;

	public CrossBeFrozen() {
		super(Protocol.MAIN_CROSS, Protocol.CROSS_CrossBeFrozen);
	}

    public int getBattleId() {
        return battleId;
    }

    public void setBattleId(int battleId) {
        this.battleId = battleId;
    }

    public int[] getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(int[] playerIds) {
        this.playerIds = playerIds;
    }
}
