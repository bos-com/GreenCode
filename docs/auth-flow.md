# 🔐 Authentication Flow

This document explains how authentication works in BOS and how frontend and backend systems interact using JWT/OAuth.

---

## 📌 Overview

BOS uses token-based authentication (JWT/OAuth) to secure API endpoints.  
Users must authenticate to receive a token, which is then used to access protected resources.

---

## 🔑 Login Flow

### Description
Users provide credentials to log in and receive an authentication token.

### Endpoint
POST /api/auth/login

### Request Example
- Method: POST  
- URL: /api/auth/login  
- Body:
  - email: user@example.com  
  - password: password123  

### Response Example
- Status: Success  
- Data:
  - token: abc123xyz  
  - user:
    - id: 1  
    - name: John Doe  

---

## 🎟️ Token Usage

### Description
After login, the client must include the token in all protected requests.

### How to Use Token
- Add token in request headers  
- Format:
  Authorization: Bearer <token>

### Example Request
- Method: GET  
- URL: /api/users  
- Header:
  Authorization: Bearer abc123xyz  

### Response Example
- Status: Success  
- Data: List of users  

---

## 🔄 Password Reset Process

### Step 1: Request Password Reset

**Endpoint:** POST /api/auth/forgot-password  

**Request Example:**
- email: user@example.com  

**Response Example:**
- Status: Success  
- Message: Password reset link sent  

---

### Step 2: Reset Password

**Endpoint:** POST /api/auth/reset-password  

**Request Example:**
- token: reset_token_here  
- new_password: newpassword123  

**Response Example:**
- Status: Success  
- Message: Password reset successful  

---

## 🔐 Authentication Notes

- Tokens may expire after a certain time  
- Expired tokens require re-login  
- Always keep tokens secure on the client side  

---

## 📌 Summary

- Login → Receive Token  
- Use Token → Access Protected APIs  
- Reset Password → If user forgets credentials  

This flow ensures secure communication between frontend and backend systems.