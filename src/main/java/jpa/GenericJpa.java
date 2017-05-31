package jpa;

import java.lang.reflect.ParameterizedType;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entitys.EntityModel;

public abstract class GenericJpa<T extends EntityModel> {

    public GenericJpa() {
    }

    @PersistenceContext(unitName = "MySQLDS")
    protected EntityManager manager;

    public void delete(T t) {
        manager.remove(manager.merge(t));
    }

    public void add(T t) {
        manager.persist(t);
    }

    public T get(Long id) {
        return (T) manager.find((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0], id);
    }

    public T update(T t) {
        manager.merge(t);
        return t;
    }

    @PostConstruct
    public void ini() {
        System.out.println("createEntityManager");
    }

    @PreDestroy
    public void close() {
        System.out.println("closeEntityManager");
    }

}
