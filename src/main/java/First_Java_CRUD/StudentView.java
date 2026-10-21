package First_Java_CRUD;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("")
public class StudentView extends VerticalLayout {


private final List<Student> students = new ArrayList<>();
private final Grid<Student> grid = new Grid<>(Student.class, false);

private final TextField nameField = new TextField("Student Name");
private final EmailField emailField = new EmailField("Email");

private Student selectedStudent;
private long nextId = 1;

public StudentView() {
    add(new H1("Student Management System"));

    grid.addColumn(Student::getId).setHeader("ID");
    grid.addColumn(Student::getName).setHeader("Name");
    grid.addColumn(Student::getEmail).setHeader("Email");

    grid.setWidthFull();

    Button addButton = new Button("Add Student", event -> addStudent());
    Button updateButton = new Button("Update Student", event -> updateStudent());
    Button deleteButton = new Button("Delete Student", event -> deleteStudent());
    Button clearButton = new Button("Clear", event -> clearForm());

    grid.asSingleSelect().addValueChangeListener(event -> {
        selectedStudent = event.getValue();

        if (selectedStudent != null) {
            nameField.setValue(selectedStudent.getName());
            emailField.setValue(selectedStudent.getEmail());
        }
    });

    add(
        new HorizontalLayout(nameField, emailField),
        new HorizontalLayout(addButton, updateButton, deleteButton, clearButton),
        grid
    );

    refreshGrid();
}

private void addStudent() {
    if (nameField.isEmpty() || emailField.isEmpty()
            || emailField.isInvalid()) {
        Notification.show("Enter a name and a valid email address.");
        return;
    }

    Student student = new Student(
        nextId++,
        nameField.getValue().trim(),
        emailField.getValue().trim()
    );

    students.add(student);
    refreshGrid();
    clearForm();

    Notification.show("Student added successfully!");
}

private void updateStudent() {
    if (selectedStudent == null) {
        Notification.show("Select a student to update.");
        return;
    }

    if (nameField.isEmpty() || emailField.isEmpty()
            || emailField.isInvalid()) {
        Notification.show("Enter a name and a valid email address.");
        return;
    }

    selectedStudent.setName(nameField.getValue().trim());
    selectedStudent.setEmail(emailField.getValue().trim());

    refreshGrid();
    clearForm();

    Notification.show("Student updated successfully!");
}

private void deleteStudent() {
    if (selectedStudent == null) {
        Notification.show("Select a student to delete.");
        return;
    }

    students.remove(selectedStudent);
    refreshGrid();
    clearForm();

    Notification.show("Student deleted successfully!");
}

private void refreshGrid() {
    grid.setItems(students);
}

private void clearForm() {
    selectedStudent = null;
    nameField.clear();
    emailField.clear();
    grid.deselectAll();
}

}
