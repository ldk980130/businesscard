package springpractice.businesscard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CardRepository {

    private final EntityManager em;

    public Long save(BusinessCard businessCard) {
        em.persist(businessCard);
        return businessCard.getId();
    }

    public Optional<BusinessCard> findById(Long id) {
        BusinessCard businessCard = em.find(BusinessCard.class, id);
        return Optional.ofNullable(businessCard);
    }

    public Optional<BusinessCard> findByName(String name) {
        List<BusinessCard> result = em.createQuery(
                "select b from BusinessCard b where b.userName =:userName", BusinessCard.class)
                .setParameter("userName", name)
                .getResultList();
        return result.stream().findAny();
    }

    public List<BusinessCard> findAll() {
        return em.createQuery("select b from BusinessCard b", BusinessCard.class)
                .getResultList();
    }

    public void delete(BusinessCard businessCard) {
        em.remove(businessCard);
    }
}
