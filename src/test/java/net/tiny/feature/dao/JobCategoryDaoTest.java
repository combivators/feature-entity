package net.tiny.feature.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;

import net.tiny.config.JsonParser;
import net.tiny.dao.CSVLoader;
import net.tiny.feature.entity.JobCategory;
import net.tiny.unit.db.Database;

@Database(persistence="persistence-eclipselink.properties"
 //,persistence="persistence-hibernate.properties"
 ,trace=true
 ,createScript= "src/test/resources/sql/create_sequence.sql"
)
public class JobCategoryDaoTest extends BaseDaoTest {

    @Test
    public void testJobCategory() throws Exception {
        super.begin();

        JobCategoryDao dao = createDao(JobCategoryDao.class);
        if (dao.count() > 0L) {
            dao.removeAll();
        }
        JobCategory root = new JobCategory();
        root.setGrade(0);
        root.setOrder(1);
        root.setName("01");
        root.setLabel("営業");
        root.setSummary("法人・個人");
        dao.insert(root);

        JobCategory middle = new JobCategory();
        middle.setGrade(1);
        middle.setOrder(1);
        middle.setName("企画営業");
        middle.setLabel("企画営業・法人営業・個人営業");
        middle.setSummary("企画営業・法人営業・個人営業・MR・その他営業関連");
        middle.setParent(root);
        dao.insert(middle);

        JobCategory small = new JobCategory();
        small.setGrade(2);
        small.setOrder(1);
        small.setName("企画営業【法人営業・個人営業】");
        small.setLabel("企画営業（法人・個人）");
        small.setSummary("企画営業");
        small.setParent(middle);
        dao.insert(small);

        middle = new JobCategory();
        middle.setGrade(1);
        middle.setOrder(2);
        middle.setName("テレマーケティング");
        middle.setLabel("テレマーケティング・コールセンター");
        middle.setSummary("テレマーケティング・コールセンター");
        middle.setParent(root);
        dao.insert(middle);

        root = new JobCategory();
        root.setGrade(0);
        root.setOrder(2);
        root.setName("02");
        root.setLabel("事務・管理");
        root.setSummary("経理、人事、総務、法務、広報、物流、資材購買など");
        dao.insert(root);

        root = new JobCategory();
        root.setGrade(0);
        root.setOrder(3);
        root.setName("99");
        root.setLabel("その他");
        root.setSummary("その他");
        dao.insert(root);
        dao.flush();

        super.commitAndContinue();
        List<JobCategory> roots = dao.findRoots(null);
        assertEquals(3, roots.size());
        root = roots.get(0);
        assertEquals(1, root.getOrder());
        assertEquals("01", root.getName());
        assertEquals("営業", root.getLabel());
        System.out.println(JsonParser.marshal(root));
        Long id = root.getId();
        assertEquals(1L, id);
        root = dao.find(id).get();
        System.out.println(JsonParser.marshal(root));

        List<JobCategory> children  = dao.findChildren(root, null);
        assertEquals(3, children.size());
        JobCategory child = children.get(0);
        assertEquals(1, child.getOrder());
        assertEquals("企画営業", child.getName());
        assertEquals("企画営業・法人営業・個人営業", child.getLabel());

        children  = dao.findChildren(child, null);
        assertEquals(1, children.size());
        assertEquals("企画営業【法人営業・個人営業】", children.get(0).getName());

        List<Long> ids  = children.get(0).getTreePaths();
        assertEquals(2, ids.size());
        List<JobCategory> parents = dao.findParents(children.get(0), null);
        assertEquals(2, parents.size());

        super.commit();
    }
    @Resource
    private DataSource dataSource;
    @Test
    public void testLoadCsvData() throws Exception {
        assertNotNull(dataSource);
        CSVLoader.Options options = new CSVLoader.Options("src/test/resources/data/csv/job_category.csv", "job_category")
                .truncated(true)
                .skip(1);
        Connection conn = dataSource.getConnection();
        CSVLoader.load(conn, options);
        conn.commit();

        super.begin();
        JobCategoryDao dao = createDao(JobCategoryDao.class);

        List<JobCategory> roots = dao.findRoots(null);
        assertEquals(15, roots.size());
        JobCategory root = roots.get(0);
        assertEquals(0, root.getGrade());
        assertEquals(1, root.getOrder());
        assertEquals("営業", root.getName());
        assertEquals("営業", root.getLabel());

        List<JobCategory> children  = dao.findChildren(root, null);
        assertEquals(16, children.size());
        JobCategory child = children.get(0);
        assertEquals(1, child.getOrder());
        assertEquals("企画営業・法人営業・個人営業・MR・その他営業関連", child.getName());
        assertEquals("企画営業・法人営業・個人営業・MR・その他営業関連", child.getLabel());

        System.out.println("job_category : " + dao.count());

        super.commit();
    }

    @Test
    public void testLoadJobCategory() throws Exception {
        super.begin();
        JobCategoryDao dao = createDao(JobCategoryDao.class);
        CSVLoader loader = new CSVLoader.Builder(dao)
                .path("src/test/resources/data/csv/job_category.csv")
                .table("job_category")
                .truncated(true)
                .skip(1)
                .build();

        long num = loader.load();
        System.out.println("job_category: " + num);

        List<JobCategory> roots = dao.findRoots(null);
        assertEquals(15, roots.size());
        JobCategory root = roots.get(0);
        assertEquals(0, root.getGrade());
        assertEquals(1, root.getOrder());
        assertEquals("営業", root.getName());
        assertEquals("営業", root.getLabel());

        List<JobCategory> children  = dao.findChildren(root, null);
        assertEquals(16, children.size());
        JobCategory child = children.get(0);
        assertEquals(1, child.getOrder());
        assertEquals("企画営業・法人営業・個人営業・MR・その他営業関連", child.getName());
        assertEquals("企画営業・法人営業・個人営業・MR・その他営業関連", child.getLabel());

        System.out.println("job_category : " + dao.count());

        super.commit();
    }

}
