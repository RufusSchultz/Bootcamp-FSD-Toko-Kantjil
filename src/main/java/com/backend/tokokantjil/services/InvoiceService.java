package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.inputs.InvoiceInputDto;
import com.backend.tokokantjil.dtos.mappers.InvoiceMapper;
import com.backend.tokokantjil.dtos.outputs.InvoiceOutputDto;
import com.backend.tokokantjil.exceptions.RecordNotFoundException;
import com.backend.tokokantjil.exceptions.UserInputIsUnprocessableException;
import com.backend.tokokantjil.models.*;
import com.backend.tokokantjil.repositories.CustomerRepository;
import com.backend.tokokantjil.repositories.InvoiceRepository;
import com.backend.tokokantjil.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.backend.tokokantjil.helpers.PriceInCentsRounder.priceInCentsRounder;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, OrderRepository orderRepository, CustomerRepository customerRepository, CustomerRepository customerRepository1) {
        this.invoiceRepository = invoiceRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository1;
    }

    public List<InvoiceOutputDto> getAllInvoices() {
        List<InvoiceOutputDto> list = new ArrayList<>();
        for (Invoice i : this.invoiceRepository.findAll()) {
            list.add(InvoiceMapper.fromInvoiceToInvoiceOutputDto(i));
        }
        return list;
    }

    public InvoiceOutputDto getInvoiceById(Long id) {
        Invoice invoice = this.invoiceRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No invoice with id " + id + " found."));
        return InvoiceMapper.fromInvoiceToInvoiceOutputDto(invoice);
    }

    public InvoiceOutputDto createInvoice(InvoiceInputDto invoiceInputDto) {
        Invoice invoice = this.invoiceRepository.save(InvoiceMapper.fromInvoiceInputDtoToInvoice(invoiceInputDto));
        return InvoiceMapper.fromInvoiceToInvoiceOutputDto(invoice);
    }

    public void deleteInvoice(Long id) {
        if (this.invoiceRepository.findById(id).isPresent()) {
            this.invoiceRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No invoice with id " + id + " found.");
        }
    }

    public InvoiceOutputDto updateInvoice(Long id, InvoiceInputDto invoiceInputDto) {
        Invoice oldInvoice = this.invoiceRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No invoice with id " + id + " found."));
        Invoice invoiceUpdate = InvoiceMapper.fromInvoiceInputDtoToInvoice(invoiceInputDto);
        Invoice newInvoice = this.invoiceRepository.save(InvoiceMapper.fromInvoiceToUpdatedInvoice(oldInvoice, invoiceUpdate));

        return InvoiceMapper.fromInvoiceToInvoiceOutputDto(newInvoice);
    }

    public InvoiceOutputDto assignOrderToInvoice(Long id, Long orderId, boolean useAgreedPrice) {
        Invoice invoice = this.invoiceRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No invoice with id " + id + " found."));
        Order order = this.orderRepository.findById(orderId).orElseThrow(() -> new RecordNotFoundException("No order with id " + orderId + " found."));

        if (order.isAppraised()) {
            if (order.isCateringOrder()) {
                if (order.getCatering() != null) {
                    if(useAgreedPrice) {
                        invoice.setFinalPrice(priceInCentsRounder(order.getCatering().getAgreedPrice()));
                    } else {
                        invoice.setFinalPrice(priceInCentsRounder(order.getCatering().getTotalSellPrice()));
                    }
                } else {
                    throw new UserInputIsUnprocessableException("Order has no catering assigned, but is expecting one.");
                }
            } else {
                invoice.setFinalPrice(priceInCentsRounder(order.getTotalPrice()));
            }
            invoice.setOrder(order);
            this.invoiceRepository.save(invoice);

            return InvoiceMapper.fromInvoiceToInvoiceOutputDto(invoice);
        } else {
            throw new UserInputIsUnprocessableException("Order has to be appraised first.");
        }
    }

    public String setInvoicePaymentStatus(Long id, boolean hasBeenPaid) {
        Invoice invoice = this.invoiceRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No invoice with id " + id + " found."));
        String response = "";

        if (hasBeenPaid) {
            if (!invoice.isPaid()) {
                if (invoice.getOrder() != null) {
                    invoice.setPaid(true);
                    this.invoiceRepository.save(invoice);

                    response = "Invoice is set to paid.";
                } else {
                    throw new UserInputIsUnprocessableException("Invoice has no order assigned.");
                }
            } else {
                throw new UserInputIsUnprocessableException("Invoice is already set to paid.");
            }
        } else {
            if (invoice.isPaid()) {
                invoice.setPaid(false);
                this.invoiceRepository.save(invoice);

                response = "Invoice is set to unpaid.";
            } else {
                throw new UserInputIsUnprocessableException("Invoice is already set to unpaid.");
            }
        }
        return response;
    }

    public List<InvoiceOutputDto> getAllInvoicesByCustomerId(Long customerId) {
        customerRepository.findById(customerId).orElseThrow(() -> new RecordNotFoundException("No customer with id " + customerId + " found."));
        List<InvoiceOutputDto> invoiceOutputDtoList = new ArrayList<>();

        for (Invoice invoice : this.invoiceRepository.findAll()) {
            if (invoice.getOrder() != null && invoice.getOrder().getCustomer() != null && invoice.getOrder().getCustomer().getId().equals(customerId)) {
                invoiceOutputDtoList.add(InvoiceMapper.fromInvoiceToInvoiceOutputDto(invoice));
            }
        }

        return invoiceOutputDtoList;
    }
}
