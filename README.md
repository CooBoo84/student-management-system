## JAVA EE with CRUD - Laboration
Laboration 1 for a student management system with CRUD-functionality.

## Endpoints

### Get a student by ID
``
GET - http://localhost:8080/student-management-system/api/v1/students/{id}
``

### Get a student by Lastname
``
GET - http://localhost:8080/student-management-system/api/v1/students/search?lastName={lastName}
``

### Get all students
``
GET - http://localhost:8080/student-management-system/api/v1/students/
``

### Create a new student
``
POST - http://localhost:8080/student-management-system/api/v1/students/
``
- JSON-body
```
{
	"firstName": "Karl",
	"lastName": "Jones",
	"email": "karl.jones@mail.com",
	"phoneNumber": "0701234567"
}
```

### Update students information
``
PUT - http://localhost:8080/student-management-system/api/v1/students/
``
- JSON-body
```
{
	"id": 1,
	"firstName": "Karl",
	"lastName": "Jones",
	"email": "karl.jones@mail.com",
	"phoneNumber": "0701234567"
}
```

### Delete a student by ID
``
DELETE - http://localhost:8080/student-management-system/api/v1/students/{id}
``

## Contributing
I did this myself for schoolproject.
