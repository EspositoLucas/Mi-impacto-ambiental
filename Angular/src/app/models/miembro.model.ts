import { Base } from './base.model';
import { IdTextPair } from './idtextpair.model';

export interface Miembro extends Base {
    persona: IdTextPair | null;
    usuario: IdTextPair | null;
    fechaIngreso: string;
    organizacion: IdTextPair | null;
    sector: IdTextPair | null;
}

export const newMiembro = (valores?: {
    id?: number;
    persona?: IdTextPair;
    usuario?: IdTextPair;
    fechaIngreso?: string;
    organizacion?: IdTextPair;
    sector?: IdTextPair;
}): Miembro => {
    return {
        id: valores?.id ?? 0,
        persona: valores?.persona ?? null,
        usuario: valores?.usuario ?? null,
        fechaIngreso: valores?.fechaIngreso ?? '',
        organizacion: valores?.organizacion ?? null,
        sector: valores?.sector ?? null,
    };
};
