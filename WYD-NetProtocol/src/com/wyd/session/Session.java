package com.wyd.session;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import com.wyd.protocol.data.AbstractData;
import com.wyd.protocol.exception.ProtocolException;
public abstract class Session {
    protected IoSession session;
    protected int       sessionId;

    public Session(IoSession session) {
        this.session = session;
    }

    public Session() {
    }

    public abstract <T> void handle(T paramT);

    public abstract void closed();

    public abstract void created();

    public abstract void opened();

    public abstract void idle(IdleStatus paramIdleStatus);

    public void defaultHandle() {
    }

    public IoSession getIoSession() {
        return this.session;
    }

    public void setIoSession(IoSession session) {
        this.session = session;
    }

    public void close() {
        if ((this.session != null) && (!(this.session.isClosing()))) {
            this.session.close(true);
        }
    }

    public void write(AbstractData seg) {
        this.session.write(seg);
    }

    public void reply(AbstractData data) {
        write(data);
    }

    public int getSessionId() {
        return this.sessionId;
    }

    public void forward(AbstractData data, int sessionId) {
        data.setSessionId(sessionId);
        write(data);
    }

	public void sendError(ProtocolException e) {
	}
}