package com.wyd.empire.protocol.data.cross;
import com.wyd.empire.protocol.Protocol;
import com.wyd.protocol.data.AbstractData;
public class CrossQuitBattle extends AbstractData {
    private int battleId;
    private int playerId;

    public CrossQuitBattle() {
        super(Protocol.MAIN_CROSS, Protocol.CROSS_CrossQuitBattle);
    }

    public int getBattleId() {
        return battleId;
    }

    public void setBattleId(int battleId) {
        this.battleId = battleId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
