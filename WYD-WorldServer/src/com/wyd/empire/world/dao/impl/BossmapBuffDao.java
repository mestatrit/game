package com.wyd.empire.world.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wyd.db.dao.impl.UniversalDaoHibernate;
import com.wyd.empire.world.bean.BossmapBuff;
import com.wyd.empire.world.dao.IBossmapBuffDao;

/**
 * The DAO class for the TabGuai entity.
 */
public class BossmapBuffDao extends UniversalDaoHibernate implements IBossmapBuffDao {
	private Map<Integer, BossmapBuff> bbMap = null;

	public BossmapBuffDao() {
		super();
	}

	public void initData() {
		Map<Integer, BossmapBuff> bbMap = new HashMap<Integer, BossmapBuff>();
		@SuppressWarnings("unchecked")
		List<BossmapBuff> bbList = getList("FROM BossmapBuff ", new Object[]{});
		for (BossmapBuff bb : bbList) {
			bbMap.put(bb.getId(), bb);
		}
		this.bbMap = bbMap;
	}

	public BossmapBuff getBossmapBuffById(int bbId) {
		return bbMap.get(bbId);
	}

	public List<BossmapBuff> findByGroup(int group) {
		List<BossmapBuff> bbList = new ArrayList<BossmapBuff>();
		for (BossmapBuff bb : bbMap.values()) {
			if (bb.getGroup() == group) {
				bbList.add(bb);
			}
		}
		return bbList;
	}
}