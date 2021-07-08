package BrickWorld.lego.service;

import BrickWorld.lego.domain.Address;
import BrickWorld.lego.domain.Member;
import BrickWorld.lego.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;


@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testJoin() throws Exception
    {
        //given
        Member member = new Member("seoyoungcheol", "seo@naver.com", "seo", new Address("12345", "기본" ,"상세"));

        //when
        Long savedId = memberService.join(member);

        //then
        Assertions.assertEquals(member, memberRepository.findOne(savedId));
        Assertions.assertEquals(member.getEmail(), memberRepository.findOne(savedId).getEmail());
    }

    @Test
    public void testDuplicateException() throws Exception
    {
        //given
        Member member1 = new Member("seoyoungcheol", "seo@naver.com", "seo", new Address("12345", "기본", "상세"));
        Member member2 = new Member("seoyoungcheol", "seo@naver.com", "seo", new Address("12345", "기본", "상세"));

        //when
        memberService.join(member1);

        //then
        Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }
}