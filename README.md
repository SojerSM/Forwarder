# Forwarder 

This project is nothing but simple REST service built as a practice of core Spring Boot, Hibernate & SQL concepts. 

It provides, as far as we act like it's available on the web, very basic management over forwarders & drivers in assumed TSL company. My goal was to focus mostly at proper database structure & relationships, as well as include some more complex logic instead of just limit the code to basic CRUD actions.   

## Guide

There are few entities connected in one structure. At the top `Forwarder` take his place. Each one contains primitive data as well as connected object `Carriage`, which represents a vehicle in a fleet. One forwarder may have multiple carriages, each carriage should be linked with `Driver` as one to one relationships and contains list of `Freight`.


## Forwarder

Primary endpoint `/api/forwarders` returns following response:

```json
[
  {
    "id": 1,
    "firstName": "Piotr",
    "lastName": "Nowak",
    "dob": "1968-08-09T23:00:00.000+00:00",
    "carriages": [
      {
        "id": 1,
        "plates": "ZS198GT",
        "pricePerKm": 0.55,
        "adr": true,
        "driver": {
          "id": 1,
          "firstName": "Marcin",
          "lastName": "Nowak",
          "dob": "1976-11-09T23:00:00.000+00:00",
          "licenceNumber": "PDTY7823W/2016"
        },
        "freights": [
          {
            "id": 1,
            "orderNumber": "FAV/954/2022",
            "distance": 456,
            "companyName": "TSL LOGISTICS",
            "value": 320.0,
            "adr": false
          },
          {
            "id": 2,
            "orderNumber": "NO65/AWD/2022",
            "distance": 567,
            "companyName": "FERCAM AUSTRIA GMBH",
            "value": 385.0,
            "adr": false
          }
        ]
      },
      {
        "id": 2,
        "plates": "ZST998Y",
        "pricePerKm": 0.53,
        "adr": false,
        "driver": {
          "id": 2,
          "firstName": "Krystian",
          "lastName": "Marciniak",
          "dob": "1987-09-11T22:00:00.000+00:00",
          "licenceNumber": "PDTWU234A/2008"
        },
        "freights": [
          {
            "id": 3,
            "orderNumber": "FV/12/2023",
            "distance": 985,
            "companyName": "FERCAM AUSTRIA GMBH",
            "value": 590.0,
            "adr": false
          }
        ]
      }
    ]
  },
  {
    "id": 2,
    "firstName": "Maciej",
    "lastName": "Chrust",
    "dob": "1965-05-18T23:00:00.000+00:00",
    "carriages": [
      {
        "id": 3,
        "plates": "ZS845PT",
        "pricePerKm": 0.55,
        "adr": false,
        "driver": null,
        "freights": []
      }
    ]
  }
]
```
Specific forwarder data returned by `/api/forwarders/{forwarderId}` with response:
```json
{
    "id": 3,
    "firstName": "Kamil",
    "lastName": "Wójcik",
    "dob": "1976-11-09T23:00:00.000+00:00",
    "carriages": []
}
```
POST and PUT methods are managed by default by one endpoint `/api/forwarders`. In order to actually add new forwarder instead of update, it's necessary to set request body id as `0`.

Removing existing forwarder with `/api/forwarders/{forwarderId}`.

At the moment it's also possible to get information about total value of freights assigned to the specific forwarder `/api/forwarders/{forwarderId}/value`.
```json
{
    "value": 705,
    "freights": 2
}
```

# Carriage

