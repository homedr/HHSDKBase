package com.hhmedic.android.sdk.base.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by iOS on 2016/11/25.
 *
 *
 */

public class HHImageUtils
{
    public static Bitmap getCacheBitmapFromView(View view)
    {
        final boolean drawingCacheEnabled = true;

        view.setDrawingCacheEnabled(drawingCacheEnabled);

        view.buildDrawingCache(drawingCacheEnabled);

        final Bitmap drawingCache = view.getDrawingCache();

        Bitmap bitmap;

        if (drawingCache != null)
        {
            bitmap = Bitmap.createBitmap(drawingCache);

            view.setDrawingCacheEnabled(false);
        }

        else
        {
            bitmap = null;
        }


        return bitmap;
    }


    /**
     * uri转换
     *
     * @param contentURI path
     * @return path
     */
    public static String convertUri(Uri contentURI, Context context)
    {
        try
        {

            Logger.e(contentURI.toString());

            final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

            // DocumentProvider
            if (isKitKat && DocumentsContract.isDocumentUri(context, contentURI)) {
                // ExternalStorageProvider
                if (isExternalStorageDocument(contentURI)) {
                    final String docId = DocumentsContract.getDocumentId(contentURI);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    if ("primary".equalsIgnoreCase(type)) {
                        return Environment.getExternalStorageDirectory() + "/" + split[1];
                    }


                }
                // DownloadsProvider
                else if (isDownloadsDocument(contentURI)) {

                    final String id = DocumentsContract.getDocumentId(contentURI);
                    final Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                    return getDataColumn(context, contentUri, null, null);
                }
                // MediaProvider
                else if (isMediaDocument(contentURI)) {
                    final String docId = DocumentsContract.getDocumentId(contentURI);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    Uri contentUri = null;
                    if ("image".equals(type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }

                    final String selection = "_id=?";
                    final String[] selectionArgs = new String[] {
                            split[1]
                    };

                    return getDataColumn(context, contentUri, selection, selectionArgs);
                }
            }


            String result;
            Cursor cursor = context.getContentResolver().query(contentURI, null, null, null, null);
            if (cursor == null)
            {
                // Source is Dropbox or other similar local file path
                result = contentURI.getPath();
            }
            else
            {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                result = cursor.getString(idx);
                cursor.close();
            }

            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;

    }


    private static String getDataColumn(Context context, Uri uri, String selection,
                                        String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }



    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }




    public static boolean bitmaoSaveFile(Bitmap image,String filePath)
    {
        File file = new File(filePath);

        if (file.exists())
        {
            file.delete();
        }

        FileOutputStream out = null;

        try
        {
            out = new FileOutputStream(file);

            image.compress(Bitmap.CompressFormat.PNG, 90, out);

        }
        catch (Exception e)
        {
            e.printStackTrace();

            return false;
        }
        finally {

            if (out!=null)
            {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }


        return true;

    }





    public static byte[] bitmap2byte(Bitmap bitmap)
    {
        if (bitmap == null)
        {
            return null;
        }

        ByteArrayOutputStream baos = null;

        try
        {
            baos=new ByteArrayOutputStream();

            bitmap.compress(Bitmap.CompressFormat.PNG, 50, baos);

            byte [] bitmapByte =baos.toByteArray();

            return bitmapByte;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {

            bitmap.recycle();

            if (baos!=null)
            {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;

    }



    public static String  createImagePath(Context context)
    {
        return HHDirUtils.getTempPath(context,String.valueOf(System.currentTimeMillis())+".jpg");
    }


}
