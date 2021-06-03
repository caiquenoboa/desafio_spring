package com.meli.desafiospring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    private LocalDate date;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId  //TODO Estudar esse cara
    @JoinColumn(name = "id_detail")
    private Detail detail;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    private int category;
    private double price;

    public Post(Detail detail, User user) {
        this.detail = detail;
        this.user = user;
    }
}
