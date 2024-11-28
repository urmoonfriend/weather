
# Weather and City Management API

## Overview
SWAGGER: http://localhost:8082/tech/v1/swagger-ui/index.html
This API provides endpoints for managing city data and fetching weather information for those cities. The API is divided into two main sections:

1. **City Management**: Endpoints for CRUD operations on city data.
2. **Weather Information**: Endpoints for retrieving current and forecasted weather data for cities.

---

## City Management Endpoints

### 1. Create a City

**Description**: Creates a new city in the database.

**Endpoint**: `POST /tech/cities`

**Request Body**:

```json
{
  "name": "City Name",
  "latitude": 12.34,
  "longitude": 56.78,
  "timezone": "Time Zone"
}
```

**Response**:

- **Success (HTTP 200)**:

  ```json
  {
    "success": true,
    "code": "OK",
    "message": {
      "id": 1,
      "name": "City Name",
      "latitude": 12.34,
      "longitude": 56.78,
      "timezone": "Time Zone",
      "createdAt": "2024-11-28T12:00:00",
      "updatedAt": "2024-11-28T12:00:00"
    }
  }
  ```

- **Error (HTTP 500)**:

  ```json
  {
    "success": false,
    "code": "SERVER_ERROR",
    "error": "Internal server error"
  }
  ```

---

### 2. Update a City

**Description**: Updates the details of an existing city.

**Endpoint**: `PUT /tech/cities/{id}`

**Path Parameters**:

- `id` (Long): The ID of the city to update.

**Request Body**:

```json
{
  "name": "Updated City Name",
  "latitude": 98.76,
  "longitude": 54.32,
  "timezone": "Updated Time Zone"
}
```

**Response**:

- **Success (HTTP 200)**:

  ```json
  {
    "success": true,
    "code": "OK",
    "message": {
      "id": 1,
      "name": "Updated City Name",
      "latitude": 98.76,
      "longitude": 54.32,
      "timezone": "Updated Time Zone",
      "createdAt": "2024-11-28T12:00:00",
      "updatedAt": "2024-11-28T13:00:00"
    }
  }
  ```

- **Error (HTTP 500)**:

  ```json
  {
    "success": false,
    "code": "SERVER_ERROR",
    "error": "Internal server error"
  }
  ```

---

### 3. Get a City by ID

**Description**: Retrieves details of a specific city by its ID.

**Endpoint**: `GET /tech/cities/{id}`

**Path Parameters**:

- `id` (Long): The ID of the city.

**Response**:

- **Success (HTTP 200)**:

  ```json
  {
    "success": true,
    "code": "OK",
    "message": {
      "id": 1,
      "name": "City Name",
      "latitude": 12.34,
      "longitude": 56.78,
      "timezone": "Time Zone",
      "createdAt": "2024-11-28T12:00:00",
      "updatedAt": "2024-11-28T12:00:00"
    }
  }
  ```

- **Error (HTTP 500)**:

  ```json
  {
    "success": false,
    "code": "SERVER_ERROR",
    "error": "Internal server error"
  }
  ```

---

### 4. Get All Cities

**Description**: Retrieves a list of all cities.

**Endpoint**: `GET /tech/cities`

**Response**:

- **Success (HTTP 200)**:

  ```json
  {
    "success": true,
    "code": "OK",
    "message": [
      {
        "id": 1,
        "name": "City Name",
        "latitude": 12.34,
        "longitude": 56.78,
        "timezone": "Time Zone",
        "createdAt": "2024-11-28T12:00:00",
        "updatedAt": null
      },
      {
        "id": 2,
        "name": "Another City",
        "latitude": 23.45,
        "longitude": 67.89,
        "timezone": "Another Time Zone",
        "createdAt": "2024-11-28T13:00:00",
        "updatedAt": null
      }
    ]
  }
  ```

- **Error (HTTP 500)**:

  ```json
  {
    "success": false,
    "code": "SERVER_ERROR",
    "error": "Internal server error"
  }
  ```

---

### 5. Delete a City

**Description**: Deletes a city by its ID.

**Endpoint**: `DELETE /tech/cities/{id}`

**Path Parameters**:

- `id` (Long): The ID of the city to delete.

**Response**:

- **Success (HTTP 200)**:

  ```json
  {
    "success": true,
    "code": "OK",
    "message": {
      "id": 1,
      "name": "City Name",
      "latitude": 12.34,
      "longitude": 56.78,
      "timezone": "Time Zone",
      "createdAt": "2024-11-28T12:00:00",
      "updatedAt": null
    }
  }
  ```

