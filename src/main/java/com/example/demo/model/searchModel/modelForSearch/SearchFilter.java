package com.example.demo.model.searchModel.modelForSearch;

public class SearchFilter {
    public enum Operator {
        EQ, LIKE, GT, LT, GTE, LTE, IN, NLIKE, NEQ
    }

    public enum Connector {
        AND, OR
    }

    String fieldName;
    Object value;
    Operator operator;
    Connector connector;


    public SearchFilter() {

    }

    public SearchFilter(String fieldName, Operator operator, Object value) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
        this.connector = Connector.AND;
    }

    public SearchFilter(String fieldName, Object value, Operator operator, Connector connector) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
        this.connector = connector;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Connector getConnector() {
        return connector;
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
    }
}
