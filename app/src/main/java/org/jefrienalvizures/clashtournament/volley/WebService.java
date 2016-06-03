package org.jefrienalvizures.clashtournament.volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**amili
 * Created by Jefrien Alvizures on 26/05/2016.
 */
public class WebService {
    public static String server = "http://clashtournament.site88.net";
    // Rutas para Usuario
    public static String autenticar = server + "/login.php";
    public static String registrar = server + "/registro.php";
    public static String addClanUsuario = server + "/addClanUsuario.php";
    // Rutas para Clan
    public static String addClan = server + "/regClan.php";
    public static String getClanById = server + "/clanById.php";

    private static WebService mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mContext;

    private WebService(Context context){
        mContext = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(20);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static synchronized WebService getInstance(Context context){
        if(mInstance == null){
            mInstance = new WebService(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader(){
        return mImageLoader;
    }
}
