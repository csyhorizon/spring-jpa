package study.data_jpa.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.data_jpa.entity.Member;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberJpaRespositoryTest {

    @Autowired MemberJpaRespository memberJpaRespository;

    @Test
    public void testMember() {
        System.out.println("memberJpaRespository = " + memberJpaRespository.getClass());
        Member member = new Member("memberA");
        Member savedMember = memberJpaRespository.save(member);

        Member findMember = memberJpaRespository.find(savedMember.getId());

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        memberJpaRespository.save(member1);
        memberJpaRespository.save(member2);

        Member findMember1 = memberJpaRespository.findById(member1.getId()).get();
        Member findMember2 = memberJpaRespository.findById(member2.getId()).get();

        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        findMember1.setUsername("member!!!!!!");

        // 리스트 조회 검증
        List<Member> all = memberJpaRespository.findAll();
        assertThat(all.size()).isEqualTo(2);

        long count = memberJpaRespository.count();
        assertThat(count).isEqualTo(2);



//        memberJpaRespository.delete(member1);
//        memberJpaRespository.delete(member2);
//
//        long deletedCount = memberJpaRespository.count();
//        assertThat(deletedCount).isEqualTo(0);
    }
}