package comrapidm3.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class InvoiceController {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceDetailRepository invoiceDetailRepository;

    @Autowired
    public InvoiceController(InvoiceRepository invoiceRepository, InvoiceDetailRepository invoiceDetailRepository) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceDetailRepository = invoiceDetailRepository;
    }

    @GetMapping("/invoices")
    public Page<Invoice> getInvoices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, 2, Sort.by(sortBy));
        return invoiceRepository.findAll(pageable);
    }

    @GetMapping("/invoices/{invoiceId}/details")
    public List<InvoiceDetail> getInvoiceDetails(@PathVariable Long invoiceId) {
        return invoiceDetailRepository.findByInvoiceId(invoiceId);
    }
}
