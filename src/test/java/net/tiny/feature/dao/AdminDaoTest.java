package net.tiny.feature.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import net.tiny.feature.entity.Admin;
import net.tiny.unit.db.Database;

@Database(persistence="persistence-eclipselink.properties"
  ,trace=true
  ,createScript= "src/test/resources/sql/create_sequence.sql"
)
public class AdminDaoTest extends BaseDaoTest {

    @Test
    public void testAdminDao() throws Exception {

        super.begin();

        AdminDao adminDao = createDao(AdminDao.class);
        long count = adminDao.count();
        System.out.println("Admin count:" + count);

        Admin admin = adminDao.find(1L);
        assertNull(admin);

/*
        List<Area> areas = areaDao.findRoots(10);
        System.out.println("Area count:" + areas.size());
        for(Area a : areas) {
            System.out.println(a.getId() + "\t" + a.toString());
        }
        System.out.println();

        area = areaDao.find(19L);
        System.out.println(area.getId() + "\t" + area.toString());
        Set<Area> children = area.getChildren();
        for(Area a : children) {
            System.out.println("\t" + a.getId() + "\t" + a.toString());
        }
        System.out.println();
*/
        super.commit();
    }

}
