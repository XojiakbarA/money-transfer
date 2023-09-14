package uz.pdp.moneytransfer.service;

import uz.pdp.moneytransfer.request.TransferRequest;

public interface TransferService {
    void transfer(TransferRequest request, String username);
}
