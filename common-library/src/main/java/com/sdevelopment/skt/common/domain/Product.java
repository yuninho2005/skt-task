package com.sdevelopment.skt.common.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "products")
//@Data
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "saveProduct",
                procedureName = "insertproductsp",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "_name", type = String.class)
                }),

        @NamedStoredProcedureQuery(name = "getAllProducts",
                procedureName = "listproductssp",
                resultClasses = Product.class)
})
public class Product implements Serializable {
    private Integer id;
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
