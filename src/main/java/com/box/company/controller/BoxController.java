package com.box.company.controller;

import com.box.company.dto.BoxModel;
import com.box.company.dto.ErrorMessage;
import com.box.company.entity.Box;
import com.box.company.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * Box controller - receives requests to retrieve, update, save and delete boxes.  Returns appropriate response
 * to each request.
 */
@Controller
@RequestMapping("/")
public class BoxController {

    @Autowired
    private BoxService boxService;

    /**
     * Responds to a request for all boxes.
     * @return - List of all boxes
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<BoxModel> getBoxes(){
        return boxService.getBoxes();
    }

    /**
     * Retrieves a box by name
     * @param boxName
     * @return a BoxModel if the box is found, otherwise an error message is returned
     */

    @RequestMapping(value = "/{boxName}", method = RequestMethod.GET)
    public ResponseEntity getBoxByName(@PathVariable String boxName) {
        Box box  = boxService.getBoxByName(boxName);

        if (null != box) {
            BoxModel boxModel = new BoxModel(box);
            return new ResponseEntity<BoxModel>(boxModel, HttpStatus.OK);
        } else {
            Object errorBody = new HashMap<String, Object>() {{
                put("errors", new Object[]{new ErrorMessage("boxName", "BoxNotFound", "The box you are requesting could not be found.")});
            }};
            return new ResponseEntity<Object>(errorBody, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Responds to a request to create or update a new box
     * @param box (Box model -  box name, list of attributes for the box)
     * @return - newly created box model
     */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public BoxModel createOrUpdateBox(@RequestBody BoxModel box){
        return boxService.saveBox(box);
    }

    /**
     * Responds to a request to delete a given box
     * @param boxName - name of the box
     * @return a response message and HttpStatus code
     */

    @RequestMapping(value = "/{boxName}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deleteBox(@PathVariable String boxName) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String result = "";
        if (boxService.deleteBox(boxName)){
            result = boxName + " was successfully deleted.";
            status = HttpStatus.OK;
        }else{
            result = "Unable to delete the box.";
        }
        return new ResponseEntity<String>(result, status);
    }

 }









