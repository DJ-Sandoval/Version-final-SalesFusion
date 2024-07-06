/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

/**
 *
 * @author Jose
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Paginador extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton prevButton, nextButton;
    private JLabel pageLabel;
    private int currentPage = 1;
    private int rowsPerPage = 10;
    private int totalRows;

    public Paginador() {
        setTitle("Paginador de Tabla");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear el modelo de tabla
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Cantidad"}, 0);
        table = new JTable(tableModel);

        // Crear los botones de paginación
        JPanel paginationPanel = new JPanel();
        prevButton = new JButton("Anterior");
        nextButton = new JButton("Siguiente");
        pageLabel = new JLabel("Página 1");

        paginationPanel.add(prevButton);
        paginationPanel.add(pageLabel);
        paginationPanel.add(nextButton);

        // Agregar acción a los botones
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage > 1) {
                    currentPage--;
                    updateTable();
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage < getTotalPages()) {
                    currentPage++;
                    updateTable();
                }
            }
        });

        // Agregar componentes al frame
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(paginationPanel, BorderLayout.SOUTH);

        // Cargar datos de ejemplo
        loadData();
        updateTable();
    }

    private void loadData() {
        // Aquí cargarías los datos de tu base de datos en lugar de este ejemplo estático
        for (int i = 1; i <= 100; i++) {
            tableModel.addRow(new Object[]{i, "Producto " + i, i * 10});
        }
        totalRows = tableModel.getRowCount();
    }

    private void updateTable() {
        tableModel.setRowCount(0); // Limpiar la tabla
        int start = (currentPage - 1) * rowsPerPage;
        int end = Math.min(start + rowsPerPage, totalRows);
        
        for (int i = start; i < end; i++) {
            tableModel.addRow(new Object[]{i + 1, "Producto " + (i + 1), (i + 1) * 10});
        }

        pageLabel.setText("Página " + currentPage);
    }

    private int getTotalPages() {
        return (int) Math.ceil((double) totalRows / rowsPerPage);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Paginador().setVisible(true);
            }
        });
    }
}