- **Error (HTTP 500)**:

  ```json
  {
    "success": false,
    "code": "SERVER_ERROR",
    "error": "Internal server error"
  }
  ```

---



# Weather Management API

## Overview

This section of the API provides endpoints for fetching current and forecasted weather information for cities.

---

## Weather Endpoints

### 1. Get Current Weather by City ID

**Description**: Retrieves current weather information for a city by its ID.

**Endpoint**: `GET /weather/current/city/{id}`

**Path Parameters**:

- `id` (Long): The ID of the city.

**Response**:

- **Success (HTTP 200)**:

  ```json
  {
    "success": true,
    "code": "OK",
    "message": {
      "location": {
        "name": "City Name",
        "region": "Region",
        "country": "Country",
        "lat": 12.34,
        "lon": 56.78,
        "timezone": "Time Zone",
        "localTimeEpoch": 1700000000,
        "localtime": "2024-11-28 12:00"
      },
      "current": {
        "lastUpdatedEpoch": 1700000000,
        "lastUpdated": "2024-11-28 12:00",
        "tempC": 20.5,
        "tempF": 68.9,
        "isDay": 1,
        "condition": {
          "text": "Sunny",
          "icon": "//cdn.weatherapi.com/weather/64x64/day/113.png",
          "code": 1000
        },
        "windMph": 5.6,
        "windKph": 9.0,
        "windDegree": 180,
        "windDir": "S",
        "pressureMb": 1012.0,
        "pressureIn": 29.88,
        "precipMm": 0.0,
        "precipIn": 0.0,
        "humidity": 50,
        "cloud": 0,
        "feelslikeC": 20.5,
        "feelslikeF": 68.9,
        "visKm": 10.0,
        "visMiles": 6.0,
        "uv": 5.0,
        "gustMph": 7.8,
        "gustKph": 12.6
      }
    }
  }
  ```

- **Error (HTTP 500)**:

  ```json
  {
    "success": false,
    "code": "SERVER_ERROR",
    "error": "Internal server error"
  }
  ```

---

### 2. Get Weather Forecast by City ID

**Description**: Retrieves weather forecast information for a city by its ID for a specified number of days.

**Endpoint**: `GET /weather/forecast/city/{id}`

**Path Parameters**:

- `id` (Long): The ID of the city.

**Query Parameters**:

- `days` (Integer, required): The number of days for the forecast (e.g., 1-14).

**Response**:

- **Success (HTTP 200)**:

  ```json
  {
    "success": true,
    "code": "OK",
    "message": {
      "location": {
        "name": "City Name",
        "region": "Region",
        "country": "Country",
        "lat": 12.34,
        "lon": 56.78,
        "timezone": "Time Zone",
        "localTimeEpoch": 1700000000,
        "localtime": "2024-11-28 12:00"
      },
      "forecast": {
        "forecastday": [
          {
            "date": "2024-11-29",
            "dateEpoch": 1700000000,
            "day": {
              "maxtempC": 25.0,
              "maxtempF": 77.0,
              "mintempC": 15.0,
              "mintempF": 59.0,
              "avgtempC": 20.0,
              "avgtempF": 68.0,
              "condition": {
                "text": "Partly cloudy",
                "icon": "//cdn.weatherapi.com/weather/64x64/day/116.png",
                "code": 1003
              }
            },
            "astro": {
              "sunrise": "06:00 AM",
              "sunset": "06:00 PM",
              "moonrise": "08:00 PM",
              "moonset": "08:00 AM",
              "moonPhase": "Full Moon",
              "moonIllumination": "100%"
            },
            "hour": [
              {
                "timeEpoch": 1700003600,
                "time": "2024-11-29 12:00",
                "tempC": 20.5,
                "tempF": 68.9,
                "condition": {
                  "text": "Sunny",
                  "icon": "//cdn.weatherapi.com/weather/64x64/day/113.png",
                  "code": 1000
                },
                "windMph": 5.6,
                "windKph": 9.0,
                "humidity": 50,
                "cloud": 0,
                "uv": 5.0
              }
            ]
          }
        ]
      }
    }
  }
  ```

- **Error (HTTP 500)**:

  ```json
  {
    "success": false,
    "code": "SERVER_ERROR",
    "error": "Internal server error"
  }
  ```
