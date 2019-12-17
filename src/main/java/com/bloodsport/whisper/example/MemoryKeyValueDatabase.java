package com.bloodsport.whisper.example;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>@ProjectName:     WhisperServer</p>
 * <p>@ClassName:       MemoryKeyValueDatabase</p>
 * <p>@PackageName:     com.bloodsport.whisper.example</p>
 * <b>
 * <p>@Description:     内存中简单时间的key - value数据库</p>
 *
 * 不要使用该类来完成存储公钥, 该类只是为了暂时完成业务而设定的
 *
 * </b>
 * <p>@author:          lumo</p>
 * <p>@date:            2019/12/17</p>
 * <p>@email:           cnrivkaer@outlook.com</p>
 */
@Service
@Primary
public class MemoryKeyValueDatabase implements KeyValueDatabase {

    /**
     * 存放简单数据的仓库
     */
    private static Map<String, String> warehouse = new LinkedHashMap<>();

    private static class MemoryKeyValueDatabaseHolder {
        private static final MemoryKeyValueDatabase INSTANCE = new MemoryKeyValueDatabase();
    }

    public static MemoryKeyValueDatabase getInstance() {
        return MemoryKeyValueDatabaseHolder.INSTANCE;
    }

    @Override
    public void insert(String key, String value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException("don't save key and value to empty item!!!");
        }
        warehouse.put(key, value);
    }

    @Override
    public void update(String key, String value) {
        insert(key, value);
    }

    @Override
    public String find(String key) {
        return warehouse.getOrDefault(key, "");
    }

    @Override
    public void remove(String key) {
        warehouse.remove(key);
    }
}
