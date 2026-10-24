# First-Java-CRUD

A simple Java student-management CRUD application built with Spring Boot and Vaadin.

## Requirements

- Java 25
- The included Maven Wrapper (no separate Maven installation required)

## Run locally

From the repository root, run:

```bash
./mvnw spring-boot:run
```

When Spring Boot has started, open [http://localhost:8080](http://localhost:8080).
Stop the application with `Ctrl+C`.

On Windows, use `mvnw.cmd spring-boot:run`.

## Use the application

The page displays a student table and a form for a student's name and email.

- **Add Student** validates the form and adds a row.
- Select a row, edit the form, and choose **Update Student** to change it.
- Select a row and choose **Delete Student** to remove it.
- Choose **Clear** to clear the form and selection.

Student records are held in memory, so they are reset when the application stops.

## Run tests

```bash
./mvnw test
```

On Windows, use `mvnw.cmd test`.

## Assignment evidence

See [doc/README.md](doc/README.md) for the project contribution statement and screenshots.
