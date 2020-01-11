package net.tiny.feature.dao;

import java.util.List;

import net.tiny.dao.BaseDao;
import net.tiny.feature.entity.Tag;
import net.tiny.feature.entity.Tag.Type;

/**
 * TagDao - 标签
 *
 */
public class TagDao extends BaseDao<Tag, Long> {

    /**
     * 查找标签
     *
     * @param type
     *            类型
     * @return 标签
     */
    public List<Tag> findList(Type type) {
        return super.getNamedQuery("Tag.findList").setParameter("type", type).getResultList();
    }
}
