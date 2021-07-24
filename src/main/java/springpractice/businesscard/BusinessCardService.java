package springpractice.businesscard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BusinessCardService {

    private final CardRepository cardRepository;

    @Transactional
    public void input(BusinessCard businessCard) {
        cardRepository.save(businessCard);
    }

    public Optional<BusinessCard> search(String name) {
        return cardRepository.findByName(name);
    }

    public List<BusinessCard> searchAll() {
        return cardRepository.findAll();
    }

    @Transactional
    public void delete(BusinessCard businessCard) {
        cardRepository.delete(businessCard);
    }
}
