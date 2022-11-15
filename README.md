# Backend Project - Java Spring Boot 

    By Randika RATHWATHTHAGE - CPS2-M2

Development include,

* 2 Different types of users
* 4 Related tables - Building, Room, Window, Heater
* 4 Data Transfer Objects(DTO) - BuildingDto, RoomDto, WindowDto, HeaterDto
* 4 Data Access Objects (DAO) - BuildingDao, RoomDao, HeaterDao, WindowDao

* Total no of unit tests 40
  *  Dao - 15 unit tests
  * endpoints - 25 unit tests

Credentials of users:

* ROLE **ADMIN**
    * username: admin
    * password: adminpwd
* Role **USER**
    * username: user
    * password: userpwd


Final application is hosted at [faircorp](https://faircorp-application-randika.cleverapps.io)

To test end-points you can use following link [Swagger](http://faircorp-application-randika.cleverapps.io/swagger-ui/index.html)

# **End-points**

## security-controller (Required user with admin role)

| HTTP method | URL | Comment |
|--|--|--|
| **GET** | /api | returns list of all users (user must have admin role to do so)|
| **GET** | /api/{id}| returns a specific user by id|


## building-controller (Requires user with admin role)

| HTTP method | URL | Comment |
|--|--|--|
| **GET** | /api/buildings | returns a list of all buildings|
| **POST** | /api/buildings | creates a new building |
| **GET** | /api/buildings/{id} | returns a specific building by id|
| **DELETE** | /api/buildings/{id} | deletes a building by id|


## room-controller (Requires user with admin role)

| HTTP method | URL | Comment |
|--|--|--|
| **GET** | /api/rooms | returns a list of all rooms|
| **POST** | /api/rooms | creates a new room |
| **GET** | /api/rooms/{id} | returns a specific room by id|
| **DELETE** | /api/rooms/{id} | deletes a room by id|
| **PUT** | /api/rooms/{id}/switch-heater | inverts all heaters statuses in room if it was ON it will become OFF or if it was OFF it will become ON|
| **PUT** | /api/rooms/{id}/switch-window | inverts all windows statuses in room if it was OPEN it will become CLOSED or if it was CLOSED it will become OPEN|

## window-controller (Requires user with admin role)

| HTTP method | URL | Comment |
|--|--|--|
| **GET** | /api/windows | returns list of all windows|
| **POST** | /api/windows | creates a new window |
| **GET** | /api/windows/{id} | returns a specific window by id|
| **DELETE** | /api/windows/{id} | deletes a window by id|
| **PUT** | /api/windows/{id}/switch | inverts window's status if it was OPEN it will become CLOSED or if it was CLOSED it will become OPEN|


## heater-controller (Requires user with admin role)

| HTTP method | URL | Comment |
|--|--|--|
| **GET** | /api/heaters | returns a list of all heaters|
| **POST** | /api/heaters | creates a new heater |
| **GET** | /api/heaters/{id} | returns a specific heater by id|
| **DELETE** | /api/heaters/{id} | deletes a heater by id|
| **PUT** | /api/heaters/{id}/switch | inverts heater's status if it was ON it will become OFF or if it was OFF it will become ON|

# **Test-Coverage**
## All unit testings are passed successfully