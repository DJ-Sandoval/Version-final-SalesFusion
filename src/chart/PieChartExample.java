
package chart;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.title.TextTitle;

public class PieChartExample {

    public PieChartExample(Map<String, Integer> data, JPanel panelGrafica) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart pieChart = ChartFactory.createPieChart3D(
                "Productos con Más Ventas",
                dataset,
                true,
                true,
                false
        );

        // Cambiar el fondo de la gráfica
        pieChart.setBackgroundPaint(new Color(32, 32, 32));  // Fondo de la gráfica
        pieChart.getPlot().setBackgroundPaint(new Color(50, 50, 50));  // Fondo del área del gráfico

        // Cambiar el color del título
        TextTitle title = pieChart.getTitle();
        title.setPaint(new Color(255, 255, 255));  // Color del título
        title.setFont(new Font("SansSerif", Font.BOLD, 18));  // Fuente del título

        PiePlot3D plot = (PiePlot3D) pieChart.getPlot();
        plot.setDepthFactor(0.2);  // Factor de profundidad para el efecto 3D
        plot.setBackgroundPaint(new Color(50, 50, 50));  // Fondo del área del gráfico 3D

        // Definir colores para las secciones del gráfico
        Color[] colors = {
            new Color(254, 255, 84),
            new Color(85, 255, 86),
            new Color(255, 85, 86),
            new Color(82, 86, 255),
            new Color(11, 96, 176),
            new Color(64, 165, 120),
            new Color(28, 43, 45)
        };

        int i = 0;
        for (Object keyObj : dataset.getKeys()) {
            Comparable key = (Comparable) keyObj;
            plot.setSectionPaint(key, colors[i % colors.length]);
            i++;
        }

        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(400, 300));

        panelGrafica.setLayout(new BorderLayout());
        panelGrafica.add(chartPanel, BorderLayout.CENTER);
        panelGrafica.validate();
    }
}
