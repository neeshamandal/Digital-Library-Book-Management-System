[digital Library.postman_collection.json](https://github.com/user-attachments/files/19511295/digital.Library.postman_collection.json)# 📚 Digital Library Management System

![Java](https://img.shields.io/badge/Java-21-%23ED8B00?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-%236DB33F?logo=spring)
![MongoDB](https://img.shields.io/badge/MongoDB-7.0-%2347A248?logo=mongodb)

A lightweight library system for managing books and borrow operations.

## 🚀 Quick Start

### Prerequisites
- Java 21
- MongoDB 7.0+

### 1. Run MongoDB
```bash
mongod --dbpath ~/data/db

# 📚 Digital Library Management System

![Swagger](https://img.shields.io/badge/Swagger-85EA2D?logo=swagger&logoColor=black)

## 🌐 API Documentation
We provide **interactive API docs** using Swagger UI:

### Access Swagger
Run the application locally and visit:  
🔗 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

![Swagger UI Preview](https://user-images.githubusercontent.com/12345678/1234567890-abcd1234-abcdefg.png)

### Key Endpoints
| Feature | Swagger Path |
|---------|-------------|
| Book Management | `/book-controller` |
| Borrow/Return | `/borrow-controller` |

### How We Configured Swagger
Added to `pom.xml`:
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.5.0</version>
</dependency>


postman collection:

[Uploading digital Li{
	"info": {
		"_postman_id": "1a658ae5-9078-403f-b2bd-729bb352f1c7",
		"name": "digital Library",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "8319816"
	},
	"item": [
		{
			"name": "get all books",
			"protocolProfileBehavior": {
				"disableBodyPruning": true
			},
			"request": {
				"method": "GET",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json"
					}
				],
				"body": {
					"mode": "raw",
					"raw": " {\n        \"bookId\": \"1234\",\n        \"title\": \"life of pi\",\n        \"author\": \"Yann Martel\",\n        \"availability\": \"CHECKED_OUT\"\n    }",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/books",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"books"
					]
				}
			},
			"response": []
		},
		{
			"name": "delete book",
			"request": {
				"method": "DELETE",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/books/1234",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"books",
						"1234"
					]
				}
			},
			"response": []
		},
		{
			"name": "search book by title or id",
			"protocolProfileBehavior": {
				"disableBodyPruning": true
			},
			"request": {
				"method": "GET",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json"
					}
				],
				"body": {
					"mode": "raw",
					"raw": " {\n        \"bookId\": \"1234\",\n        \"title\": \"life of pi\",\n        \"author\": \"Yann Martel\",\n        \"availability\": \"CHECKED_OUT\"\n    }",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/books/search?query=life of pi&page=0&size=3",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"books",
						"search"
					],
					"query": [
						{
							"key": "query",
							"value": "life of pi"
						},
						{
							"key": "page",
							"value": "0"
						},
						{
							"key": "size",
							"value": "3"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "add books",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json"
					}
				],
				"body": {
					"mode": "raw",
					"raw": " {\n        \"bookId\": \"12345\",\n        \"title\": \"life of pi\",\n        \"author\": \"Yann Martel\",\n        \"availability\": \"CHECKED_OUT\"\n    }",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/books",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"books"
					]
				}
			},
			"response": []
		},
		{
			"name": "update book",
			"request": {
				"method": "PUT",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json"
					}
				],
				"body": {
					"mode": "raw",
					"raw": " {\n        \"bookId\": \"12345\",\n        \"title\": \"life of pi\",\n        \"author\": \"Yann Martel\",\n        \"availability\": \"AVAILABLE\"\n    }",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/books/12345",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"books",
						"12345"
					]
				}
			},
			"response": []
		}
	]
}brary.postman_collection.json…]()
