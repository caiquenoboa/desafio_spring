package com.mercadolivre.desafioSpring.views;

public class PostView {
    public interface Simple { }
    public interface Promotional extends Simple, UserView.Detailed { }
}
