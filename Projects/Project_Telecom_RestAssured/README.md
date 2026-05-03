# 🔌 REST Assured API Automation - Telecom Project

## 📌 Project Overview
This project is an API automation framework built using REST Assured, Java, and TestNG.  
It performs end-to-end testing of a Contact Management system, covering user creation, authentication, and contact operations.

Base URL:
https://thinking-tester-contact-list.herokuapp.com

---

## 🧠 Objective
- Automate REST API testing using REST Assured  
- Validate complete user and contact lifecycle  
- Ensure API reliability with status and data validation  

---

## 🛠️ Tech Stack
- Java  
- REST Assured  
- TestNG  
- Maven  

---

## 🏗️ Framework Design

- BaseTest for common setup (Base URI)
- POJO classes for request payload (User & Contact)
- TokenManager for handling authentication token
- Utility class for dynamic email generation

---

## 🔄 Test Flow (End-to-End)

1. Add new user  
2. Generate authentication token  
3. Get user profile  
4. Update user details  
5. Login with updated user  
6. Add new contact  
7. Get contact list  
8. Get specific contact  
9. Update contact (PUT)  
10. Update contact (PATCH)  
11. Logout user  

---

## ✅ Features

- Dynamic email generation for unique test data  
- Token-based authentication handling  
- Reusable request setup  
- POJO-based request body creation  
- Sequential test execution using TestNG dependencies  

---

## ▶️ How to Run

1. Clone the repository  
2. Open project in Eclipse / IntelliJ  
3. Run TestNG test file  
4. View execution logs in console  

---

## 📊 Validations Performed

- Status code validation (200, 201)  
- Response body validation  
- Data integrity checks  
- Token validation  

---

## 🧠 Note
This project demonstrates real-world API automation with authentication and CRUD operations.

---

## 👨‍💻 Author
Sourabh Matade

## 🤖 AI Assistance
AI tools were used for guidance in understanding concepts, improving code structure, and enhancing documentation.  
The implementation, logic, and test scenarios were developed and executed independently.