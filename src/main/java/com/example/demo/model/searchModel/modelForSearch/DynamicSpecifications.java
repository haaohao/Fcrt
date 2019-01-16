package com.example.demo.model.searchModel.modelForSearch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class DynamicSpecifications {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static Logger logger = LoggerFactory.getLogger(DynamicSpecifications.class);

    private DynamicSpecifications() {
    }

    public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters) {
        return (root, query, builder) -> {
            if (CollectionUtils.isEmpty(filters)) {
                return builder.conjunction();
            }
            List<Predicate> andPredicates = new ArrayList<>();
            List<Predicate> orPredicates = new ArrayList<>();

            for (Iterator<SearchFilter> iterator = filters.iterator(); iterator.hasNext(); ) {
                SearchFilter filter = iterator.next();
                Path expression = getSearchFilterPath(root, filter);
                Object value = filter.value;
                if (Date.class.equals(expression.getJavaType()) && value != null) {
                    try {
                        value = dateFormat.parse(value.toString());
                    } catch (ParseException e) {
                        logger.error(e.getMessage());
                        value = filter.value;
                    }
                }
                if (filter.connector == SearchFilter.Connector.OR) {
                    buildPredicts(orPredicates, filter, builder, expression, root, value);

                } else {
                    buildPredicts(andPredicates, filter, builder, expression, root, value);

                }
            }
            if ((!CollectionUtils.isEmpty(andPredicates)) && (!CollectionUtils.isEmpty(orPredicates))) {
                return builder.and(builder.and(andPredicates.toArray(new Predicate[andPredicates.size()])), builder.or(orPredicates.toArray(new Predicate[orPredicates.size()])));
            } else {
                if (!CollectionUtils.isEmpty(andPredicates)) {
                    return builder.and(andPredicates.toArray(new Predicate[andPredicates.size()]));
                }
                if (!CollectionUtils.isEmpty(orPredicates)) {
                    return builder.or(orPredicates.toArray(new Predicate[orPredicates.size()]));
                }
            }
            return builder.conjunction();
        };
    }

    public static Path getSearchFilterPath(Root root, SearchFilter filter) {
        // nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
        String[] names = StringUtils.split(filter.fieldName, ".");
        Path expression = root.get(names[0]);
        for (int i = 1; i < names.length; i++) {
            expression = expression.get(names[i]);
        }
        return expression;
    }

    private static void buildPredicts(List<Predicate> predicates, SearchFilter filter, CriteriaBuilder builder, Path expression, Root root, Object value) {
        // logic operator
        switch (filter.operator) {
            case EQ:
                if (value == null) {
                    predicates.add(builder.isNull(expression));
                } else {
                    predicates.add(builder.equal(expression, value));
                }
                break;
            case NLIKE:
                predicates.add(builder.notLike(builder.upper(expression), "%" + value.toString().toUpperCase() + "%"));
                break;
            case NEQ:
                if (value == null) {
                    predicates.add(builder.isNotNull(expression));
                } else {
                    predicates.add(builder.notEqual(expression, value));
                }
                break;
            case LIKE:
                predicates.add(builder.like(builder.upper(expression), "%" + value.toString().toUpperCase() + "%"));
                break;
            case GT:
                predicates.add(builder.greaterThan(expression, (Comparable) value));
                break;
            case LT:
                predicates.add(builder.lessThan(expression, (Comparable) value));
                break;
            case GTE:
                predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) value));
                break;
            case LTE:
                predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) value));
                break;
            case IN:
                predicates.add(expression.in((List<String>) value));
                break;
            default:
                break;
        }
    }

}



