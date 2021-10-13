import {OrderLine} from './OrderLine';

export interface User {
  id?: number;
  username: string;
  login: string;
  address: string;
  orders: OrderLine[];
}
