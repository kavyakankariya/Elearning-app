package p1;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class CourseApp extends JFrame {

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;

    public CourseApp() {
        initComponents();
    }

    private void initComponents() {
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setTitle("Available Courses");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Java Basics");
        jButton1.addActionListener(evt -> new CourseContentForm("Java Basics").setVisible(true));

        jButton2.setText("Data Structures");
        jButton2.addActionListener(evt -> new CourseContentForm("Data Structures").setVisible(true));

        jButton3.setText("Web Development");
        jButton3.addActionListener(evt -> new CourseContentForm("Web Development").setVisible(true));

        jButton4.setText("Algorithms");
        jButton4.addActionListener(evt -> new CourseContentForm("Algorithms").setVisible(true));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                    .addComponent(jButton2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(12, 12, 12)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    static class CourseContentForm extends JFrame {
        public CourseContentForm(String courseName) {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception ex) {
                System.err.println("Nimbus look and feel not available");
            }

            setTitle(courseName + " - Course Content");
            setSize(600, 450);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            getContentPane().setBackground(new Color(245, 245, 255));

            JLabel titleLabel = new JLabel(courseName);
            titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
            titleLabel.setForeground(new Color(60, 60, 120));
            titleLabel.setBorder(BorderFactory.createEmptyBorder(14, 0, 0, 0));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel imageLabel = new JLabel(getCourseImage(courseName));
            imageLabel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2),
                    BorderFactory.createEmptyBorder(8, 8, 8, 8)));

            JPanel imageCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            imageCenterPanel.setOpaque(false);
            imageCenterPanel.add(imageLabel);
            imageCenterPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel topPanel = new JPanel();
            topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
            topPanel.setOpaque(false);
            topPanel.setBorder(BorderFactory.createEmptyBorder(18, 0, 0, 0));
            topPanel.add(titleLabel);
            topPanel.add(Box.createRigidArea(new Dimension(0, 8)));
            topPanel.add(imageCenterPanel);
            topPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            JTextArea contentArea = new JTextArea();
            contentArea.setEditable(false);
            contentArea.setLineWrap(true);
            contentArea.setWrapStyleWord(true);
            contentArea.setText(getCourseContent(courseName));
            contentArea.setFont(new Font("Verdana", Font.PLAIN, 16));
            contentArea.setBackground(new Color(255, 255, 255));
            contentArea.setForeground(new Color(60, 60, 120));
            contentArea.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(180, 180, 230), 1),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            JScrollPane scrollPane = new JScrollPane(contentArea);
            scrollPane.setPreferredSize(new Dimension(500, 140));
            scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.setOpaque(false);
            contentPanel.setBorder(BorderFactory.createEmptyBorder(12, 18, 14, 18));
            contentPanel.add(scrollPane, BorderLayout.CENTER);

            JButton backButton = new JButton("  Back to Courses");
            backButton.setFont(new Font("Arial", Font.BOLD, 15));
            backButton.setBackground(new Color(90, 160, 255));
            backButton.setForeground(Color.WHITE);
            backButton.setFocusPainted(false);
            backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            backButton.setBorder(BorderFactory.createEmptyBorder(6, 15, 6, 15));
            backButton.setToolTipText("Return to course list");

          backButton.addActionListener(e -> {
    new NewJFrame().setVisible(true);
    dispose();
});


            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            bottomPanel.setOpaque(false);
            bottomPanel.add(backButton);

            setLayout(new BorderLayout(0, 0));
            add(topPanel, BorderLayout.NORTH);
            add(contentPanel, BorderLayout.CENTER);
            add(bottomPanel, BorderLayout.SOUTH);
        }

        // PASTE THIS ENTIRE CODE BLOCK

private ImageIcon getCourseImage(String courseName) {
    String imagePath;
    switch (courseName) {
        case "Java Basics":
            imagePath = "/images/java.jpg";
            break;
        case "Data Structures":
            imagePath = "/images/ds.jpg";
            break;
        case "Web Development":
            imagePath = "/images/web.jpg";
            break;
        case "Algorithms":
            imagePath = "/images/algo.jpg";
            break;
        default:
            imagePath = "/images/default.jpg";
            break;
    }

    java.net.URL imgURL = getClass().getResource(imagePath);
    if (imgURL != null) {
        ImageIcon originalIcon = new ImageIcon(imgURL);
        Image img = originalIcon.getImage();
        Image resizedImg = img.getScaledInstance(520, 140, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    } else {
        System.err.println("Image not found: " + imagePath);
        return new ImageIcon(); // Return an empty icon if not found
    }
}

private String getCourseContent(String courseName) {
    String filename;
    // This switch correctly determines the file path
    switch (courseName) {
        case "Java Basics":
            // IMPORTANT: The path for resources should not include "src"
            filename = "/p1/courses/java basics.txt";
            break;
        case "Data Structures":
            filename = "/p1/courses/Data Structure.txt";
            break;
        case "Web Development":
            filename = "/p1/courses/Web Development.txt";
            break;
        case "Algorithms":
            filename = "/p1/courses/Algorithms.txt";
            break;
        default:
            filename = null;
            break;
    }

    if (filename == null) {
        return "Content not available for this course.";
    }

    // --- THIS IS THE NEW FILE-READING LOGIC ---
    StringBuilder contentBuilder = new StringBuilder();
    try (InputStream is = getClass().getResourceAsStream(filename);
         BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

        if (is == null) {
            // This happens if the file path is wrong or the file doesn't exist
            return "Error: Cannot find the course file: " + filename;
        }

        String line;
        while ((line = reader.readLine()) != null) {
            contentBuilder.append(line).append("\n"); // Append each line and a newline
        }
    } catch (IOException | NullPointerException e) {
        e.printStackTrace();
        return "Error reading content for " + courseName;
    }

    return contentBuilder.toString();
}

  /*      private String getCourseContent(String courseName) {
            return switch (courseName) {
               case "Java Basics" -> "courses/java basics.txt";
                case "Data Structures" -> "Data Structures course covers lists, stacks, queues, trees, and graphs for efficient data management.\nData Structures: Focuses on organizing and storing data efficiently. Covers arrays, linked lists, stacks, queues, trees, graphs, and hash tables. Teaches insertion, deletion, searching, and traversal techniques, along with analyzing time and space complexity. Essential for writing optimized code and solving complex programming challenges.";
                case "Web Development" -> "Web Development course explores HTML, CSS, JavaScript, and server-side programming basics.\nWeb Development: Teaches how to design and develop functional, interactive websites and web applications. Covers HTML, CSS, JavaScript for front-end, and optionally back-end technologies like Node.js, PHP, or databases. Explains responsive design, DOM manipulation, APIs, and client-server communication to build modern, user-friendly applications.";
                case "Algorithms" -> "Study various algorithms including sorting, searching, dynamic programming, and graph-based algorithms.\n Algorithms: Explores efficient methods for solving computational problems. Includes sorting, searching, recursion, dynamic programming, graph algorithms, and analyzing algorithmic complexity. Develops problem-solving skills, logical thinking, and optimization techniques crucial for software development, competitive programming, and technical interviews.";
                default -> "Content not available for this course.";
            };
        }
    }
*/
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CourseApp().setVisible(true));
    }
}
}
