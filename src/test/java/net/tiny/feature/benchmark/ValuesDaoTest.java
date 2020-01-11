package net.tiny.feature.benchmark;

import org.junit.jupiter.api.Test;

import net.tiny.feature.dao.BaseDaoTest;
import net.tiny.unit.db.Database;

@Database(persistence="persistence-eclipselink.properties"
,trace=true
,createScript= "src/test/resources/sql/create_sequence.sql"
)
public class ValuesDaoTest extends BaseDaoTest {

    @Test
    public void testLoadHugeData() throws Exception {
        super.begin();

        super.commit();
    }

}
