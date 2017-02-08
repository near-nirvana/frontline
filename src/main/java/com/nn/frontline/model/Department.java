package com.nn.frontline.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by santiago.ginzburg on 2/4/16.
 */
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;

    public Department() {
    }

    protected Department(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    /**Builder.*/
    public static class Builder {
        private Long id;
        private String name;

        public Builder withId(final Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Department build() {
            return new Department(this);
        }
    }
}
