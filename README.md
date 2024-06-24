# Group Chat in Java

This Code let you create a server with multiple client apps to connect together and have a group chat.

Created by

- @AndreasRonaldi
- @vieridrew
- @otwtajir

## Feature

- Signup
- Login
- Create Room
- Remove Created Room (Only By Owner)
- Join a Room
- List Avaliable Room
- Chatting in a Room
- As a Room Owner, you can kick unwanted user

## Future Feature

- [v] Connect to MySQL database
- [x] Add support for file & image transfer

## Requiremnt

- Java Development Kit (JDK) version 8+
- Apache Maven 3.8.6+


## Database

### Structure

```bash
group_chat                  # Database name
├── user                    # Table Name
│   ├── idUser              # Integer (Primary Key) 
│   ├── username            # Varchar (Unique)
│   └── password            # Varchar (SHA256)
└── room
    ├── idRoom              # Integer (Primary Key) 
    ├── name                # Varchar
    └── ownerId             # Varchar (Foreign Key to `idUser`)
```

### Installation
1. Create Database name `group_chat`.
2. Open `Database\Database.sql` and copy the query.
3. Open SQL Queries with database `group_chat` and paste the query from before.
4. Run the query.

## Running the application

### Database
- Make sure your database is open to connection.

### Running Server

1. Go to `/server` in project folder
2. Compile the Project using using `mvn compile`
3. Run the Project using `mvn exec:java`

### Running Client

1. Go to `/` in project folder
2. Compile The Server.java using `javac Client/Client.java`
3. Run Server.java using `java -cp . Client.Client`
