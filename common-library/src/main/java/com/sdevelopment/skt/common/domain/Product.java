package com.sdevelopment.skt.common.domain;

import java.io.Serializable;

@Entity
@Table(name = "employees")
@Data
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "getAllEmployees",
                procedureName = "get_all_employees",
                resultClasses = Product.class)
})
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "getAllEmployees",
                procedureName = "get_all_employees",
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
