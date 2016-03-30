package mx.edu.utng.xmlreader.image;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.net.URLEncoder;

/**
 * Created by qas on 15/03/16.
 */
public class FileCache {
    private File cacheDir;

    public FileCache(Context context){

     //Buscar el directorio para guardar las imagenes
        if(android.os.Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED)){
            cacheDir = new File(
                android.os.Environment.getExternalStorageDirectory(),
                    "ImageFeed");
        }else{
            cacheDir = context.getCacheDir();
        }
        if(!cacheDir.exists()){
            cacheDir.mkdir();
        }
    }

    public File getFile(String url){
        String fileName = URLEncoder.encode(url);
        File file = new File(cacheDir, fileName);
        return file;
    }

    public void clear(){
        File[] files = cacheDir.listFiles();
        if(files==null){
            return;
        }
        for (File file: files){
            file.delete();
        }
    }
}
