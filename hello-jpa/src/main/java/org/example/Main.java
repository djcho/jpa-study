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
//
//    public static void testSave2(EntityManager em){
//        //팀1 저장
//        Team team1 = new Team("team1", "팀1");
//        em.persist(team1);
//
//        //회원1 저장
//        Member member1 = new Member("member1", "회원1");
//
//        //양방향 연관관계 설정
//        member1.setTeam(team1);          //연관관계 설정 member1 -> team1
//        em.persist(member1);
//
//        //회원2 저장
//        Member member2 = new Member("member2", "회원2");
//        member2.setTeam(team1);          //연관관계 설정 member2 -> team1
//        em.persist(member2);
//
//        em.flush();
//
//        Team findTeam = em.find(Team.class, "team1");
//
//        Team team2 = new Team("team2", "팀2");
//        member2.setTeam(team2);
//        em.persist(team2);
//
//        em.flush();
//
//        Team findTeam2 = em.find(Team.class, "team1");
//    }
//
//    public static void testSaveNonOwner(EntityManager em){
//        //회원1 저장
//        Member member1 = new Member("member1", "회원1");
//        em.persist(member1);
//
//        //회원2 저장
//        Member member2 = new Member("member2", "회원2");
//        em.persist(member2);
//
//        Team team1 = new Team("team1", "팀1");
//        //주인이 아닌 곳만 연관관계 설정
//        team1.getMembers().add(member1);
//        team1.getMembers().add(member2);
//
//        em.persist(team1);
//    }
//
//    private static void queryLogicJoin(EntityManager em){
//        String jpql = "select m from Member m join m.team t where t.name=:teamName";
//
//        List<Member> resultList = em.createQuery(jpql, Member.class)
//                .setParameter("teamName", "팀1")
//                .getResultList();
//
//        for(Member member : resultList){
//            System.out.println("[query] member.username=" + member.getUsername());
//        }
//    }
//
//    private static void updateRelation(EntityManager em){
//        //새로운 팀2
//        Team team2 = new Team("team2", "팀2");
//        em.persist(team2);
//
//        //회원1에 새로운 팀2 설정
//        Member member = em.find(Member.class, "member1");
//        member.setTeam(team2);
//    }
//
//    private static void deleteRelation(EntityManager em){
//        Member member1 = em.find(Member.class, "member1");
//        member1.setTeam(null);
//    }
//
//    public static void biDirection(EntityManager em){
//        Team team = em.find(Team.class, "team1");
//        List<Member> members = team.getMembers(); //(팀 -> 회원)
//
//        for(Member member : members){
//            System.out.println("member.username = " + member.getUsername());
//        }
//    }
}