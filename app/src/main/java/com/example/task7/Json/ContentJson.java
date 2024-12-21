package com.example.task7.Json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ContentJson {
    public int curPage;
    public int errorCode;
    public String errorMsg;
    public List<ContentDataBean> datas;


    public static class ContentDataBean {


        public String author;
        public String link;
        public String niceDate;
        public String superChapterName;
        public String title;
    }

    public static ContentJson decodeJson(String json) {
        ContentJson result = new ContentJson();
        try {
            JSONObject jsonObject = new JSONObject(json);
            result.errorCode = jsonObject.getInt("errorCode");
            result.errorMsg = jsonObject.getString("errorMsg");
            if (result.errorCode == -1) {

            } else {
                result.datas = new ArrayList<>();
                JSONObject data = jsonObject.getJSONObject("data");
                result.curPage = data.getInt("curPage");
                JSONArray datas = data.getJSONArray("datas");
                ContentDataBean contentDataBean;


                for (int i = 0; i < datas.length(); i++) {
                    contentDataBean = new ContentDataBean();
                    JSONObject datasJSONObject = datas.getJSONObject(i);
                    contentDataBean.author = datasJSONObject.getString("author");
                    contentDataBean.link = datasJSONObject.getString("link");
                    contentDataBean.niceDate = datasJSONObject.getString("niceDate");
                    contentDataBean.title = datasJSONObject.getString("title");
                    contentDataBean.superChapterName = datasJSONObject.getString("superChapterName");
                    result.datas.add(contentDataBean);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
