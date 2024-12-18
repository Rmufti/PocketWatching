package com.pocketwatching.app;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class barGraph extends Transactions{

    public barGraph(String user, String pass){
        super(user, pass);
    }

    // Method to create a sample dataset
    public DefaultCategoryDataset createBarGraphDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Example data
        getInformation();
        HashMap<String, Double> data = Transactions.getList();

        // Populate dataset
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            dataset.addValue(entry.getValue(), "Expense", entry.getKey()); // RowKey: "Expense", ColumnKey: Category
        }

        return dataset;
    }

    // Method to create a bar chart
    private JFreeChart createBarGraph(DefaultCategoryDataset dataset) {
        // Create the chart with a vertical orientation
        JFreeChart chart = ChartFactory.createBarChart(
                "Monthly Expenses", // chart title
                "Month", // x-axis label
                "Income (Monthly)", // y-axis label
                dataset, // dataset
                PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips
                false // URLs
        );

        // Get the plot of the chart
        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        // Create a custom Y-axis with a specific range and tick interval
        ValueAxis yAxis = plot.getRangeAxis();

        // Set the minimum and maximum values for the Y-axis
        yAxis.setRange(0, Transactions.getIncome()); // The range from 0 to 250 (adjust as needed)

        // Set the tick mark interval for the Y-axis (e.g., every 50 units)
        if (yAxis instanceof NumberAxis) {
            NumberAxis numberAxis = (NumberAxis) yAxis;
            numberAxis.setTickUnit(new org.jfree.chart.axis.NumberTickUnit(50)); // Tick marks every 50
        }

        return chart;
    }

    // Method to display the chart in a JFrame
    public void displayBarGraph(JFreeChart chart) {
        JFrame frame = new JFrame("Bar Chart Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Add chart to panel and display
        ChartPanel chartPanel = new ChartPanel(chart);
        frame.add(chartPanel);
        frame.setVisible(true);
    }

    // mvn exec:java -Dexec.mainClass="com.pocketwatching.app.barGraph"
    public static void main(String[] args) {
        barGraph example = new barGraph("rameez", "Pak1$tan");

        
        //This is for BarGraph (Expenses)
        // Create the dataset
       DefaultCategoryDataset dataset = example.createBarGraphDataset();
        System.out.println(dataset.toString());
        // Create the chart
       JFreeChart chart = example.createBarGraph(dataset);
       // Display the chart
       example.displayBarGraph(chart);
    }

    
}
