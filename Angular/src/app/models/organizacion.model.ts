import { Base } from './base.model';
import {
    CantidadComoIdTextPair,
    newCantidadComoIdTextPair,
} from './cantidad.model';
import { IdTextPair } from './idtextpair.model';

export interface Organizacion extends Base {
    razonSocial: string;
    tipoOrganizacion: IdTextPair | null;
    clasificacion: IdTextPair | null;
    cantDiasHabilesPorSemana: number;
    factorK: CantidadComoIdTextPair;
}

export const newOrganizacion = (valores?: {
    id?: number;
    razonSocial?: string;
    tipoOrganizacion?: IdTextPair;
    clasificacion?: IdTextPair;
    cantDiasHabilesPorSemana?: number;
    factorK?: CantidadComoIdTextPair;
}): Organizacion => {
    return {
        id: valores?.id ?? 0,
        razonSocial: valores?.razonSocial ?? '',
        tipoOrganizacion: valores?.tipoOrganizacion ?? null,
        clasificacion: valores?.clasificacion ?? null,
        cantDiasHabilesPorSemana: valores?.cantDiasHabilesPorSemana ?? 5,
        factorK: valores?.factorK ?? newCantidadComoIdTextPair(),
    };
};
