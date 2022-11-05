package org.binar.bioskop.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.binar.bioskop.dto.TicketData;
import org.binar.bioskop.services.InvoiceService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService{

    public byte[] generateFile(TicketData data){
        log.info("Creating a ticket");
        try{
            File file = ResourceUtils.getFile("classpath:Invoice.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            List<Object> state = new ArrayList<>();
            state.add(data);
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("data", "myData");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JRBeanCollectionDataSource(state));
            log.info("Ticket has been created");
            return JasperExportManager.exportReportToPdf(jasperPrint);
        }catch(IOException | JRException e){    
            log.error("Error detected {}", e.getMessage());      
            return null;
        }
    }

}