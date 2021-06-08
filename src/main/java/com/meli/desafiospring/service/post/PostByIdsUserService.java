package com.meli.desafiospring.service.post;

import com.meli.desafiospring.gateway.repository.PostRepository;
import com.meli.desafiospring.model.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PostByIdsUserService {

    private final PostRepository postRepository;

    public List<Post> getAllPostByIdsUser(List<Integer> idsUser){
        List<Post> posts = new ArrayList<>();

        LocalDate dateBefore15Days = LocalDate.now().minusDays(15);

        postRepository.findAllByUserIdInAndDateGreaterThan(idsUser, dateBefore15Days)
                        .ifPresent(p -> posts.addAll(p));

        return posts;

    }
}
