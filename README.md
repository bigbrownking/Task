# TaskForPractice Application

This repository contains the source code for the TaskForPractice application, which is a simple RESTful API for managing users, news, and requests.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- BCryptPasswordEncoder

## API Endpoints

1. **Register User**: `POST /api/register`
    - Registers a new user with the provided details.
    - Request body:
      ```json
      {
        "username": "user123",
        "hashed_password": "password123",
        "role": false
      }
      ```
    - Response: Returns the registered user details.

2. **Login User**: `POST /api/login`
    - Logs in an existing user with the provided credentials.
    - Request body:
      ```json
      {
        "username": "user123",
        "hashed_password": "password123"
      }
      ```
    - Response: Returns the user details if login is successful, otherwise returns an error.

3. **Submit Request**: `POST /api/request`
    - Submits a request with the provided details.
    - Request body:
      ```json
      {
        "userId": 1,
        "createdBy": "user123",
        "text": "This is a sample request."
      }
      ```
    - Response: Returns the submitted request details.

4. **Get All News**: `GET /api/news`
    - Retrieves a list of all news articles.
    - Response: Returns a list of news articles.

5. **Publish News**: `POST /api/news/publish?user_id={userId}`
    - Publishes a new news article with the provided details.
    - Request body:
      ```json
      {
        "heading": "New Article",
        "content": "This is the content of the article."
      }
      ```
    - Response: Returns the published news article details if the user is an admin, otherwise returns an error.

## Running the Application

1. Clone the repository: `git clone https://github.com/your_username/taskforpractice.git`
2. Navigate to the project directory: `cd taskforpractice`
3. Configure the PostgreSQL database in the `application.properties` file.
4. Build the project: `mvn clean install`
5. Run the application: `java -jar target/taskforpractice-1.0.0.jar`

Now, the TaskForPractice application should be up and running, and you can access the API endpoints described above.
