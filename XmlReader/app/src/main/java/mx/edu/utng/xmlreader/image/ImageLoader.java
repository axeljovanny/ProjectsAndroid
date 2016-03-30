package mx.edu.utng.xmlreader.image;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import mx.edu.utng.xmlreader.R;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by qas on 15/03/16.
 */
public class ImageLoader {
    MemoryCache memoryCache = new MemoryCache();
    FileCache fileCache;
    private Map<ImageView, String> imageViews =
            Collections.synchronizedMap(
              new WeakHashMap<ImageView, String>());
    ExecutorService executorService;
    final int stub_id = R.mipmap.ic_launcher;


    public ImageLoader(Context context){
        fileCache = new FileCache(context);
        executorService = Executors.newFixedThreadPool(5);

    }

    private class PhotoToLoad{
        private String url;
        private ImageView imageView;

        public PhotoToLoad(String url, ImageView imageView){
            this.url = url;
            this.imageView = imageView;
        }
    }

    class PhotoLoader implements Runnable{
        PhotoToLoad photoToLoad;
        PhotoLoader(PhotoToLoad photoToLoad){
            this.photoToLoad = photoToLoad;
        }

        @Override
        public void run() {
            try {
                if(imageViewReused(photoToLoad)){
                    return;
                }
                //Complementar
                Bitmap bitmap = getBitmap(photoToLoad.url);
                memoryCache.put(photoToLoad.url, bitmap);
                if (imageViewReused(photoToLoad)){
                    return;
                }
                BitmapDisplayer bitmapDisplayer
                        = new BitmapDisplayer(
                        bitmap, photoToLoad);
                Activity activity = (Activity)
                        photoToLoad.imageView.getContext();
                activity.runOnUiThread(bitmapDisplayer);
            }catch (Throwable th){
                Log.e("PhotoLoader", th.toString());
            }
        }
  }

    boolean imageViewReused(PhotoToLoad photoToLoad){
        String tag = imageViews.get(photoToLoad.imageView);
        if(tag==null|| !tag.equals(photoToLoad.url)){
            return true;
        }
        return false;
    }

    class BitmapDisplayer implements Runnable{
        Bitmap bitmap;
        PhotoToLoad photoToLoad;
        public BitmapDisplayer(Bitmap bitmap,
                               PhotoToLoad photoToLoad){
          this.bitmap = bitmap;
            this.photoToLoad = photoToLoad;
        }

        @Override
        public void run() {
            if(imageViewReused(photoToLoad)){
                return;
            }
            if(bitmap!=null){
                photoToLoad.imageView.setImageBitmap(bitmap);
            }else{
                photoToLoad.imageView.setImageResource(stub_id);
            }
        }
    }

    public void clearCache(){
        memoryCache.clear();
        fileCache.clear();
    }

   private void queuePhoto(String url, ImageView imageView){
        PhotoToLoad photoToLoad = new PhotoToLoad(url, imageView);
       executorService.submit(new PhotoLoader(photoToLoad));
    }

    public void displayImage(String url, ImageView imageView){
        imageViews.put(imageView, url);
        Bitmap bitmap = memoryCache.get(url);
        if(bitmap!=null){
           imageView.setImageBitmap(bitmap);
        }else{
            queuePhoto(url, imageView);
            imageView.setImageResource(stub_id);
        }
    }

    private Bitmap getBitmap(String url){
        File file = fileCache.getFile(url);

        //Cargando del cache SD
        Bitmap bitmap = decodeFile(file);

        if(bitmap!=null){
            return bitmap;
        }

        //Cargamos de la Web
        try {
            Bitmap bm = null;
            URL imageUrl = new URL(url);
            HttpURLConnection connection =
                    (HttpURLConnection) imageUrl.openConnection();
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);
            connection.setInstanceFollowRedirects(true);
            InputStream inputStream = connection.getInputStream();
            OutputStream outputStream = new FileOutputStream(file);
            Utils.copyStream(inputStream, outputStream);
            outputStream.close();
            bitmap = decodeFile(file);

            return  bitmap;
        }catch (Throwable e){
            Log.e("ImageLoader.getBitmap", e.toString());
            if(e instanceof OutOfMemoryError){
                memoryCache.clear();
            }
            return null;
        }
    }

    //Decodificar la imagen y escalarla para reducir tamaño en memoria
    private Bitmap decodeFile(File file){
        //Decodificamos imagen
        try{
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            FileInputStream stream = new FileInputStream(file);
            BitmapFactory.decodeStream(stream, null, options);
            stream.close();

            final int  REQUIRED_SIZE = 70;
            int width_tmp = options.outWidth;
            int height_tmp = options.outHeight;
            int scale = 1;

            while (true){
                if(width_tmp/2 < REQUIRED_SIZE ||
                        height_tmp/2 < REQUIRED_SIZE){
                    break;
               }
                width_tmp /=2;
                height_tmp/=2;
                scale*=2;
            }

            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = scale;
            FileInputStream stream2 = new FileInputStream(file);
            Bitmap bitmap = BitmapFactory.decodeStream(
                    stream2, null, options2);
            stream2.close();
            return bitmap;
        }catch (FileNotFoundException e){
            Log.e("ImageLoader.decodeFile", e.toString());
        }catch (IOException e){
            Log.e("ImageLoader.decodeFile", e.toString());
        }
        return null;
    }

}
