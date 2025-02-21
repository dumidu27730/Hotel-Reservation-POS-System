package edu.icet.controller.report;

import edu.icet.db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;

public class ReportFormController {

    @FXML
    void btnBookingReportOnAction(ActionEvent event) {


    }

    @FXML
    void btnEmployeeReportOnAction(ActionEvent event) {
       // src/main/resources/business_reports/employee_A4.jrxml
        try {
            JasperDesign design = JRXmlLoader.load("");
            JasperReport jasperReport = JasperCompileManager.compileReport(design);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint,"employee.pdf");

            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnGuestReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnRoomReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnUserReportOnAction(ActionEvent event) {

    }

}
