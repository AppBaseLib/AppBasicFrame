package com.abt.app.viewmodel;

import com.abt.app.app.MainConstant;
import com.abt.app.data.bean.SimpleNewsBean;
import com.abt.app.model.INewsModel;
import com.abt.app.model.NewsModelImpl;
import com.abt.app.ui.adapter.NewsAdapter;
import com.abt.app.ui.main.IMainView;
import com.abt.basic.arch.mvvm.view.load.BaseLoadListener;

import java.util.List;

/**
 * @描述： @NewsVM
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class NewsVM implements BaseLoadListener<SimpleNewsBean> {
    private static final String TAG = "NewsVM";
    private INewsModel mNewsModel;
    private IMainView mNewsView;
    private NewsAdapter mAdapter;
    private int currPage = 1; //当前页数
    private int loadType; //加载数据的类型

    public NewsVM(IMainView mNewsView, NewsAdapter mAdapter) {
        this.mNewsView = mNewsView;
        this.mAdapter = mAdapter;
        mNewsModel = new NewsModelImpl();
        getNewsData();
    }

    /**
     * 第一次获取新闻数据
     */
    private void getNewsData() {
        loadType = MainConstant.LoadData.FIRST_LOAD;
        mNewsModel.loadNewsData(currPage, this);
    }

    /**
     * 获取下拉刷新的数据
     */
    public void loadRefreshData() {
        loadType = MainConstant.LoadData.REFRESH;
        currPage = 1;
        mNewsModel.loadNewsData(currPage, this);
    }

    /**
     * 获取上拉加载更多的数据
     */
    public void loadMoreData() {
        loadType = MainConstant.LoadData.LOAD_MORE;
        currPage++;
        mNewsModel.loadNewsData(currPage, this);
    }

    @Override
    public void loadSuccess(List<SimpleNewsBean> list) {
        if (currPage > 1) {
            //上拉加载的数据
            mAdapter.loadMoreData(list);
        } else {
            //第一次加载或者下拉刷新的数据
            mAdapter.refreshData(list);
        }
    }

    @Override
    public void loadFailure(String message) {
        // 加载失败后的提示
        if (currPage > 1) {
            //加载失败需要回到加载之前的页数
            currPage--;
        }
        mNewsView.loadFailure(message);
    }

    @Override
    public void loadStart() {
        mNewsView.loadStart(loadType);
    }

    @Override
    public void loadComplete() {
        mNewsView.loadComplete();
    }
}

