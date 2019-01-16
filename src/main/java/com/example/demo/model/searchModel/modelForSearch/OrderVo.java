package com.example.demo.model.searchModel.modelForSearch;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

import org.springframework.data.domain.Sort;

public class OrderVo {
    private String property;
    private String direction;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Sort.Direction getDirection() {
        if (DESC.toString().equalsIgnoreCase(direction.toUpperCase())){
            return DESC;
        }else {
            return ASC;
        }
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
