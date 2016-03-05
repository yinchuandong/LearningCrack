package com.example.learningcrack;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    static String TAG = "MainActivity_1";

    ContentResolver resolver;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resolver = getContentResolver();
        uri = Uri.parse("content://com.mb.wx.provider.NotePad/notes");
        testDelete();
        testWrite();
        testQuery();

//        Log.d(TAG, "onCreate :" + getMyUUID());
//        Log.d(TAG, "onCreate :" + e.b("mb", "ECA8C719FBCD112C5763DBC32DBE8E3B49603BD7955A4B90777CB77CB3FF2C33A87823802A0832C77A4851785692F72E"));
//        Log.d(TAG, "onCreate :" + e.b("mb", "05A826EB31320D2FD28359B62B56B7B0"));
    }

    private void testQuery() {
        String[] columns = {"title", "note", "created", "modified"};
//        Cursor cursor = resolver.query(uri, columns, "title=?", new String[]{"权限"}, null);
        Cursor cursor = resolver.query(uri, columns, null, null, null);
        if (cursor == null){
            Log.d(TAG, "testQuery null");
            return;
        }
        while (cursor.moveToNext()) {
            int num = cursor.getColumnCount();
            Log.d(TAG, "num:" + num);
            for (int i = 0; i < num; i++){
                Log.d(TAG, cursor.getString(i));
            }
        }
        cursor.close();
    }

    private void testWrite(){
//        String key = e.a("mb", "ffffffff-90e3-c9fb-9c1c-963000000000");
        String key = e.a("mb", getMyUUID());
        Log.d(TAG, "testWrite :" + key);
        ContentValues values = new ContentValues();
        values.put("title", key);
        values.put("note", "mb");
        values.put("created", System.currentTimeMillis());
        values.put("modified", System.currentTimeMillis());
        resolver.insert(uri, values);

        ContentValues values2 = new ContentValues();
        values2.put("title", "权限");
        values2.put("note", e.a("mb", "null"));
        values2.put("created", System.currentTimeMillis());
        values2.put("modified", System.currentTimeMillis());
        resolver.insert(uri, values2);
    }

    private void testDelete(){
        resolver.delete(uri, null, null);
    }

    private String getMyUUID(){
        final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String uniqueId = deviceUuid.toString();
        Log.d(TAG,"uuid="+uniqueId);
        return uniqueId;
    }

}
