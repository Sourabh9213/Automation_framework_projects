# 🧪 Selenium Automation Framework - bstackdemo.com

## 📌 Project Overview
This project is a robust Selenium Automation Framework developed using Java, TestNG, and Maven to automate testing of an e-commerce web application.

🔗 Application Under Test: https://bstackdemo.com/

The framework is designed using the Page Object Model (POM) to ensure scalability, maintainability, and reusability.

---

## 🧠 Objective
- Automate core e-commerce functionalities
- Build a reusable and scalable automation framework
- Validate application behavior across multiple scenarios

---

## 🛠️ Tech Stack
- Java  
- Selenium WebDriver  
- TestNG  
- Maven  
- Extent Reports  
- Git & GitHub  

---

## 🏗️ Framework Architecture

- BaseTest class for setup and teardown  
- Page Object Model (LoginPage, ProductPage, CartPage, CheckoutPage)  
- ConfigReader for configuration management  
- WebDriverFactory for browser handling  
- WaitUtil for explicit waits  
- ScreenshotUtil for capturing failures  
- TestNG Listeners for reporting  

---

## 📂 Project Structure
- base → common setup  
- pages → page classes  
- tests → test cases  
- utils → reusable utilities  
- reports → execution reports  

---

## 🔄 Test Scenarios Covered

### 🔹 Login Module
- Valid login  
- Invalid login (handled limitation case)  

### 🔹 Product Module
- Add single item  
- Add multiple items  
- Search functionality  

### 🔹 Cart Module
- Verify cart items  
- Remove item  
- Cart validation  

### 🔹 Checkout Module
- Valid checkout  
- Checkout without items  

---

## 📊 Features
- Page Object Model (POM) design  
- Data-driven configuration  
- Reusable and modular code  
- Screenshot capture on failure  
- Extent Reports with execution details  
- Multi-browser support  

---

## ▶️ How to Run

1. Clone the repository  
2. Open in Eclipse / IntelliJ  
3. Update config.properties if required  
4. Run TestNG XML file  
5. View reports in `/reports` folder  

---

## 📈 Sample Execution Result
- Total Tests: 15  
- Passed: 15  
- Failed: 0  

---

## 🧠 Key Learning
- Framework design principles  
- TestNG execution and grouping  
- Selenium synchronization handling  
- Reporting and debugging  

---

## 👨‍💻 Author
Sourabh 

---

## 🤖 AI Assistance
AI tools were used for improving documentation and structuring the framework.  
The implementation, logic, and test scenarios were developed independently.