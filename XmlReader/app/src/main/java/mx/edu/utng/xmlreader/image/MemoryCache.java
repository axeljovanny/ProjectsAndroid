package mx.edu.utng.xmlreader.image;

import android.graphics.Bitmap;
import android.util.Log;


import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by qas on 15/03/16.
 */
public class MemoryCache {
    private static final  String TAG = "MemoryCache";
    private Map<String, Bitmap> cache =
            Collections.synchronizedMap(
                    new LinkedHashMap<String, Bitmap>(
                            10,1.5f, true));
    private long size=0;
    private long limit=1000000;

    public MemoryCache(){
        setLimit(Runtime.getRuntime().maxMemory()/4);
    }

    public void setLimit(long limit){
        this.limit = limit;
        Log.i(TAG, "La memoria cache usara "+ limit/1024./1024.+"MB");
    }

    public Bitmap get(String id){
        try{
            if(!cache.containsKey(id)){
                return null;
            }
            return cache.get(id);
        }catch (NullPointerException e){
            Log.e(TAG, e.getMessage().toString());
            return  null;
        }
    }

    public long getSizeInBytes(Bitmap bitmap){
        if(bitmap==null){
            return 0;
        }
        return bitmap.getRowBytes()*bitmap.getHeight();
    }

    public void checkSize(){
        Log.i(TAG, "Cache size="+size+" length="+cache.size());
        if(size>limit){
            Iterator<Map.Entry<String, Bitmap>> iterator=
                    cache.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, Bitmap> entry = iterator.next();
                size-=getSizeInBytes(entry.getValue());
                iterator.remove();
                if(size<=limit){
                    break;
                }
                Log.i(TAG, "Cache limpiada. Tamaño nuevo"+cache.size());
            }
        }
    }

    public void put(String id, Bitmap bitmap){
        try{
            if(cache.containsKey(id)){
                size-=getSizeInBytes(cache.get(id));
            }
            cache.put(id, bitmap);
            size+=getSizeInBytes(bitmap);
            checkSize();
        }catch (Throwable th){
            Log.e(TAG, th.getMessage().toString());
        }
    }

    public void clear(){
        try {
            cache.clear();
            size = 0;
        }catch (NullPointerException e){
            Log.e(TAG, e.getMessage().toString());
        }
    }
}
