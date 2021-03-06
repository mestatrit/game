package com.wyd.empire.world.server.service.base;

import java.util.List;

import com.wyd.db.page.PageList;
import com.wyd.db.service.UniversalManager;
import com.wyd.empire.world.bean.PlayerPicture;

public interface IPictureUploadService extends UniversalManager {

	/**
	 * 获取玩家头像上传对象
	 * 
	 * @param playerId
	 * @return
	 */
	public PlayerPicture getPictureUploadById(int playerId);

	/**
	 * GM工具--查询玩家自定义信息情况
	 * 
	 * @param key
	 *            查询条件
	 * @param pageIndex
	 *            当前页
	 * @param pageSize
	 *            每页显示多少条
	 * @return
	 */
	public PageList getPicturePageList(String key, int pageIndex, int pageSize);

	/**
	 * GM工具--查询玩家自定义信息情况
	 * 
	 * @param ids
	 * @return
	 */
	public List<PlayerPicture> getPictureListById(String ids);
}
