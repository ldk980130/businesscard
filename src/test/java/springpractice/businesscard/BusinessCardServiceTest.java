package springpractice.businesscard;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BusinessCardServiceTest {

    @Autowired BusinessCardService businessCardService;
    @Autowired CardRepository cardRepository;

    @Test
    public void 명함_입력() throws Exception {
        //given
        BusinessCard businessCard = BusinessCard.createCard
                ("userA", "010-4199-6985", "우형");

        //when
        businessCardService.input(businessCard);

        //then
        assertThat(cardRepository.findByName("userA").get())
                .isEqualTo(businessCard);
    }

    @Test
    public void 명함_검색() throws Exception {
        //given
        BusinessCard cardA = BusinessCard.createCard
                ("userA", "010-4199-6985", "우형");
        BusinessCard cardB = BusinessCard.createCard
                ("userB", "010-4199-6985", "우형");

        //when
        businessCardService.input(cardA);
        businessCardService.input(cardB);

        //then
        assertThat(businessCardService.search("userA").get()).isEqualTo(cardA);
        assertThat(businessCardService.search("userB").get()).isEqualTo(cardB);
    }

    @Test
    public void 전체_검색() throws Exception {
        //given
        BusinessCard cardA = BusinessCard.createCard
                ("userA", "010-4199-6985", "우형");
        BusinessCard cardB = BusinessCard.createCard
                ("userB", "010-4199-6985", "우형");

        //when
        businessCardService.input(cardA);
        businessCardService.input(cardB);
        List<BusinessCard> businessCards = businessCardService.searchAll();

        //then
        assertThat(businessCards.size()).isEqualTo(2);
    }

    @Test
    public void 명함삭제() throws Exception {
        //given
        BusinessCard cardA = BusinessCard.createCard
                ("userA", "010-4199-6985", "우형");
        BusinessCard cardB = BusinessCard.createCard
                ("userB", "010-4199-6985", "우형");
        businessCardService.input(cardA);
        businessCardService.input(cardB);

        //when
        businessCardService.delete(cardA);
        List<BusinessCard> businessCards = businessCardService.searchAll();

        //then
        assertThat(businessCards.size()).isEqualTo(1);
    }

}