import { Base } from './base.model';
import { IdTextPair } from './idtextpair.model';
import { newUnidad, Unidad } from './unidad.model';

export interface Cantidad extends Base {
    unidad: Unidad;
    valor: number;
}

export interface CantidadComoIdTextPair extends Base {
    unidad: IdTextPair | null;
    valor: number;
    text?: string;
}

export const newCantidad = (valores?: {
    id?: number;
    unidad?: Unidad;
    valor?: number;
}): Cantidad => {
    return {
        id: valores?.id ?? 0,
        unidad: valores?.unidad ?? newUnidad(),
        valor: valores?.valor ?? 0,
    };
};

export const newCantidadComoIdTextPair = (valores?: {
    id?: number;
    unidad?: IdTextPair;
    valor?: number;
}): CantidadComoIdTextPair => {
    const cantidad = {
        id: valores?.id ?? 0,
        unidad: valores?.unidad ?? null,
        valor: valores?.valor ?? 0,
    };
    cantidadFormatTextMessage(cantidad);
    return cantidad;
};

export function cantidadFormatTextMessage(cantidad: CantidadComoIdTextPair) {
    cantidad.text = `${cantidad.valor} ${cantidad.unidad?.text}`;
}
