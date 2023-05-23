/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.entidades;

/**
 *
 * @author Aylin
 */
public class Publicacion {
    int id_post;
    String title;
    String description;
    String date_posted;
    String media;
    int idCategory;
    //int id_postcat; ?
    String category;
    int post_status;
    int post_user;
    Usuario post_userdata = new Usuario();
    int post_likes;

    public Publicacion() {
    }

    public Publicacion(int id_post, String title, String description, String date_posted, String media, int idCategory, String category, int post_status, int post_user) {
        this.id_post = id_post;
        this.title = title;
        this.description = description;
        this.date_posted = date_posted;
        this.media = media;
        this.idCategory = idCategory;
        this.category = category;
        this.post_status = post_status;
        this.post_user = post_user;
    }

    public Publicacion(String title, String description, String media,  int idCategory, int post_user) {
        this.title = title;
        this.description = description;
        this.media = media;
        this.idCategory = idCategory;
        this.post_user = post_user;
    }
    
    public Publicacion(int id_post, String title, String description, String media) {
        this.id_post = id_post;
        this.title = title;
        this.description = description;
        this.media = media;
        this.idCategory = idCategory;
    }
    
    public Publicacion(int id_post, int post_status) {
        this.post_status = post_status;
    }
    
    
    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public Usuario getPost_userdata() {
        return post_userdata;
    }

    public void setPost_userdata(Usuario post_userdata) {
        this.post_userdata.setFirstname(post_userdata.getFirstname());
        this.post_userdata.setpLastname(post_userdata.getpLastname());
        this.post_userdata.setProfileImg(post_userdata.getProfileImg());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_posted() {
        return date_posted;
    }

    public void setDate_posted(String date_posted) {
        this.date_posted = date_posted;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPost_status() {
        return post_status;
    }

    public void setPost_status(int post_status) {
        this.post_status = post_status;
    }

    public int getPost_user() {
        return post_user;
    }

    public void setPost_user(int post_user) {
        this.post_user = post_user;
    }

    public int getPost_likes() {
        return post_likes;
    }

    public void setPost_likes(int likes) {
        this.post_likes = likes;
    }
    
}
