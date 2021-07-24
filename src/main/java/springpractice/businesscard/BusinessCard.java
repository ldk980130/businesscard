package springpractice.businesscard;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
public class BusinessCard {

    protected BusinessCard() {}
    public static BusinessCard createCard(String userName, String phoneNumber, String corporationName) {
        BusinessCard businessCard = new BusinessCard();
        businessCard.userName = userName;
        businessCard.phoneNumber = phoneNumber;
        businessCard.corporationName = corporationName;
        businessCard.createDate = LocalDateTime.now();
        return businessCard;
    }

    @Id @GeneratedValue
    @Column(name = "businesscard_id")
    private Long id;

    private String userName;

    private String phoneNumber;

    private String corporationName;

    private LocalDateTime createDate;
}
