PocketWatching

PocketWatching is a JavaFX-based budgeting application that simplifies financial tracking, helps users manage their budgets, and promotes smarter spending habits. With a clean and modern UI, PocketWatching makes it easy to sign in, create accounts, and visualize your personal finances.

Features

Sign-In Page: Secure access to your account with username and password.

Create Account: Easy setup for new users.

Interactive UI: Buttons with hover effects and user-friendly design.

Taglines and Slogans: Engaging text that inspires users to take control of their finances.

Future Features:

Budget visualization with graphs.

Expense tracking and categorization.

Financial tips and insights.

Tech Stack

Language: Java 11+

Framework: JavaFX

Build Tool: Maven

Prerequisites

Ensure you have the following installed:

Java 11 or higher

Apache Maven 3.6.0 or higher

Setup Instructions

Clone the Repository

git clone https://github.com/your-repo/pocketwatching.git
cd pocketwatching

Build the Project
Run the following Maven command to build the project:

mvn clean install

Run the Application
Launch the app using Maven:

mvn javafx:run

Project Structure

src/main/java: Contains all Java source files, including scenes for Sign-In, Create Account, and the main menu.

src/main/resources: Stores resources like CSS and FXML files.

pom.xml: Maven configuration file to manage dependencies.

Dependencies

This project uses the following dependencies managed via Maven:

JavaFX: For building the user interface.

<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-controls</artifactId>
    <version>17</version>
</dependency>

JUnit (optional): For testing purposes.

<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.8.2</version>
    <scope>test</scope>
</dependency>

We welcome contributions to PocketWatching! Here’s how you can get involved:

Fork the repository.

Create a feature branch.

Commit your changes and push to your branch.

Submit a pull request.

License

This project is licensed under the MIT License. See the LICENSE file for details.

Contact

For questions, suggestions, or feedback, feel free to reach out to:

Author: Rameez Mufti

Email: rameez@example.com

LinkedIn: Rameez Mufti

Future Roadmap

Add secure password hashing for user accounts.

Introduce interactive budgeting graphs.

Expand financial tools to include investment tracking.

Integrate with external APIs for real-time updates.

Thank you for using PocketWatching! Manage your money smarter, starting today.