First, general response `/api/carriages` with following response:
```json
[
    {
        "id": 1,
        "plates": "ZS198GT",
        "pricePerKm": 0.55,
        "adr": true,
        "driver": {},
        "freights": []
    },
    {
        "id": 2,
        "plates": "ZST998Y",
        "pricePerKm": 0.53,
        "adr": false,
        "driver": {},
        "freights": []
    },
    {
        "id": 3,
        "plates": "ZS845PT",
        "pricePerKm": 0.55,
        "adr": false,
        "driver": {},
        "freights": []
    }
]
```
To get specific carriage `/api/carriage/{carriageId}`.
```json
{
    "id": 1,
    "plates": "ZS198GT",
    "pricePerKm": 0.55,
    "adr": true,
    "driver": {
        "id": 1,
        "firstName": "Marcin",
        "lastName": "Nowak",
        "dob": "1976-11-09T23:00:00.000+00:00",
        "licenceNumber": "PDTY7823W/2016"
    },
    "freights": [
        {
            "id": 1,
            "orderNumber": "FAV/954/2022",
            "distance": 456,
            "companyName": "TSL LOGISTICS",
            "value": 320.0,
            "adr": false
        },
        {
            "id": 2,
            "orderNumber": "NO65/AWD/2022",
            "distance": 567,
            "companyName": "FERCAM AUSTRIA GMBH",
            "value": 385.0,
            "adr": true
        }
    ]
}
```

POST / PUT same as before `/api/carriages` with request body id of "0" if evoke of save method expected or no id but with `foreign id` of linked forwarder.

Removing existing carriage with `/api/carriages/{carriageId}`

It's also possible to get just driver data `/api/carriages/{carriageId}/driver` based on given carriage id
```json
{
    "id": 1,
    "firstName": "Marcin",
    "lastName": "Nowak",
    "dob": "1976-11-09T23:00:00.000+00:00",
    "licenceNumber": "PDTY7823W/2016"
}
```
as well as requesting `/api/carriages/{carriageId}/freights` for list of freights done by given carriage
```json
[
    {
        "id": 6,
        "orderNumber": "FAV/556/2023",
        "distance": 1245,
        "companyName": "Morrison Freight",
        "value": 690.0,
        "adr": true
    }
]
```
and calculate total value of freights and distance driven by with `/api/carriages/{carriageId}/value`
```json
{
    "totalValue": 1075,
    "totalDistance": 1848,
    "freights": 3
}
```

# Freights

General request for list of all freights `/api/freights` with response:
```json
[
    {
        "id": 1,
        "orderNumber": "FAV/954/2022",
        "distance": 456,
        "companyName": "TSL LOGISTICS",
        "value": 320.0,
        "adr": false
    },
    {
        "id": 2,
        "orderNumber": "NO65/AWD/2022",
        "distance": 567,
        "companyName": "FERCAM AUSTRIA GMBH",
        "value": 385.0,
        "adr": false
    },
    {
        "id": 3,
        "orderNumber": "TR/443A/2021",
        "distance": 2521,
        "companyName": "ROYAL TRANSPORT",
        "value": 1420.0,
        "adr": true
    }
]
```
To get specific freight info `/api/freights/{freightId}`.
```json
{
    "id": 1,
    "orderNumber": "FAV/954/2022",
    "distance": 456,
    "companyName": "TSL LOGISTICS",
    "value": 320.0,
    "adr": false
}
```
POST / PUT same as before `/api/freights` with request body id of "0" if evoke of save method expected or no id but with `foreign id` of linked carriage.

Removing existing freight with `/api/freights/{freightId}`.

# Drivers

General request for list of all drivers `/api/drivers` with response:
```json
[
    {
        "id": 1,
        "firstName": "Marcin",
        "lastName": "Nowak",
        "dob": "1976-11-09T23:00:00.000+00:00",
        "licenceNumber": "PDTY7823W/2016"
    },
    {
        "id": 2,
        "firstName": "Krystian",
        "lastName": "Marciniak",
        "dob": "1987-09-11T22:00:00.000+00:00",
        "licenceNumber": "PDTWU234A/2008"
    }
]
```
To get specific driver info `/api/drivers/{driverId}`.
```json
{
    "id": 1,
    "firstName": "Marcin",
    "lastName": "Nowak",
    "dob": "1976-11-09T23:00:00.000+00:00",
    "licenceNumber": "PDTY7823W/2016"
}
```
POST / PUT same as before `/api/drivers` with request body id of "0" if evoke of save method expected or no id but with `foreign id` of linked carriage.

Removing existing driver with `/api/drivers/{driverId}`.






