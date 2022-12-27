package org.example;

import javax.persistence.*;

@Entity
@Table(name="MEMBER")
public class Member {
    public Member(){
    }

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String username;

    //매핑 정보가 없는 필드
    private Integer age;

    //Getter, Setter
    public String getId() {
        return this.id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public Integer getAge(){
        return this.age;
    }
    public void setAge(Integer age){
        this.age = age;
    }
}
