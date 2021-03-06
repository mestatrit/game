package com.wyd.empire.protocol.data.admin;
import com.wyd.empire.protocol.Protocol;
import com.wyd.protocol.data.AbstractData;
/**
 * 封禁用户结果
 * @see AbstractData
 * @author mazheng
 */
public class AddResult extends AbstractData {
    private boolean success;
    public AddResult(int sessionId, int serial) {
        super(Protocol.MAIN_ADMIN, Protocol.ADMIN_AddResult, sessionId, serial);
    }

    public AddResult() {
        super(Protocol.MAIN_ADMIN, Protocol.ADMIN_AddResult);
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
