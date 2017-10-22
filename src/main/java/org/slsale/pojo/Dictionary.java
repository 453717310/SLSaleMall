package org.slsale.pojo;

/**
 * Created by dll on 2017/10/3.
 * 数据字典表
 */
public class Dictionary extends Base {
    private String typeCode;//类型编码
    private String typeName;//类型名称
    private Integer valueId;//类型值ID
    private String valueName;//类型值name

    public Dictionary() {
    }

    public Dictionary(String typeCode, String typeName, Integer valueId, String valueName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
        this.valueId = valueId;
        this.valueName = valueName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "typeCode='" + typeCode + '\'' +
                ", typeName='" + typeName + '\'' +
                ", valueId=" + valueId +
                ", valueName='" + valueName + '\'' +
                '}';
    }
}
