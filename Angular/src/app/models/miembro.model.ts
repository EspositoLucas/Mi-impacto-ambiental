import { Base } from './base.model';
import { IdTextPair } from './idtextpair.model';

export interface Miembro extends Base {
    persona: IdTextPair | null;
    usuario: IdTextPair | null;
    fechaIngreso: string;
    sector: IdTextPair | null;
}

export const newMiembro = (valores?: {
    id?: number;
    persona?: IdTextPair;
    usuario?: IdTextPair;
    fechaIngreso?: string;
    sector?: IdTextPair;
}): Miembro => {
    return {
        id: valores?.id ?? 0,
        persona: valores?.persona ?? null,
        usuario: valores?.usuario ?? null,
        fechaIngreso: valores?.fechaIngreso ?? '',
        sector: valores?.sector ?? null,
    };
};
