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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.binar.SpringJPA.dto.TicketData;
import org.binar.SpringJPA.services.InvoiceService;
import org.jfree.util.Log;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    public byte[] generateFile(TicketData data){
        Log.info("Creating a ticket");
        try{
            File file = ResourceUtils.getFile("classpath:Invoice.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            List<Object> state = new ArrayList<>();
            state.add(data);
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("data", "myData");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JRBeanCollectionDataSource(state));
            Log.info("Ticket has been created");
            return JasperExportManager.exportReportToPdf(jasperPrint);
        }catch(IOException | JRException e){    
            Log.error("Error detected",e);      
            return null;
        }
    }

}