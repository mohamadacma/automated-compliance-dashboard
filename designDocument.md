# Automated Compliance Dashboard

## 1. Project Title
**Title**: Automated Compliance Dashboard

***Subtitle***: A Scalable, Real-Time Platform for Managing Regulatory Compliance Across Multiple Frameworks

## 2. Description

### Problem Statement
Organizations need to comply with multiple regulatory frameworks (e.g., SOC 2, ISO 27001, GDPR), which requires constant monitoring, reporting, and risk management. Managing these tasks manually is complex, error-prone, and resource-intensive.

### Solution
The Automated Compliance Dashboard provides a centralized platform to monitor compliance status in real-time, manage compliance rules, generate reports, and receive alerts on potential risks. The solution integrates with cloud services to ensure scalability and reliability.

## 3. Problems to Resolve
- **Data Integration**: Integrating with various data sources to collect compliance-related data in real-time. Compliance data is often scattered across different systems, making it difficult to obtain a comprehensive view of compliance status. The dashboard will integrate with multiple data sources, such as internal logs, third-party APIs, and cloud services, to aggregate compliance data in real-time.
- **Real-Time Monitoring**: Implementing real-time monitoring and alerts for compliance status and potential risks. Traditional compliance checks are typically performed periodically, which can lead to delayed detection of non-compliance issues. This project will implement real-time monitoring using WebSocket and serverless functions to ensure that compliance status is always up-to-date.
- **Report Generation**: Enabling users to generate and download compliance reports for audits. Generating reports for audits is a time-consuming process, often involving manual data collection and formatting. The dashboard will automate report generation, pulling data from integrated sources and formatting it according to regulatory requirements.
- **Security and Access Control**: Ensuring that data is securely stored and accessible only by authorized users. Compliance data is highly sensitive, and unauthorized access could lead to severe consequences. The project will implement robust security measures, including data encryption, role-based access control, and secure API endpoints, to ensure that only authorized users can access or modify compliance data.

## 4. User Stories
- **U1**: As a compliance officer, I want to view a dashboard that displays the compliance status of my organization in real-time so that I can quickly identify and address potential issues.
- **U2**: As a compliance officer, I want to receive real-time alerts when compliance thresholds are breached.
- **U3**: As a compliance officer, I want to add, edit, and delete compliance rules to ensure our systems are aligned with the latest regulations.
- **U4**: As a compliance officer, I want to generate compliance reports for audits.
- **U5**: As a compliance officer, I want to monitor the compliance status of different business units separately to identify and address specific risks.

## 5. Project Scope

### In Scope
- Develop a real-time dashboard that displays compliance status across multiple frameworks.
- Integrate with AWS services (e.g., DynamoDB, S3, Lambda) for data storage and processing.
- Implement a REST API using Spring Boot for managing compliance rules and generating reports.

### Out of Scope
- Advanced analytics features (e.g., predictive risk assessment).
- Integration with external security systems (e.g., firewalls, SIEMs).
- Multi-tenancy features for supporting multiple organizations within the same instance.

## 6. Proposed Architectural Overview
- **Spring Boot**: Core application, handling API requests, rule management, and generating compliance reports.
- **AWS Lambda**: Used for specific tasks like real-time monitoring and alerting.
- **DynamoDB**: Storage for compliance rules and status data.
- **API Gateway**: Exposes the Lambda functions as RESTful endpoints.
- **WebSocket**: Enables real-time updates to the dashboard.

## 7. API
## API Endpoints
- **GET /api/compliance-status**: Fetches the current compliance status across all frameworks.
- **POST /api/rules**: Adds a new compliance rule.
- 
- ### Add Compliance Rule Endpoint

* Accepts a POST request to /api/rules
* Accepts data to create a new compliance rule with the following information:

ruleName (String): Name of the compliance rule
description (String): Detailed description of what the rule checks for
framework (String): The compliance framework this rule belongs to (e.g., SOC 2, GDPR)
threshold (int): The threshold value that triggers a compliance action
action (String): The action to be taken when the rule is breached

##### UML sequence diagram representation:
![img_6.png](src/main/java/resources/img_6.png)

Returns the newly created compliance rule with a unique ruleId assigned by the Compliance Dashboard service.
- **PUT /api/rules/{id}**: Updates an existing compliance rule.
- **DELETE /api/rules/{id}**: Deletes a compliance rule.
- **GET /api/reports**: Generates and downloads a compliance report.

### Update Compliance Rule Endpoint

