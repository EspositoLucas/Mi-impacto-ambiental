import { Base } from './base.model';
import { IdTextPair } from './idtextpair.model';

export interface Persona extends Base {
    nombre: string;
    apellido: string;
    tipoDocumento: IdTextPair | null;
    documento: string;
}

export const newPersona = (valores?: {
    id?: number;
    nombre?: string;
    apellido?: string;
    tipoDocumento?: IdTextPair;
    documento?: string;
}): Persona => {
    return {
        id: valores?.id ?? 0,
        nombre: valores?.nombre ?? '',
        apellido: valores?.apellido ?? '',
        tipoDocumento: valores?.tipoDocumento ?? null,
        documento: valores?.documento ?? '',
    };
};
