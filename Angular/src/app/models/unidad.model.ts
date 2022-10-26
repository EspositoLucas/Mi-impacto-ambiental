import { Base } from './base.model';

export interface Unidad extends Base {
    text: string;
    nombre: string;
    simbolo: string;
    tipoUnidad: string;
}

export const newUnidad = (valores?: {
    id?: number;
    nombre?: string;
    simbolo?: string;
    tipoUnidad?: string;
}): Unidad => {
    const unidad = {
        id: valores?.id ?? 0,
        nombre: valores?.nombre ?? '',
        simbolo: valores?.simbolo ?? '',
        tipoUnidad: valores?.tipoUnidad ?? '',
    } as Unidad;
    unidad.text = unidad.simbolo;
    return unidad;
};
