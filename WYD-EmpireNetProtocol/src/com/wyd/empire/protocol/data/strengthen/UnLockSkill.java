package com.wyd.empire.protocol.data.strengthen;

import com.wyd.empire.protocol.Protocol;
import com.wyd.protocol.data.AbstractData;

public class UnLockSkill extends AbstractData {
    
    /** 武器ID*/
    private int weaponId; 

	public UnLockSkill(int sessionId, int serial) {
        super(Protocol.MAIN_STRENGTHEN, Protocol.STRENGTHEN_UnLockSkill, sessionId, serial);
    }

    public UnLockSkill() {
        super(Protocol.MAIN_STRENGTHEN, Protocol.STRENGTHEN_UnLockSkill);
    }

	public int getWeaponId() {
		return weaponId;
	}

	public void setWeaponId(int weaponId) {
		this.weaponId = weaponId;
	}
   

}
