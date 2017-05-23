CREATE TABLE category
(
    id INTEGER PRIMARY KEY NOT NULL,
    description VARCHAR(255),
    name VARCHAR(255) NOT NULL
);
CREATE TABLE image
(
    id BIGINT PRIMARY KEY NOT NULL,
    path VARCHAR(255),
    item_id BIGINT
);
CREATE TABLE item
(
    id BIGINT PRIMARY KEY NOT NULL,
    article_number VARCHAR(255) NOT NULL,
    cost NUMERIC(19,2),
    description VARCHAR(255),
    name VARCHAR(255) NOT NULL,
    purchase_date TIMESTAMP,
    registration_date TIMESTAMP,
    category INTEGER,
    place INTEGER
);
CREATE TABLE person
(
    id BIGINT PRIMARY KEY NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE person_roles
(
    person_id BIGINT NOT NULL,
    role INTEGER NOT NULL
);
CREATE TABLE place
(
    id INTEGER PRIMARY KEY NOT NULL,
    description VARCHAR(255),
    name VARCHAR(255) NOT NULL
);
CREATE TABLE role
(
    id INTEGER PRIMARY KEY NOT NULL,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(250)
);

CREATE UNIQUE INDEX uk_46ccwnsi9409t36lurvtyljak ON category (name);
ALTER TABLE image ADD FOREIGN KEY (item_id) REFERENCES item (id);
ALTER TABLE item ADD FOREIGN KEY (category) REFERENCES category (id);
ALTER TABLE item ADD FOREIGN KEY (place) REFERENCES place (id);
CREATE UNIQUE INDEX uk_l1niyih32ddsh0ddmv6ahxe8p ON item (article_number);
CREATE UNIQUE INDEX uk_3tnwg2lomhbqckauuc1997bx7 ON person (login);
ALTER TABLE person_roles ADD FOREIGN KEY (person_id) REFERENCES person (id);
ALTER TABLE person_roles ADD FOREIGN KEY (role) REFERENCES role (id);
CREATE UNIQUE INDEX role_name_key ON role (name);
