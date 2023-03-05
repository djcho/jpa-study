package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //[엔티티 매니저 팩토리] - 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hellojpa");
        //[엔티티 매니저] - 생성
        EntityManager em = emf.createEntityManager();
        //[트랜잭션] - 획득
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();     //[트랜잭션] - 시작
            testSave(em);
            tx.commit();    //[트랜잭션] - 커밋
        }catch (Exception e){
            tx.rollback();  //[트랜잭션] - 롤백
        }
        finally {
            em.close();     //[엔티티 매니저] - 종료
        }
        emf.close();        //[엔티티 매니저 팩토리] - 종료
    }

    //비즈니스 로직
    private static void logic(EntityManager em){
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("예나");
        member.setAge(3);

        //등록
        em.persist(member);

        //수정
        member.setAge(20);

        //한 건 조회
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());

        //목록 조회
        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        System.out.println("members.size=" + members.size());

        //삭제
        em.remove(member);
    }

    public static void testSave(EntityManager em){
        Member member1 = new Member("member1", "회원1");
        Member member2 = new Member("member2", "회원2");

        Team team1 = new Team("team1", "팀1");
        team1.getMembers().add(member1);
        team1.getMembers().add(member2);

        em.persist(member1);//INSERT-member1
        em.persist(member2);//INSERT-member2
        em.persist(team1);  //INSERT-team1, UPDATE-member1.fk,
                            //UPDATE-member2.fk
    }
}
