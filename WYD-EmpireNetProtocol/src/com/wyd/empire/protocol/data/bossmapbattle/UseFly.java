package com.wyd.empire.protocol.data.bossmapbattle;

import com.wyd.empire.protocol.Protocol;
import com.wyd.protocol.data.AbstractData;

public class UseFly extends AbstractData {
	private int     battleId;
	private int     playerOrGuai;
	private int     currentId;

	public UseFly(int sessionId, int serial) {
		super(Protocol.MAIN_BOSSMAPBATTLE, Protocol.BOSSMAPBATTLE_UseFly, sessionId, serial);
	}

	public UseFly() {
		super(Protocol.MAIN_BOSSMAPBATTLE, Protocol.BOSSMAPBATTLE_UseFly);
	}

	public int getBattleId() {
		return battleId;
	}

	public void setBattleId(int battleId) {
		this.battleId = battleId;
	}

	public int getPlayerOrGuai() {
		return playerOrGuai;
	}

	public void setPlayerOrGuai(int playerOrGuai) {
		this.playerOrGuai = playerOrGuai;
	}

	public int getCurrentId() {
		return currentId;
	}

	public void setCurrentId(int currentId) {
		this.currentId = currentId;
	}

}
