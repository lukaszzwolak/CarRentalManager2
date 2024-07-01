package pl.lukasz.CarRentalManager.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Invoice> invoices;

    @OneToMany
    private List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    @JsonManagedReference
    private Model model;

    @Pattern(regexp="[A-Z]{2}-[0-9]{4,6}", message = "Registration must have 2 uppercase letters and 4 to 6 digits")
    private String registrationNumber;

    @NotNull(message = "Status is required")
    private Status status;

    public Car() {
        status = Status.Available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public @NotBlank(message = "Registration number is required") @Pattern(regexp = "[A-Z]{2}-[0-9]{4,6}", message = "Invalid registration number format") String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(@NotBlank(message = "Registration number is required") @Pattern(regexp = "[A-Z]{2}-[0-9]{4,6}", message = "Invalid registration number format") String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDisplayName() {
        return model.getBrand().getName() + " " + model.getName() + "(" + registrationNumber + ")";
    }
}
