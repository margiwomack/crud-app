package com.box.company.service;

import com.box.company.dto.AttributeModel;
import com.box.company.entity.Attribute;
import com.box.company.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.box.company.specification.AttributeSpecification.nameMatches;

/**
 * Attribute service performs CRUD operations for Attributes
 */
@Component
@Transactional
public class AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;

    /**
     * Retrieves a list of all attributes
     * @return
     */
    public List<AttributeModel> getAttributes(){
        List<Attribute> attributes = attributeRepository.findAll();
        List<AttributeModel> attributeModels = new ArrayList<AttributeModel>(attributes.size());

        for (Attribute attribute: attributes){
            attributeModels.add(new AttributeModel(attribute));
        }
        return attributeModels;

    }

    /**
     * Updates a given Attribute
     * @param attributeModel
     * @param attributeName
     * @return - Updated AttributeModel
     */
    public AttributeModel updateAttribute(AttributeModel attributeModel, String attributeName){
        //find the attribute
       Attribute attribute = findAttribute(attributeName);
        //if the attribute is found, update its name otherwise return null
        if (null != attribute){
            attribute.setName(attributeModel.getName());
            attributeRepository.saveAndFlush(attribute);
            return new AttributeModel(attribute);
       }else{
            return null;
        }
    }

    /**
     * Saves a new attribute to the database.  If an attribute by the same name exists, then a DataIntegrity violation
     * is thrown
     * @param attributeModel (that contains an attribute name)
     * @return - AttributeModel
     */
    public AttributeModel saveAttribute(AttributeModel attributeModel){
        //if the attribute is not found, create a new one and save it to the database
        Attribute attribute = findAttribute(attributeModel.getName());
        if (null == attribute){
            attribute = new Attribute(attributeModel);
            attributeRepository.save(attribute);
        }
        return new AttributeModel(attribute);
    }

    /**
     * Deletes an attribute if the attribute exists.
     * @param attributeName
     * @return true if deletion was successful
     */
    public boolean deleteAttribute (String attributeName){
        Attribute attribute = findAttribute(attributeName);

        boolean successfulDeletion = false;
        /*
        If the attribute is found, delete it.  After deletion, verify that the attribute is not found.  If the
        attribute is not found, deletion was successful.
         */
        if (null != attribute){
            attributeRepository.delete(attribute);
            if (null == findAttribute(attributeName))
            successfulDeletion = true;
        }
        return successfulDeletion;
    }

    /**
     * finds an attribute by name
     * @param attributeName
     * @return an Attribute if one exists or null
     */
    private Attribute findAttribute(String attributeName){
        return attributeRepository.findOne(nameMatches(attributeName));
    }

}
