package com.pwc.aml.dao;

import com.pwc.aml.entity.Article;

import java.util.List;

/**
 * Created by whuang072 on 6/20/2017.
 */
public interface IArticleDAO {
    List<Article> getAllArticles();
    Article getArticleById(int articleId);
    void addArticle(Article article);
    void updateArticle(Article article);
    void deleteArticle(int articleId);
    boolean articleExists(String title, String category);
}
