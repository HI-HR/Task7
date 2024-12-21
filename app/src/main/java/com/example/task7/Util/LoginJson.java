package com.example.task7.Util;


import android.util.Log;

import org.json.JSONObject;


public class LoginJson {


    public LoginDataBean data;

    public int errorCode;//0成功,-1失败

    public String errorMsg;


    public static class LoginDataBean {
        public android.util.Log Log;
        /**
         * admin : false
         * chapterTops : []
         * coinCount : 10
         * collectIds : []
         * email :
         * icon :
         * id : 165513
         * nickname : Linfunny
         * password :
         * publicName : Linfunny
         * token :
         * type : 0
         * username : Linfunny
         */

        public boolean admin;
        public int coinCount;
        public String email;
        public String icon;
        public int id;
        public String nickname;
        public String password;
        public String publicName;
        public String token;
        public int type;
        public String username;

    }

    public static LoginJson decodeJson(String json) {
        LoginJson result = new LoginJson();
        try {
            JSONObject jsonObject = new JSONObject(json);
            result.errorCode = jsonObject.getInt("errorCode");
            result.errorMsg = jsonObject.getString("errorMsg");
            if (result.errorCode == -1) {

            } else {
                result.data = new LoginDataBean();
                JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                result.data.publicName = jsonObjectData.getString("publicName");
                Log.d("ld", result.data.publicName);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
