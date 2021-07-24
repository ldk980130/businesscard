package springpractice.businesscard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import springpractice.businesscard.BusinessCard;
import springpractice.businesscard.BusinessCardService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CardController {

    private final BusinessCardService businessCardService;

    @GetMapping("/new")
    public String inputCard(Model model) {
        model.addAttribute("form", new CardForm());
        return "createCard";
    }

    @PostMapping("/new")
    public String input(CardForm form, Model model) {

        if (form.getUserName().isEmpty()) {
            model.addAttribute("form", form);
            model.addAttribute("message", "이름은 필수 입력 사항입니다.");
            return "createCard";
        }

        BusinessCard businessCard =
                BusinessCard.createCard(form.getUserName(), form.getNumber(), form.getCorporationName());

        businessCardService.input(businessCard);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String findCard(Model model) {
        model.addAttribute("form", new SearchForm());
        return "searchCard";
    }

    @PostMapping("/search")
    public String find(SearchForm form, Model model) {
        String target = form.getUserName();
        Optional<BusinessCard> findCard = businessCardService.search(target);

        if (findCard.isPresent()) {
            model.addAttribute("findCard", findCard.get());
            return "showFindCard";
        }
        else {
            form.setErrorMessage("찾는 명함이 없습니다.");
            model.addAttribute("form", form);
            return "searchCard";
        }
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<BusinessCard> businessCards = businessCardService.searchAll();
        model.addAttribute("cards", businessCards);
        return "businessCardList";
    }

    @GetMapping("/delete/{name}")
    public String delete(@PathVariable String name) {
        Optional<BusinessCard> businessCard = businessCardService.search(name);
        businessCardService.delete(businessCard.get());
        return "redirect:/";
    }
}
