package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import org.hibernate.metamodel.internal.MetamodelImpl;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.persister.entity.SingleTableEntityPersister;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;
import java.util.List;

public abstract class BaseRepository<T extends BaseEntity> {

    protected final EntityManager entityManager;

    public BaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(T obj) {
        entityManager.persist(obj);
    }

    public T merge(T obj) {
        return entityManager.merge(obj);
    }

    public List<T> getAll() {
        String query = "FROM " + getEntityName() + " e";
        return entityManager.createQuery(query, getEntityClass())
                .getResultList();
    }

    public T getById(long id) {
        return entityManager.find(getEntityClass(), id);
    }

    public boolean hasData() {
        String tableName = getTableName();
        String sqlQuery = "select 1 from " + tableName;
        return entityManager.createNativeQuery(sqlQuery)
                .setMaxResults(1)
                .getResultStream()
                .findAny().isPresent();
    }

    public abstract Class<T> getEntityClass();

    protected String getEntityName() {
        return getEntityClass()
                .getAnnotation(Entity.class)
                .name();
    }

    protected String getTableName() {
        Class<T> entityClazz = getEntityClass();

        boolean tieneAnnotationTable = entityClazz.isAnnotationPresent(Table.class);
        if (tieneAnnotationTable) {
            return entityClazz.getAnnotation(Table.class).name();
        }

        MetamodelImpl metamodel = (MetamodelImpl) entityManager.getMetamodel();
        EntityPersister entityPersister = metamodel.entityPersister(entityClazz);
        if (entityPersister instanceof SingleTableEntityPersister) {
            return ((SingleTableEntityPersister) entityPersister).getTableName();
        } else {
            throw new IllegalArgumentException(entityClazz + " no se mappea a una tabla.");
        }
    }
}
