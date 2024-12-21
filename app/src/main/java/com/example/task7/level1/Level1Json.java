package com.example.task7.level1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Level1Json {

    public int errorCode;
    public String errorMsg;
    public List<Level1JsonDataBean> data;


    public static class Level1JsonDataBean {
        public String author;
        public int courseId;
        public String cover;
        public String desc;
        public int id;
        public String lisense;
        public String lisenseLink;
        public String name;
        public int order;
        public int parentChapterId;
        public int type;
        public boolean userControlSetTop;
        public int visible;

    }

    public static Level1Json decodeJson(String json) {
        Level1Json result = new Level1Json();
        result.data = new ArrayList<>();
        Level1JsonDataBean dataBean;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                dataBean = new Level1JsonDataBean();
                JSONObject dataObj = jsonArray.getJSONObject(i);
                dataBean.cover = dataObj.getString("cover");
                dataBean.author = dataObj.getString("author");
                dataBean.desc = dataObj.getString("desc");
                dataBean.name = dataObj.getString("name");
                result.data.add(dataBean);
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        return result;
    }
}
