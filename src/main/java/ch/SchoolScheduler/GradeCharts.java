/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.SchoolScheduler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author Jan
 */
public class GradeCharts {

    @Inject
    private Controller c;

    private LineChartModel durchschnitt;

    public LineChartModel getDurchschnitt() {
        return durchschnitt;
    }

    public void setDurchschnitt(LineChartModel durchschnitt) {
        this.durchschnitt = durchschnitt;
    }

    public GradeCharts() throws ParseException {
        durchschnitt = initLinearModel();
        durchschnitt.setTitle("Notendurchschnitt");
        durchschnitt.setLegendPosition("e");
        Axis yAxis = durchschnitt.getAxis(AxisType.Y);
        yAxis.setMin(1);
        yAxis.setMax(6);
        durchschnitt.setExtender("Chart");
    }

    private LineChartModel initLinearModel() throws ParseException {
        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Notendurchschnitt");

        series1.set("August", getAverage(6));
        series1.set("September", getAverage(7));
        series1.set("Oktober", getAverage(8));
        series1.set("November", getAverage(9));
        series1.set("Dezember", getAverage(10));
        series1.set("Januar", getAverage(11));
        series1.set("Februar", getAverage(12));
        series1.set("März", getAverage(1));
        series1.set("April", getAverage(2));
        series1.set("Mai", getAverage(3));
        series1.set("Juni", getAverage(4));
        series1.set("Juli", getAverage(5));

        model.addSeries(series1);

        return model;
    }

    private float getAverage(int Monat) throws ParseException {
        int yearUntergrenze;

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int yearNow = cal.get(Calendar.YEAR);
        yearUntergrenze = yearNow;

        String m = Monat + "";
        if (m.length() < 2) {
            m = "0" + m;
        }
        if (Monat < 6) {
            yearUntergrenze -= 1;
        }

        ResultSet rs;
        Connection con = c.getConnection();
        PreparedStatement ps;
        int currentlyLoggedIn = c.getCurrentlyLoggedIn();

        float noten = 0;
        float gesamt = 0;
        
        try {
            ps = con.prepareStatement("SELECT note FROM pruefungen WHERE Benutzer_id=? AND datum<? AND datum>? AND note>0");
            ps.setInt(1, currentlyLoggedIn);

            String sdate = yearNow + "-" + m + "-01";
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            date = df.parse(sdate);

            String sdate2 = yearUntergrenze + "-" + m + "-01";
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = df2.parse(sdate);

            ps.setDate(2, new java.sql.Date(date.getTime()));
            ps.setDate(3, new java.sql.Date(date2.getTime()));
            rs = ps.executeQuery();

            while (rs.next()) {
                noten += 1;
                gesamt += rs.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return noten / gesamt;
    }
}
