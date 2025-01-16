package LetterAnim;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class AnimationPanel extends JPanel {
    private List<Point2D> letterZ;
    private List<Point2D> letterL;

    private double angle = 0;
    private double scale = 1;
    private boolean growing = true;
    private int dx = 2, dy = 2;
    private int offsetX = 100, offsetY = 100;

    private Color color = Color.RED;

    public AnimationPanel() {
        letterZ = loadPoints("Z.txt");
        letterL = loadPoints("L.txt");

        setBackground(Color.BLACK);
    }

    private List<Point2D> loadPoints(String fileName) {
        List<Point2D> points = new ArrayList<>();

        String filePath = "C:\\Users\\omerz\\eclipse-workspace\\LetterAnim\\src\\LetterAnim\\" + fileName;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] coords = line.split(",");
                double x = Double.parseDouble(coords[0]);
                double y = Double.parseDouble(coords[1]);
                points.add(new Point2D.Double(x, y));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing coordinates in file: " + e.getMessage());
        }

        return points;
    }



    public void startAnimation() {
        Timer timer = new Timer(30, e -> {
            angle += 2;
            if (angle >= 360) angle = 0;

            if (growing) {
                scale += 0.02;
                if (scale >= 1.5) growing = false;
            } else {
                scale -= 0.02;
                if (scale <= 0.5) growing = true;
            }

            offsetX += dx;
            offsetY += dy;
            if (offsetX <= 0 || offsetX >= getWidth() - 200) dx = -dx;
            if (offsetY <= 0 || offsetY >= getHeight() - 200) dy = -dy;

            color = new Color((color.getRed() + 5) % 256, (color.getGreen() + 10) % 256, (color.getBlue() + 15) % 256);

            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //recommended for animating sharp lines, enables smooth transition.

        g2.setColor(color);
        drawPolygon(g2, letterZ, offsetX, offsetY);

        AffineTransform transform = new AffineTransform();
        transform.translate(offsetX + 300, offsetY + 100);
        transform.scale(scale, scale);
        transform.rotate(Math.toRadians(angle), 50, 50);
        g2.setTransform(transform);
        drawPolygon(g2, letterL, 0, 0);

        g2.setTransform(new AffineTransform());
    }

    private void drawPolygon(Graphics2D g2, List<Point2D> points, int offsetX, int offsetY) {
        if (points.isEmpty()) return;

        g2.setStroke(new BasicStroke(5));

        Path2D path = new Path2D.Double();
        path.moveTo(points.get(0).getX() + offsetX, points.get(0).getY() + offsetY);

        for (int i = 1; i < points.size(); i++) {
            path.lineTo(points.get(i).getX() + offsetX, points.get(i).getY() + offsetY);
        }

        g2.draw(path);
    }

}
