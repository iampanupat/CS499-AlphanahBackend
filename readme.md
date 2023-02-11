## API

### Authentication API
| HTTP Methods | Endpoints            | Action               |
|--------------|----------------------|----------------------|
| `POST`       | `/register/customer` | Register as Customer |
| `POST`       | `/register/merchant` | Register as Merchant |
| `POST`       | `/login`             | Login                |

### Account API
| HTTP Methods | Endpoints         | Action                      |
|--------------|-------------------|-----------------------------|
| `GET`        | `/account`        | Get current account detail  |
| `GET`        | `/account/{uuid}` | Get account by Account UUID |
| `PUT`        | `/account`        | Update account detail       |
| `PUT`        | `/account/image`  | Update account image        |

### Product API
| HTTP Methods | Endpoints                 | Action                             |
|--------------|---------------------------|------------------------------------|
| `GET`        | `/product`                | Get all products                   |
| `GET`        | `/product/{product_uuid}` | Get product detail by Product UUID |
| `POST`       | `/product`                | Create product                     |
| `PUT`        | `/product/{product_uuid}` | Update product detail              |
| `DELETE`     | `/product/{product_uuid}` | Delete product                     |

### Product Option API
| HTTP Methods | Endpoints                                              | Action                                    |
|--------------|--------------------------------------------------------|-------------------------------------------|
| `GET`        | `/product/{product_uuid}/option`                       | Get all product options by Product UUID   |
| `GET `       | `/product/{product_uuid}/option/{product_option_uuid}` | Get product option by Product Option UUID |
| `POST`       | `/product/{product_uuid}/option`                       | Create product option                     |
| `PUT `       | `/product/{product_uuid}/option/{product_option_uuid}` | Update product option detail              |
| `DELETE`     | `/product/{product_uuid}/option/{product_option_uuid}` | Delete product option                     |

### Review API
| HTTP Methods | Endpoints                                      | Action                    |
|--------------|------------------------------------------------|---------------------------|
| `GET`        | `/product/{product_uuid}/review`               | Get all reviews           |
| `GET`        | `/product/{product_uuid}/review/{review_uuid}` | Get review by Review UUID |
| `POST`       | `/product/{product_uuid}/review`               | Create review             |
| `PUT`        | `/product/{product_uuid}/review/{review_uuid}` | Update review detail      |
| `DELETE`     | `/product/{product_uuid}/review/{review_uuid}` | Delete review             |

### Category API
| HTTP Methods | Endpoints                          | Action                        |
|--------------|------------------------------------|-------------------------------|
| `GET`        | `/category`                        | Get all categories            |
| `GET`        | `/category/{category_uuid}`        | Get category by Category UUID |
| `POST`       | `/category`                        | Create category               |
| `POST`       | `/category/{parent_category_uuid}` | Create subcategory            |

### Product Category API
| HTTP Methods | Endpoints                                          | Action                                   |
|--------------|----------------------------------------------------|------------------------------------------|
| `POST`       | `/product/{product_uuid}/category/{category_uuid}` | Create product and category relationship |
| `DELETE`     | `/product/{product_uuid}/category/{category_uuid}` | Delete product and category relationship |

### Image API
| HTTP Methods | Endpoints                                                         | Action                 |
|--------------|-------------------------------------------------------------------|------------------------|
| `GET`        | `/product/{product_uuid}/image`                                   | Get all product images |
| `POST`       | `/product/{product_uuid}/image`                                   | Create product image   |
| `DELETE`     | `/product/{product_uuid}/image/{image_uuid}`                      | Delete product image   |
| `GET`        | `/product/{product_uuid}/review/{review_uuid}/image`              | Get all review images  |
| `POST`       | `/product/{product_uuid}/review/{review_uuid}/image`              | Create review image    |
| `DELETE`     | `/product/{product_uuid}/review/{review_uuid}/image/{image_uuid}` | Delete review image    |
