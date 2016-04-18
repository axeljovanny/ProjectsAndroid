package mx.edu.utng.gestbaby.pack;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils {
    public static void closeStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
            }
        }
    }

    public static void closeStream(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
            }
        }
    }

    public static long copy(File in, OutputStream out) throws IOException {
        return copy(new FileInputStream(in), out);
    }

    public static long copy(InputStream in, File out) throws IOException {
        return copy(in, new FileOutputStream(out));
    }

    public static long copy(InputStream input, OutputStream output) throws IOException {
        try {
            byte[] buffer = new byte[4096];
            long count = 0;
            while (true) {
                int n = input.read(buffer);
                if (-1 == n) {
                    break;
                }
                output.write(buffer, 0, n);
                count += (long) n;
            }
            output.flush();
            return count;
        } finally {
            closeStream(input);
            closeStream(output);
        }
    }
}
