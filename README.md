## JAVA EE with CRUD - Laboration
Laboration 2 for a student management system (inkluding teachers and subjects) with CRUD-functionality.

## Start
At startup, the database fills up with 4 students, 2 teachers and 3 subjects.
```
Student1 = ("Super","Mario","super.mario@mail.com","010123456");
Student2 = ("Luigi","Mario","luigi@mail.com","010987654");
Student3 = ("Donkey","Kong","donkey.kong@mail.com","010456789");
Student4 = ("Shigeru","Miyamoto","mr.miyamoto@mail.com","010147258");
```
```
Teacher1 = ("Link", "Zelda", "link.zelda@mail.com", "070134567");
Teacher2 = ("Warren", "Buffett", "warren.e.buffett@mail.com", "99999999");
```
```
Subject1 = ("Java",teacher1);
Subject2 = ("Python", teacher1);
Subject3 = ("C",teacher2);
```

## Endpoints
- [Students](#students)
- [Subjects](#subjects)
- [Teachers](#teachers)
---
## Students
### Get all students
``
GET - http://localhost:8080/student-management-system/api/v1/students/
``

### Get a student by ID
``
GET - http://localhost:8080/student-management-system/api/v1/students/{id}
``
| Example |
| --- |
| localhost:8080/student-management-system/api/v1/students/3 |

### Get a student by Lastname
``
GET - http://localhost:8080/student-management-system/api/v1/students/search?lastName={lastName}
``
| Example |
| --- |
| localhost:8080/student-management-system/api/v1/students/search?lastName=Mario |

### Create a new student
``
POST - http://localhost:8080/student-management-system/api/v1/students/
``
- JSON-body
```
{
	"firstName": "Princess",
	"lastName": "Mario",
	"email": "mrs.mario@mail.com",
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
	"firstName": "Ganon",
	"lastName": "Dark",
	"email": "ganon@mail.com",
	"phoneNumber": "0701234567"
}
```

### Delete a student by ID
``
DELETE - http://localhost:8080/student-management-system/api/v1/students/{id}
``
| Example |
| --- |
| localhost:8080/student-management-system/api/v1/students/1 |

## Subjects
### Get all subjects
``
GET - http://localhost:8080/student-management-system/api/v1/subjects/
``

### Get a subjects by ID
``
GET - http://localhost:8080/student-management-system/api/v1/subjects/{id}
``
| Example |
| --- |
| localhost:8080/student-management-system/api/v1/subjects/2 |

### Get subjects, students and teacher by subject name
``
GET - http://localhost:8080/student-management-system/api/v1/subjects/search?name={name}
``
| Example |
| --- |
| localhost:8080/student-management-system/api/v1/subjects/search?name=Java |

### Create a new subject
``
POST - http://localhost:8080/student-management-system/api/v1/subjects/
``
- JSON-body
```
{
	"name": "Svenska"
}
```

### Update subjects information
``
PUT - http://localhost:8080/student-management-system/api/v1/subjects/
``
- JSON-body
```
  {
    "id": 2,
    "name": "Spelprogrammering",
    "students": [
      {
        "email": "super.mario@mail.com",
        "firstName": "Super",
        "id": 1,
        "lastName": "Mario",
        "phoneNumber": "010123456"
      },
      {
        "email": "luigi@mail.com",
        "firstName": "Luigi",
        "id": 3,
        "lastName": "Mario",
        "phoneNumber": "010987654"
      }
    ],
    "teacher": {
      "email": "link.zelda@mail.com",
      "firstName": "Link",
      "id": 8,
      "lastName": "Zelda",
      "phoneNumber": "070134567"
    }
  }
```

### Delete a subject by ID
``
DELETE - http://localhost:8080/student-management-system/api/v1/subjects/{id}
``
| Example |
| --- |
| localhost:8080/student-management-system/api/v1/subjects/2 |

## Teachers
### Get all teachers
``
GET - http://localhost:8080/student-management-system/api/v1/teachers/
``

### Get a teacher by ID
``
GET - http://localhost:8080/student-management-system/api/v1/teachers/{id}
``
| Example |
| --- |
| localhost:8080/student-management-system/api/v1/teachers/8 |

### Get a teacher by Lastname
``
GET - http://localhost:8080/student-management-system/api/v1/teachers/search?lastName={lastName}
``
| Example |
| --- |
| localhost:8080/student-management-system/api/v1/teachers/search?lastName=Zelda |

### Get Student by subject and teacher
``
GET - http://localhost:8080/student-management-system/api/v1/teachers/getstudentbysubjectandteacher/{subject}/{teacher}
``
| Example |
| --- |
| localhost:8080/student-management-system/api/v1/teachers/getstudentbysubjectandteacher/Python/Link |

### Create a new teacher
``
POST - http://localhost:8080/student-management-system/api/v1/teachers/
``
- JSON-body
```
{
	"firstName": "Ganondorf",
	"lastName": "Light",
	"email": "ganondorf.light@mail.com",
	"phoneNumber": "0701234567"
}
```

### Update teachers information
``
PUT - http://localhost:8080/student-management-system/api/v1/teachers/
``
- JSON-body
```
{
	"id": 8,
	"firstName": "Mr",
	"lastName": "Toad",
	"email": "mr.toad@mail.com",
	"phoneNumber": "0701234567"
}
```

### Delete a teacher by ID
``
DELETE - http://localhost:8080/student-management-system/api/v1/teachers/{id}
``
| Example |
| --- |
| localhost:8080/student-management-system/api/v1/teachers/8 |

## Contributing
I did this myself for schoolproject.
