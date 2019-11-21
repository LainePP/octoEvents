# Octo Events

Octo Events is an application that listens to Github Events via webhooks and expose by an api for later use.

![alt text](imgs/octo_events.png)

 The test consists in building 2 endpoints:

## 1. Webhook Endpoint

The Webhook endpoint receives events from Github and saves them on the database, in order to do that you must read the following docs:

* Webhooks Overview: https://developer.github.com/webhooks/ 
* Creating Webhooks : https://developer.github.com/webhooks/creating/

It must be called `/events`

## 2. Events Endpoint

The Events endpoint will expose the persist the events by an api that will filter by issue number

**Request:**

> GET /issues/1000/events

**Response:**

> 200 OK
```javascript
[ 
  { "action": "open", created_at: "...",}, 
  { "action": "closed", created_at: "...",} 
]
```

## Instruções para rodar
* Ter um banco de dados PostgreSQL com as seguintes credenciais:
    * user = "octoevents"
    * password = "octo"
    * database = octoevents
    
* Baixar as libs do graddle 
