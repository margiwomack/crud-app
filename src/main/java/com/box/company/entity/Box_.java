package com.box.company.entity;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Created by Owner on 5/16/14.
 */
@StaticMetamodel(Box.class)
public class Box_ {
    private static volatile SingularAttribute<Box, Long> id;
    private static volatile SingularAttribute<Box, String> name;
    private static volatile SetAttribute<Box, Attribute> attributes;

    public static SetAttribute<Box, Attribute> getAttributes() {
        return attributes;
    }

    public static void setAttributes(SetAttribute<Box, Attribute> attributes) {
        Box_.attributes = attributes;
    }

    public static SingularAttribute<Box, Long> getId() {
        return id;
    }

    public static void setId(SingularAttribute<Box, Long> id) {
        Box_.id = id;
    }

    public static SingularAttribute<Box, String> getName() {
        return name;
    }

    public static void setName(SingularAttribute<Box, String> name) {
        Box_.name = name;
    }
}
