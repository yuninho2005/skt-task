package com.sdevelopment.skt.common.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
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

    @Override
    public boolean equals(Object o) {
        // null check
        if (o == null) {
            return false;
        }

        // this instance check
        if (this == o) {
            return true;
        }

        // instanceof Check and actual value check
        if ((o instanceof Product) && (((Product) o).getName() == this.name)) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {
        int prime = 31; // Odd prime commonly use to generate hashCode due to it's mathematical properties.
        int result = 1;

        result = prime * result + ((name == null) ? 0 : name.hashCode());

        return result;
    }
}
