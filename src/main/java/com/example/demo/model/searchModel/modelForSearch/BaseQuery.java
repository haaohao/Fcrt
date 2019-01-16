package com.example.demo.model.searchModel.modelForSearch;

import com.example.demo.utils.ModelUtils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

public class BaseQuery<T> {
    protected Pageable pageable = null;
    protected Sort sort;
    protected Integer page = -1;
    protected Integer start = 0;
    protected Integer limit = 10;
    protected List<OrderVo> sortOrderList=new ArrayList<>();
    protected List<SearchFilter> searchFilters;
    protected Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public BaseQuery() {
        entityClass = ModelUtils.getUniquenessGenericClass(getClass());
    }


    public Pageable getPageable() {
        if (pageable == null && page >= 0) {
            if (getSort() == null) {
                pageable = PageRequest.of(page - 1, limit);
            }
            pageable = PageRequest.of(page - 1, limit, getSort());
        }
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Sort getSort() {
        return Sort.by(getSortOrderList());

    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public List<SearchFilter> getSearchFilters() {
        return searchFilters;
    }

    public void setSearchFilters(List<SearchFilter> searchFilters) {
        this.searchFilters = searchFilters;
    }

    public Specification<T> buildSpecification() {
        if (getSearchFilters() == null || CollectionUtils.isEmpty(getSearchFilters())) {
            return null;
        } else {
            return DynamicSpecifications.bySearchFilter(getSearchFilters());
        }

    }

    public List<Sort.Order> getSortOrderList() {
        List<Sort.Order> list = new ArrayList<>();
        for (OrderVo orderVo : sortOrderList) {
            list.add(new Sort.Order(orderVo.getDirection(), orderVo.getProperty()));
        }
        return list;
    }

    public void setSortOrderList(List<OrderVo> sortOrderList) {
        this.sortOrderList = sortOrderList;
    }
}
