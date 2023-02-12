package com.alphanah.alphanahbackend.model.order_item;

import com.alphanah.alphanahbackend.model.order.PaidResponseM1;
import com.alphanah.alphanahbackend.model.product.ProductResponseM1;
import com.alphanah.alphanahbackend.model.product_option.ProductOptionResponseM1;
import lombok.Data;

@Data
public class PaidItemResponseM2 extends PaidItemResponseM1 {
    private ProductResponseM1 product;
    private ProductOptionResponseM1 option;
    private PaidResponseM1 order;
}
