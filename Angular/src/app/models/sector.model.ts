import { Base } from './base.model';
import { IdTextPair } from './idtextpair.model';

export interface Sector extends Base {
    nombre: string;
    organizacion: IdTextPair | null;
    espacio: IdTextPair | null;
    miembros: IdTextPair[];
    solicitudes: IdTextPair[];
}

export const newSector = (valores?: {
    id?: number;
    nombre?: string;
    organizacion?: IdTextPair;
    espacio?: IdTextPair;
    miembros?: IdTextPair[];
    solicitudes?: IdTextPair[];
}): Sector => {
    return {
        id: valores?.id ?? 0,
        nombre: valores?.nombre ?? '',
        organizacion: valores?.organizacion ?? null,
        espacio: valores?.espacio ?? null,
        miembros: valores?.miembros ?? [],
        solicitudes: valores?.solicitudes ?? [],
    };
};
