package net.tiny.feature.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import net.tiny.dao.CSVLoader;
import net.tiny.dao.Dao;
import net.tiny.feature.entity.ReviewKeyword;
import net.tiny.feature.entity.ReviewKeywordCategory;
import net.tiny.unit.db.Database;

@Database(persistence="persistence-eclipselink.properties"
  ,trace=true
  ,createScript= "src/test/resources/sql/create_sequence.sql"
  )
public class ReviewKeywordDaoTest extends BaseDaoTest {

    @Test
    public void testLoadReviewKeywords() throws Exception {
        super.begin();
        Dao<ReviewKeywordCategory, Long> categoryDao = new Dao<>(Long.class, ReviewKeywordCategory.class);
        categoryDao.setEntityManager(super.entityManager);
        CSVLoader loader = new CSVLoader.Builder(categoryDao)
                .path("src/test/resources/data/csv/review_keyword_category.csv")
                .table("review_keyword_category")
                .truncated(true)
                .skip(1)
                .build();

        long num = loader.load();
        assertEquals(num , categoryDao.count());
        System.out.println("review_keyword_category: " + num);


        Dao<ReviewKeyword, Long> dao = new Dao<>(Long.class, ReviewKeyword.class);
        dao.setEntityManager(super.entityManager);
        loader = new CSVLoader.Builder(dao)
                .path("src/test/resources/data/csv/review_keyword.csv")
                .table("review_keyword")
                .truncated(true)
                .skip(1)
                .build();

        num = loader.load();
        assertEquals(num , dao.count());
        System.out.println("review_keyword: " + num);

        ReviewKeyword keyword = dao.find(1L).get();
        assertNotNull(keyword);
        assertEquals("やりがいがある", keyword.getKeyword());
        ReviewKeywordCategory category = keyword.getCategory();
        assertEquals("職場の雰囲気", category.getName());
        Set<ReviewKeyword> keywords = category.getKeywords();
        assertEquals(21, keywords.size());

        super.commit();
    }
}
