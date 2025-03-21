package com.two_weeks_backend.two_weeks_backend.utils.specification;

import lombok.ToString;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ToString
public class Predicate {
    private String attribute;
    private String operator;
    private String values;

    private Integer startIndex;
    private Integer endIndex;

    private jakarta.persistence.criteria.Predicate predicate;

    public Predicate(String query, Integer startIndex, Integer endIndex, @SuppressWarnings("rawtypes") Root root,
            CriteriaBuilder cb) {
        Pattern pattern = Pattern.compile("([\\w.]+)<(\\w+)>([\\w,\\s]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(query);
        if (matcher.find()) {
            this.attribute = matcher.group(1);
            this.operator = matcher.group(2);
            this.values = matcher.group(3);
        }
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.predicate = this.buildPredicate(root, cb);

    }

    public Predicate(Predicate predicate, Connector connector) {
        this.startIndex = connector.getStartIndex();
        this.endIndex = predicate.getEndIndex();
        this.predicate = predicate.getJavaxPredicate().not();
    }

    public Predicate(Predicate predicate1, Predicate predicate2, Connector connector, CriteriaBuilder cb) {
        this.startIndex = predicate1.getStartIndex();
        this.endIndex = predicate2.getEndIndex();

        jakarta.persistence.criteria.Predicate javaxPredicate1 = predicate1.getJavaxPredicate();
        jakarta.persistence.criteria.Predicate javaxPredicate2 = predicate2.getJavaxPredicate();
        switch (connector.getConnector()) {
            case "and":
                this.predicate = cb.and(javaxPredicate1, javaxPredicate2);
                break;
            case "or":
                this.predicate = cb.or(javaxPredicate1, javaxPredicate2);
                break;
        }
    }

    @SuppressWarnings("unchecked")
    private jakarta.persistence.criteria.Predicate buildPredicate(@SuppressWarnings("rawtypes") Root root,
            CriteriaBuilder cb) {
        jakarta.persistence.criteria.Predicate predicate = null;
        @SuppressWarnings("rawtypes")
        Path attribute = getAttribute(root);

        switch (this.operator) {
            case "eq":
                Object valueEq = this.getValue();
                if (this.getValue().equalsIgnoreCase("true") || this.getValue().equalsIgnoreCase("false")) {
                    valueEq = Boolean.parseBoolean(this.getValue());
                }
                predicate = cb.equal(attribute, valueEq);
                break;
            case "ne":
                Object valueNe = this.getValue();
                if (this.getValue().equalsIgnoreCase("true") || this.getValue().equalsIgnoreCase("false")) {
                    valueNe = Boolean.parseBoolean(this.getValue());
                }
                predicate = cb.notEqual(attribute, valueNe);
                break;
            case "ct":
                predicate = cb.like(attribute, "%" + this.getValue() + "%");
                break;
            case "sw":
                predicate = cb.like(attribute, "%" + this.getValue());
                break;
            case "ew":
                predicate = cb.like(attribute, this.getValue() + "%");
                break;
            case "gt":
                predicate = cb.greaterThan(attribute, this.getValue());
                break;
            case "lt":
                predicate = cb.lessThan(attribute, this.getValue());
                break;
            case "ge":
                predicate = cb.greaterThanOrEqualTo(attribute, this.getValue());
                break;
            case "le":
                predicate = cb.lessThanOrEqualTo(attribute, this.getValue());
                break;
            case "in":
                // predicate = attribute.in(this.getValues());
                break;
        }
        return predicate;
    }

    @SuppressWarnings("rawtypes")
    private Path getAttribute(Root root) {
        if (this.containsNestedEntities()) {
            String[] nestedEntitiesAndAttribute = this.getNestedEntitiesAndAttribute();

            String firstNestedEntity = nestedEntitiesAndAttribute[0];
            Join join = root.join(firstNestedEntity);

            for (Integer i = 1; i < nestedEntitiesAndAttribute.length - 1; i++) {
                String nestedEntity = nestedEntitiesAndAttribute[i];
                join = join.join(nestedEntity);
            }

            String attribute = nestedEntitiesAndAttribute[nestedEntitiesAndAttribute.length - 1];

            return join.get(attribute);
        }

        return root.get(this.attribute);
    }

    private boolean containsNestedEntities() {
        return this.attribute.contains(".");
    }

    private String[] getNestedEntitiesAndAttribute() {
        return this.attribute.split("[.]");
    }

    private String getValue() {
        return this.values;
    }

    private String[] getValues() {
        return this.values.split("[,]");
    }

    public jakarta.persistence.criteria.Predicate getJavaxPredicate() {
        return this.predicate;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public boolean isEndIndex(Integer endIndex) {
        return this.endIndex == endIndex;
    }

    public boolean isStartIndex(Integer startIndex) {
        return this.startIndex == startIndex;
    }
}
