/*
 * projectName: zoina-search
 * fileName: pageBean.java
 * packageName: com.zoina.search.entity
 * date: 2020-04-17 9:49
 */
package com.zoina.search.entity;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collections;
import java.util.List;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: pageBean
 * @packageName: com.zoina.search.entity
 * @description:
 * @data: 2020-04-17 9:49
 **/
public class PageBean<T> extends Page<T> {

    private List<T> items;
    private long total;
    private long size;
    private long current;
    private String[] ascs;
    private String[] descs;
    private boolean optimizeCountSql;
    private boolean isSearchCount;

    public PageBean() {
        this.items = Collections.emptyList();
        this.total = 0L;
        this.size = 10L;
        this.current = 1L;
        this.optimizeCountSql = true;
        this.isSearchCount = true;
    }

    public PageBean(long current, long size) {
        this(current, size, 0L);
    }

    public PageBean(long current, long size, long total) {
        this(current, size, total, true);
    }

    public PageBean(long current, long size, boolean isSearchCount) {
        this(current, size, 0L, isSearchCount);
    }

    public PageBean(long current, long size, long total, boolean isSearchCount) {
        this.items = Collections.emptyList();
        this.total = 0L;
        this.size = 10L;
        this.current = 1L;
        this.optimizeCountSql = true;
        this.isSearchCount = true;
        if (current > 1L) {
            this.current = current;
        }

        this.size = size;
        this.total = total;
        this.isSearchCount = isSearchCount;
    }

    @Override
    public boolean hasPrevious() {
        return this.current > 1L;
    }

    @Override
    public boolean hasNext() {
        return this.current < this.getPages();
    }

    public List<T> getItems() {
        return this.items;
    }

    /**
     * 设置items值
     * @param items items
     * @return 当前对象
     * @deprecated 已通过 $setRecord(List<T> records)方法给items赋值
     */
    @Deprecated
    public PageBean<T> setItems(List<T> items) {
        this.items = items;
        return this;
    }

    @Override
    public long getTotal() {
        return this.total;
    }

    @Override
    public Page<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public Page<T> setSize(long size) {
        this.size = size;
        return this;
    }

    @Override
    public Page<T> setRecords(List<T> records) {
        this.items = records;
        return super.setRecords(records);
    }

    @Override
    public long getCurrent() {
        return this.current;
    }

    @Override
    public Page<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

    @Override
    public String[] ascs() {
        return this.ascs;
    }

    @Override
    public Page<T> setAscs(List<String> ascs) {
        if (CollectionUtils.isNotEmpty(ascs)) {
            this.ascs = (String[]) ascs.toArray(new String[0]);
        }

        return this;
    }

    @Override
    public Page<T> setAsc(String... ascs) {
        this.ascs = ascs;
        return this;
    }

    @Override
    public String[] descs() {
        return this.descs;
    }

    @Override
    public Page<T> setDescs(List<String> descs) {
        if (CollectionUtils.isNotEmpty(descs)) {
            this.descs = (String[]) descs.toArray(new String[0]);
        }

        return this;
    }

    @Override
    public Page<T> setDesc(String... descs) {
        this.descs = descs;
        return this;
    }

    @Override
    public boolean optimizeCountSql() {
        return this.optimizeCountSql;
    }

    @Override
    public boolean isSearchCount() {
        return this.total < 0L ? false : this.isSearchCount;
    }

    @Override
    public Page<T> setSearchCount(boolean isSearchCount) {
        this.isSearchCount = isSearchCount;
        return this;
    }

    @Override
    public Page<T> setOptimizeCountSql(boolean optimizeCountSql) {
        this.optimizeCountSql = optimizeCountSql;
        return this;
    }
}