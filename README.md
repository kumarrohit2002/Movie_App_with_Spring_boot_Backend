# MovieFlix API

MovieFlix is a Spring Boot-based REST API for managing movies, including movie details and poster uploads.

## Tech Stack
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL/MySQL
- Lombok
- Jakarta Validation
- Multipart File Upload

## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/movieflix.git
   cd movieflix
   ```
2. Configure application properties in `src/main/resources/application.properties`:
   ```properties
   project.poster=./uploads
   base.url=http://localhost:8081
   spring.datasource.url=jdbc:mysql://localhost:3306/movieflix
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   ```
3. Build and run the application:
   ```sh
   mvn spring-boot:run
   ```

## API Endpoints

### 1. File Upload & Retrieve
#### Upload File
- **Endpoint:** `POST /file/upload`
- **Request:**
  ```sh
  curl -X POST -F "file=@/path/to/image.png" http://localhost:8081/file/upload
  ```
- **Response:**
  ```json
  "Uploaded File: image.png"
  ```

#### Retrieve File
- **Endpoint:** `GET /file/{fileName}`
- **Example:** `GET /file/image.png`
- **Response:** Returns the image file as a response.

### 2. Movie Management
#### Add Movie
- **Endpoint:** `POST /api/v1/movie/add-movie`
- **Request:**
  ```sh
  curl -X POST -F "file=@/path/to/image.png" -F "movieDto={\"title\": \"Inception\", \"director\": \"Christopher Nolan\", \"studio\": \"Warner Bros\", \"releaseYear\": 2010, \"movieCast\": [\"Leonardo DiCaprio\"]}" http://localhost:8080/api/v1/movie/add-movie
  ```
- **Response:**
  ```json
  {
    "movieId": 1,
    "title": "Inception",
    "director": "Christopher Nolan",
    "studio": "Warner Bros",
    "movieCast": ["Leonardo DiCaprio"],
    "releaseYear": 2010,
    "poster": "image.png",
    "posterUrl": "http://localhost:8080/file/image.png"
  }
  ```

### 3. Get Movie Details
#### Get a Movie by ID
- **Endpoint:** `GET /api/v1/movie/{movieId}`
- **Example:** `GET /api/v1/movie/1`
- **Response:**
  ```json
  {
    "movieId": 1,
    "title": "Inception",
    "director": "Christopher Nolan",
    "studio": "Warner Bros",
    "movieCast": ["Leonardo DiCaprio"],
    "releaseYear": 2010,
    "poster": "image.png",
    "posterUrl": "http://localhost:8080/file/image.png"
  }
  ```

### 4. Get All Movies
- **Endpoint:** `GET /api/v1/movie/all`
- **Response:**
  ```json
  [
    {
      "movieId": 1,
      "title": "Inception",
      "director": "Christopher Nolan",
      "studio": "Warner Bros",
      "movieCast": ["Leonardo DiCaprio"],
      "releaseYear": 2010,
      "poster": "image.png",
      "posterUrl": "http://localhost:8080/file/image.png"
    }
  ]
  ```

## License
This project is open-source and available under the MIT License.

