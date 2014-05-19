package com.box.company.controller;

import com.box.company.dto.AttributeModel;
import com.box.company.dto.BoxModel;
import com.box.company.entity.Attribute;
import com.box.company.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Attribute controller - receives requests to retrieve, update, save and delete attributes.  Returns appropriate response
  * to each request.
  */
@Controller
@RequestMapping("/attribute")
public class AttributeController {

   @Autowired
   private AttributeService attributeService;

    /**
     * Handles requests for retrieving all attributes.  Returns a list of attributeModel objects (JSON) objects.
     * @return
     */

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<AttributeModel> getBoxes(){
        return attributeService.getAttributes();
    }

    /**
     * Handles requests for creating new attributes.  Takes a new attribute model (JSON) and returns the newly created
     * attribute model (JSON)
     * @param attribute
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public AttributeModel createAttribute(@RequestBody AttributeModel attribute){
        return attributeService.saveAttribute(attribute);
    }

    /**
     * Handles requests for updating an attribute.
     * @param attributeModel - Takes an attributeModel object (JSON) - with the updated name
     * @param attributeName - Takes the name of the attribute to update.
     * @return - the updated attribute model -JSON
     */
    @RequestMapping(value = "/{attributeName}" ,method = {RequestMethod.PUT })
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public AttributeModel updateAttribute(@RequestBody AttributeModel attributeModel, @PathVariable String attributeName) {
        return attributeService.updateAttribute(attributeModel, attributeName);
    }

    /**
     * Handles requests for deleting an attribute.  Takes an attribute name and returns a response object with a message
     * indicting whether or not the deletion was successful.
     * @param attributeName
     * @return - Response Object
     */
    @RequestMapping(value = "/{attributeName}", method = {RequestMethod.DELETE })
    public ResponseEntity deleteAttribute(@PathVariable String attributeName) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String result = "";
        if (attributeService.deleteAttribute(attributeName)){
            result = "The attribute " + attributeName + " was successfully deleted.";
            status = HttpStatus.OK;
        }else{
            result = "Unable to delete the attribute.";
        }
        return new ResponseEntity<String>(result, status);
    }

}
