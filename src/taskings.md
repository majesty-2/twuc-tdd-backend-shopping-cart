- 场景1：Given 购物车中没有商品，When请求购物车，Then返回空的购物车信息和商品总价（数据结构和样例）
- [ ] Controller:  stub service, 接收请求、调用service，返回购物车信息
- [ ] Service：stub repository，调用 repository 获取商品，计算总价返回购物车信息
- [ ] Repository：fake DB，调用db获取购物车商品信息
- [ ] API 测试：fake DB

```json
// GET /shoppingCart
{
  "response": {
    "products": [
      {
        "id": "number",
        "name": "string",
        "price": "decimal",
        "quantity": "number"
      }
    ],
    "totalAmount": "decimal"
  }
}

//exmaple: 

{
  "response": {
   "products":[], 
   "totalAmount": 0
  }
}

```

- 场景2：Given 购物车中有商品，When请求购物车，Then返回购物车信息和商品总价（数据结构和样例）
- [ ] Controller:  stub service, 接收请求、调用service，返回购物车信息
- [ ] Service：stub repository，调用 repository 获取商品，计算总价返回购物车信息
- [ ] Repository：fake DB，调用db获取购物车商品信息
- [ ] API 测试：fake DB

```json
//exmaple: 
{
  "response": {
    "products": [
      {
        "id":1,
        "name": "cola",
        "price": 1,
        "quantity": 1
      },{
        "id":2,
        "name": "candy",
        "price": 2,
        "quantity": 2
      }
    ],
    "totalAmount": 5
  }
}
```
