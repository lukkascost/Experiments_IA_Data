import {ExtractorType} from './enums/ExtractorType';

export interface ISampleDTO {
    createdAt: string;
    updatedAt: string;
    extractorType: ExtractorType;
    originalFileName: string;
    order: number;
    label: string;
    attributes: any;
    getAttributes();
}

export class SampleListDTO  implements  ISampleDTO {
    id: string;
    attributes: number;
    createdAt: string;
    extractorType: ExtractorType;
    label: string;
    order: number;
    originalFileName: string;
    updatedAt: string;

    getAttributes() {
    }

}

export class SampleRegisterDTO  implements  ISampleDTO {
    attributes: any[] = [];
    createdAt: string;
    extractorType: ExtractorType = ExtractorType.GLCM;
    label: string;
    order: number;
    originalFileName: string;
    updatedAt: string;

    getAttributes() {
    }

}