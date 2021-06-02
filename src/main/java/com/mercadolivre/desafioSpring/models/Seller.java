package com.mercadolivre.desafioSpring.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Seller extends User{
    @ManyToMany(mappedBy = "followed")
    private List<User> followers;

    @OneToMany(mappedBy = "seller")
    private List<Post> posts;

    @Builder
    public Seller(Integer id, String userName, List<Seller> followed, List<User> followers, List<Post> posts){
        super(id, userName, followed);
        this.followers = followers;
        this.posts = posts;
    }
}
