package com.jiyun.asus.day02_fist.utlis;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by asus on 2017/8/16.
 */

public class OkHttpUlist {
    private static OkHttpUlist okHttpUlist;
    private RequestQueue request;
    private OkHttpUlist(Context context){
        request= Volley.newRequestQueue(context);
    }
    public static synchronized OkHttpUlist getOkHttpUlist(Context context){
        if (okHttpUlist==null){
            okHttpUlist=new OkHttpUlist(context);
        }
        return okHttpUlist;
    }

   public void sendGet(String url, Response.Listener<String> listener, Response.ErrorListener errorListener){
       StringRequest stringRequest=new StringRequest(url,listener,errorListener);
       request.add(stringRequest);
   }


}
