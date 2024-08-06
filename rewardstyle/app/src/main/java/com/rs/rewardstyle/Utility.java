package com.rs.rewardstyle;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rs.rewardstyle.model.Ltk;
import com.rs.rewardstyle.model.Root;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class Utility {

    public static String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            Log.e("GetJsonFromAssets", e.getMessage());
            return null;
        }

        return jsonString;
    }

    public static List<Ltk> getArray(String str) {
        Gson gson = new Gson();
        Type root = new TypeToken<Root>() {
        }.getType();

        Root rootModel= gson.fromJson(str, root);
        List<Ltk> list = rootModel.getLtks();
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Log.i("data", "> Item " + i + "\n" + list.get(i).getId());
            }
        }
        return list;
    }


}
