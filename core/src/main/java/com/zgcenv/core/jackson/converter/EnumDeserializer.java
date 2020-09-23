package com.zgcenv.core.jackson.converter;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.io.IOException;

import static com.zgcenv.core.jackson.converter.EnumSerializer.ALL_ENUM_KEY_FIELD;

/**
 * @ClassName EnumDeserializer
 * @Description enum反序列化工具
 * @Author Mr.Jangni
 * @Date 2020-9-10
 * @Version 1.0
 **/
public class EnumDeserializer extends StdDeserializer<Enum<?>> {
    private final static Logger logger = LoggerFactory.getLogger(EnumDeserializer.class);
    public final static EnumDeserializer INSTANCE = new EnumDeserializer();


    public EnumDeserializer() {
        super(Enum.class);
    }

    @Override
    public Enum<?> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        try {
            // 读取
            JsonNode node = jp.getCodec().readTree(jp);
            // 当前字段
            String currentName = jp.currentName();
            // 当前对象
            Object currentValue = jp.getCurrentValue();
            // 在对象中找到改字段
            Class findPropertyType = BeanUtils.findPropertyType(currentName, currentValue.getClass());
            JsonNode code = node.get(ALL_ENUM_KEY_FIELD);
            String val = code != null ? code.asText() : node.asText();
            if (StrUtil.isBlank(val) || StrUtil.NULL.equals(val)) {
                return null;
            }
            return Enum.valueOf(findPropertyType, val);
        } catch (Exception e) {
            logger.warn("解析枚举失败", e);
            return null;
        }
    }


}