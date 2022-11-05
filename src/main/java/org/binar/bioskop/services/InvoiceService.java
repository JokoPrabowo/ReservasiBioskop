package org.binar.bioskop.services;

import org.binar.bioskop.dto.TicketData;

public interface InvoiceService {
    public byte[] generateFile(TicketData data);
}