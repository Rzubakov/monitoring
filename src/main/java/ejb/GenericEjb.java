package ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entitys.EntityModel;

public abstract class GenericEjb<T extends EntityModel> {

    public GenericEjb(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @PersistenceContext(unitName = "MySQLDS")
    protected EntityManager manager;
    
    private Class<T> entityClass;

    public void delete(T t) {
        manager.remove(manager.merge(t));
    }

    public void add(T t) {
        manager.persist(t);
    }

    public T get(Long id) {
        return (T) manager.find(entityClass, id);
    }

    public T update(T t) {
        manager.merge(t);
        return t;
    }
    public T detach(T t) {
        manager.detach(t);
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
