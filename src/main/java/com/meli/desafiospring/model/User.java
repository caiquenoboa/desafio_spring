package com.meli.desafiospring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meli.desafiospring.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(32) default 'CLIENT'")
    private UserType userType;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Post> posts;

    public void addUserFollower(User user){
        List<User> users = this.getFollowers();
        users.add(user);
        this.setFollowers( users );
    }

    public boolean isUserTypeClient(){
        return this.userType.equals(UserType.CLIENT);
    }


}
