
export interface IDatasetDTO {
    id: string;
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
}
