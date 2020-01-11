package net.tiny.feature.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import net.tiny.dao.BaseDao;

public abstract class BaseDaoTest {

    @PersistenceContext(unitName = "persistenceUnit")
    protected EntityManager entityManager;

    @Resource
    protected DataSource dataSource;

    protected <T> T createDao(Class<T> daoType) throws Exception {
        BaseDao<?, ?> dao = (BaseDao<?, ?>)daoType.newInstance();
        dao.setEntityManager(entityManager);
        return daoType.cast(dao);
    }

    protected LocalDate createDate(String date)  {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    protected Connection getJdbcConnection() throws SQLException  {
        return dataSource.getConnection();
    }


    protected void begin()  {
        entityManager.getTransaction().begin();
    }

    protected void commit()  {
        entityManager.getTransaction().commit();
    }

    protected void commitAndContinue() {
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
    }
}
