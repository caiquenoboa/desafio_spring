package com.mercadolivre.desafioSpring.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.mercadolivre.desafioSpring.views.PostView;
import com.mercadolivre.desafioSpring.views.UserView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Comparator;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostInfoResponse {
    @JsonView(UserView.Detailed.class)
    private Integer userId;
    private Integer id_post;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    private ProductInfoResponse detail;
    private Integer category;
    private Double price;
    @JsonView(PostView.PromotionalSimple.class)
    private Boolean hasPromo;
    @JsonView(PostView.PromotionalSimple.class)
    private Double discount;

    public static Comparator<PostInfoResponse> PostInfoResponseNameComparator = new Comparator<PostInfoResponse>() {
        public int compare(PostInfoResponse post1, PostInfoResponse post2) {
            return post1.getDate().compareTo(post2.getDate());
        }
    };
}
