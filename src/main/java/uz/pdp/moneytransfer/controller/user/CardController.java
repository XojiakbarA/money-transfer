package uz.pdp.moneytransfer.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uz.pdp.moneytransfer.dto.CardDTO;
import uz.pdp.moneytransfer.response.Response;
import uz.pdp.moneytransfer.service.CardService;

import java.util.List;

@RestController
@RequestMapping("/my/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping
    public ResponseEntity<Response> getAllByUsername(Authentication authentication) {
        List<CardDTO> cards = cardService.getAllByUsername(authentication.getName());

        Response response = new Response(HttpStatus.OK.name(), cards);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getByIdAndUsername(@PathVariable Long id, Authentication authentication) {
        CardDTO card = cardService.getByIdAndUsername(id, authentication.getName());

        Response response = new Response(HttpStatus.OK.name(), card);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<Response> activate(@PathVariable Long id, Authentication authentication) {
        cardService.activateByIdAndUsername(id, authentication.getName());

        Response response = new Response(HttpStatus.ACCEPTED.name());

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Response> deactivate(@PathVariable Long id, Authentication authentication) {
        cardService.deactivateByIdAndUsername(id, authentication.getName());

        Response response = new Response(HttpStatus.ACCEPTED.name());

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
