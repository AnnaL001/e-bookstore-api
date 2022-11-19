CREATE DATABASE e_bookstore;
\c e_bookstore;

CREATE TABLE socials (
                         id BIGSERIAL PRIMARY KEY,
                         instagram varchar,
                         facebook varchar,
                         twitter varchar
);

CREATE TABLE authors (
                         id BIGSERIAL PRIMARY KEY,
                         image varchar,
                         firstname varchar(255),
                         lastname varchar(255),
                         fullname varchar(255),
                         short_bio text,
                         reads int DEFAULT 0,
                         socials_id bigint references socials(id)
);

CREATE TABLE file_types (
                            id BIGSERIAL PRIMARY KEY,
                            name varchar(255)
);

CREATE TABLE books (
                       id BIGSERIAL PRIMARY KEY,
                       image varchar,
                       title varchar(255),
                       short_bio text,
                       volume int,
                       year int,
                       reads int DEFAULT 0,
                       publisher varchar(255),
                       language varchar(255),
                       isbn10 varchar(255),
                       isbn13 varchar(255),
                       file_size varchar(255),
                       file_type_id bigint references file_types(id),
                       is_stand_alone boolean
);

CREATE TABLE genres (
                        id BIGSERIAL PRIMARY KEY,
                        title varchar(255),
                        description text
);

CREATE TABLE series (
                        id BIGSERIAL PRIMARY KEY,
                        title varchar(255)
);

CREATE TABLE author_books (
                              id BIGSERIAL PRIMARY KEY,
                              book_id bigint references books(id),
                              author_id bigint references authors(id),
                              created_at timestamp
);

CREATE TABLE author_genres (
                               id BIGSERIAL PRIMARY KEY,
                               genre_id bigint references genres(id),
                               author_id bigint references authors(id),
                               created_at timestamp
);

CREATE TABLE genre_books (
                             id BIGSERIAL PRIMARY KEY,
                             book_id bigint references books(id),
                             genre_id bigint references genres(id),
                             created_at timestamp
);

CREATE TABLE author_series (
                               id BIGSERIAL PRIMARY KEY,
                               series_id bigint references series(id),
                               author_id bigint references authors(id),
                               created_at timestamp
);

CREATE TABLE book_series (
                             id BIGSERIAL PRIMARY KEY,
                             book_id bigint references books(id),
                             series_id bigint references series(id),
                             created_at timestamp
);

CREATE DATABASE e_bookstore_test WITH TEMPLATE e_bookstore;