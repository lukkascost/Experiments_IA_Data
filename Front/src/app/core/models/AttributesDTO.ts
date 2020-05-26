import {ExtractorType} from './enums/ExtractorType';

export interface IAttributeDTO {
    id: string;
    updatedAt: string;
    createdAt: string;
    name: string;
    value: string;
    normalizedValue: string;
    order: any;
}

export class AttributesRegisterDTO  implements  IAttributeDTO {
    createdAt: string;
    id: string;
    name: string;
    normalizedValue: string;
    order: any;
    updatedAt: string;
    value: string;
}
