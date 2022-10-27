package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseEntityDto;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.utils.EnumUtils;
import dds.grupo4.tpimpacto.utils.ListUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrganizacionDto extends BaseEntityDto {
    private String razonSocial;
    private IdTextPair tipoOrganizacion;
    private IdTextPair clasificacion;
    private CantidadDto factorK;
    private int cantDiasHabilesPorSemana;
    private List<IdTextPair> sectores = new ArrayList<>();
    private List<IdTextPair> contactos = new ArrayList<>();
    private List<IdTextPair> solicitudes = new ArrayList<>();

    private OrganizacionDto(Organizacion organizacion) {
        super(organizacion);
    }

    public static OrganizacionDto from(Organizacion organizacion) {
        OrganizacionDto dto = new OrganizacionDto(organizacion);
        dto.setRazonSocial(organizacion.getRazonSocial());
        dto.setTipoOrganizacion(EnumUtils.enumToIdTextPair(organizacion.getTipoOrganizacion()));
        dto.setClasificacion(EnumUtils.enumToIdTextPair(organizacion.getClasificacion()));
        dto.setFactorK(CantidadDto.from(organizacion.getFactorK()));
        dto.setCantDiasHabilesPorSemana(organizacion.getCantDiasHabilesPorSemana());
        dto.setSectores(ListUtils.toIdTextPairList(organizacion.getSectores()));
        dto.setContactos(ListUtils.toIdTextPairList(organizacion.getContactos()));
        dto.setSolicitudes(ListUtils.toIdTextPairList(organizacion.getSolicitudes()));
        return dto;
    }

}
