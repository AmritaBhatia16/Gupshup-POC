# Gupshup's WhatsApp Access API
## Description
The service demonstrates the integration of Gupshup's WhatsApp Business API and showcases its key functionalities. It provides various REST APIs to enable outbound messaging via WhatsApp and receive their corresponding delivery reports.

## Functional Requirements / Feature Set
-   **Template Messaging:** Users can send outbound template messages to opted-in numbers via WhatsApp.
-   **Opt-in Mechanism:** Users can mark numbers as opted-in for the Access API app.
-   **Delivery Report Monitoring:** The service receives delivery reports on the status of outbound messages (enqueued, failed, sent, delivered, read).
-   **Session Messaging:** Users can send outbound session messages via WhatsApp.

## Sequence Diagrams
<img width="686" alt="Template Messaging Service" src="https://github.com/AmritaBhatia16/Gupshup-POC/assets/141222739/9002a64b-a4d5-419a-8f8c-102223068a29">

<img width="653" alt="Messaging Service" src="https://github.com/AmritaBhatia16/Gupshup-POC/assets/141222739/d3f64c1b-9468-41e4-89f5-8809bd9118c3">

<img width="724" alt="Opt-In Service" src="https://github.com/AmritaBhatia16/Gupshup-POC/assets/141222739/e4c7575d-b9ec-4cdf-8440-6e12d38055ca">


## API Contract

### Template Messaging
-   **HTTP Method**
    - POST
- **Request Path**
    - /whatsapp/send/template
- **Request Path Param**
- **Request Headers**\
  - Content-Type: application/json
-   **Request Body**

        {
            "destination": "919x68xx46x6",
            "template": {
        	    "id": "583efbaf-639f-4195-af0c-d15eaaa53890",
        	    "params": ["Param1","Param2"]
            }
        }
-   **Response Body**\
Success Response

          {
              "messageId": "08768d7e-94e0-403a-8c8b-b8ba38c8df1f",
              "status": "submitted"
          }


### Session Messaging
- **HTTP Method**
    - POST
- **Request Path**
    - /whatsapp/send
-   **Request Path Param**
- **Request Headers**
    - Content-Type: application/json

-   **Request Body**

        {
        	"channel": "whatsapp",
            "destination": "919x68xx46x6",
            "message": {
    	        "type": "file",
    	        "url": "https://www.buildquickbots.com/whatsapp/media/sample/pdf/sample01.pdf",
    	        "filename": "Sample file"
    		}
        }

-   **Response Body**\
    Success Response

        {
	        "status": "submitted"
	        "messageId": "81aa89c1-57bb-495a-86b5-9372f639fcbb",
	    }

### User Opt-In
-   **HTTP Method**
    - POST
-   **Request Path**
    - /whatsapp/opt-in
-   **Request Path Param**

-   **Request Headers**
    - Content-Type: application/json
-   **Request Param**
    - `user: “919x68xx46x6”`
-   **Request Body**
-   **Response Body**