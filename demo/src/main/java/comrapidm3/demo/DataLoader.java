package comrapidm3.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceDetailRepository invoiceDetailRepository;

    @Autowired
    public DataLoader(InvoiceRepository invoiceRepository, InvoiceDetailRepository invoiceDetailRepository) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceDetailRepository = invoiceDetailRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Invoice invoice1 = new Invoice();
        invoice1.setSender("ABC");
        invoice1.setReceiver("XYZ");
        invoice1.setTotalAmount(200);
        InvoiceDetail detail1 = new InvoiceDetail();
        detail1.setPaidAmount(10);
        InvoiceDetail detail2 = new InvoiceDetail();
        detail2.setPaidAmount(50);
        invoice1.setDetails(Arrays.asList(detail1, detail2));

        // Add more Invoice data here if needed

        invoiceRepository.save(invoice1);
    }
}
