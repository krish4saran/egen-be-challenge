# Emulator

A personal weight tracker. This system is responsible for
-	Consuming data sent from different sensors (emulators)
-	Storing the received data in MongoDB
-	Running the data through different rules to make basic predictions

Emulator is a spring boot microservice to capture metrics information. Currently the scope of the service is intended only for a single person. The service will also generate an alert if the below are met:
```
-Detects under weight – if the weight of the person drops below 10% of his base weight
-Detects over weight – if the weight of the person shoots 10% over his base weight
```
BASE WEIGHT - The first weight value sent by the sensor will be considered as the base weight of the person. Successive requests will be run through the above rules based on this base weight.

#STACK
-	Spring Boot (Spring MVC)
-	Morphia
-	Mongo DB
-	Easy Rule
-	PostMan (Testing)
-	Maven



## API's (Metric)

CREATE METRIC
```
POST /api/v1/metric HTTP/1.1
Host: localhost:8080
Content-Type: application/json
```
```json
{
  "timeStamp": "1458062849878", 
  "value": "100"
}
``` 
READ ALL METRICS
```
GET /api/v1/metric HTTP/1.1
Host: localhost:8080
```
Response:
```json
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
```

READ METRICS BETWEEN TIMESTAMP RANGE
```
GET /api/v1/metric?from=1458062849879&amp;to=1458062849881 HTTP/1.1
Host: localhost:8080
```
Response:
```json
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
```


API's (Alert)

READ ALERTS
```
GET /api/v1/alert HTTP/1.1
Host: localhost:8080
```

Response:
```json
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
```

READ ALERTS BETWEEN TWO TIMESTAMP
```
GET /api/v1/alert?from=1458062849879&to=1458062849881 HTTP/1.1
Host: localhost:8080
```
Response:
```json
[
  {
    "timeStamp": 1458062849880,
    "alertType": "OVERWEIGHT"
  }
]
```

Testing Using Sensor-Emulator
```

saranjiths-MacBook-Pro-2:target saranjithkrishnan$ java -jar -Dbase.value=150 -Dapi.url=http://localhost:8080/api/v1/metric sensor-emulator-0.0.1-SNAPSHOT.jar
Posting data {"timeStamp": "1474173356520", "value": "150"} to api at http://localhost:8080/api/v1/metric
Posting data {"timeStamp": "1474173361811", "value": "151"} to api at http://localhost:8080/api/v1/metric
Posting data {"timeStamp": "1474173366836", "value": "152"} to api at http://localhost:8080/api/v1/metric
Posting data {"timeStamp": "1474173371855", "value": "153"} to api at http://localhost:8080/api/v1/metric
Posting data {"timeStamp": "1474173376875", "value": "154"} to api at http://localhost:8080/api/v1/metric
Posting data {"timeStamp": "1474173381894", "value": "155"} to api at http://localhost:8080/api/v1/metric
Posting data {"timeStamp": "1474173386948", "value": "156"} to api at http://localhost:8080/api/v1/metric
Posting data {"timeStamp": "1474173391967", "value": "157"} to api at http://localhost:8080/api/v1/metric
Posting data {"timeStamp": "1474173396987", "value": "158"} to api at http://localhost:8080/api/v1/metric
```

Contributor
```

Saranjith Krishnan (https://www.linkedin.com/in/saranjith-krishnan-80983322)
```
