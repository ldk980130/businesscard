package springpractice.businesscard;

import org.assertj.core.api.Assertions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CardRepositoryTest {

    @Autowired CardRepository cardRepository;

    @Test
    public void 명함저장() throws Exception {
        //given
        BusinessCard cardA = BusinessCard.createCard
                ("userA", "010-4199-6985", "우형");

        //when
        Long saveId = cardRepository.save(cardA);

        //then
        Optional<BusinessCard> findCard = cardRepository.findById(saveId);
        assertThat(findCard.isPresent()).isEqualTo(true);
    }

    @Test
    public void 이름으로조회() throws Exception {
        //given
        BusinessCard cardA = BusinessCard.createCard
                ("userA", "010-4199-6985", "우형");
        Long saveId = cardRepository.save(cardA);

        //when
        Optional<BusinessCard> userA = cardRepository.findByName("userA");

        //then
        assertThat(userA.get().getUserName()).isEqualTo("userA");
    }

    @Test
    public void 전체조회() throws Exception {
        //given
        BusinessCard cardA = BusinessCard.createCard
                ("userA", "010-4199-6985", "우형");
        BusinessCard cardB = BusinessCard.createCard
                ("userB", "010-4199-6985", "우형");

        //when
        cardRepository.save(cardA);
        cardRepository.save(cardB);

        //then
        List<BusinessCard> all = cardRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
    }

    @Test
    public void 명함삭제() throws Exception {
        //given
        BusinessCard cardA = BusinessCard.createCard
                ("userA", "010-4199-6985", "우형");
        BusinessCard cardB = BusinessCard.createCard
                ("userB", "010-4199-6985", "우형");
        cardRepository.save(cardA);
        cardRepository.save(cardB);

        //when
        cardRepository.delete(cardA);

        //then
        List<BusinessCard> all = cardRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }
}