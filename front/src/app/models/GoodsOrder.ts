import {OrderLine} from './OrderLine';

export interface GoodsOrder {
  id?: number;
  client: string;
  data: string;
  address: string;
  lines: OrderLine[];
}
