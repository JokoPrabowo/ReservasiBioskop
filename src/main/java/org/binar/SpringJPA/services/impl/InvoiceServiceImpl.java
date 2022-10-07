package org.binar.SpringJPA.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.binar.SpringJPA.dto.TicketData;
import org.binar.SpringJPA.services.InvoiceService;


@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService{

    public byte[] generateFile(TicketData data){
        try{
            File file = ResourceUtils.getFile("Invoice.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            List<TicketData> ticket = new ArrayList<>();
            ticket.add(data);
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("user", data.getUsername());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JRBeanCollectionDataSource(ticket));
            return JasperExportManager.exportReportToPdf(jasperPrint);
        }catch(IOException | JRException e){
            log.info("error has accour()", e.getMessage());            
            return null;
        }
    }

}