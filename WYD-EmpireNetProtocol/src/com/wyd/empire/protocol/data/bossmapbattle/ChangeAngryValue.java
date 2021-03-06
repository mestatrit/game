package com.wyd.empire.protocol.data.bossmapbattle;

import com.wyd.empire.protocol.Protocol;
import com.wyd.protocol.data.AbstractData;

public class ChangeAngryValue extends AbstractData {
	private int     battleId;
	private int     playerOrGuai;//0:player 1:guai
	private int		currentId;//角色id
	private int     angryValue;
	
	public ChangeAngryValue(int sessionId, int serial) {
		super(Protocol.MAIN_BOSSMAPBATTLE, Protocol.BOSSMAPBATTLE_ChangeAngryValue, sessionId, serial);
	}

	public ChangeAngryValue() {
		super(Protocol.MAIN_BOSSMAPBATTLE, Protocol.BOSSMAPBATTLE_ChangeAngryValue);
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

	public int getAngryValue() {
		return angryValue;
	}

	public void setAngryValue(int angryValue) {
		this.angryValue = angryValue;
	}
}
