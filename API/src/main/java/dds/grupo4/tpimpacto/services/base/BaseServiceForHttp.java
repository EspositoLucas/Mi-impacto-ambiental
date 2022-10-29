package dds.grupo4.tpimpacto.services.base;

import dds.grupo4.tpimpacto.dtos.base.BaseEntityDto;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithSingleResult;
import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.repositories.base.BaseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseServiceForHttp<TEntity extends BaseEntity, TRepo extends BaseRepository<TEntity>, TDto extends BaseEntityDto>
        extends BaseService<TEntity, TRepo> {
    public BaseServiceForHttp(TRepo repository) {
        super(repository);
    }

    @Transactional
    public ResponseWithResults<TDto> getAllDtos() {
        List<TDto> dtos = this.getAll().stream()
                .map(this::createDtoFromEntity)
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, dtos);
    }

    @Transactional
    public ResponseWithSingleResult<TDto> getDtoById(long id) {
        return new ResponseWithSingleResult<>(HttpStatus.OK, createDtoFromEntity(this.getById(id)));
    }

    @Transactional
    public ResponseWithSingleResult<TDto> saveFromDto(TDto dto) {
        TEntity entity = createEntity();
        updateEntityFieldsFromDto(entity, dto);
        TEntity savedEntity = this.save(entity);
        return new ResponseWithSingleResult<>(HttpStatus.CREATED, createDtoFromEntity(savedEntity));
    }

    @Transactional
    public ResponseWithSingleResult<TDto> editFromDto(long id, TDto dto) {
        if (id != dto.getId()) {
            return new ResponseWithSingleResult<>(
                    HttpStatus.BAD_REQUEST,
                    "El ID de la ruta (" + id + ")  no coincide con el de la request (" + dto.getId() + ")",
                    null);
        }

        TEntity entity = getById(dto.getId());
        updateEntityFieldsFromDto(entity, dto);
        return new ResponseWithSingleResult<>(HttpStatus.OK, createDtoFromEntity(entity));
    }

    @Transactional
    public BaseResponse deleteEntity(long id) {
        super.delete(id);
        return new BaseResponse(HttpStatus.OK);
    }

    public abstract TEntity createEntity();

    public abstract TDto createDtoFromEntity(TEntity entity);

    public abstract void updateEntityFieldsFromDto(TEntity entity, TDto dto);
}
