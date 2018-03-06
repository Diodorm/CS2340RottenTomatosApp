package com.example.alexa.finalproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Singleton for volley
 */
public final class VolleySingleton {
    private RequestQueue queue;
    private ImageLoader imageLoader;
    private static VolleySingleton instance;
    private static Context context;

    /**
     * private constructor to create the single instance
     * @param context the context
     */
    private VolleySingleton(Context context) {
        VolleySingleton.context = context;
        queue = getRequestQueue();
        imageLoader = new ImageLoader(this.queue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<>(10);
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }
        });
    }

    /**
     * @return the request queue
     */
    public RequestQueue getRequestQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(context.getApplicationContext());
        }

        return queue;
    }

    public ImageLoader getImageLoader(){
        return this.imageLoader;
    }

    /**
     * @param context the context
     * @return the single instance of the class
     */
    public static synchronized VolleySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new VolleySingleton(context);
        }
        return instance;
    }

    /**
     * @param req the request to add
     * @param <T> type of the request (currently only using json so maybe remove this)
     */
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
