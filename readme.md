## API

### Authentication API
| HTTP Methods | Endpoints                 | Action               | Access Right |
|--------------|---------------------------|----------------------|--------------|
| `POST`       | `/register?role=CUSTOMER` | Register as Customer | **ALL**      |
| `POST`       | `/register?role=MERCHANT` | Register as Merchant | **ALL**      |
| `POST`       | `/login`                  | Login                | **ALL**      |

**`POST` | `/register?role=CUSTOMER` & `/register?role=MERCHANT` : JSON Request Body**
```
{
    "email" : "dummy@email.com",
    "password" : "Qwertyui1",
    "confirmPassword" : "Qwertyui1"
}
```

**`POST` | `/login` : JSON Request Body**
```
{
    "email" : "dummy@email.com",
    "password" : "Qwertyui1"
}
```


### Account API
| HTTP Methods | Endpoints                 | Action                             | Access Right | 
|--------------|---------------------------|------------------------------------|--------------|
| `GET`        | `/account`                | Get current account detail         | **ALL**      |
| `GET`        | `/account/{account_uuid}` | Get account detail by Account UUID | **ALL**      |
| `PUT`        | `/account`                | Update current account detail      | **ALL**      |
| `PUT`        | `/account/image`          | Update current account image       | **ALL**      |

**`PUT` | `/account` : JSON Request Body**
```
{
    "firstname" : "Stop"
    "lastname" : "Jeep"
    "address" : "1 Infinite Loop, Cupertino, CA 95014"
    "phone" : 0901111111
}
```


### Product API
| HTTP Methods | Endpoints                    | Action                                   | Access Right |
|--------------|------------------------------|------------------------------------------|--------------|
| `GET`        | `/product`                   | Get all product details                  | **ALL**      |
| `GET`        | `/product?name={String}`     | Get all product details by Name          | **ALL**      |
| `GET`        | `/product?category={String}` | Get all product details by Category      | **ALL**      |
| `GET`        | `/product?merchant={UUID}`   | Get all product details by Merchant UUID | **ALL**      |
| `GET`        | `/product/{product_uuid}`    | Get product detail by Product UUID       | **ALL**      |
| `POST`       | `/product`                   | Create product                           | **MERCHANT** |
| `PUT`        | `/product/{product_uuid}`    | Update product detail                    | **MERCHANT** |
| `DELETE`     | `/product/{product_uuid}`    | Delete product                           | **MERCHANT** |

**`POST` | `/product` : JSON Request Body**

**`PUT` | `/product/{product_uuid}` : JSON Request Body**
```
{
    "name" : "iPhone 14 Pro Max"
    "description" : "An expensive phone"
}
```


### Product Option API
| HTTP Methods | Endpoints                                              | Action                                           | Access Right |
|--------------|--------------------------------------------------------|--------------------------------------------------|--------------|
| `GET`        | `/product/{product_uuid}/option`                       | Get all product option details by Product UUID   | **ALL**      |
| `GET `       | `/product/{product_uuid}/option/{product_option_uuid}` | Get product option detail by Product Option UUID | **ALL**      |
| `POST`       | `/product/{product_uuid}/option`                       | Create product option                            | **MERCHANT** |
| `PUT `       | `/product/{product_uuid}/option/{product_option_uuid}` | Update product option detail                     | **MERCHANT** |
| `DELETE`     | `/product/{product_uuid}/option/{product_option_uuid}` | Delete product option                            | **MERCHANT** |

**`POST` | `/product/{product_uuid}/option` : JSON Request Body**

**`PUT` | `/product/{product_uuid}/option/{product_option_uuid}` : JSON Request Body**
```
{
    "name" : "128GB"
    "price" : 44900
    "quantity" : 100 
}
```


### Review API
| HTTP Methods | Endpoints                                      | Action                                 | Access Right |
|--------------|------------------------------------------------|----------------------------------------|--------------|
| `GET`        | `/product/{product_uuid}/review`               | Get all review details by Product UUID | **ALL**      |
| `GET`        | `/product/{product_uuid}/review/{review_uuid}` | Get review detail by Review UUID       | **ALL**      |
| `POST`       | `/product/{product_uuid}/review`               | Create review                          | **CUSTOMER** |
| `PUT`        | `/product/{product_uuid}/review/{review_uuid}` | Update review detail                   | **CUSTOMER** |
| `DELETE`     | `/product/{product_uuid}/review/{review_uuid}` | Delete review                          | **CUSTOMER** |

**`POST` | `/product/{product_uuid}/review` : JSON Request Body**

**`PUT` | `/product/{product_uuid}/review/{review_uuid}` : JSON Request Body**
```
{
    "message" : "Good Product",
    "rating" : 5,
}
```


### Category API
| HTTP Methods | Endpoints                          | Action                               | Access Right |
|--------------|------------------------------------|--------------------------------------|--------------|
| `GET`        | `/category`                        | Get all category details             | **ALL**      |
| `GET`        | `/category/{category_uuid}`        | Get category detail by Category UUID | **ALL**      |
| `POST`       | `/category`                        | Create category                      | **MERCHANT** |
| `POST`       | `/category/{parent_category_uuid}` | Create subcategory                   | **MERCHANT** |

