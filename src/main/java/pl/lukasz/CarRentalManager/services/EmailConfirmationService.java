package pl.lukasz.CarRentalManager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lukasz.CarRentalManager.entities.Client;
import pl.lukasz.CarRentalManager.entities.EmailConfirmation;
import pl.lukasz.CarRentalManager.repositories.EmailConfirmationRepository;

import java.util.UUID;

@Service
public class EmailConfirmationService {

    @Autowired
    private EmailConfirmationRepository emailConfirmationRepository;

    public void createEmailConfirmation(Client client) {
        EmailConfirmation emailConfirmation = new EmailConfirmation();
        emailConfirmation.setReservation(client.getReservations().get(client.getReservations().size() - 1)); // Możesz doprecyzować, który dokładnie obiekt rezerwacji z klienta
        emailConfirmation.setConfirmationCode(generateConfirmationCode());
        emailConfirmation.setConfirmed(false);

        emailConfirmationRepository.save(emailConfirmation);

        sendConfirmationEmail(client.getEmail(), emailConfirmation.getConfirmationCode());
    }

    private String generateConfirmationCode() {
        return UUID.randomUUID().toString();
    }

    private void sendConfirmationEmail(String recipientEmail, String confirmationCode) {
        System.out.println("Sending confirmation email to: " + recipientEmail);
        System.out.println("Confirmation code: " + confirmationCode);
    }
}
