package com.pocketwatching.app;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class PieChart extends Transactions{

    public PieChart(String user, String pass){
        super(user,pass);
    }

    public static DefaultPieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        getInformation();

        HashMap<String, Double> list = Transactions.getList();
        for (Map.Entry<String, Double> entry : list.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }
        return dataset;

    }

    public static JFreeChart createPieChart(PieDataset dataset) {
        JFreeChart piechart = ChartFactory.createPieChart(
                "Expenses", // chart title
                dataset, // data
                true, // include legend
                true,
                false);

        return piechart;
    }

    public void DisplayPieChart(JFreeChart pie) {

        JFrame frame = new JFrame("Pie Graph Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Add chart to panel and display
        ChartPanel chartPanel = new ChartPanel(pie);
        frame.add(chartPanel);
        frame.setVisible(true);
    }
// mvn exec:java -Dexec.mainClass="com.pocketwatching.app.PieChart"
    public static void main(String[] args) {
        PieChart example = new PieChart("rameez", "Pak1$tan");


        DefaultPieDataset dataset1 = example.createDataset();

        JFreeChart Piechart = example.createPieChart(dataset1);
        example.DisplayPieChart(Piechart);
    }
    
}
