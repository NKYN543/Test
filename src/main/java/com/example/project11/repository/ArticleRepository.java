package com.example.project11.repository;

import com.example.project11.util.HibernateUtil;
import com.example.project11.model.Article;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

import java.util.List;

@ApplicationScoped
public class ArticleRepository {

    public List<com.example.project11.model.Article> findAll() throws PersistenceException {
        EntityManager em = com.example.project11.util.HibernateUtil.getEntityManager();
        List<com.example.project11.model.Article> articles = null;
        try {
            em.getTransaction().begin();
            articles = em
                    .createQuery("SELECT a FROM Article a", com.example.project11.model.Article.class)
                    .getResultList();
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("articles_not_found", e);
        } finally {
            em.close();
        }
        return articles;
    }

    public com.example.project11.model.Article findByID(Integer id) throws PersistenceException {
        EntityManager em = com.example.project11.util.HibernateUtil.getEntityManager();
        com.example.project11.model.Article article = null;
        try {
            em.getTransaction().begin();
            article = em
                    .createQuery("SELECT a FROM Article a WHERE a.id = :id", com.example.project11.model.Article.class)
                    .setParameter("id", id)
                    .getSingleResult();
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("article_not_found", e);
        } finally {
            em.close();
        }
        return article;
    }

    public void create(com.example.project11.model.Article article) throws PersistenceException {
        EntityManager em = com.example.project11.util.HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(article);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("", e);
        } finally {
            em.close();
        }
    }

    public void update(com.example.project11.model.Article article) throws PersistenceException {
        EntityManager em = com.example.project11.util.HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            com.example.project11.model.Article existingArticle = em.find(com.example.project11.model.Article.class, article.getId());
            try {
                HibernateUtil.copyNonNullProperties(article, existingArticle);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            em.merge(existingArticle);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("", e);
        } finally {
            em.close();
        }
    }
}
