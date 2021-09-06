package com.metocs.common.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;


public interface BaseService<T> extends IService<T> {


    default boolean save(T entity,String data) {
        return SqlHelper.retBool(this.getBaseMapper().insert(entity));
    }

    default boolean saveBatch(Collection<T> entityList,String data) {
        return this.saveBatch(entityList, 1000);
    }

    boolean saveBatch(Collection<T> entityList, int batchSize,String data);

    @Transactional(
            rollbackFor = {Exception.class}
    )
    default boolean saveOrUpdateBatch(Collection<T> entityList,String data) {
        return this.saveOrUpdateBatch(entityList, 1000);
    }


    default boolean removeById(Serializable id,String data) {
        return SqlHelper.retBool(this.getBaseMapper().deleteById(id));
    }


    default boolean removeByMap(Map<String, Object> columnMap,String data) {
        Assert.notEmpty(columnMap, "error: columnMap must not be empty", new Object[0]);
        return SqlHelper.retBool(this.getBaseMapper().deleteByMap(columnMap));
    }


    default boolean remove(Wrapper<T> queryWrapper,String data) {
        return SqlHelper.retBool(this.getBaseMapper().delete(queryWrapper));
    }


    default boolean removeByIds(Collection<? extends Serializable> idList,String data) {
        return CollectionUtils.isEmpty(idList) ? false : SqlHelper.retBool(this.getBaseMapper().deleteBatchIds(idList));
    }


    default boolean updateById(T entity,String data) {
        return SqlHelper.retBool(this.getBaseMapper().updateById(entity));
    }


    default boolean update(Wrapper<T> updateWrapper,String data) {
        return this.update((T) null, updateWrapper);
    }


    default boolean update(T entity, Wrapper<T> updateWrapper,String data) {
        return SqlHelper.retBool(this.getBaseMapper().update(entity, updateWrapper));
    }


    @Transactional(
            rollbackFor = {Exception.class}
    )
    default boolean updateBatchById(Collection<T> entityList,String data) {
        return this.updateBatchById(entityList, 1000);
    }


    boolean updateBatchById(Collection<T> entityList, int batchSize,String data);


    boolean saveOrUpdate(T entity,String data);

    T getOne(Wrapper<T> queryWrapper, boolean throwEx,String data);

    Map<String, Object> getMap(Wrapper<T> queryWrapper,String data);


    default T getById(Serializable id,String data) {
        return this.getBaseMapper().selectById(id);
    }


    default List<T> listByIds(Collection<? extends Serializable> idList,String data) {
        return this.getBaseMapper().selectBatchIds(idList);
    }


    default List<T> listByMap(Map<String, Object> columnMap,String data) {
        return this.getBaseMapper().selectByMap(columnMap);
    }


    default T getOne(Wrapper<T> queryWrapper,String data) {
        return this.getOne(queryWrapper, true);
    }



    default int count(String data) {
        return this.count(Wrappers.emptyWrapper());
    }


    default int count(Wrapper<T> queryWrapper,String data) {
        return SqlHelper.retCount(this.getBaseMapper().selectCount(queryWrapper));
    }


    default List<T> list(Wrapper<T> queryWrapper,String data) {
        return this.getBaseMapper().selectList(queryWrapper);
    }


    default List<T> list(String data) {
        return this.list(Wrappers.emptyWrapper());
    }


    default <E extends IPage<T>> E page(E page, Wrapper<T> queryWrapper,String data) {
        return this.getBaseMapper().selectPage(page, queryWrapper);
    }


    default <E extends IPage<T>> E page(E page,String data) {
        return this.page(page, Wrappers.emptyWrapper());
    }


    default List<Map<String, Object>> listMaps(Wrapper<T> queryWrapper,String data) {
        return this.getBaseMapper().selectMaps(queryWrapper);
    }


    default List<Map<String, Object>> listMaps(String data) {
        return this.listMaps(Wrappers.emptyWrapper());
    }


    default List<Object> listObjs(String data) {
        return this.listObjs(Function.identity());
    }


    default <V> List<V> listObjs(Function<? super Object, V> mapper,String data) {
        return this.listObjs(Wrappers.emptyWrapper(), mapper);
    }


    default List<Object> listObjs(Wrapper<T> queryWrapper,String data) {
        return this.listObjs(queryWrapper, Function.identity());
    }


    default <V> List<V> listObjs(Wrapper<T> queryWrapper, Function<? super Object, V> mapper,String data) {
        return (List)this.getBaseMapper().selectObjs(queryWrapper).stream().filter(Objects::nonNull).map(mapper).collect(Collectors.toList());
    }


    default <E extends IPage<Map<String, Object>>> E pageMaps(E page, Wrapper<T> queryWrapper,String data) {
        return this.getBaseMapper().selectMapsPage(page, queryWrapper);
    }


    default <E extends IPage<Map<String, Object>>> E pageMaps(E page,String data) {
        return this.pageMaps(page, Wrappers.emptyWrapper());
    }


    default boolean saveOrUpdate(T entity, Wrapper<T> updateWrapper,String data) {
        return this.update(entity, updateWrapper) || this.saveOrUpdate(entity);
    }
}
