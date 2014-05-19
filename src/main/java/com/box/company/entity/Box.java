package com.box.company.entity;

import com.box.company.dto.AttributeModel;
import com.box.company.dto.BoxModel;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Owner on 5/15/14.
 */
@Entity
@Table(name="box", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Box {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Attribute> attributes;

    public Box (){}

    public Box (Long id){
        this.id = id;
    }

    public Box(String name){
        this.name = name;
    }

    public Box(String name, Set<Attribute> newAttributes){
        this.name = name;
        this.attributes= new HashSet<Attribute>(attributes.size());

        for(Attribute attribute: newAttributes){
            attributes.add(new Attribute(attribute.getId(),attribute.getName()));
        }

     }

    public Box (String name, BoxModel boxModel){
        this.id = boxModel.getId();
        this.name = name;
        this.attributes= new HashSet<Attribute>();

        for(AttributeModel attribute: boxModel.getBoxAttributes()){
            attributes.add(new Attribute(attribute.getId(),attribute.getName()));
        }

    }


    public Box(BoxModel boxModel){
        this.id = boxModel.getId();
        this.name = boxModel.getName();
        this.attributes= new HashSet<Attribute>(boxModel.getBoxAttributes().size());

        for(AttributeModel attribute: boxModel.getBoxAttributes()){
            this.attributes.add(new Attribute(attribute.getId(),attribute.getName()));
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

    public Set<Attribute> getListOfAttributes() {
        return attributes;
    }

    public void setListOfAttributes(Set<Attribute> listOfAttributes) {
        this.attributes = listOfAttributes;
    }
}
