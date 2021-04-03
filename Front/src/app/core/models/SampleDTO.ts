import {ExtractorType} from './enums/ExtractorType';

export interface ISampleDTO {
    createdAt: string;
    updatedAt: string;
    extractorType: ExtractorType;
    originalFileName: string;
    label: string;
    attributes: any;
    fileId: string;
    getAttributes();
}

export class SampleListDTO  implements  ISampleDTO {
    id: string;
    attributes: number;
    createdAt: string;
    extractorType: ExtractorType;
    label: string;
    originalFileName: string;
    updatedAt: string;
    fileId: string;

    getAttributes() {
    }


}

export class SampleRegisterDTO  implements  ISampleDTO {
    attributes: any[] = [];
    createdAt: string;
    extractorType: ExtractorType = ExtractorType.GLCM;
    label: string;
    originalFileName: string;
    updatedAt: string;
    fileId: string;

    getAttributes() {
    }


}
