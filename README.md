#Receipt Processor Application

Description:

The Receipt Processor application is designed to process receipts, extract relevant details (such as item names, quantities, prices, and total amounts), and store the data in a structured format. The application exposes API endpoints for processing receipts and fetching the associated loyalty points based on receipt data.

Features:

API Endpoints: Exposes endpoints to process receipts and retrieve loyalty points associated with a receipt.
Data Validation: Ensures that extracted receipt data is structured and validated before saving.
Logging: Logs various application events for easier debugging and tracking.


Technologies Used:

Backend: Java , Spring Boot
Logging: SLF4J and Logback
Testing : Mockito, Junit
API documentation : Swagger (OpenAPI)

Setup Instructions
Prerequisites
JDK 17 or higher
Maven (for Spring Boot project)
Docker (if using Docker to run the application)

Running the Application
Clone the repository:

git clone https://github.com/your-username/receipt-processor.git

cd receipt-processor
