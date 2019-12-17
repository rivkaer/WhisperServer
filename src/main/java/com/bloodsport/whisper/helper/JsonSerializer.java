package com.bloodsport.whisper.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * <p>@ProjectName:     WhisperServer</p>
 * <p>@ClassName:       JsonSerializer</p>
 * <p>@PackageName:     com.bloodsport.whisper.helper</p>
 * <b>
 * <p>@Description:     Json序列化</p>
 * </b>
 * <p>@author:          lumo</p>
 * <p>@date:            2019/12/17</p>
 * <p>@email:           cnrivkaer@outlook.com</p>
 */
public class JsonSerializer {

    private static Gson mGson;

    {
        mGson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    private static class JsonSerializerHolder{
        private static final JsonSerializer INSTANCE = new JsonSerializer();
    }

    public static JsonSerializer getInstance() {
        return JsonSerializerHolder.INSTANCE;
    }

    /**
     * 获取当前json序列化器
     * @return json序列化器（Gson）
     */
    public static Gson getSerializer() {
        return getInstance().mGson;
    }
}
