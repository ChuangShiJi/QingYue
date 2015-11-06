package com.chsj.qingyue.tools;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtils {

    public static final String CACHEDIR = Environment.getExternalStorageDirectory() + "/qingyue/images";


    public static boolean isMounted() {
        return Environment.MEDIA_MOUNTED.equals(
                Environment.getExternalStorageState());
    }

    public static void saveImg(String url, byte[] bytes) throws IOException {
        if (!isMounted()) return;

        File dir = new File(CACHEDIR);
        if (!dir.exists()) dir.mkdirs();

        FileOutputStream fos = new FileOutputStream(new File(dir, getName(url)));
        fos.write(bytes);
        fos.close();

    }

    public static void saveImg(String url, Bitmap bitmap) throws IOException {
        if (!isMounted()) return;

        File dir = new File(CACHEDIR);
        if (!dir.exists()) dir.mkdirs();

        //将图片对象写入到指定输出流中
        bitmap.compress(getFormat(url), 100,
                new FileOutputStream(new File(dir, getName(url))));

    }

    private static CompressFormat getFormat(String url) {
        // TODO 获取图片的格式
        String fileName = getName(url);
        if (fileName.endsWith("png")) {
            return CompressFormat.PNG;
        }

        return CompressFormat.JPEG;
    }

    public static Bitmap getImg(String url) {
        if (!isMounted()) return null;


        File imgFile = new File(CACHEDIR, getName(url));
        if (imgFile.exists()) {
            return BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }

        return null;
    }

    public static String getName(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    public String getName(String url, int end) {
        return url.substring(url.lastIndexOf("/") + 1, end);
    }


    /**
     * 保存图片到系统图库
     *
     * @param context
     * @param bmp
     */
    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "QingYue");

        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsolutePath())));
    }


}
