EGEN CHALLENGE - EMULATOR

Emulator is a spring boot microservice to capture metrics information. Currently the scope of the service is intended only for a single person. The service will also generate an alert if the below are met:
o	Detects under weight – if the weight of the person drops below 10% of his base weight
o	Detects over weight – if the weight of the person shoots 10% over his base weight

BASE WEIGHT - The first weight value sent by the sensor will be considered as the base weight of the person. Successive requests will be run through the above rules based on this base weight.

FRAMEWORKS
-	Spring Boot (Spring MVC)
-	Morphia
-	Mongo DB
-	PostMan (Testing)
-	Maven

Contributor
Saranjith Krishnan (https://www.linkedin.com/in/saranjith-krishnan-80983322)
saran.poduval@gmail.com
309 825 5911
