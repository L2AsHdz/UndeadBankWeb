import {Classification} from "./Classification";

export class AccountType {
  accountTypeDataId: number;
  currency: string;
  initialBalance: number;
  exchangeRate: number;
  accountTypeClassification: Classification;

  constructor() {
    this.accountTypeClassification = new Classification();
  }
}
