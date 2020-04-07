# Backend
## Endpoints
- Base URL https://secretfamilyrecipescookbook.herokuapp.com
### Login and Registration
`/register`

  Request Type: POST
  -Headers
    - Content-Type: application/json
  - Body
  ```
  {
	"username":"newuser",
	"password":"password",
	"useremail":"email@email.com"
  }
  ```

  Reponse: 201 Created
 

`/login`

  Request Type: POST
  -Headers
    - Content-Type: application/x-www-form-urlencoded
    - Authorization: Basic client-id:client-secret (base64 encoded)
  - Body
    - grant_type: password
    - username: <username>
    - password: <password>

  Reponse: 200 Ok
  ```
  {
    "access_token": "token_here",
    "token_type": "bearer",
    "expires_in": 3599,
    "scope": "read write trust"
  }
  ```
  
  ### Recipes
  
  `/recipes/{recipename}`

  Request Type: GET
  -Headers
    - Authorization: Bearer <token>
 

  Reponse: 200 Ok
  - Returns recipe matching name
  
  Response: 404 Not Found
  ```
  {
    "title": "Resource Not Found",
    "status": 404,
    "detail": "Recipe with name recipe not found",
    "timestamp": "06 Apr 2020 19:58:52:822 -0400",
    "developerMessage": "com.tjclawson.secretrecipe.exceptions.ResourceNotFoundException",
    "errors": {}
  }
  ```
  
  `/recipes/recipes`

  Request Type: GET
  -Headers
    - Authorization: Bearer <token>
 

  Reponse: 200 Ok
  - Returns all of user's recipes
  
