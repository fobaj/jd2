package com.noirix.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cars {

    private Long id;

    private String model;

    private Integer creation_year;

    private Integer user_id;

    private Double price;

    private String color;

    public Cars(Long id, String model, Integer creation_year, Integer user_id) {
        this.id = id;
        this.model = model;
        this.creation_year = creation_year;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
