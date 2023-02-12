## API

### Authentication API
| HTTP Methods | Endpoints            | Action               | Access Right |
|--------------|----------------------|----------------------|--------------|
| `POST`       | `/register/customer` | Register as Customer | ALL          |
| `POST`       | `/register/merchant` | Register as Merchant | ALL          |
| `POST`       | `/login`             | Login                | ALL          |

### Account API
| HTTP Methods | Endpoints                 | Action                             | Access Right | 
|--------------|---------------------------|------------------------------------|--------------|
| `GET`        | `/account`                | Get current account detail         | ALL          |
| `GET`        | `/account/{account_uuid}` | Get account detail by Account UUID | ALL          |
| `PUT`        | `/account`                | Update current account detail      | ALL          |
| `PUT`        | `/account/image`          | Update current account image       | ALL          |

### Product API
| HTTP Methods | Endpoints                           | Action                                             | Access Right |
|--------------|-------------------------------------|----------------------------------------------------|--------------|
| `GET`        | `/product`                          | Get all product details                            | ALL          |
| `GET`        | `/product/search?keyword={keyword}` | Get all product details that have the same Keyword | ALL          |
| `GET`        | `/product/owned`                    | Get all owned product details                      | MERCHANT     |
| `GET`        | `/product/{product_uuid}`           | Get product detail by Product UUID                 | ALL          |
| `POST`       | `/product`                          | Create product                                     | MERCHANT     |
| `PUT`        | `/product/{product_uuid}`           | Update product detail                              | MERCHANT     |
| `DELETE`     | `/product/{product_uuid}`           | Delete product                                     | MERCHANT     |

### Product Option API
| HTTP Methods | Endpoints                                              | Action                                           | Access Right |
|--------------|--------------------------------------------------------|--------------------------------------------------|--------------|
| `GET`        | `/product/{product_uuid}/option`                       | Get all product option details by Product UUID   | ALL          |
| `GET `       | `/product/{product_uuid}/option/{product_option_uuid}` | Get product option detail by Product Option UUID | ALL          |
| `POST`       | `/product/{product_uuid}/option`                       | Create product option                            | MERCHANT     |
| `PUT `       | `/product/{product_uuid}/option/{product_option_uuid}` | Update product option detail                     | MERCHANT     |
| `DELETE`     | `/product/{product_uuid}/option/{product_option_uuid}` | Delete product option                            | MERCHANT     |

### Review API
| HTTP Methods | Endpoints                                      | Action                                 | Access Right |
|--------------|------------------------------------------------|----------------------------------------|--------------|
| `GET`        | `/product/{product_uuid}/review`               | Get all review details by Product UUID | ALL          |
| `GET`        | `/product/{product_uuid}/review/{review_uuid}` | Get review detail by Review UUID       | ALL          |
| `POST`       | `/product/{product_uuid}/review`               | Create review                          | CUSTOMER     |
| `PUT`        | `/product/{product_uuid}/review/{review_uuid}` | Update review detail                   | CUSTOMER     |
| `DELETE`     | `/product/{product_uuid}/review/{review_uuid}` | Delete review                          | CUSTOMER     |

### Category API
| HTTP Methods | Endpoints                          | Action                               | Access Right |
|--------------|------------------------------------|--------------------------------------|--------------|
| `GET`        | `/category`                        | Get all category details             | ALL          |
| `GET`        | `/category/{category_uuid}`        | Get category detail by Category UUID | ALL          |
| `POST`       | `/category`                        | Create category                      | MERCHANT     |
| `POST`       | `/category/{parent_category_uuid}` | Create subcategory                   | MERCHANT     |

### Product Category API
| HTTP Methods | Endpoints                                          | Action                                   | Access Right |
|--------------|----------------------------------------------------|------------------------------------------|--------------|
| `POST`       | `/product/{product_uuid}/category/{category_uuid}` | Create product and category relationship | MERCHANT     |
| `DELETE`     | `/product/{product_uuid}/category/{category_uuid}` | Delete product and category relationship | MERCHANT     |

### Image API
| HTTP Methods | Endpoints                                                         | Action                                 | Access Right |
|--------------|-------------------------------------------------------------------|----------------------------------------|--------------|
| `GET`        | `/product/{product_uuid}/image`                                   | Get all product images by Product UUID | ALL          |
| `POST`       | `/product/{product_uuid}/image`                                   | Create product image                   | MERCHANT     |
| `DELETE`     | `/product/{product_uuid}/image/{image_uuid}`                      | Delete product image                   | MERCHANT     |
| `GET`        | `/product/{product_uuid}/review/{review_uuid}/image`              | Get all review images by Review UUID   | ALL          |
| `POST`       | `/product/{product_uuid}/review/{review_uuid}/image`              | Create review image                    | CUSTOMER     |
| `DELETE`     | `/product/{product_uuid}/review/{review_uuid}/image/{image_uuid}` | Delete review image                    | CUSTOMER     |

### Order API
| HTTP Methods | Endpoints         | Action                   | Access Right |
|--------------|-------------------|--------------------------|--------------|
| `GET`        | `/cart`           | Get shopping cart detail | CUSTOMER     |
| `GET`        | `/purchase_order` | Get all purchase history | CUSTOMER     |
| `POST`       | `/checkout`       | Checkout shopping cart   | CUSTOMER     |

### Order Item API
| HTTP Methods | Endpoints                                           | Action                                                | Access Right |
|--------------|-----------------------------------------------------|-------------------------------------------------------|--------------|
| `POST`       | `/cart/{product_uuid}/option/{product_option_uuid}` | Add product to cart                                   | CUSTOMER     |
| `DELETE`     | `/cart/{product_uuid}/option/{product_option_uuid}` | Remove product from cart                              | CUSTOMER     |
| `GET`        | `/sale_order`                                       | Get all sales order details                           | MERCHANT     |
| `GET`        | `/sale_order/{order_item_uuid}`                     | Get sales order detail by Order Item UUID             | MERCHANT     |
| `PUT`        | `/sale_order/{order_item_uuid}`                     | Update sales order delivery status by Order Item UUID | MERCHANT     |