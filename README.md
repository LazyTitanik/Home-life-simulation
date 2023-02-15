# OMO Smart Home

1. [Why only one commit](#why-one-commit)
2. [Functionality describtion](#functionality-describtion)
3. [Configuration files](#configuration-files)
4. [Possible values](#possible-values)
5. [Launching](#launching)
6. [Reports](#reports)
7. [Design patterns used](#design-patterns-used)
8. [Functional requirements](#funktional-requirments)

## Why one commit

This project has been implemented as a semestral work in university, so original git repository is in private university's system. I found it impossible to fork it with keeping commits history.

## Functionality describtion

The application reads a file of configuration, which is loaded by name as arguments of the program (view [Launching](#launching)) as well as filenames of reports. During the simulation, every living creature acts randomly (within defined limits and rules) and interacts with devices. Some devices such as cercuit breakout and fire alarm can react to outer events. 

All events that had place during the simulation are written to [reports](#reports) in chronological order.

## Configuration files

In our program you can choose your own home configuration. This configuration you should right in the file using .XML or .JSON format according to the structure:

- DaysCount
- Floor 1
    - number
    - Room 1
        - name
        - Device 1
        - Device 2
    - Room 2
        - name
        - Device 1
- Floor 2
    - number
    - Room 1 
        - name
        - Device 1
        - Device 2
        - Device 3

- Living 1
    - stuff
    - name
    - floor number
    - room name
- Living 2

```Attention!!!```

In case .xml file is sent as config file, the app will create identical .json file. So if you have 2 different configurations (in .xml and .json format) but with same name, (Ex. config.xml and config.json), rename one of them so the app does not delete your config.json

## Possible values 
- room name: Bathroom, Bedroom, Garage, Kitchen, LivingRoom, Toilet
- device name: Bath, Car, CircuitBreaker, Console, Cooker, FireAlarm, Fridge, Heating, Light, Rag, Remote, Skis, Television, Thermometer, Washer
- living stuff: Father, Mother, Son, Daughter, Cat, Dog
- living name: Whatever you want
- floor number and room name: this floor and this room must be at home        

Also there are some rules for the configuration:
1. Home must have at least 1 floor
2. Each floor must have at least 1 room
3. Each room must have at least 1 device

Examples for configuration are in the foulder "configurations"

## Launching
To launch the application you need to start it with 4 parameters:
1. name of configuration file
2. name of file for the Event Report
3. name of file for the Consumption Report
4. name of file for the Activity And Usage Report

## Reports
- In EventReport we have all events, which happened in the house
- In CosumptionReport we have information about the amount of water, gas and electricity that was used by each device
- In ActivityAndUsageReport we have information about which person switched a device on and how many times they did it

## Design patterns used

1. Singleton
    >Classes House, SmartHome, EventObserver, all classes that are responsible for reports
    
2. Observer 
    >EventObserver notifies classes that implement EventListner interface
3. Builder
    >Classes RoomBuilder, FloorBuilder, StrategyBuilder
4. State machine
    >Devices can be Active, Broken and Broken. Each state defines device's behavior and can only be changed according to defined rules

5. Strategy
    >Every living creature chooses new strategy for each day, which defines it behaviour

6. Decorator
    >Every living creature reacts differently to same events. This is implemented with update() method in interface Eventlistener. Each class calls update() method of its parent and than adds it's own reacts, which is special for it.

7. Chain od responsibility
    >In case someone broke a device, son tries to repair it (in case son is present in the house). If he succeeds, the device can be used again. If he did not, he asks father to help

8. Adapter
    >Class XMLtoJSON creates .json file out of .xml to read the configuration

9. Lazy loading
    >All the events are stored ass class instances and are converted to strings only when it is requested
10. Marker
    >Empty interfaces PersonStrategy and PetStrategy make using unappropriate strategy impossible
11. Null object
    >NoRoom class is used instead of null in order to avoid NullPointerException