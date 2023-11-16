import {Classification} from "./Classification";

export class User {
  userId: number;
  identificationNumber: number;
  fullName: string;
  username: string;
  password: string;
  birthday: string;
  email: string;
  phoneNumber: string;
  userClassification: Classification;

  constructor() {
    this.userClassification = new Classification();
  }
}
