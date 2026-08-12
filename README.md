# Hospital Management System

A full-stack Hospital Management System designed to manage patients, doctors, appointments, authentication, and role-based access through a secure web application.

The project is being developed with a modular architecture so that additional hospital workflows and intelligent scheduling features can be integrated without restructuring the core application.

---

## Overview

The Hospital Management System provides a centralized platform for managing healthcare-related operations.

The system is designed around multiple user roles:

- **Admin** – manages system-level resources and users
- **Doctor** – accesses doctor-related information and operations
- **Receptionist** – supports administrative and appointment workflows
- **Patient** – manages patient-related information and appointments

Authentication and authorization are handled by the backend using JWT and Spring Security.

---

## Features

### Authentication & Security

- User registration and login
- BCrypt password hashing
- JWT-based authentication
- Stateless authentication
- Role-based authorization
- Protected REST APIs
- Role-specific API access
- Centralized exception handling
- Request validation

### User Roles

| Role | Purpose |
|------|---------|
| ADMIN | System administration and management |
| DOCTOR | Doctor-related operations |
| RECEPTIONIST | Reception and appointment workflows |
| PATIENT | Patient-related operations |

### Doctor Management

- Create doctor records
- Retrieve doctors
- Retrieve a doctor by ID
- Update doctor information
- Delete doctor records
- Role-based access control

### Patient Management

- Patient user accounts
- Patient profile management
- Medical profile information
- Profile retrieval and updates
- Role-based access control

### Appointment Management

- Appointment creation
- Appointment retrieval
- Appointment updates
- Appointment deletion
- Doctor and patient association

---

## Technology Stack

### Backend

- Java 17
- Spring Boot 3.5.x
- Spring Web
- Spring Data JPA
- Hibernate
- Spring Security
- JWT
- Bean Validation
- Maven

### Database

- MySQL

### Frontend

- React
- Vite
- JavaScript
- Tailwind CSS
- shadcn/ui
- Axios
- React Router

---

## System Architecture

```text
                    ┌─────────────────────┐
                    │    React Frontend   │
                    │   Vite + Tailwind   │
                    └──────────┬──────────┘
                               │
                         REST API / JWT
                               │
                               ▼
                    ┌─────────────────────┐
                    │   Spring Boot API   │
                    ├─────────────────────┤
                    │ Controllers         │
                    │ Services            │
                    │ Repositories        │
                    │ Spring Security     │
                    └──────────┬──────────┘
                               │
                           JPA / Hibernate
                               │
                               ▼
                    ┌─────────────────────┐
                    │       MySQL         │
                    └─────────────────────┘