package com.wyd.empire.protocol.data.account;
import com.wyd.protocol.data.AbstractData;
import com.wyd.empire.protocol.Protocol;
/**
 * 帐号验证结果
 * @see AbstractData
 * @author mazheng
 */
public class VerificationResult extends AbstractData {
    private int 	status;	    // 0验证通过，1验证失败
    public VerificationResult(int sessionId, int serial) {
        super(Protocol.MAIN_ACCOUNT, Protocol.ACCOUNT_VerificationResult, sessionId, serial);
    }

    public VerificationResult() {
        super(Protocol.MAIN_ACCOUNT, Protocol.ACCOUNT_VerificationResult);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
