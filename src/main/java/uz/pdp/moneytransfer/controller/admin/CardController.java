package uz.pdp.moneytransfer.controller.admin;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.moneytransfer.dto.CardDTO;
import uz.pdp.moneytransfer.marker.OnCreate;
import uz.pdp.moneytransfer.request.CardRequest;
import uz.pdp.moneytransfer.response.Response;
import uz.pdp.moneytransfer.service.CardService;

@Validated
@RestController("adminCardController")
@RequestMapping("/admin/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping
    public ResponseEntity<Response> getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<CardDTO> cards = cardService.getAll(PageRequest.of(page, size));

        Response response = new Response(HttpStatus.OK.name(), cards);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id) {
        CardDTO card = cardService.getById(id);

        Response response = new Response(HttpStatus.OK.name(), card);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Validated(OnCreate.class)
    @PostMapping
    public ResponseEntity<Response> create(@Valid @RequestBody CardRequest request) {
        CardDTO card = cardService.create(request);

        Response response = new Response(HttpStatus.CREATED.name(), card);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@Valid @RequestBody CardRequest request, @PathVariable Long id) {
        CardDTO card = cardService.update(id, request);

        Response response = new Response(HttpStatus.OK.name(), card);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<Response> activate(@PathVariable Long id) {
        cardService.activate(id);

        Response response = new Response(HttpStatus.ACCEPTED.name());

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Response> deactivate(@PathVariable Long id) {
        cardService.deactivate(id);

        Response response = new Response(HttpStatus.ACCEPTED.name());

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        cardService.deleteById(id);

        Response response = new Response(HttpStatus.ACCEPTED.name());

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
