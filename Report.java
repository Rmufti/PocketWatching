package com.pocketwatching.app;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.Plot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.PieDataset;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.BasicStroke;
import java.util.HashMap;
import java.util.Map;

public class Report extends Transactions {

    public Report(String user, String pass) {
        super(user, pass);
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

        JFrame frame = new JFrame("Bar Chart Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);

        // Add chart to panel and display
        ChartPanel chartPanel = new ChartPanel(pie);
        frame.add(chartPanel);
        frame.setVisible(true);
    }


    public DefaultCategoryDataset createLineChartDataset() {
        // Create a dataset with two series: Net Savings and Saving Rate
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        getInformation();

        Double netSave = Transactions.getNetSavings();
        // Add a single data point for Net Savings
        dataset.addValue(netSave, "Net Savings", "Today");

        Double saveRate = Transactions.getSavingRatePercentage();
        // Add a single data point for Saving Rate
        dataset.addValue(saveRate, "Saving Rate", "Today");

        return dataset;
    }

    // Create the chart with the dataset
    public static JFreeChart createLineChart(DefaultCategoryDataset dataset) {
        // Create the line chart with the appropriate labels and dataset
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Net Savings & Saving Rate Today", // Title
                "Time", // X-Axis label
                "Amount ($) / Rate (%)", // Y-Axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Orientation (vertical)
                true, // Include legend
                true, // Enable tooltips
                false // Disable URLs
        );

        // Get the plot
        CategoryPlot plot = (CategoryPlot) lineChart.getPlot(); // Specific for category charts

        // Customize the plot (e.g., background color, gridlines)
        plot.setBackgroundPaint(Color.white); // Set background color
        plot.setDomainGridlinePaint(Color.gray); // Set gridline color for x-axis
        plot.setRangeGridlinePaint(Color.gray); // Set gridline color for y-axis

        // Customize the line series appearance (e.g., color, stroke)
        plot.getRenderer().setSeriesPaint(0, Color.BLUE); // Set first series (Net Savings) color to blue
        plot.getRenderer().setSeriesStroke(0, new BasicStroke(2.0f)); // Set stroke for the first series

        plot.getRenderer().setSeriesPaint(1, Color.RED); // Set second series (Saving Rate) color to red
        plot.getRenderer().setSeriesStroke(1, new BasicStroke(2.0f)); // Set stroke for the second series

        // Optionally, you can adjust the domain axis and range axis properties (e.g.,
        // ticks)
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLowerMargin(0.05); // Adjust margin on the left side
        domainAxis.setUpperMargin(0.05); // Adjust margin on the right side

        return lineChart;
    }

    public void displayLineChart(JFreeChart chart) {
        // Create a JFrame to display the chart
        JFrame frame = new JFrame("Line Chart Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Set the size of the frame

        // Add the chart to the frame using a ChartPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        frame.add(chartPanel);

        // Make the frame visible
        frame.setVisible(true);
    }

    // Method to create a sample dataset
    public static DefaultCategoryDataset createBarGraphDataset() {
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
    public static JFreeChart createBarGraph(DefaultCategoryDataset dataset) {
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

    public static void printDataset(DefaultCategoryDataset dataset) {
        System.out.println("Dataset contents:");
        for (int i = 0; i < dataset.getRowCount(); i++) {
            String rowKey = dataset.getRowKey(i).toString();
            for (int j = 0; j < dataset.getColumnCount(); j++) {
                String columnKey = dataset.getColumnKey(j).toString();
                Number value = dataset.getValue(i, j);
                System.out.println(rowKey + " (" + columnKey + "): " + value);
            }
        }
    }

    public void saveChartAsJPG(JFreeChart chart, String filePath, int width, int height) {
        File outputFile = new File(filePath);

        try {
            ChartUtils.saveChartAsJPEG(outputFile, chart, width, height);
            System.out.println("Chart saved as JPG at: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving chart as JPG: " + e.getMessage());
        }
    }
    // Main method
    // mvn exec:java -Dexec.mainClass="com.pocketwatching.app.Transactions"

    // mvn exec:java -Dexec.mainClass="com.pocketwatching.app.Report"
    public static void main(String[] args) {
        Report report = new Report("rameez", "Pak1$tan");
        /*
         * // Display Pie Chart
         * System.out.println("Generating Pie Chart...");
         * DefaultPieDataset pieDataset = Report.createDataset(); // Create the dataset
         * for pie chart
         * JFreeChart pieChart = Report.createPieChart(pieDataset); // Create the pie
         * chart
         * report.DisplayPieChart(pieChart); // Display the pie chart
         */

        // Display Line Chart
        System.out.println("Generating Line Chart...");
        DefaultCategoryDataset lineDataset = report.createLineChartDataset(); // Create the dataset for line chart
        JFreeChart lineChart = Report.createLineChart(lineDataset); // Create the line chart
        report.displayLineChart(lineChart); // Display the line chart

        /*
         * // Display Bar Chart
         * System.out.println("Generating Bar Chart...");
         * DefaultCategoryDataset barDataset = report.createBarGraphDataset(); // Create
         * the dataset for bar chart
         * JFreeChart barChart = report.createBarGraph(barDataset); // Create the bar
         * chart
         * report.displayBarGraph(barChart); // Display the bar chart
         * 
         * 
         */
    }
}
