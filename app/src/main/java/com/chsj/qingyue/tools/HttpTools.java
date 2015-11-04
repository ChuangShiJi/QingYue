package com.chsj.qingyue.tools;

import android.os.Build;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * ProjectName : com.chsj.qingyue.tasks
 * Created by : ChSJ.Team
 * Email : zhaoq_hero163.com
 * On 2015/11/3 // 20:23
 */

/**
 * http 请求工具类
 */
public final class HttpTools {

    public static final String USER_AGENT = "QingYue_1.0.0(" + Build.MODEL + "," + Build.VERSION.SDK_INT + ")";

    private HttpTools() {

    }

    private static final int CONNECT_TIMECOUT = 5 * 1000;
    private static final int READ_TIMEOUT = 30 * 1000;

    public static byte[] doGet(String url) {
        byte[] ret = null;
        if (url != null) {
            HttpURLConnection conn = null;
            try {
                URL u = new URL(url);
                conn = (HttpURLConnection) u.openConnection();
//                设置连接的配置
                conn.setReadTimeout(CONNECT_TIMECOUT);
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setRequestMethod("GET");
//                完善Http请求的内容，
//                1.设定通用Http头信息
//                设置Accept头信息，告诉服务器客户端接收什么样的数据
//                conn.setRequestProperty("Accept","application/*,text/*,image/*,*/*");
//                设置接收的内容压缩编码算法
                conn.setRequestProperty("Accept-Encoding", "gzip");
//                设置USER-Agent
                conn.setRequestProperty("User-Agent", USER_AGENT);
                conn.connect();
                int code = conn.getResponseCode();
                if (code == 200) {
                    InputStream fin = null;
                    try {
                        fin = conn.getInputStream();
                        String encoding = conn.getHeaderField("Content-Encoding");
                        if ("gzip".equals(encoding)) {
                            fin = new GZIPInputStream(fin);
                        }

                        ret = StreamUtils.readStream(fin);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        StreamUtils.close(fin);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                StreamUtils.close(conn);
            }
        }
        return ret;
    }

    //  post请求
    public static byte[] doPost(String url, byte[] data) {
        byte[] ret = null;
        if (url != null) {
            HttpURLConnection conn = null;
            try {
                URL u = new URL(url);
                conn =
                        (HttpURLConnection) u.openConnection();
                conn.setReadTimeout(CONNECT_TIMECOUT);
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setRequestMethod("POST");  // GET
                conn.setDoOutput(true);         // GET 无
                conn.setRequestProperty("Accept-Encoding", "gzip");
//                设置USER-Agent
                conn.setRequestProperty("User-Agent", USER_AGENT);
                conn.connect();

                if (data != null) {
                    OutputStream outputStream =
                            conn.getOutputStream();
                    outputStream.write(data);
                }

                int responseCode = conn.getResponseCode();

                if (responseCode == 200) {
                    InputStream fin = null;
                    try {
                        fin = conn.getInputStream();
                        String encoding = conn.getHeaderField("Content-Encoding");
                        if ("gzip".equals(encoding)) {
                            fin = new GZIPInputStream(fin);
                        }

                        ret = StreamUtils.readStream(fin);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        StreamUtils.close(fin);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                StreamUtils.close(conn);
            }
        }
        return ret;
    }

}

