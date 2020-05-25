export enum UserType {
    ADMIN = 0,
    OTHER = 1,
    OPERATOR = 2,
    VIEWER = 3
}


export interface IUserDTO {
    description: string;
    name: string;
    userType: UserType;
    email: string;
    password: string;
    confirmPassword: string;
    updatedAt: string;
}

export class UserDTO implements IUserDTO {
    public description: string;
    public name: string;
    public userType: UserType;
    public email: string;
    public password: string;
    public confirmPassword: string;
    public updatedAt: string;

    constructor() {
        this.userType = UserType.VIEWER;
    }
}
