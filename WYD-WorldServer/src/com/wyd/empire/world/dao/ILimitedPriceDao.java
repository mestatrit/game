package com.wyd.empire.world.dao;

import com.wyd.db.dao.UniversalDao;
import com.wyd.empire.world.bean.LimitedPrice;

/**
 * The DAO interface for the TabPlayeritemsfromshop entity.
 */
public interface ILimitedPriceDao extends UniversalDao {
	public LimitedPrice getByCount(int itemdId, int count);

	/**
	 * 获取最大购买次数
	 * 
	 * @param count
	 * @return
	 */
	public int getMaxCount(int itemdId, int vipLevel);

}