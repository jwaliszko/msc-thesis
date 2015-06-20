-- Table: "access"
       
CREATE TABLE "access"
(
  id serial NOT NULL,
  username character varying(100),
  "password" character varying(100),
  CONSTRAINT access_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "access" OWNER TO sa;

-- Table: district

CREATE TABLE district
(
  id serial NOT NULL,
  "name" character varying(100) NOT NULL,
  CONSTRAINT district_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE district OWNER TO sa;

-- Table: postal_code

CREATE TABLE postal_code
(
  id serial NOT NULL,
  "value" character varying(6) NOT NULL,
  CONSTRAINT postal_code_pkey PRIMARY KEY (id),
  CONSTRAINT postal_code_value_key UNIQUE (value)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE postal_code OWNER TO sa;

-- Table: street

CREATE TABLE street
(
  id serial NOT NULL,
  "name" character varying(100) NOT NULL,
  CONSTRAINT street_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE street OWNER TO sa;

-- Table: traffic_condition

CREATE TABLE traffic_condition
(
  id serial NOT NULL,
  parent_id integer,
  "name" character varying(100) NOT NULL,
  description character varying(100),  
  CONSTRAINT traffic_condition_pkey PRIMARY KEY (id),
  CONSTRAINT traffic_condition_parent_id_fkey FOREIGN KEY (parent_id)
      REFERENCES traffic_condition (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT traffic_condition_name_key UNIQUE (name)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE traffic_condition OWNER TO sa;

-- Table: traffic_condition_2_postal_code

CREATE TABLE traffic_condition_2_postal_code
(
  traffic_condition_id integer NOT NULL,
  postal_code_id integer NOT NULL,
  CONSTRAINT traffic_condition_2_postal_code_postal_code_id_fkey FOREIGN KEY (postal_code_id)
      REFERENCES postal_code (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT traffic_condition_2_postal_code_traffic_condition_id_fkey FOREIGN KEY (traffic_condition_id)
      REFERENCES traffic_condition (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE traffic_condition_2_postal_code OWNER TO sa;

-- Table: postal_code_2_street

CREATE TABLE postal_code_2_street
(
  postal_code_id integer NOT NULL,
  street_id integer NOT NULL,
  CONSTRAINT postal_code_2_street_postal_code_id_fkey FOREIGN KEY (postal_code_id)
      REFERENCES postal_code (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT postal_code_2_street_street_id_fkey FOREIGN KEY (street_id)
      REFERENCES street (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE postal_code_2_street OWNER TO sa;

-- Table: street_2_district

CREATE TABLE street_2_district
(
  street_id integer NOT NULL,
  district_id integer NOT NULL,
  CONSTRAINT street_2_district_district_id_fkey FOREIGN KEY (district_id)
      REFERENCES district (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT street_2_district_street_id_fkey FOREIGN KEY (street_id)
      REFERENCES street (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE street_2_district OWNER TO sa;