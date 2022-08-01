package com.example.marketapp.dto.response;

import java.math.BigDecimal;
import java.util.List;

public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal amountOfMoney;

    private List<Long> productsIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(BigDecimal amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public List<Long> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(List<Long> productsIds) {
        this.productsIds = productsIds;
    }

    @Override
    public String toString() {
        return "UserResponseDto{"
                + "id=" + id
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", amountOfMoney=" + amountOfMoney
                + ", productsIds=" + productsIds
                + '}';
    }
}
