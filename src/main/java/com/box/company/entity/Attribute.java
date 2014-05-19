package com.box.company.entity;

import com.box.company.dto.AttributeModel;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Owner on 5/15/14.
 */

@Entity
@Table(name="attribute")
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "attributes")
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Cascade(CascadeType.ALL)
    private Set<Box> boxes = new HashSet<Box>();

    public Attribute(){}

    public Attribute(Long id){
        this.id = id;
    }

    public Attribute(String name){
        this.name = name;
    }
    public Attribute(AttributeModel attributeModel){
        this.name = attributeModel.getName();
    }

    public Attribute(String newName, AttributeModel attributeModel){
        this.name = newName;
        this.id = attributeModel.getId();
    }

    public Attribute(Long id, String name){
        this.name = name;
        this.id = id;
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

    public Set<Box> getBoxes() {
        return boxes;
    }

    public void setBoxes(Set<Box> boxes) {
        this.boxes = boxes;
    }
}
