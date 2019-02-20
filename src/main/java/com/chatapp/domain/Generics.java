package com.chatapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="live_mall_generics")
@Data @AllArgsConstructor @NoArgsConstructor
public class Generics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "text")
    private String text;
    @Column(name = "lang")
    private String lang;
    @Column(name = "identifier")
    private String identifier;


}
