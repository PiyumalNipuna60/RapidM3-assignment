package comrapidm3.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<InvoiceDetail> findByInvoiceId(Long invoiceId);
}

