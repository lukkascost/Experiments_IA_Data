import {ExtractorType} from './enums/ExtractorType';
import {ISampleDTO, SampleListDTO, SampleRegisterDTO} from './SampleDTO';
import {DatasetListDTO, IDatasetDTO} from './DatasetDTO';

export interface Page<T> {
    content: T[];
    totalElements: number;
    totalPages: number;
    last: boolean;
    first: boolean;
    numberOfElements: number;
    size: number;
    number: number;
    empty: boolean;
}

export class PageDatasetDTOImpl implements Page<IDatasetDTO> {
    content: IDatasetDTO[];
    empty: boolean;
    first: boolean;
    last: boolean;
    number: number;
    numberOfElements: number;
    size: number;
    totalElements: number;
    totalPages: number;
    constructor() {
        this.content = [];
        this.numberOfElements = 0;
        this.totalElements = 0;
    }
}

export class PageSampleImpl implements Page<ISampleDTO> {
    content: ISampleDTO[];
    empty: boolean;
    first: boolean;
    last: boolean;
    number: number;
    numberOfElements: number;
    size: number;
    totalElements: number;
    totalPages: number;


    constructor() {
        this.content = [];
    }
}

