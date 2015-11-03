package com.chsj.qingyue.tools;
/**
 * Author:whl
 * Email:294084532@qq.com
 * 2015/10/12
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;

/**
 * IO流的工具类
 */
public final class StreamUtils {
    private StreamUtils() {
    }

    public static byte[] readStream(InputStream in) throws IOException {
        byte[] ret = null;
        if (in != null) {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();

            byte buf[] = new byte[128];
            int len;
            while ((len = in.read(buf)) != -1) {
                bout.write(buf, 0, len);
            }
//                注意buf必须进行等于空的操作,减少内存溢出的可能性
            buf = null;
            ret = bout.toByteArray();
            bout.close();
        }
        return ret;
    }

    public static void close(Object stream) {
        if (stream != null) {
            try {
                if (stream instanceof InputStream) {
                    ((InputStream) stream).close();
                } else if (stream instanceof OutputStream) {
                    ((OutputStream) stream).close();
                } else if (stream instanceof Reader) {
                    ((Reader) stream).close();
                } else if (stream instanceof Writer) {
                    ((Writer) stream).close();
                } else if (stream instanceof HttpURLConnection) {
                    ((HttpURLConnection) stream).disconnect();
                }
            } catch (Exception e) {
            }
        }
    }
}
