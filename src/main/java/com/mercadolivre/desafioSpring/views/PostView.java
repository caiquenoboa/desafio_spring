package com.mercadolivre.desafioSpring.views;

public class PostView {
    public interface Simple { }
    public interface PromotionalSimple extends Simple{ }
    public interface PromotionalDetailed extends PromotionalSimple, UserView.Detailed { }
}
