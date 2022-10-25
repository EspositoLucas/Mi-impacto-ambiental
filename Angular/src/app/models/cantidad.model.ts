import { Base } from './base.model';
import { IdTextPair, newIdTextPair } from './idtextpair.model';
import { newUnidad, Unidad } from './unidad.model';

export interface Cantidad extends Base {
    unidad: Unidad;
    valor: number;
}

export interface CantidadComoIdTextPair extends Base {
    unidad: IdTextPair | null;
    valor: number;
}

export const newCantidad = (valores?: {
    id?: number;
    unidad?: Unidad;
    valor?: number;
}): Cantidad => {
    return {
        id: valores?.id ?? null,
        unidad: valores?.unidad ?? newUnidad(),
        valor: valores?.valor ?? 0,
    };
};

export const newCantidadComoIdTextPair = (valores?: {
    id?: number;
    unidad?: IdTextPair;
    valor?: number;
}): CantidadComoIdTextPair => {
    return {
        id: valores?.id ?? null,
        unidad: valores?.unidad ?? null,
        valor: valores?.valor ?? 0,
    };
};
