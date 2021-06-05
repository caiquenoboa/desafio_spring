package com.meli.desafiospring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
//@Data
@Getter
@Setter
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
    @JsonIgnore
    private User user;

    private int category;
    private double price;

    public Post(Detail detail, User user) {
        this.detail = detail;
        this.user = user;
    }
}
