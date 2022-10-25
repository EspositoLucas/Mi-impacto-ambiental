export interface IdTextPair {
    id: number;
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