* **Method**: PUT
* **Path**: `/api/rules/{ruleId}`
* **Description**: Updates an existing compliance rule
* **Request Body**:
  ```json
  {
    "ruleName": "string",
    "description": "string",
    "framework": "string",
    "threshold": int,
    "action": "string"
  }

* Response: Returns the updated compliance rule information
* Error Handling: If ruleId not found: Throw RuleNotFoundException
 ** If invalid input: Throw InvalidAttributeValueException

 ### Delete Compliance Rule Endpoint

* **Method**: DELETE
* **Path**: /api/rules/{ruleId}
* **Description**: Deletes a compliance rule
* **Response**: Success message
* Error Handling: If ruleId not found: Throw RuleNotFoundException

### Get Compliance Status Endpoint

* **Method**: GET
* **Path**: /api/compliance-status
* **Description**: Retrieves current compliance status across all frameworks
* **Query Parameters**:

* framework (optional): Filter by specific framework
* businessUnit (optional): Filter by specific business unit

* **Response**: List of compliance statuses
* Note: Returns empty list if no data found for given filters

### Get Violations Endpoint

* **Method**: GET
* **Path**: /api/violations
* **Description** : Retrieves list of compliance violations
* **Query Parameters**:

-status (optional): Filter by violation status
-severity (optional): Filter by violation severity
-fromDate (optional): Filter violations from this date
-toDate (optional): Filter violations up to this date


Response: List of violations with details
Note: Returns empty list if no violations found for given filters

### Generate Compliance Report Endpoint

* **Method**: POST
* **Path**: /api/reports
* **Description**: Generates a compliance report
* **Request Body**:
  ```json
  {
    "frameworkId": "string",
   "fromDate": "string (ISO 8601 format)",
    "toDate": "string (ISO 8601 format)",
    "includeViolations": boolean
  }

* Response: URL to download the generated report
* Error Handling:

If report generation fails: Throw ReportGenerationException

## 9. Tables and Models
- **DynamoDB Tables**:
    - **ComplianceManagement**: 
      Partition Key: PK (String)
      Sort Key: SK (String)
      Attributes:

     Data (Map - will contain all other attributes)
     GSI1PK (String - for Global Secondary Index)
     GSI1SK (String - for Global Secondary Index)
- 
  ### Public Models
#### //ComplianceRulesModel
* String ruleId; // Unique identifier for each compliance rule

* String ruleName; // Name of the compliance rule

* String description; // Description of what the rule checks for

* int threshold; // The threshold value that triggers a compliance action

* String action; // The action to be taken when the rule is breached

* String background;

* ZonedDateTime createdAt; // The date and time when the rule was created.
* ZonedDateTime updatedAt; // The date and time when the rule was last updated

#### //ComplianceViolationModel
- String violationId; // Unique identifier for the violation
- String description; // Description of the compliance violation
- ZonedDateTime detectedAt; // Timestamp when the violation was detected
- String resolutionStatus; // Status of the resolution process (e.g., Open, In Progress, Resolved)
- ZonedDateTime resolvedAt; // Timestamp when the violation was resolved (null if not resolved)
- String businessUnit; // The business unit affected by this violation
- String severity; // Severity level of the violation (e.g., Low, Medium, High, Critical)
- String ruleId; // Reference to the compliance rule that was violated
- String frameworkId; // Reference to the compliance framework this violation relates to
- String assignedTo; // User ID of the person assigned to resolve this violation
- List<String> affectedAssets; // List of assets or systems affected by this violation
- String remediationPlan; // Description of the plan to resolve the violation
- List<String> evidenceLinks; // Links to evidence related to the violation or its resolution

- #### //ComplianceStatusModel
- * String statusId; // Unique identifier for this status entry
- * String framework;  // Name of the compliance framework (e.g., SOC 2, ISO 27001, GDPR)
  * String status;  // Current status of compliance (e.g., Compliant, Non-compliant, At Risk)
  * ZonedDateTime lastUpdated;  // The date and time when the compliance status was last updated
  * List<ComplianceViolationModel> activeViolations; // List of current active violations
  * String businessUnit;  // The specific business unit or department to which this compliance status applies
  * String lastAssessmentId; // Reference to the last compliance assessment performed
  * int riskScore;  // A numerical score representing the level of risk associated with the current compliance status
  * ZonedDateTime nextAssessmentDue; // When the next compliance assessment is due
  
#### // UserModel: (user management, authentication, and authorization.)
 - String userId
 - String username
  -String email
 - String hashedPassword
 - List<String> roles
  - ZonedDateTime lastLogin
  - Boolean isActive

#### //AuditLogModel: (a record of all significant actions within the system, which is a key aspect of compliance and security.)
- String logId
- String actionType
- String userId
- ZonedDateTime timestamp
- String details
- String relatedEntityId
- String ipAddress

#### //FrameworkModel:
 -String frameworkId;
 -String name;
 -String description;
-String version;
 -ZonedDateTime lastUpdated;
 -List<String> associatedRuleIds;

## 10. Pages
### Dashboard
A central page displaying real-time compliance status, risk alerts, and key metrics. Provides an at-a-glance overview of the organization's compliance health.

![img_2.png](src/main/java/resources/img_2.png)
### Rule Management
A page for adding, editing, and deleting compliance rules. Allows administrators to define and maintain the compliance framework.

![img_3.png](src/main/java/resources/img_3.png)

### Violations
A page for viewing and managing compliance violations. Enables users to track, prioritize, and resolve non-compliance issues.

![img_4.png](src/main/java/resources/img_4.png)

### Users
A page for managing user accounts, roles, and permissions. Facilitates user administration and access control.

![img_5.png](src/main/java/resources/img_5.png)


- **Reports**: A page for generating and downloading compliance reports.

## Testing Approach
- Unit tests for Lambda functions using JUnit
- Integration tests for API endpoints using Postman or a similar tool
- DynamoDB local for database testing
- Jest for frontend component testing
- Implement CI/CD pipeline using GitHub Actions for automated testing

## MVP Core Features
1. User authentication and authorization
2. CRUD operations for compliance rules
3. Basic compliance status dashboard
4. Simple violation detection and reporting
5. Fundamental data visualizations using charts.js or a similar library
