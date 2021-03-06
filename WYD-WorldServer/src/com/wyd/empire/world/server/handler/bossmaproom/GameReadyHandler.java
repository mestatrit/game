package com.wyd.empire.world.server.handler.bossmaproom;

import org.apache.log4j.Logger;

import com.wyd.empire.protocol.data.bossmaproom.GameReady;
import com.wyd.empire.world.bossmaproom.BossRoom;
import com.wyd.empire.world.exception.ErrorMessages;
import com.wyd.empire.world.server.service.factory.ServiceManager;
import com.wyd.protocol.data.AbstractData;
import com.wyd.protocol.exception.ProtocolException;
import com.wyd.protocol.handler.IDataHandler;

/**
 * 玩家准备游戏
 * 
 * @author Administrator
 */
public class GameReadyHandler implements IDataHandler {
	Logger log = Logger.getLogger(GameReadyHandler.class);

	// 读取房间列表
	public void handle(AbstractData data) throws Exception {
		GameReady gameReady = (GameReady) data;
		try {
			BossRoom room = ServiceManager.getManager().getBossRoomService().getRoom(gameReady.getRoomId());
			if (null == room) {
				throw new ProtocolException(ErrorMessages.ROOM_NOTFOUND_MESSAGE, data.getSerial(), data.getSessionId(), data.getType(),
						data.getSubType());
			}
			if (1 == room.getBattleStatus()) {
				throw new ProtocolException(ErrorMessages.ROOM_UPDATE_MESSAGE, data.getSerial(), data.getSessionId(), data.getType(),
						data.getSubType());
			}
			room.getPlayerList().get(gameReady.getOldSeat()).setReady(gameReady.getReady());
			ServiceManager.getManager().getBossRoomService().SynRoomInfo(gameReady.getRoomId());

		} catch (ProtocolException ex) {
			throw ex;
		} catch (Exception ex) {
			log.error(ex, ex);
			throw new ProtocolException(ErrorMessages.ROOM_NOTFOUND_MESSAGE, data.getSerial(), data.getSessionId(), data.getType(),
					data.getSubType());
		}
	}
}
