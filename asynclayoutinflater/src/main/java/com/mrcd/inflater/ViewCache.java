package com.mrcd.inflater;

import android.util.SparseArray;
import android.view.View;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 缓存 {@link AsyncLayoutInflater} 创建的View, key 为 RecyclerView的 viewType 或者 activity 的 layout id.
 */
public class ViewCache {

    private final SparseArray<Queue<View>> mViewCaches = new SparseArray<>() ;
    private OnViewCacheEmptyListener mViewCacheEmptyListener ;

    public synchronized void put(int type, View view) {
        if ( view == null ) {
            return;
        }
        Queue<View> viewQueue = mViewCaches.get(type) ;
        if ( viewQueue == null ) {
            viewQueue = new ArrayDeque<>() ;
            mViewCaches.put(type, viewQueue);
        }
        viewQueue.add(view) ;
    }


    /**
     * take view from view queue
     * @param type
     * @return
     */
    public synchronized View take(int type) {
        final Queue<View> viewQueue = mViewCaches.get(type) ;
        View cacheView = null ;
        if (viewQueue != null && viewQueue.size() > 0) {
            cacheView = viewQueue.remove() ;
            // callback
            if ( viewQueue.size() == 0 && mViewCacheEmptyListener != null ) {
                mViewCacheEmptyListener.onViewCacheEmpty(type);
            }
        }
        return cacheView ;
    }

    public synchronized boolean hasCache(int type) {
        final Queue<View> viewQueue = mViewCaches.get(type) ;
        return viewQueue != null ? viewQueue.size() > 0 : false;
    }


    public void setViewCacheEmptyListener(OnViewCacheEmptyListener viewCacheEmptyListener) {
        this.mViewCacheEmptyListener = viewCacheEmptyListener;
    }

    public synchronized void clear() {
        mViewCaches.clear();
    }

    /**
     * view cache empty for a specific type
     */
    public static interface OnViewCacheEmptyListener {
        void onViewCacheEmpty(int viewType) ;
    }
}
