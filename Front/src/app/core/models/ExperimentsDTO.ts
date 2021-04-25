
export interface IExperimentDTO {
    description: string;
    name: string;
}

export class ExperimentListDTO  implements  IExperimentDTO {
    description: string;
    id: string;
    name: string;
    rounds: number;
    constructor() {
        this.description = '';
    }
}

export class ExperimentRegisterDTO  implements  IExperimentDTO {
    description: string;
    name: string;
    id: string;
    createdAt: string;
    updatedAt: string;

    constructor() {
        this.description = '';
    }
}
