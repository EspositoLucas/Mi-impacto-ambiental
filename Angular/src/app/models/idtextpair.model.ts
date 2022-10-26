import { Base } from './base.model';

export interface IdTextPair extends Base {
    text: string;
}

export const newIdTextPair = (valores?: {
    id: number;
    text: string;
}): IdTextPair => {
    return {
        id: valores?.id ?? 0,
        text: valores?.text ?? '',
    };
};
