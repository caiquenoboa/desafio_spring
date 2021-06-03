package com.meli.desafiospring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
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
    private Post post;
}
