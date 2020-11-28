package org.port.services;

import org.port.dao.BaseRepository;
import org.port.data.enums.SearchLogic;
import org.port.data.search.SearchData;
import org.port.data.search.SearchField;
import org.port.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AbstractBaseService<T, I> implements BaseService<T, I> {

    private final BaseRepository<T, I> repo;
    @Autowired
    private MongoTemplate mongoTemplate;

    public AbstractBaseService(BaseRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<T> findAll() {
        return repo.findAll();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Page<T> search(SearchData<T> searchData) {
        Class<T> clazz = searchData.getClazz();
        Pageable pageable = searchData.getPageable();
        List<SearchField> fields = searchData.getFields();
        Query query = new Query();
        query.with(pageable);
        query = buildCriteria(fields, query);
        List list = mongoTemplate.find(query, clazz);
        Query finalQuery = query;
        return PageableExecutionUtils.getPage(
                list,
                pageable,
                () -> mongoTemplate.count(finalQuery, clazz));
    }

    private Query buildCriteria(List<SearchField> fields, Query query) {
        fields.forEach(field -> {
            String name = field.getFieldName();
            final SearchLogic logic = field.getLogic();
            Object value = field.getValue();

            if (value != null && StringUtil.isNotEmpty(name)) {
                switch (logic) {
                    case like:
                        query.addCriteria(Criteria.where(name).regex(value.toString()));
                    case eq:
                        query.addCriteria(Criteria.where(name).is(value));
                }

            }
        });
        return query;
    }

    private Object convertValue(Field field, Object value) {
        Class clazz = field.getType();

        if (clazz.isEnum()) {
            if (value instanceof Collection) {

                List placeholder = new ArrayList();
                ((Collection) value).forEach(val ->
                        placeholder.add(Enum.valueOf(clazz, val.toString())));

                value = placeholder;
            } else {
                value = Enum.valueOf(clazz, value.toString());
            }
        }

        if (List.class.equals(clazz)) {
            ParameterizedType genericType = (ParameterizedType) field.getGenericType();

            Type[] typeArray = genericType.getActualTypeArguments();
            if (typeArray.length > 0) {
                Type actualType = typeArray[0];
                if (Long.class.equals(actualType)) {
                    if (value instanceof Collection) {

                        List placeholder = new ArrayList();
                        ((Collection) value).forEach(val ->
                                placeholder.add(Long.valueOf(val.toString())));

                        value = placeholder;
                    } else {
                        value = Long.valueOf(value.toString());
                    }
                }
            }
        }

        if (!List.class.equals(clazz) && value instanceof List<?> && !clazz.isEnum()) {
            List placeholder = new ArrayList();
            ((Collection) value).forEach(val ->
                    placeholder.add(Long.valueOf(val.toString())));

            value = placeholder;
        }

        if (clazz == LocalDateTime.class) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            value = LocalDateTime.parse(value.toString(), formatter);
        }

        return value;
    }
}
