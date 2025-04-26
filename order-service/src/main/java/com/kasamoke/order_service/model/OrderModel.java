package com.kasamoke.order_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "orders")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_id;

    @NotNull(message = "Product id is mandatory")
    private Integer product_id;

    @NotNull(message = "Product quantity is mandatory")
    private Integer product_quantity;

    @NotBlank(message = "Shipping address is mandatory")
    private String shipping_address;

    @NotBlank(message = "Customer name is mandatory")
    private String customer_name;

    @NotBlank(message = "Customer address is mandatory")
    private String customer_address;

    @NotNull(message = "Order Date is mandatory")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date order_date;

    public OrderModel() {
    }

    public OrderModel(Integer order_id, Integer product_id, Integer product_quantity, String shipping_address, String customer_name, String customer_address, Date order_date) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.product_quantity = product_quantity;
        this.shipping_address = shipping_address;
        this.customer_name = customer_name;
        this.customer_address = customer_address;
        this.order_date = order_date;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(Integer product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }
}
