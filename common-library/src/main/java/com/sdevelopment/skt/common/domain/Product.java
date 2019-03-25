package com.sdevelopment.skt.common.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "products")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "saveProduct",
                procedureName = "InsertProduct",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "pname", type = String.class)
                }),

        @NamedStoredProcedureQuery(name = "getAllProducts",
                procedureName = "GetAllProducts",
                resultClasses = Product.class)
})
public class Product implements Serializable {
    @Id
    private Integer id;

    @NotBlank(message = "Name may not be empty")
    private String name;

    public Product() {

    }

    public Product(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
