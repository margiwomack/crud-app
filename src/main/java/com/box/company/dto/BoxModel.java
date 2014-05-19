package com.box.company.dto;
import com.box.company.entity.Attribute;
import com.box.company.entity.Box;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *This class is the JSON model representation of a Box Object.
 */
public class BoxModel {
    private Long id;
    private String name;
    private List<AttributeModel> boxAttributes;

    public BoxModel(){}

    public BoxModel (String name, Set<Attribute>attributes){
        this.name = name;
        this.boxAttributes = new ArrayList<AttributeModel>();
        for (Attribute attribute: attributes){
            boxAttributes.add(new AttributeModel(attribute));
        }
    }

    public BoxModel(Box boxEntity){
       id = boxEntity.getId();
       name = boxEntity.getName();

        Set<Attribute> attributes = boxEntity.getListOfAttributes();
        boxAttributes = new ArrayList<AttributeModel>(attributes.size());
        for(Attribute attribute : attributes) {
            boxAttributes.add(new AttributeModel(attribute));
        }
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

    public List<AttributeModel> getBoxAttributes() {
        return boxAttributes;
    }

    public void setBoxAttributes(List<AttributeModel> boxAttributes) {
        this.boxAttributes = boxAttributes;
    }
}