### Product Category API
| HTTP Methods | Endpoints                                          | Action                                   | Access Right |
|--------------|----------------------------------------------------|------------------------------------------|--------------|
| `POST`       | `/product/{product_uuid}/category/{category_uuid}` | Create product and category relationship | **MERCHANT** |
| `DELETE`     | `/product/{product_uuid}/category/{category_uuid}` | Delete product and category relationship | **MERCHANT** |

### Image API
| HTTP Methods | Endpoints                                                         | Action                                 | Access Right |
|--------------|-------------------------------------------------------------------|----------------------------------------|--------------|
| `POST`       | `/product/{product_uuid}/main_image`                              | Create product main image              | **MERCHANT** |
| `GET`        | `/product/{product_uuid}/image`                                   | Get all product images by Product UUID | **ALL**      |
| `POST`       | `/product/{product_uuid}/image`                                   | Create product image                   | **MERCHANT** |
| `DELETE`     | `/product/{product_uuid}/image/{image_uuid}`                      | Delete product image                   | **MERCHANT** |
| `GET`        | `/product/{product_uuid}/review/{review_uuid}/image`              | Get all review images by Review UUID   | **ALL**      |
| `POST`       | `/product/{product_uuid}/review/{review_uuid}/image`              | Create review image                    | **CUSTOMER** |
| `DELETE`     | `/product/{product_uuid}/review/{review_uuid}/image/{image_uuid}` | Delete review image                    | **CUSTOMER** |

### Order API
| HTTP Methods | Endpoints                      | Action                                  | Access Right |
|--------------|--------------------------------|-----------------------------------------|--------------|
| `POST`       | `/cart/coupon/{coupon_code}`   | Apply coupon to shopping cart           | **CUSTOMER** |
| `DELETE`     | `/cart/coupon`                 | Remove coupon from shopping cart        | **CUSTOMER** |
| `GET`        | `/cart`                        | Get shopping cart detail                | **CUSTOMER** |
| `GET`        | `/purchase_order`              | Get all purchase history                | **CUSTOMER** |
| `GET`        | `/purchase_order/{order_uuid}` | Get purchase order detail by Order UUID | **CUSTOMER** |
| `POST`       | `/checkout`                    | Checkout shopping cart                  | **CUSTOMER** |

**`POST` | `/checkout` : JSON Request Body**
```
{
    "paymentMethodId" : "",
    "paymentIntentId" : "",
    "firstname" : "Jebs",
    "lastname" : "Brazil",
    "phone" : "0800000000",
    "address" : "50 Ngamwongwan Rd, Chatuchak Bangkok 10900 Thailand"
}
```


### Order Item API
| HTTP Methods | Endpoints                                           | Action                                                | Access Right |
|--------------|-----------------------------------------------------|-------------------------------------------------------|--------------|
| `POST`       | `/cart/{product_uuid}/option/{product_option_uuid}` | Add product to cart                                   | **CUSTOMER** |
| `PUT`        | `/cart/{product_uuid}/option/{product_option_uuid}` | Update product quantity in shopping cart              | **CUSTOMER** |
| `DELETE`     | `/cart/{product_uuid}/option/{product_option_uuid}` | Remove product from cart                              | **CUSTOMER** |
| `GET`        | `/sale_order`                                       | Get all sales order details                           | **MERCHANT** |
| `GET`        | `/sale_order/{order_item_uuid}`                     | Get sales order detail by Order Item UUID             | **MERCHANT** |
| `PUT`        | `/sale_order/{order_item_uuid}`                     | Update sales order delivery status by Order Item UUID | **MERCHANT** |

**`POST` & `PUT` | `/cart/{product_uuid}/option/{product_option_uuid}` : JSON Request Body**
```
{
    "quantity" : 5
}
```


### Coupon API
| HTTP Methods | Endpoints                   | Action                                    | Access Right |
|--------------|-----------------------------|-------------------------------------------|--------------|
| `GET`        | `/coupon`                   | Get all coupon details                    | **ALL**      |
| `GET`        | `/coupon?type={Enum}`       | Get all coupon details by Type            | **ALL**      |   
| `GET`        | `/coupon?started={Boolean}` | Get all coupon details by Started Boolean | **ALL**      |   
| `GET`        | `/coupon?expired={Boolean}` | Get all coupon details by Expired Boolean | **ALL**      |   
| `GET`        | `/coupon?runOut={Boolean}`  | Get all coupon details by Run Out Boolean | **ALL**      |   
| `GET`        | `/coupon?merchant={UUID}`   | Get all coupon details by Merchant UUID   | **ALL**      |         
| `GET`        | `/coupon/{coupon_code}`     | Get coupon detail by Coupon Code          | **ALL**      |        
| `POST`       | `/coupon`                   | Create coupon                             | **MERCHANT** |
| `DELETE`     | `/coupon/{coupon_code}`     | Delete coupon                             | **MERCHANT** |

**`POST` | `/coupon` : JSON Request Body**
```
{ 
    "code" : "ALPHANAH",
    "type" : "FREE_SHIPPING" / "GIFT_CARD" / "PERCENTAGE_DISCOUNT",
    "value" : 50,
    "startDate" : "2023-03-01T00:00:00.000+07:00",
    "endDate" : "2023-04-01T00:00:00.000+07:00",
    "maxUse" : 10
}
```
