package com.example.task7.level1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.task7.R;

public class Level1Activity extends AppCompatActivity {
    private ViewPager2 mVp2;
    private Level1Json mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);
        initView();
        Level1Json level1Json = Level1Json.decodeJson("{\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"articleList\": [],\n" +
                "            \"author\": \"阮一峰\",\n" +
                "            \"children\": [],\n" +
                "            \"courseId\": 13,\n" +
                "            \"cover\": \"https://www.wanandroid.com/blogimgs/f1cb8d34-82c1-46f7-80fe-b899f56b69c1.png\",\n" +
                "            \"desc\": \"C 语言入门教程。\",\n" +
                "            \"id\": 548,\n" +
                "            \"lisense\": \"知识共享 署名-相同方式共享 3.0协议\",\n" +
                "            \"lisenseLink\": \"https://creativecommons.org/licenses/by-sa/3.0/deed.zh\",\n" +
                "            \"name\": \"C 语言入门教程_阮一峰\",\n" +
                "            \"order\": 270000,\n" +
                "            \"parentChapterId\": 547,\n" +
                "            \"type\": 0,\n" +
                "            \"userControlSetTop\": false,\n" +
                "            \"visible\": 1\n" +
                "        },\n" +
                "        {\n" +
                "            \"articleList\": [],\n" +
                "            \"author\": \"阮一峰\",\n" +
                "            \"children\": [],\n" +
                "            \"courseId\": 13,\n" +
                "            \"cover\": \"https://wanandroid.com/blogimgs/396ea6a1-e654-4f08-80ce-c99b8b90bc29.png\",\n" +
                "            \"desc\": \"本教程完整介绍 HTML 语言的所有内容，既可以当作初学者的入门教程，也可以用作参考手册查阅语法。\\n\\n\",\n" +
                "            \"id\": 549,\n" +
                "            \"lisense\": \"知识共享 署名-相同方式共享 3.0协议\",\n" +
                "            \"lisenseLink\": \"https://creativecommons.org/licenses/by-sa/3.0/deed.zh\",\n" +
                "            \"name\": \"HTML 教程_阮一峰\",\n" +
                "            \"order\": 270001,\n" +
                "            \"parentChapterId\": 547,\n" +
                "            \"type\": 0,\n" +
                "            \"userControlSetTop\": false,\n" +
                "            \"visible\": 1\n" +
                "        },\n" +
                "        {\n" +
                "            \"articleList\": [],\n" +
                "            \"author\": \"阮一峰\",\n" +
                "            \"children\": [],\n" +
                "            \"courseId\": 13,\n" +
                "            \"cover\": \"https://www.wanandroid.com/blogimgs/2337b2bf-e55b-4529-8549-566b7c922693.png\",\n" +
                "            \"desc\": \"本教程介绍 SSH（主要是它的实现 OpenSSH）的概念和基本用法，也可以当作手册查询。\\n\\n\",\n" +
                "            \"id\": 550,\n" +
                "            \"lisense\": \"知识共享 署名-相同方式共享 3.0协议\",\n" +
                "            \"lisenseLink\": \"https://creativecommons.org/licenses/by-sa/3.0/deed.zh\",\n" +
                "            \"name\": \"SSH 教程_阮一峰\",\n" +
                "            \"order\": 270002,\n" +
                "            \"parentChapterId\": 547,\n" +
                "            \"type\": 0,\n" +
                "            \"userControlSetTop\": false,\n" +
                "            \"visible\": 1\n" +
                "        },\n" +
                "        {\n" +
                "            \"articleList\": [],\n" +
                "            \"author\": \"阮一峰\",\n" +
                "            \"children\": [],\n" +
                "            \"courseId\": 13,\n" +
                "            \"cover\": \"https://www.wanandroid.com/blogimgs/d0b37e44-4907-4cbc-8f67-321e64b6c2d3.png\",\n" +
                "            \"desc\": \"本教程介绍 Linux 命令行 Bash 的基本用法和脚本编程。\\n\\n\",\n" +
                "            \"id\": 551,\n" +
                "            \"lisense\": \"知识共享 署名-相同方式共享 3.0协议\",\n" +
                "            \"lisenseLink\": \"https://creativecommons.org/licenses/by-sa/3.0/deed.zh\",\n" +
                "            \"name\": \"Bash 脚本教程_阮一峰\",\n" +
                "            \"order\": 270003,\n" +
                "            \"parentChapterId\": 547,\n" +
                "            \"type\": 0,\n" +
                "            \"userControlSetTop\": false,\n" +
                "            \"visible\": 1\n" +
                "        },\n" +
                "        {\n" +
                "            \"articleList\": [],\n" +
                "            \"author\": \"阮一峰\",\n" +
                "            \"children\": [],\n" +
                "            \"courseId\": 13,\n" +
                "            \"cover\": \"https://www.wanandroid.com/blogimgs/3cb4b574-afff-4578-950b-8579e6b44c10.png\",\n" +
                "            \"desc\": \"Web API 教程，提供各种浏览器 API 文档，正在建设中。\",\n" +
                "            \"id\": 552,\n" +
                "            \"lisense\": \"知识共享 署名-相同方式共享 3.0协议\",\n" +
                "            \"lisenseLink\": \"https://creativecommons.org/licenses/by-sa/3.0/deed.zh\",\n" +
                "            \"name\": \"WebAPI教程_阮一峰\",\n" +
                "            \"order\": 270004,\n" +
                "            \"parentChapterId\": 547,\n" +
                "            \"type\": 0,\n" +
                "            \"userControlSetTop\": false,\n" +
                "            \"visible\": 1\n" +
                "        },\n" +
                "        {\n" +
                "            \"articleList\": [],\n" +
                "            \"author\": \"阮一峰\",\n" +
                "            \"children\": [],\n" +
                "            \"courseId\": 13,\n" +
                "            \"cover\": \"https://www.wanandroid.com/blogimgs/127411e8-a6aa-4bfc-ae5c-576a0ba3f939.png\",\n" +
                "            \"desc\": \"本教程全面介绍 JavaScript 核心语法，覆盖了 ES5 和 DOM 规范的所有内容。\\n\\n本教程适合初学者当作 JavaScript 语言入门教程，学完后就可以承担实际的网页开发工作，也适合当作日常使用的参考手册。\\n\",\n" +
                "            \"id\": 553,\n" +
                "            \"lisense\": \"知识共享 署名-相同方式共享 3.0协议\",\n" +
                "            \"lisenseLink\": \"https://creativecommons.org/licenses/by-sa/3.0/deed.zh\",\n" +
                "            \"name\": \"JavaScript 教程\",\n" +
                "            \"order\": 270005,\n" +
                "            \"parentChapterId\": 547,\n" +
                "            \"type\": 0,\n" +
                "            \"userControlSetTop\": false,\n" +
                "            \"visible\": 1\n" +
                "        }\n" +
                "    ],\n" +
                "    \"errorCode\": 0,\n" +
                "    \"errorMsg\": \"\"\n" +
                "}");

        mVp2.setAdapter(new Vp2Adapter(level1Json));


    }

    private void initView() {
        mVp2=findViewById(R.id.vp2_level1);
    }
}