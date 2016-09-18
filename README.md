EMULATOR

Emulator is a spring boot microservice to capture metrics information. Currently the scope of the service is intended only for a single person. The service will also generate an alert if the below are met:
o	Detects under weight – if the weight of the person drops below 10% of his base weight
o	Detects over weight – if the weight of the person shoots 10% over his base weight

BASE WEIGHT - The first weight value sent by the sensor will be considered as the base weight of the person. Successive requests will be run through the above rules based on this base weight.

FRAMEWORKS
-	Spring Boot (Spring MVC)
-	Morphia
-	Mongo DB
-	Easy Rule
-	PostMan (Testing)
-	Maven

API's (Metric)

CREATE METRIC
POST /api/v1/metric HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{
  "timeStamp": "1458062849878", 
  "value": "100"
}

READ ALL METRICS
GET /api/v1/metric HTTP/1.1
Host: localhost:8080

Response:
[
  {
    "timeStamp": 1458062849878,
    "value": 100,
    "alert": null
  },
  {
    "timeStamp": 1458062849879,
    "value": 102,
    "alert": null
  },
  {
    "timeStamp": 1458062849880,
    "value": 120,
    "alert": {
      "timeStamp": 1458062849880,
      "alertType": "OVERWEIGHT"
    }
  },
  {
    "timeStamp": 1458062849881,
    "value": 80,
    "alert": {
      "timeStamp": 1458062849881,
      "alertType": "UNDERWEIGHT"
    }
  }
]

READ METRICS BETWEEN TIMESTAMP RANGE
GET /api/v1/metric?from=1458062849879&amp;to=1458062849881 HTTP/1.1
Host: localhost:8080

Response:
[
  {
    "timeStamp": 1458062849880,
    "value": 120,
    "alert": {
      "timeStamp": 1458062849880,
      "alertType": "OVERWEIGHT"
    }
  }
]

API's (Alert)

READ ALERTS
GET /api/v1/alert HTTP/1.1
Host: localhost:8080

Response:
[
  {
    "timeStamp": 1458062849879,
    "alertType": "OVERWEIGHT"
  },
  {
    "timeStamp": 1458062849880,
    "alertType": "OVERWEIGHT"
  },
  {
    "timeStamp": 1458062849881,
    "alertType": "UNDERWEIGHT"
  }
]

READ ALERTS BETWEEN TWO TIMESTAMP
GET /api/v1/alert?from=1458062849879&to=1458062849881 HTTP/1.1
Host: localhost:8080

Response:
[
  {
    "timeStamp": 1458062849880,
    "alertType": "OVERWEIGHT"
  }
]

Contributor
Saranjith Krishnan (https://www.linkedin.com/in/saranjith-krishnan-80983322)

