package pl.lukasz.CarRentalManager.services;

import org.springframework.stereotype.Service;
import pl.lukasz.CarRentalManager.entities.Client;
import pl.lukasz.CarRentalManager.entities.EmailConfirmation;
import pl.lukasz.CarRentalManager.repositories.EmailConfirmationRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EmailConfirmationService {

    private final EmailConfirmationRepository emailConfirmationRepository;

    public EmailConfirmationService(EmailConfirmationRepository emailConfirmationRepository) {
        this.emailConfirmationRepository = emailConfirmationRepository;
    }

    public EmailConfirmation createEmailConfirmation(Client client) {
        EmailConfirmation confirmation = new EmailConfirmation();
        confirmation.setClient(client);
        confirmation.setConfirmationCode(generateConfirmationCode());
        confirmation.setExpiryDateTime(LocalDateTime.now().plusHours(24)); // Example: expires in 24 hours

        return emailConfirmationRepository.save(confirmation);
    }

    private String generateConfirmationCode() {
        return UUID.randomUUID().toString();
    }

    public void confirmEmail(String confirmationCode) {
        EmailConfirmation confirmation = emailConfirmationRepository.findByConfirmationCode(confirmationCode);
        if (confirmation != null && !confirmation.getExpiryDateTime().isBefore(LocalDateTime.now())) {
            Client client = confirmation.getClient();
            client.setEmailConfirmed(true);
        } else {
            throw new IllegalArgumentException("Invalid or expired confirmation code");
        }
    }
}
