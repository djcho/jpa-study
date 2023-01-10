package org.example;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {
    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String username;

    //연관관계 매핑
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    //매핑 정보가 없는 필드
    private Integer age;

    //==추가==
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private Date description;

    public Member(){
    }
    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }

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

    public void setTeam(Team team) {
        this.team = team;
    }
}
