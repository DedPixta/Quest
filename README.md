# Quest

Quest site with authorization using servlet technology

## Goals

- The structure of a web application on servlets
- Displaying information to the user using JSP / JSTL
- User Authorization and Authentication
- Saving the user's game progress

## To run project

1. Download the project and extract it
2. Make sure you have installed docker and ports 8080, 5432 are free
3. Open a command line in the location where the application is installed and run the command:
```
docker compose up
```
4. The application will download all dependencies and create a docker container. Docker container will be launched automatically
5. After the successful completion of the previous point, the application will be available at the link:
```
http://localhost:8080/quest
```
## Settings

Default users available at startup:

- **user: user** (can only play)
- **editor: editor** (can view accounts and edit quests)
- **admin: admin** (can view accounts, edit quests and edit users)

Users can be added by signup or creating by user with role 'admin'. 

>**Important**  
Role '**admin**' can be manually assigned only by another administrator.

## Screenshots

![1](https://user-images.githubusercontent.com/101488434/194350855-48579a12-1e02-4afc-bf5e-2cabef7ba8fd.PNG)

![6](https://user-images.githubusercontent.com/101488434/194350862-bb4e6685-fe6d-4998-bfef-ef06d81705cf.PNG)

![5](https://user-images.githubusercontent.com/101488434/194350871-a228ecfe-3eca-45a5-9009-071417e17943.PNG)

![3](https://user-images.githubusercontent.com/101488434/194350867-404d5220-7c5e-4b12-a087-03549c0ad611.PNG)

![2](https://user-images.githubusercontent.com/101488434/194350863-da6ae5f3-d198-4580-b703-3dc4a8f51a1f.PNG)

## Technologies used
- Servlet API
- Lombok
- Liquibase
- Tomcat
- Docker
- Hibernate
- PostgreSQL