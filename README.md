# InstaStay – Android Hotel Booking Application

InstaStay is an Android-based hotel booking application built using Java and SQLite.  
The application allows users to search hotels, view details, and make bookings while ensuring data consistency through validation logic.

---

## 🚀 Features

- User authentication with login functionality  
- Search and explore hotels with detailed information  
- Booking system with structured data storage  
- Booking details view for users  
- Direct hotel contact via phone integration  
- Payment redirection to external payment applications  
- SMS integration for communication  

---

## 🧠 Core Logic

### Booking Validation
Implemented logic to prevent **double booking of rooms** by validating:
- Room type  
- Hotel name  
- Date range (check-in and check-out overlap detection)  

This ensures that the same room cannot be booked for overlapping time periods, maintaining data consistency and reliability.

---

## 🏗️ Application Flow

1. User Login  
2. Home Screen  
3. Search Hotels  
4. View Hotel Information  
5. Book Room  
6. View Booking Details  
7. Payment Process  
8. Getaway / Confirmation  

---

## 🛠️ Tech Stack

- **Programming Language:** Java  
- **Frontend:** Android XML Layouts  
- **Database:** SQLite (multiple tables for structured data handling)  
- **Platform:** Android SDK  

---

## 🗄️ Database Design

- Used **multiple tables** to manage:
  - User data  
  - Hotel details  
  - Booking information  

- Implemented data insertion and retrieval operations for efficient handling of bookings and user records  

---

## 🔗 Integrations

- **Phone Dialer Integration:**  
  Enables users to directly contact hotels from within the app  

- **Payment Integration:**  
  Redirects users to external payment applications for transactions  

- **SMS Integration:**  
  Supports sending messages for communication or booking-related actions  

---

## ⚙️ Key Highlights

- Designed real-world booking workflows with validation logic  
- Handled edge cases such as invalid inputs and booking conflicts  
- Focused on reliable data storage and retrieval using SQLite  
- Built an end-to-end application with multiple screens and integrations  

---

## 📌 Note

This is a student project developed to demonstrate application development, database management, and problem-solving in real-world scenarios.
