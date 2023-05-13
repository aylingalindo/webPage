/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.entidades;

/**
 *
 * @author Aylin
 */
public class Usuario {
    private int idUser;
    private String firstname;
    private String pLastname;
    private String mLastname;
    private String birthdate;
    private String email;
    private String username;
    private String password;
    private String profileImg;
    private String coverImg;
    private String singupDate;
    private String city;
    private String state;
    private String country;
    private String occupation;
    private int userStatus;

    public Usuario(int idUser, String firstname, String pLastname, String mLastname, String birthdate, String email, String username, String password, String profileImg, String coverImg, String singupDate, String city, String state, String country, String occupation, int userStatus) {
        this.idUser = idUser;
        this.firstname = firstname;
        this.pLastname = pLastname;
        this.mLastname = mLastname;
        this.birthdate = birthdate;
        this.email = email;
        this.username = username;
        this.password = password;
        this.profileImg = profileImg;
        this.coverImg = coverImg;
        this.singupDate = singupDate;
        this.city = city;
        this.state = state;
        this.country = country;
        this.occupation = occupation;
        this.userStatus = userStatus;
    }
    
    public Usuario(int idUser, String firstname, String pLastname, String mLastname, String username, String password, String profileImg, String coverImg) {
        this.idUser = idUser;
        this.firstname = firstname;
        this.pLastname = pLastname;
        this.mLastname = mLastname;
        this.username = username;
        this.password = password;
        this.profileImg = profileImg;
        this.coverImg = coverImg;
    }
    
    public Usuario(int idUser, String birthdate, String email, String city, String state, String country, String occupation) {
        this.idUser = idUser;
        this.birthdate = birthdate;
        this.email = email;
        this.city = city;
        this.state = state;
        this.country = country;
        this.occupation = occupation;
    }
    
    public Usuario(String firstname, String pLastname, String mLastname, String birthdate, String email, String username, String password, String profileImg) {
        this.firstname = firstname;
        this.pLastname = pLastname;
        this.mLastname = mLastname;
        this.birthdate = birthdate;
        this.email = email;
        this.username = username;
        this.password = password;
        this.profileImg = profileImg;
    }

    public Usuario() {
        
    }
    
    public Usuario(String Usuario, String contrasena) {
        this.username = Usuario;
        this.password = contrasena;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getpLastname() {
        return pLastname;
    }

    public String getmLastname() {
        return mLastname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public String getSingupDate() {
        return singupDate;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getOccupation() {
        return occupation;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setpLastname(String pLastname) {
        this.pLastname = pLastname;
    }

    public void setmLastname(String mLastname) {
        this.mLastname = mLastname;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public void setSingupDate(String singupDate) {
        this.singupDate = singupDate;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }
    
    
}


