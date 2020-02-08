package net.tiny.feature.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import net.tiny.dao.CSVLoader;
import net.tiny.dao.Dao;
import net.tiny.dao.IDao;
import net.tiny.feature.entity.Admin;
import net.tiny.feature.entity.Token;
import net.tiny.unit.db.Database;

@Database(persistence="persistence-eclipselink.properties"
  ,trace=true
  ,createScript= "src/test/resources/sql/create_sequence.sql"
)
public class AdminDaoTest extends BaseDaoTest {

    @Test
    public void testAdminWithToken() throws Exception {

        super.begin();

        IDao<Admin, Long> adminDao = Dao.getDao(super.entityManager, Admin.class);
        long count = adminDao.count();
        System.out.println("Admin count:" + count);

        Optional<Admin> entity = adminDao.find(1L);
        assertFalse(entity.isPresent());


        Connection conn = super.getJdbcConnection();
        CSVLoader.Options options = new CSVLoader.Options("src/test/resources/data/csv/admin.csv", "admin")
                .truncated(true)
                .skip(1);
        CSVLoader.load(conn, options);
        super.commitAndContinue();

        entity = adminDao.find(1L);
        assertTrue(entity.isPresent());

        Admin admin = entity.get();
        Token token = new Token();
        token.setId("1111111");
        token.setAdmin(admin);
        admin.setToken(token);
        adminDao.update(admin);

        super.commitAndContinue();

        IDao<Token, String> tokenDao;
        tokenDao = new Dao<>(String.class, Token.class);
        tokenDao.setEntityManager(super.entityManager);

        Optional<Token> tokenEntity = tokenDao.find("1111111");
        assertTrue(tokenEntity.isPresent());
        Admin tokened = tokenEntity.get().getAdmin();
        assertNotNull(tokened);
        assertEquals(admin.getName(), tokened.getName());

        super.commit();
    }

}
