package com.wyd.empire.world.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.wyd.db.dao.impl.UniversalDaoHibernate;
import com.wyd.empire.world.bean.Title;
import com.wyd.empire.world.dao.ITitleDao;

/**
 * The DAO class for the TabConsortiaright entity.
 */
public class TitleDao extends UniversalDaoHibernate implements ITitleDao {

	private Map<Integer, Title> titleMap = null;

	public TitleDao() {
		super();
	}

	@SuppressWarnings("unchecked")
	public void initData() {
		List<Title> titleList = getList("FROM Title ", new Object[]{});
		Map<Integer, Title> titleMap = new HashMap<Integer, Title>();
		for (Title title : titleList) {
			titleMap.put(title.getId(), title);
		}
		this.titleMap = titleMap;
	}

	public int getAllTitleSize() {
		return this.titleMap.size();
	}

	public Title getTitleById(int titleId) {
		return titleMap.get(titleId);
	}
}