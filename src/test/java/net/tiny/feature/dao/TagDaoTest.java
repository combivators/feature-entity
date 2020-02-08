package net.tiny.feature.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import net.tiny.dao.Page;
import net.tiny.dao.Pageable;
import net.tiny.feature.entity.Article;
import net.tiny.feature.entity.ArticleCategory;
import net.tiny.feature.entity.Tag;
import net.tiny.feature.entity.Tag.Type;
import net.tiny.unit.db.Database;

@Database(persistence="persistence-eclipselink.properties"
  ,trace=true
  ,createScript= "src/test/resources/sql/create_sequence.sql"
)
public class TagDaoTest extends BaseDaoTest {

    @Test
    public void testTagDao() throws Exception {
        super.begin();

        TagDao tagDao = createDao(TagDao.class);
        assertNotNull(tagDao);
        long count = tagDao.count();
        System.out.println("Tag count:" + count);
        assertEquals(0L, count);

        List<Tag> tags = tagDao.findList(Type.product);
        System.out.println("Product tag count:" + tags.size());
        assertTrue(tags.isEmpty());

        tags = tagDao.findList(Type.article);
        System.out.println("Article tag count:" + tags.size());
        assertTrue(tags.isEmpty());

        ArticleCategoryDao articleCategoryDao = createDao(ArticleCategoryDao.class);
        ArticleCategory category = null;
        assertFalse(articleCategoryDao.find(1L).isPresent());

        ArticleDao articleDao = createDao(ArticleDao.class);
        LocalDate beginDate = createDate("20000101");
        LocalDate endDate  = createDate("20991231");
        Integer first = 0;
        Integer num = 100;
        List<Article> articles = articleDao.findList(category, beginDate, endDate, first, num);
        System.out.println("Article count: " + articles.size());
        assertEquals(0, articles.size());

        Pageable pageable = new Pageable();
        Page<Article> page = articleDao.findPage(category, tags, pageable);
        System.out.println("Article page: " + page.getTotal());

        super.commit();

    }

}
