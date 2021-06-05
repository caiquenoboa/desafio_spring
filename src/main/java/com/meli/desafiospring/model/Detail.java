package com.meli.desafiospring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
//@Data
@Getter
@Setter
@Entity
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

    @OneToOne(mappedBy = "detail")
    @JsonIgnore
    private Post post;
}
