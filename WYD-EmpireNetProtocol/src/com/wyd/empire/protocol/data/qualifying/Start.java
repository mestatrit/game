package com.wyd.empire.protocol.data.qualifying;

import com.wyd.empire.protocol.Protocol;
import com.wyd.protocol.data.AbstractData;

public class Start extends AbstractData {
	private int playerScore;
	
	public Start(int sessionId, int serial) {
		super(Protocol.MAIN_QUALIFYING, Protocol.QUALIFYING_Start, sessionId, serial);
	}

	public Start() {
		super(Protocol.MAIN_QUALIFYING, Protocol.QUALIFYING_Start);
	}

	public int getPlayerScore() {
		return playerScore;
	}

	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}

	
}
