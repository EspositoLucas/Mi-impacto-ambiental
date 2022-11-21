import { Base } from './base.model';
import { IdTextPair } from './idtextpair.model';

export interface Solicitud extends Base {
    miembro: IdTextPair;
    organizacion: IdTextPair;
    sector: IdTextPair;
    activo: boolean;
}
