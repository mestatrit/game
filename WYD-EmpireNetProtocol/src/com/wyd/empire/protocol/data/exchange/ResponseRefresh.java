package com.wyd.empire.protocol.data.exchange;
import com.wyd.protocol.data.AbstractData;
import com.wyd.empire.protocol.Protocol;
/**
 * 
 * @see AbstractData
 * @author zhaopeilong
 */
public class ResponseRefresh extends AbstractData {
	
	private	int	code; //0表示成功，1表示失败
	private	String	message; //提示语


    public ResponseRefresh(int sessionId, int serial) {
        super(Protocol.MAIN_EXCHANGE, Protocol.EXCHANGE_ResponseRefresh, sessionId, serial);
    }

    public ResponseRefresh() {
        super(Protocol.MAIN_EXCHANGE, Protocol.EXCHANGE_ResponseRefresh);
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
