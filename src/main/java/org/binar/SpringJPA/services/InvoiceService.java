package org.binar.SpringJPA.services;

import org.binar.SpringJPA.dto.TicketData;

public interface InvoiceService {
    public byte[] generateFile(TicketData data);
}