import { Base } from './base.model';

export interface Contacto extends Base {
    nombre: string;
    apellido: string;
    email: string;
    telefono: string;
    deseaRecibirPorWhatsapp: boolean;
    deseaRecibirPorEmail: boolean;
}

export const newContacto = (valores?: {
    id?: number;
    nombre?: string;
    apellido?: string;
    email?: string;
    telefono?: string;
    deseaRecibirPorWhatsapp?: boolean;
    deseaRecibirPorEmail?: boolean;
}): Contacto => {
    return {
        id: valores?.id ?? 0,
        nombre: valores?.nombre ?? '',
        apellido: valores?.apellido ?? '',
        email: valores?.email ?? '',
        telefono: valores?.telefono ?? '',
        deseaRecibirPorWhatsapp: valores?.deseaRecibirPorWhatsapp ?? false,
        deseaRecibirPorEmail: valores?.deseaRecibirPorEmail ?? false,
    };
};
