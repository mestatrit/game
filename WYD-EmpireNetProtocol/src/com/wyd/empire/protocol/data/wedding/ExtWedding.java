package com.wyd.empire.protocol.data.wedding;
import com.wyd.protocol.data.AbstractData;
import com.wyd.empire.protocol.Protocol;
/**
 * 
 * @see AbstractData
 * @author zhaopeilong
 */
public class ExtWedding extends AbstractData {

	private String wedNum;	//婚礼编号
    public ExtWedding(int sessionId, int serial) {
        super(Protocol.MAIN_WEDDING, Protocol.WEDDING_ExtWedding, sessionId, serial);
    }

    public ExtWedding() {
        super(Protocol.MAIN_WEDDING, Protocol.WEDDING_ExtWedding);
    }

	public String getWedNum() {
		return wedNum;
	}

	public void setWedNum(String wedNum) {
		this.wedNum = wedNum;
	}

}
