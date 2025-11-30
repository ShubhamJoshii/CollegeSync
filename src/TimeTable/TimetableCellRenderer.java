package TimeTable;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

public class TimetableCellRenderer extends DefaultTableCellRenderer {

    private Map<String, Color> subjectColorMap;
    
    // State variables for the custom painting
    private String textToDraw = "";
    private int mergeStatus = 0; // 0=None, 1=Left Side of Merge, 2=Right Side of Merge
    private Color customForeground = Color.BLACK;

    public TimetableCellRenderer(Map<String, Color> map) {
        this.subjectColorMap = map;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        
        String cellValue = (value == null) ? "" : value.toString();
        this.textToDraw = cellValue;
        this.mergeStatus = 0; // Reset
        this.customForeground = Color.BLACK; // Default

        // --- 1. DETECT MERGES ---
        String prevVal = "";
        if (col > 0) {
            Object p = table.getValueAt(row, col - 1);
            prevVal = (p != null) ? p.toString() : "";
        }
        
        String nextVal = "";
        if (col < table.getColumnCount() - 1) {
            Object n = table.getValueAt(row, col + 1);
            nextVal = (n != null) ? n.toString() : "";
        }

        boolean sameAsPrev = cellValue.equals(prevVal) && !cellValue.isEmpty();
        boolean sameAsNext = cellValue.equals(nextVal) && !cellValue.isEmpty();

        // --- 2. SETUP TEXT & COLORS ---
        
        // A. HOLIDAY ROW (Keep this simple logic)
        if (cellValue.contains("H O L I D A Y")) {
            c.setBackground(new Color(255, 200, 200));
            c.setForeground(Color.BLACK);
            // Only show text in middle column (e.g. 5), hide in others
            if (col == 5) setText(cellValue);
            else setText("");
        }
        // B. STANDARD CELLS
        else {
            // Apply Colors
            c.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
            if (col == 0) {
                setHorizontalAlignment(CENTER);
                c.setBackground(new Color(253, 255, 206));
                c.setForeground(Color.BLACK);
            } else if (cellValue.contains("T E A") || cellValue.contains("L U N C H")) {
                c.setBackground(new Color(242, 242, 242));
                c.setForeground(Color.BLACK);
            } else if (cellValue.equalsIgnoreCase("Free")) {
                c.setBackground(new Color(192, 255, 192));
                c.setForeground(Color.BLACK);
            } else if (!cellValue.isEmpty()) {
                c.setBackground(Color.WHITE);
                // Subject Color Lookup
                String shortCode = cellValue.split(" ")[0].toUpperCase();
                if (subjectColorMap != null && subjectColorMap.containsKey(shortCode)) {
                    this.customForeground = subjectColorMap.get(shortCode);
                } else {
                    this.customForeground = Color.DARK_GRAY;
                }
                c.setForeground(this.customForeground);
            } else {
                 c.setBackground(Color.WHITE);
            }

            // --- 3. MERGE LOGIC FOR LABS (Split Painting) ---
            if (sameAsNext && !sameAsPrev) {
                // Left side of a merge
                this.mergeStatus = 1;
                setText(""); // Hide default text, we will paint it manually
            } else if (sameAsPrev && !sameAsNext) {
                // Right side of a merge
                this.mergeStatus = 2;
                setText(""); // Hide default text, we will paint it manually
            } else if (sameAsPrev && sameAsNext) {
                // Middle of a big merge (rare for labs, but treat as empty)
                setText("");
            } else {
                // Normal Cell
                setText(cellValue); 
            }
        }

        // --- 4. BORDERS ---
        // Hide right border if next cell is the same
        Color gridColor = Color.LIGHT_GRAY;
        int rightBorder = (sameAsNext) ? 0 : 1;
        Border border = BorderFactory.createMatteBorder(0, 0, 1, rightBorder, gridColor);
        c.setBorder(border);
        
        // Handle Selection
        if (isSelected) {
            c.setBackground(table.getSelectionBackground());
            c.setForeground(table.getSelectionForeground());
            this.customForeground = table.getSelectionForeground();
        }

        return c;
    }

    // --- 5. CUSTOM PAINTING (The Magic Part) ---
    @Override
    protected void paintComponent(Graphics g) {
        // 1. Paint Background & Borders (Standard JLabel stuff)
        super.paintComponent(g);

        // 2. If we are merging, paint the text manually at the border
        if (mergeStatus > 0 && !textToDraw.isEmpty()) {
            Graphics2D g2 = (Graphics2D) g;
            
            // Turn on Text Antialiasing for smooth text
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            
            g2.setColor(this.customForeground);
            g2.setFont(getFont());
            
            FontMetrics fm = g2.getFontMetrics();
            int strWidth = fm.stringWidth(textToDraw);
            
            // Calculate Y to center vertically
            // (Height - FontHeight) / 2 + Ascent gives the baseline
            int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();

            int x = 0;
            
            if (mergeStatus == 1) { 
                // LEFT CELL: Draw text so the center is at the right edge
                // Formula: CellWidth - (Half Text Width)
                x = getWidth() - (strWidth / 2);
            } else if (mergeStatus == 2) {
                // RIGHT CELL: Draw text so the center is at the left edge
                // Formula: 0 - (Half Text Width)
                x = 0 - (strWidth / 2);
            }
            
            g2.drawString(textToDraw, x, y);
        }else{
            setHorizontalAlignment(CENTER);
        }
    }
}