package uz.pdp.moneytransfer.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import uz.pdp.moneytransfer.dto.InputDTO;
import uz.pdp.moneytransfer.marker.OnCreate;
import uz.pdp.moneytransfer.request.InputRequest;
import uz.pdp.moneytransfer.response.Response;
import uz.pdp.moneytransfer.service.InputService;

@Validated
@RestController("adminInputController")
@RequestMapping("/admin/inputs")
public class InputController {
    @Autowired
    private InputService inputService;

    @GetMapping
    public ResponseEntity<Response> getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<InputDTO> inputs = inputService.getAll(PageRequest.of(page, size));

        Response response = new Response(HttpStatus.OK.name(), inputs);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id) {
        InputDTO input = inputService.getById(id);

        Response response = new Response(HttpStatus.OK.name(), input);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Validated(OnCreate.class)
    @PostMapping
    public ResponseEntity<Response> create(@Valid @RequestBody InputRequest request) {
        InputDTO input = inputService.create(request);

        Response response = new Response(HttpStatus.CREATED.name(), input);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@Valid @RequestBody InputRequest request, @PathVariable Long id) {
        InputDTO input = inputService.update(id, request);

        Response response = new Response(HttpStatus.CREATED.name(), input);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        inputService.deleteById(id);

        Response response = new Response(HttpStatus.ACCEPTED.name());

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
