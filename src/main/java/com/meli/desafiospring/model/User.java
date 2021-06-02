package com.meli.desafiospring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String userName;

    @ManyToMany
    @JoinTable(
            name = "user_follower",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_follower"))
    private List<User> followers;

    public void addUserFollower(User user){
        List<User> users = this.getFollowers();
        users.add(user);
        this.setFollowers( users );
    }
}
