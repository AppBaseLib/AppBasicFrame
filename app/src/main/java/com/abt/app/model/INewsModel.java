package com.abt.app.model;

import com.abt.app.data.bean.SimpleNewsBean;
import com.abt.basic.arch.mvvm.view.load.BaseLoadListener;

/**
 * @描述： @INewsModel
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public interface INewsModel {
    /**
     * 获取新闻数据
     * @param page 页数
     * @param loadListener
     */
    void loadNewsData(int page, BaseLoadListener<SimpleNewsBean> loadListener);

    void loadNewsData(int page, BaseLoadListener<SimpleNewsBean> loadListener);

}
