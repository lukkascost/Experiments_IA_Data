import {IDatasetDTO} from './DatasetDTO';
import {IExperimentDTO} from './ExperimentsDTO';

export interface IRoundsDTO {
    createdAt: string;
    updatedAt: string;
    dataset: IDatasetDTO;
    experiment: IExperimentDTO;
    name: string;
}

export class RoundsListDTO implements  IRoundsDTO {
    id: string;
    name: string;
    createdAt: string;
    dataset: IDatasetDTO;
    experiment: IExperimentDTO;
    updatedAt: string;
    confusionMatrix: any;
    labels: any;
    confusionMatrixTotalElements: number;
    constructor() {
    }
}
