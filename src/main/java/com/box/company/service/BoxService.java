package com.box.company.service;
import com.box.company.dto.AttributeModel;
import com.box.company.dto.BoxModel;
import com.box.company.entity.Attribute;
import com.box.company.entity.Box;
import com.box.company.repository.BoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import static com.box.company.specification.BoxSpecification.nameMatches;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Handles all database services for boxes.
 */
@Component
@Transactional
public class BoxService {

    @Autowired
    private BoxRepository boxRepository;

    /**
     * Retrieves a list of all the boxes ( returns each boxes id, name and list of attributes)
     * @return - List<BoxModel>
     */
    public List<BoxModel> getBoxes(){
       //retrieves a list of all boxes (joins box, box_attribute, and attribute tables)
        List<Box>boxes = boxRepository.findAll();
        //creating a list of 'BoxModels' - json for the front end
        List<BoxModel> boxModels = new ArrayList<BoxModel>();
        for (Box box: boxes){
            boxModels.add(new BoxModel(box));
        }
        return boxModels;
    }

    /**
     * Creates or updates a box.
     * @param boxModel
     * @return - BoxModel
     */
    public BoxModel saveBox(BoxModel boxModel){
        //find the box in the database
        Box box = findBox(boxModel);
        /*
        If the box is found, set and save its new properties to the database and return the model, else create a new box
         */
        if(null != box){
            box.setName(boxModel.getName());
            box = updateBoxAttributes(box, boxModel);
            boxRepository.saveAndFlush(box);
            return new BoxModel(box);
        }else{
            box = new Box(boxModel);
            boxRepository.save(box);
            return new BoxModel(box);
        }
    }

    /**
     * Updates the attributes for an existing box
     * @param box Object to be updated
     * @param model - model from the front end
     * @return - an updated Box
     */

    private Box updateBoxAttributes(Box box, BoxModel model){
        //new set of attributes for the box
        Set<Attribute> setOfAttributes = new HashSet<Attribute>();

        //go through the attributes in the model and create a new Set of attributes for an existing box
        for (AttributeModel attribute: model.getBoxAttributes()){
            Attribute newAttribute = new Attribute();
            newAttribute.setId(attribute.getId());
            newAttribute.setName(attribute.getName());
            setOfAttributes.add(newAttribute);
        }
          box.setListOfAttributes(setOfAttributes);
          return box;
    }

    /**
     * Retrieves a Box Object by name
     * @param boxName
     * @return
     */
    public Box getBoxByName(String boxName){
        return boxRepository.findOne(nameMatches(boxName));
    }

    /**
     * Deletes and returns true if the box is found and successfully deleted, otherwise returns false.
     * @param boxName
     * @return true or false
     */
    public boolean deleteBox(String boxName){
        boolean successfulDeletion = false;
       //get the Box from the database
        Box box = getBoxByName(boxName);
        /*
        If the box is found, delete it.  After deletion, verify that the box is not found.  If the
        box is not found, deletion was successful.
         */
        if (null != box){
            boxRepository.delete(box);
            if (null == getBoxByName(boxName))
                successfulDeletion = true;
        }
        return successfulDeletion;
    }

    /**
     * Finds a box - takes a BoxModel
     * @param boxModel
     * @return
     */
    public Box findBox(BoxModel boxModel){
        return boxRepository.findOne(nameMatches(boxModel.getName()));
    }


}
