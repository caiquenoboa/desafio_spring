package com.mercadolivre.desafioSpring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Campo obrigatório")
    @Column(unique = true)
    private String userName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="FOLLOWERS",
               joinColumns = @JoinColumn(name = "USER_ID"),
               inverseJoinColumns = @JoinColumn(name = "SELLER_ID"))
    @JsonIgnore
    private List<Seller> followed;
}
