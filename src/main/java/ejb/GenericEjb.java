package ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entitys.EntityModel;
import javax.annotation.security.RolesAllowed;

@RolesAllowed({"ROBOT","ADMIN","USER"})
public abstract class GenericEjb<T extends EntityModel> {

    public GenericEjb(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @PersistenceContext(unitName = "MySQLDS")
    protected EntityManager manager;
    
    private Class<T> entityClass;

    public T delete(T t) {
        manager.remove(manager.find(entityClass, t.getId()));
        return t;
    }

    public T add(T t) {
        manager.persist(t);
        return t;
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
