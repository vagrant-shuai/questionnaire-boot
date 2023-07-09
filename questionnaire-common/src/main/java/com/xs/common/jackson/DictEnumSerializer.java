package com.xs.common.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.xs.common.entity.IDictEnum;

import java.io.IOException;


/**
 * 字典枚举序列化定义
 * @author xs
 */
public class DictEnumSerializer extends JsonSerializer<IDictEnum> {

    @Override
    public void serialize(IDictEnum dictEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(dictEnum.getValue());
        jsonGenerator.writeFieldName(jsonGenerator.getOutputContext().getCurrentName() + "Desc");
        jsonGenerator.writeString(dictEnum.getDesc());
    }


    @Override
    public Class handledType() {
        return IDictEnum.class;
    }

}