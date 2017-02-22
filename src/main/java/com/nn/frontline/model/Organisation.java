package com.nn.frontline.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by santiagoginzburg on 14/02/2017.
 */

@Entity
@Table(name = "organisation")
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("PMD")
public class Organisation {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;
    @Column
    private String url;
    @Column
    private Double lat;
    @Column
    private Double lng;
    @Column
    private String postcode;
    @Column
    private String type;
    @Column
    private String summary;
    @Column
    private String branch;
    @Column
    private String tags;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String distance;


    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(final Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(final Double lng) {
        this.lng = lng;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(final String postcode) {
        this.postcode = postcode;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(final String summary) {
        this.summary = summary;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(final String branch) {
        this.branch = branch;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(final String tags) {
        this.tags = tags;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(final String distance) {
        this.distance = distance;
    }

    public Organisation(){

    }


    public Organisation(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.url = builder.url;
        this.lat = builder.lat;
        this.lng = builder.lng;
        this.postcode = builder.postcode;
        this.type = builder.type;
        this.summary = builder.summary;
        this.branch =  builder.branch;
        this.tags = builder.tags;
    }

    /**Builder.*/
    public static class Builder {
        private Long id;
        private String name;
        private String url;
        private Double lat;
        private Double lng;
        private String postcode;
        private String type;
        private String summary;
        private String branch;
        private String tags;

        public Builder withId(final Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withUrl(final String url) {
            this.url = url;
            return this;
        }

        public Builder withLat(final Double lat) {
            this.lat = lat;
            return this;
        }

        public Builder withLng(final Double lng) {
            this.lng = lng;
            return this;
        }

        public Builder withPostcode(final String postcode) {
            this.postcode = postcode;
            return this;
        }

        public Builder withType(final String type) {
            this.type = type;
            return this;
        }

        public Builder withSummary(final String summary) {
            this.summary = summary;
            return this;
        }

        public Builder withBranch(final String branch) {
            this.branch = branch;
            return this;
        }

        public Builder withTags(final String tags) {
            this.tags = tags;
            return this;
        }

        public Organisation build() {
            return new Organisation(this);
        }
    }
}
