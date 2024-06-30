package pl.lukasz.CarRentalManager.repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import pl.lukasz.CarRentalManager.entities.*;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    default List<Invoice> findAllNotArchivedInvoices() {
        return findAll().stream()
                .filter(i -> !i.isArchived())
                .toList();
    }
    default List<Invoice> findAllArchivedInvoices() {
        return findAll().stream()
                .filter(i -> i.isArchived())
                .toList();
    }

    default Invoice archive(Invoice invoice) {
        invoice.setArchived(true);
        return save(invoice);
    }
}