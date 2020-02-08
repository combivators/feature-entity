package net.tiny.feature.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import net.tiny.feature.entity.Article;
import net.tiny.feature.entity.ArticleCategory;
import net.tiny.feature.entity.Tag;
import net.tiny.feature.entity.Tag.Type;
import net.tiny.unit.db.Database;

@Database(persistence="persistence-eclipselink.properties"
  ,trace=true
  ,createScript= "src/test/resources/sql/create_sequence.sql"
)
public class ArticleDaoTest extends BaseDaoTest {

    @Test
    public void testArticleTag() throws Exception {
        Long[] articleCategoryIds = new Long[100];
        Long[] articleIds = new Long[100];
        Long[] tagIds = new Long[20];

        super.begin();

        ArticleCategoryDao articleCategoryDao = createDao(ArticleCategoryDao.class);
        ArticleDao articleDao = createDao(ArticleDao.class);
        TagDao tagDao = createDao(TagDao.class);

        for (int i = 0; i < articleCategoryIds.length; i++) {
            String name = "test" + i;
            ArticleCategory articleCategory = new ArticleCategory();
            if (i < 20) {
                articleCategory.setName(name);
                articleCategory.setOrder(i);
                articleCategoryDao.insert(articleCategory);
                articleCategoryDao.flush();
            } else {
                ArticleCategory parent = articleCategoryDao.find(articleCategoryIds[0]).get();
                assertNotNull(parent);
                articleCategory.setName(name);
                articleCategory.setOrder(i);
                articleCategory.setParent(parent);
                articleCategoryDao.insert(articleCategory);
                articleCategoryDao.flush();
            }
            articleCategoryIds[i] = articleCategory.getId();
            assertNotNull(articleCategoryIds[i]);
        }

        //articleCategoryDao.flush();
        articleCategoryDao.clear();
        super.commitAndContinue();
        assertEquals(new Long(1L), articleCategoryIds[0]);

        ArticleCategory articleCategory = articleCategoryDao.find(articleCategoryIds[0]).get();
        assertNotNull(articleCategory);
        articleCategory = articleCategoryDao.find(articleCategoryIds[1]).get();
        assertNotNull(articleCategory);

        for (int i = 0; i < tagIds.length; i++) {
            String name = "test" + i;
            Tag tag = new Tag();
            tag.setName(name);
            tag.setOrder(i);
            tag.setType(Type.article);
            tagDao.insert(tag);
            tagDao.flush();
            tagIds[i] = tag.getId();
        }
        tagDao.clear();
        super.commitAndContinue();

        Tag tag0 = tagDao.find(tagIds[0]).get();
        Tag tag1 = tagDao.find(tagIds[1]).get();
        Tag tag2 = tagDao.find(tagIds[2]).get();
        assertNotNull(tag0);
        assertNotNull(tag1);
        assertNotNull(tag2);

        for (int i = 0; i < articleIds.length; i++) {
            String name = "test" + i;
            Article article = new Article();
            article.setTitle(name);
            article.setContent(name);
            article.setIsPublication(true);
            article.setIsTop(false);
            article.setHits(0L);
            ArticleCategory parent;
            if (i < 20) {
                parent = articleCategoryDao.find(articleCategoryIds[0]).get();
                assertNotNull(parent);
                article.setArticleCategory(parent);
            } else {
                parent = articleCategoryDao.find(articleCategoryIds[1]).get();
                assertNotNull(parent);
                article.setArticleCategory(parent);
            }

            if (i < 20) {
                Set<Tag> tags = new HashSet<Tag>();
                if (i < 10) {
                    tags.add(tag0);
                    tags.add(tag1);
                }
                tags.add(tag2);
                article.setTags(tags);
            }

            articleDao.insert(article);
            articleDao.flush();
            articleIds[i] = article.getId();
        }

        articleDao.clear();

        super.commit();
    }


}