package com.example.task7.Json;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TutorialJson {
    public int errorCode;
    public String errorMsg;
    public List<TutorialJson.TutorialJsonDataBean> data;


    public static class TutorialJsonDataBean {
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

    public static TutorialJson decodeJson(String json) {
        TutorialJson result = new TutorialJson();
        result.data = new ArrayList<>();
        TutorialJson.TutorialJsonDataBean dataBean;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                dataBean = new TutorialJson.TutorialJsonDataBean();
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
