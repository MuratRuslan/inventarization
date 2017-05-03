CREATE TABLE "person" (
  "id" BIGINT NOT NULL,
  "name" varchar(100) NOT NULL,
  "lastname" varchar(100) NOT NULL,
  "role" VARCHAR(100) NOT NULL ,
  "login" varchar(100) UNIQUE,
  "password" varchar(50),
  CONSTRAINT person_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);

CREATE TABLE "role" (
  "id" BIGINT NOT NULL,
  "name" varchar(100) NOT NULL,
  "description" varchar(100) ,
  CONSTRAINT person_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);

CREATE TABLE "item" (
  "id" BIGINT NOT NULL,
  "category" int,
  "place" SERIAL NOT NULL,
  "purchase_date" timestamp,
  "registration_date" timestamp,
  "responsible_person" bigint,
  "description" varchar(250),
  "name" varchar(100) NOT NULL,
  "inventar_number" varchar(250) NOT NULL UNIQUE,
  "cost" numeric(19,2),
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


CREATE TABLE "image" (
  "id" BIGINT NOT NULL,
  "path" varchar,
  "item_id" bigint,
  CONSTRAINT image_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);




ALTER TABLE "item" ADD CONSTRAINT "item_fk0" FOREIGN KEY ("category") REFERENCES "category"("id");
ALTER TABLE "item" ADD CONSTRAINT "item_fk1" FOREIGN KEY ("place") REFERENCES "place"("id");
ALTER TABLE "item" ADD CONSTRAINT "item_fk2" FOREIGN KEY ("responsible_person") REFERENCES "person"("id");



ALTER TABLE "person_roles" ADD CONSTRAINT "person_roles_fk0" FOREIGN KEY ("person_id") REFERENCES "person"("id");
ALTER TABLE "person_roles" ADD CONSTRAINT "person_roles_fk1" FOREIGN KEY ("role") REFERENCES "role"("id");


ALTER TABLE "image" ADD CONSTRAINT "image_fk0" FOREIGN KEY ("item_id") REFERENCES "item"("id");
