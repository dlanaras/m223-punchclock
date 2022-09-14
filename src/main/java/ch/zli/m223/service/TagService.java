package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationScoped
public class TagService {
    @Inject
    private EntityManager entityManager;

    public List<Tag> findAll() {
        var query = entityManager.createQuery("FROM Entry", Tag.class);
        return query.getResultList();
    }

    @Transactional
    public Tag createTag(Tag tag) {
        entityManager.persist(tag);
        return tag;
    }

    @Transactional
    public Tag updateTag(Tag tag) {
        return entityManager.merge(tag);
    }

    public void deleteTag(Long id) {
        var entity = entityManager.find(Tag.class, id);
        entityManager.remove(entity);
    }
    
}
