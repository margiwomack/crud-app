package com.box.company.entity;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Created by Owner on 5/17/14.
 */
@StaticMetamodel(Attribute.class)
public class Attribute_ {

    private static volatile SingularAttribute<Attribute, Long> id;
    private static volatile SingularAttribute<Attribute, String> name;
    private static volatile SetAttribute<Attribute, Box> boxes;

    public static SingularAttribute<Attribute, Long> getId() {return id;}

    public static void setId(SingularAttribute<Attribute, Long> id) {
        Attribute_.id = id;
    }

    public static SingularAttribute<Attribute, String> getName() {
        return name;
    }

    public static void setName(SingularAttribute<Attribute, String> name) {
        Attribute_.name = name;
    }

    public static SetAttribute<Attribute, Box> getBoxes() {
        return boxes;
    }

    public static void setBoxes(SetAttribute<Attribute, Box> boxes) {
        Attribute_.boxes = boxes;
    }
}
