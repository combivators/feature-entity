package net.tiny.feature.dao;

import net.tiny.feature.entity.ArticleCategory;

/**
 * ArticleCategoryDao - 文章分类
 *
 */
public class ArticleCategoryDao extends BaseCategoryDao<ArticleCategory, Long> {

    @Override
    protected String getEntityName() {
        return "ArticleCategory";
    }
}
