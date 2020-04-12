Project Title:<br>
Project named 'Library Management' is used to handle all library operations with respect to user.
A user can view all the books from Library, issue a book and can return the book.
User has a 'My Borrowed' list, where he can see the books borrowed by him.

Getting Started:<br>
Prerequisites: 
You need to have Java installed on the system. Java version 8
or above is preferred.
Maven is required to build the project.<br>
Import: Import the project as a Java project in IntelliJ or Eclipse.
Set it as a Maven project.<br>

Architecture: <br>
An MVC architecture has been followed for the application. Where layers are separated for
model, view and controllers. User interacts with the Views, which internally interacts with the controller layer.
The controller layer fetches the data from Database only via service layer. There are two different service layers.
one interacts with controller layer and the other with Database.<br>
For the development, TDD approach has been followed. First the test cases has been created for the scenarios,
and then the actual code was written.<br>
Special focus was given to design principles while development. The different layers are losely coupled with
each other. Also the KISS and DRY principles are followed. The application is designed in very simple way and
avoiding any complex structure and also the code repetition is avoided.<br>

Database:<br>
H2 Database is used for the simplicity.<br>

Test cases:<br>
All the test cases are created in src/test/java folder. If you explicitly want to run all the test cases,
you can select java folder and run all test cases.<br>

Run: To run the application, First of all do the maven clean install to build the code. This will also run all 
the test cases.Then go to class 'LibraryManagementApplication.java' and run it as a Java Application.
Then on the Browser hit the URL 'http://localhost:8080/' as the application uses port 8080.
You will see the 'View Books' link, Click on it to see the books available in the Library.


