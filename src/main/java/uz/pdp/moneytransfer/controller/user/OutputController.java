package uz.pdp.moneytransfer.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.moneytransfer.dto.UserOutputDTO;
import uz.pdp.moneytransfer.response.Response;
import uz.pdp.moneytransfer.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("/my/outputs")
public class OutputController {
    @Autowired
    private OutputService outputService;

    @GetMapping
    public ResponseEntity<Response> getAllInputsByUserId(Authentication authentication) {
        List<UserOutputDTO> outputs = outputService.findAllByFromCard_Username(authentication.getName());

        Response response = new Response(HttpStatus.OK.name(), outputs);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
