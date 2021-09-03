export interface OrderLine {
  id?: number;
  orderId: number;
  goodsId: number;
  countNumber: number;
  goodsName: string;
  price: number;
}
