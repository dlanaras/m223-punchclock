package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Category;

@ApplicationScoped
public class CategoryService {
    @Inject
    private EntityManager entityManager;

    public List<Category> findAll() {
        var query = entityManager.createQuery("FROM Entry", Category.class);
        return query.getResultList();
    }

    @Transactional
    public Category createCategory(Category category) {
        entityManager.persist(category);
        return category;
    }

    @Transactional
    public Category updateCategory(Category category) {
        return entityManager.merge(category);
    }

    public void deleteCategory(Long id) {
        var entity = entityManager.find(Category.class, id);
        entityManager.remove(entity);
    }
    
}
