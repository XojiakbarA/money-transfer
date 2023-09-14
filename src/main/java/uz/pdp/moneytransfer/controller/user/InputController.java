package uz.pdp.moneytransfer.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.moneytransfer.dto.UserInputDTO;
import uz.pdp.moneytransfer.response.Response;
import uz.pdp.moneytransfer.service.InputService;

import java.util.List;

@RestController("userInputController")
@RequestMapping("/my/inputs")
public class InputController {
    @Autowired
    private InputService inputService;

    @GetMapping
    public ResponseEntity<Response> getAllInputsByUserId(Authentication authentication) {
        List<UserInputDTO> inputs = inputService.findAllByToCard_Username(authentication.getName());

        Response response = new Response(HttpStatus.OK.name(), inputs);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
