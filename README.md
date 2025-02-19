# **Receipt Processor Application**

## **Description:**

The **Receipt Processor** application is designed to process receipts, extract relevant details (such as item names, quantities, prices, and total amounts), and store the data in a structured format. The application exposes API endpoints for processing receipts and fetching the associated loyalty points based on receipt data.

---

## **Technologies Used:**
- **Backend:** Java, Spring Boot
- **Logging:** SLF4J and Logback
- **Testing:** Mockito, JUnit
- **API Documentation:** Swagger (OpenAPI)

---

## **Setup Instructions**

### **1. Clone the Repository:**
```bash
git clone https://github.com/Shivakumar980/receipt-processor.git
```

### **2. Build the Docker Image:**
From the root of the project, run the following command to build the Docker image:
```bash
docker build --tag receipt-processor .
```
### **3. Run the Docker Container:**
Once the Docker image is built, run the following command to start the container:
```bash
docker run -p 8080:8080 receipt-processor
```
    
---

## **API Documentation**

Once the application is running, you can access the API endpoints and documentation at:

- **Swagger UI**:  
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
  

- **Swagger JSON Documentation**:  
   [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)  
   
---


## **Endpoints**

### **1. POST /receipts/process**
**Description:** Processes a receipt and saves the data.

### **2. GET /receipts/{id}/points**
**Description:** Retrieves the loyalty points associated with a specific receipt ID.

---

## **Logging:**

Logs are stored using **SLF4J** and **Logback** for better debugging and tracking of application events.

---

## **Tests:**

To run the tests, use the following Maven command in a ** new terminal**:
```bash
mvn test
```
