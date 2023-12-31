package uz.pdp.moneytransfer.service.impl;

import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.moneytransfer.Commission;
import uz.pdp.moneytransfer.entity.Card;
import uz.pdp.moneytransfer.entity.Input;
import uz.pdp.moneytransfer.entity.Output;
import uz.pdp.moneytransfer.request.TransferRequest;
import uz.pdp.moneytransfer.service.CardService;
import uz.pdp.moneytransfer.service.InputService;
import uz.pdp.moneytransfer.service.OutputService;
import uz.pdp.moneytransfer.service.TransferService;

@Service
public class TransferServiceImpl implements TransferService {
    @Autowired
    private CardService cardService;
    @Autowired
    private InputService inputService;
    @Autowired
    private OutputService outputService;

    @Transactional
    @Override
    public void transfer(TransferRequest request, String username) {
        Card fromCard = cardService.findByIdAndUsername(request.getFromCardId(), username);
        Card toCard = cardService.findById(request.getToCardId());

        checkCardIsActive(fromCard);
        checkCardIsActive(toCard);

        Float amount = request.getAmount();
        Float amountWithCommission = amountWithCommission(amount, fromCard, toCard);

        checkBalance(fromCard, amountWithCommission);

        fromCard.setBalance(fromCard.getBalance() - amountWithCommission);
        cardService.save(fromCard);

        toCard.setBalance(toCard.getBalance() + amount);
        cardService.save(toCard);

        saveInput(fromCard, toCard, amount);
        saveOutput(fromCard, toCard, amount, amountWithCommission - amount);

    }

    private void checkBalance(Card card, Float amount) {
        if (card.getBalance() < amount) {
            throw new EntityActionVetoException("There are insufficient funds on the card number " + card.getNumber() + " balance", null);
        }
    }
    private void checkCardIsActive(Card card) {
        if (!card.getActive()) {
            throw new EntityActionVetoException("Card with number " + card.getNumber() + " is not active", null);
        }
    }
    private Float amountWithCommission(Float amount, Card fromCard, Card toCard) {
        if (fromCard.getUsername().equals(toCard.getUsername())) {
            return (amount * Commission.OWN_CARD.getValue()) + amount;
        } else {
            return (amount * Commission.FOREIGN_CARD.getValue()) + amount;
        }
    }
    private void saveInput(Card fromCard, Card toCard, Float amount) {
        Input input = new Input();
        input.setFromCard(fromCard);
        input.setToCard(toCard);
        input.setAmount(amount);
        inputService.save(input);
    }
    private void saveOutput(Card fromCard, Card toCard, Float amount, Float commissionAmount) {
        Output output = new Output();
        output.setFromCard(fromCard);
        output.setToCard(toCard);
        output.setAmount(amount);
        output.setCommissionAmount(commissionAmount);
        outputService.save(output);
    }
}
