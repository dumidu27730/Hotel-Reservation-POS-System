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
        try {
            JasperDesign design = JRXmlLoader.load("src/main/resources/report/customer_daily_report_A4.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(design);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint,"booking.pdf");

            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnEmployeeReportOnAction(ActionEvent event) {

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
