package net.tiny.feature.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import net.tiny.dao.BaseDao;
import net.tiny.feature.entity.Admin;
import net.tiny.feature.entity.Role;
/**
 * AdminDao - 管理员
 *
 */
public class AdminDao extends BaseDao<Admin, Long> {

    /**
     * 判断用户名是否存在
     *
     * @param username
     *            用户名
     * @return 用户名是否存在
     */
    public boolean exists(String username) {
        if (username == null) {
            return false;
        }
        //String jpql = "select count(*) from Admin admin where lower(admin.username) = lower(:username)";
        //Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
        return (null != findByUsername(username));
    }

    /**
     * 根据用户名查找管理员
     *
     * @param username
     *            用户名
     * @return 管理员，若不存在则返回null
     */
    public Admin findByUsername(String username) {
        if (username == null) {
            return null;
        }
        try {
            //String jpql = "select admin from Admin admin where lower(admin.username) = lower(:username)";
            return getNamedQuery("Admin.findByUsername").setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * 根据用户ID查找角色
     *
     * @param id
     *            用户ID
     * @return 用户角色
     */
    public List<String> findAuthorities(Long id) {
        List<String> authorities = new ArrayList<String>();
        Admin admin = find(id);
        if (admin != null) {
            for (Role role : admin.getRoles()) {
                authorities.addAll(role.getAuthorities());
            }
        }
        return authorities;
    }
}
