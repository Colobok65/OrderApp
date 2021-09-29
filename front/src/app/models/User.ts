import {OrderLine} from './OrderLine';

export interface User {
  id?: number;
  login: string;
  username: string;
  orders: OrderLine[];
}
