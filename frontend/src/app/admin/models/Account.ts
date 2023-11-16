import {Classification} from "./Classification";

export class Account {
  accountId: number;
  nameAccount: string;
  associationPin: number;
  balance: number;
  accountClassification: Classification;
  statusClassification: Classification;
  notify: boolean;

  constructor() {
    this.accountClassification = new Classification();
    this.statusClassification = new Classification();
  }
}
