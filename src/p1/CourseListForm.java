package p1;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CourseListForm extends JFrame {

    private JList<String> courseList;
    private final DefaultListModel<String> listModel;

    public CourseListForm() {
        setTitle("Course List");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the DefaultListModel and add courses
        listModel = new DefaultListModel<>();
        listModel.addElement("Java Basics");
        listModel.addElement("Data Structures");
        listModel.addElement("Web Development");
        listModel.addElement("Algorithms");

        // Create the JList with the model
        courseList = new JList<>(listModel);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Scroll pane for the course list
        JScrollPane scrollPane = new JScrollPane(courseList);

        // Button to open selected course
        JButton openCourseBtn = new JButton("Open Course");

        // Action on button click
        openCourseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCourse = courseList.getSelectedValue();
                if (selectedCourse != null) {
                    // For now, show selected course in a dialog
                    JOptionPane.showMessageDialog(CourseListForm.this,
                            "Opening course: " + selectedCourse);
                    // Could open Course Content Form here
                } else {
                    JOptionPane.showMessageDialog(CourseListForm.this,
                            "Please select a course from the list.", "No Course Selected",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Layout setup
        setLayout(new BorderLayout(10, 10));
        add(new JLabel("Available Courses:"), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(openCourseBtn, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CourseListForm().setVisible(true);
        });
    }
}
