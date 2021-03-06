package com.wyd.empire.protocol.data.admin;
import com.wyd.empire.protocol.Protocol;
import com.wyd.protocol.data.AbstractData;
/**
 * 新增结果
 * @see AbstractData
 * @author mazheng
 */
public class AddByJsonResult extends AbstractData {
    private boolean success;
    public AddByJsonResult(int sessionId, int serial) {
        super(Protocol.MAIN_ADMIN, Protocol.ADMIN_AddByJsonResult, sessionId, serial);
    }

    public AddByJsonResult() {
        super(Protocol.MAIN_ADMIN, Protocol.ADMIN_AddByJsonResult);
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
