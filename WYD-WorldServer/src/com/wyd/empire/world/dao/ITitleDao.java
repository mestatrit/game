package com.wyd.empire.world.dao;

import com.wyd.db.dao.UniversalDao;
import com.wyd.empire.world.bean.Title;

/**
 * The DAO interface for the TabChatRecord entity.
 */
public interface ITitleDao extends UniversalDao {
	/**
	 * 初始化称号基础数据
	 */
	public void initData();

	/**
	 * 获取所有称号数量
	 * 
	 * @return
	 */
	public int getAllTitleSize();

	/**
	 * 获取指定称号
	 * 
	 * @param titleId
	 * @return
	 */
	public Title getTitleById(int titleId);
}