package mx.edu.utng.xmlreader.image;

import android.util.Log;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by qas on 15/03/16.
 */
public class Utils {
    public static void copyStream(InputStream is,
                                  OutputStream os){

      final int bufferSize = 1024;
        try{
            byte[] bytes = new byte[bufferSize];
            for (;;){
                int count = is.read(bytes, 0, bufferSize);
                if(count==-1){
                    break;
                }
                os.write(bytes, 0, count);
            }
        }catch (Exception e){
            Log.e("Utils.copyStream", e.toString());
        }
    }
}
