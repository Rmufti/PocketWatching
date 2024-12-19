
# PocketWatching

**PocketWatching** is a JavaFX-based budgeting application designed to simplify financial tracking, help users manage their budgets, and promote smarter spending habits. With a clean and modern UI, PocketWatching enables seamless sign-in, account creation, and intuitive financial visualization.

## 🚀 Features

- **Sign-In Page**: Secure access to your account with username and password.
- **Create Account**: Easy setup for new users.
- **Interactive UI**: Buttons with hover effects and user-friendly design.
- **Taglines and Slogans**: Engaging text to inspire users to take control of their finances.

### 🛠️ Future Features
- **Budget Visualization**: Interactive graphs to understand spending patterns.
- **Expense Tracking**: Categorization and analysis of your expenses.
- **Financial Insights**: Tips and recommendations for smarter money management.

---

## 🖥️ Tech Stack

- **Language**: Java 11+
- **Framework**: JavaFX
- **Build Tool**: Maven

---

## 📋 Prerequisites

Ensure you have the following installed:
- **Java**: Version 11 or higher
- **Apache Maven**: Version 3.6.0 or higher

---

## 🛠️ Setup Instructions

### Clone the Repository
```bash
git clone https://github.com/your-repo/pocketwatching.git
cd pocketwatching
```

### Build the Project
Run the following Maven command to build the project:

```bash
mvn clean install
```

### Run the Application
Launch the app using Maven:
    - Go to MainJavaFX.java. 

```bash
mvn exec:java -Dexec.mainClass="com.pocketwatching.app.HelloJavaFX"
```

### 📂 Project Structure
- `src/main/java`: Contains all Java source files, including scenes for Sign-In, Create Account, and the main menu.
- `src/main/resources`: Stores resources like CSS and FXML files.
- `pom.xml`: Maven configuration file to manage dependencies.

### 📦 Dependencies
This project uses the following dependencies managed via Maven:

#### Core
JavaFX: For building the user interface.
```xml
<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-controls</artifactId>
    <version>17</version>
</dependency>
```

#### Testing (Optional)
JUnit: For unit testing.
```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.8.2</version>
    <scope>test</scope>
</dependency>
```

## 🤝 Contributing
We welcome contributions to PocketWatching! Here’s how you can get involved:

1. Fork the repository.
2. Create a feature branch.
3. Commit your changes and push to your branch.
4. Submit a pull request.

## 📜 License
This project is licensed under the MIT License. See the LICENSE file for details.

## Author
For questions, suggestions, or feedback, feel free to reach out:

- Author: Rameez Mufti


## 🛤️ Future Roadmap
- Add secure password hashing for user accounts.
- Introduce interactive budgeting graphs.
- Expand financial tools to include investment tracking.
- Integrate with external APIs for real-time updates.

Thank you for using PocketWatching!  
Manage your money smarter, starting today.
