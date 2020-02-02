package net.tiny.feature.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import net.tiny.dao.CSVLoader;
import net.tiny.feature.entity.Field;
import net.tiny.unit.db.Database;

@Database(persistence="persistence-eclipselink.properties"
  ,trace=true
  ,createScript= "src/test/resources/sql/create_sequence.sql"
)
public class FieldDaoTest extends BaseDaoTest {

    @Test
    public void testLoadField() throws Exception {
        super.begin();
        FieldDao dao = createDao(FieldDao.class);
        CSVLoader loader = new CSVLoader.Builder(dao)
                .path("src/test/resources/data/csv/field.csv")
                .table("field")
                .truncated(true)
                .skip(1)
                .build();

        long num = loader.load();
        System.out.println("field: " + num);
        assertEquals(102, num);

        List<Field> roots = dao.findRoots(null);
        assertEquals(19, roots.size());


        List<Field> children  = dao.findChildren(roots.get(18), null);
        assertEquals(4, children.size());
        Field child = children.get(2);
        assertEquals("職業紹介・労働者派遣", child.getName());

        super.commit();
    }
}
