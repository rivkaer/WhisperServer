package com.bloodsport.whisper.example;

/**
 * <p>@ProjectName:     WhisperServer</p>
 * <p>@ClassName:       KeyValueDatabase</p>
 * <p>@PackageName:     com.bloodsport.whisper.example</p>
 * <b>
 * <p>@Description:     KeyValue数据库接口</p>
 * </b>
 * <p>@author:          lumo</p>
 * <p>@date:            2019/12/17</p>
 * <p>@email:           cnrivkaer@outlook.com</p>
 */
public interface KeyValueDatabase {

    void insert(String key, String value);

    void update(String key, String value);

    String find(String key);

    void remove(String key);

}
