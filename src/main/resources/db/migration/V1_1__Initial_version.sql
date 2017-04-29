CREATE TABLE "person" (
  "id" serial NOT NULL,
  "name" varchar(100) NOT NULL,
  "lastname" varchar(100) NOT NULL,
  "login" varchar(100) UNIQUE,
  "password" varchar(50),
  CONSTRAINT person_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "item" (
  "id" serial NOT NULL,
  "category" int,
  "place" bigint NOT NULL,
  "purchaseDate" DATE,
  "registrationDate" DATE,
  "responsiblePerson" bigint,
  "description" varchar(250),
  "name" varchar(100) NOT NULL,
  "inventarNumber" varchar(250) NOT NULL UNIQUE,
  "cost" money,
  CONSTRAINT item_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "category" (
  "id" serial NOT NULL,
  "name" varchar(100) NOT NULL UNIQUE,
  "description" varchar(250),
  CONSTRAINT category_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "place" (
  "id" serial NOT NULL,
  "name" varchar(100) NOT NULL UNIQUE,
  "description" varchar(250) NOT NULL,
  CONSTRAINT place_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "personRoles" (
  "personId" bigint NOT NULL,
  "role" int NOT NULL
) WITH (
OIDS=FALSE
);



CREATE TABLE "role" (
  "id" serial NOT NULL,
  "name" varchar(100) NOT NULL UNIQUE,
  "description" varchar(250),
  CONSTRAINT role_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "image" (
  "id" serial NOT NULL,
  "path" varchar,
  "item_id" bigint,
  CONSTRAINT image_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);




ALTER TABLE "item" ADD CONSTRAINT "item_fk0" FOREIGN KEY ("category") REFERENCES "category"("id");
ALTER TABLE "item" ADD CONSTRAINT "item_fk1" FOREIGN KEY ("place") REFERENCES "place"("id");
ALTER TABLE "item" ADD CONSTRAINT "item_fk2" FOREIGN KEY ("responsiblePerson") REFERENCES "person"("id");



ALTER TABLE "personRoles" ADD CONSTRAINT "personRoles_fk0" FOREIGN KEY ("personId") REFERENCES "person"("id");
ALTER TABLE "personRoles" ADD CONSTRAINT "personRoles_fk1" FOREIGN KEY ("role") REFERENCES "role"("id");


ALTER TABLE "image" ADD CONSTRAINT "image_fk0" FOREIGN KEY ("item_id") REFERENCES "item"("id");
