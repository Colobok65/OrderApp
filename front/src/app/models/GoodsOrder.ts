import {OrderLine} from './OrderLine';

export interface GoodsOrder {
  id?: number;
  client: string;
  date: string;
  address: string;
  lines: OrderLine[];
}
