package org.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @Column(name = "TEAM_ID")
    private String id;

    private String name;

    //==추가==//
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<Member>();

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setMember(Member member){
        this.members.add(member);
    }
    public List<Member> getMembers() {
        return this.members;
    }
}
