# E-Bookstore-Api
## Description
### Endpoints
#### Book
**GET ENDPOINTS**
| Endpoint url                  |  Operation                          |
|:------------------------------|:------------------------------------|
|/books                         | Retrieve list of books              |
|/books/{id}                    | Retrieve a book                     |
|/series/{id}/books             | Retrieve books within a book series |
|/authors/{id}/books            | Retrieve a specific author's books  |
|/genres/{id}/books             | Retrieve books of a specific genre  |
|/books/{id}/related-books      | Retrieve related books              |
|/popular-books                 | Retrieve popular books              |


#### Author
**GET ENDPOINTS**
| Endpoint url                  |  Operation                          |
|:------------------------------|:------------------------------------|
|/authors                       | Retrieve list of authors            |
|/authors/{id}                  | Retrieve details of an author       |
|/popular-authors               | Retrieve popular authors            |


#### Genre
**GET ENDPOINTS**
| Endpoint url                  |  Operation                          |
|:------------------------------|:------------------------------------|
|/genres                        | Retrieve list of authors            |
|/genres/{id}                   | Retrieve a specific genre's details |


#### Series
**GET ENDPOINTS**
| Endpoint url                  |  Operation                          |
|:------------------------------|:------------------------------------|
|/series                        | Retrieve a list of book series      |
|/series/{id}                   | Retrieve details of a book series   |


**Other endpoints to be added**
