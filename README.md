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
- Connect to MySQL database

## Future Feature

- [Not_Possible] Add support for file & image transfer

## Requiremnt

- Java Development Kit (JDK) version 8+
- Apache Maven 3.8.6+

## Database Stucture

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

## Running the application

### Running Server

1. Go to `/server` folder
2. Compile the Project using using `mvn compile`
3. Run the Project using `mvn exec:java`

### Running Client

1. Go to `/Client` folder
2. Compile The Server.java using `javac Client.java`
3. Run Server.java using `java Client.java`
