import {SampleRegisterDTO} from './SampleDTO';

export interface IDatasetDTO {
    description: string;
    name: string;
    samples: any;
    getSamples();
}

export class DatasetListDTO  implements  IDatasetDTO {
    description: string;
    id: string;
    name: string;
    samples: number;

    getSamples() {
        return this.samples;
    }
    constructor() {
        this.description = '';
    }
}

export class DatasetRegisterDTO  implements  IDatasetDTO {
    description: string;
    name: string;
    samples: SampleRegisterDTO[];
    createdAt: string;
    updatedAt: string;

    getSamples() {
        return this.samples;
    }


    constructor() {
        this.description = '';
    }
}
