package com.wyd.empire.protocol.data.cross;
import com.wyd.empire.protocol.Protocol;
import com.wyd.protocol.data.AbstractData;
public class CrossPostionsForPlayers extends AbstractData {
    private int   battleId;
    private int   playerId;
    private int   idcount;
    private int[] playerIds;
    private int[] postionX;
    private int[] postionY;

    public CrossPostionsForPlayers() {
        super(Protocol.MAIN_CROSS, Protocol.CROSS_CrossPostionsForPlayers);
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

    public int getIdcount() {
        return idcount;
    }

    public void setIdcount(int idcount) {
        this.idcount = idcount;
    }

    public int[] getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(int[] playerIds) {
        this.playerIds = playerIds;
    }

    public int[] getPostionX() {
        return postionX;
    }

    public void setPostionX(int[] postionX) {
        this.postionX = postionX;
    }

    public int[] getPostionY() {
        return postionY;
    }

    public void setPostionY(int[] postionY) {
        this.postionY = postionY;
    }
}
