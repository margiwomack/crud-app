package com.box.company.dto;
import com.box.company.entity.Attribute;

/**
 *This class is the JSON model representation of an Attribute Object.
 */
public class AttributeModel {

    private Long id;
    private String name;
   // private List<BoxModel> boxes;

    public AttributeModel(){}

    public AttributeModel(String attributeName){
        name = attributeName;
    }

    public AttributeModel(Attribute attributeEntity){
        id = attributeEntity.getId();
        name = attributeEntity.getName();

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
