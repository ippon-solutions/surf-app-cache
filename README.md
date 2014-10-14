surf-app-cache
==============

##Project set up

This project is using maven, after cloning use the following to download all dependencies ```mvn clean install```

##Running the application

Run the application using, ```mvn jetty:run```

##Run JUnit tests

Run the application tests using, ```mvn test```

##End points

###Locations

####Get all locations

To find out all possible locations, go to: ```/surf-app-cache/Locations/All```

###Forecasts

####Get location summary details

To get the next available timestep for a location, go to: ```/surf-app-cache/Forecasts/{location}/Summary```
Enter the location name in ```{location}```


####Get location forecast

To get the full forecast details for a location, go to: ```/surf-app-cache/Forecasts/{location}```
Enter the location name in ```{location}```
