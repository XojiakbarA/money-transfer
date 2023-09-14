package uz.pdp.moneytransfer.controller.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.moneytransfer.request.TransferRequest;
import uz.pdp.moneytransfer.response.Response;
import uz.pdp.moneytransfer.service.TransferService;

@RestController
@RequestMapping("/transfers")
public class TransferController {
    @Autowired
    private TransferService transferService;

    @PostMapping
    public ResponseEntity<Response> transfer(@Valid @RequestBody TransferRequest request, Authentication authentication) {
        transferService.transfer(request, authentication.getName());

        Response response = new Response(HttpStatus.OK.name());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
