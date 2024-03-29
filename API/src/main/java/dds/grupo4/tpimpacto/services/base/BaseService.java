package dds.grupo4.tpimpacto.services.base;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.repositories.base.BaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public abstract class BaseService<TEntity extends BaseEntity, TRepo extends BaseRepository<TEntity>> {

    protected final TRepo repository;

    public BaseService(TRepo repository) {
        this.repository = repository;
    }

    @Transactional
    public List<TEntity> getAll() {
        return repository.getAll();
    }

    @Transactional
    public TEntity getById(long id) {
        return repository.getById(id);
    }

    @Transactional
    public TEntity save(TEntity obj) {
        try {
            repository.save(obj);
        } catch (Exception ex) {
            log.error("Error al llamar a save(), asi que se llama a merge()", ex);
            obj = repository.merge(obj);
        }
        return obj;
    }

    @Transactional
    public List<TEntity> saveAll(List<TEntity> entities) {
        return entities.stream()
                .filter(Objects::nonNull)
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(long id) {
        TEntity obj = this.getById(id);
        this.repository.delete(obj);
    }

    @Transactional
    public boolean hasData() {
        return repository.hasData();
    }
}
