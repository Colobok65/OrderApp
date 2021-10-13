import {OrderLine} from './OrderLine';

export interface GoodsOrder {
  id?: number;
  client: string;
  date: string;
  address: string;
  userId: number;
  lines: OrderLine[];
}
