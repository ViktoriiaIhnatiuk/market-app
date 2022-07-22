package com.example.marketapp.dto.request;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ProductRequestDto {
@NotNull
@NotBlank
@NotEmpty
    private String name;
@Positive
@NotNull
@Digits(integer = 10, fraction = 2)
    private BigDecimal price;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
