CREATE TABLE department (
  id BIGINT PRIMARY KEY,
  name VARCHAR(50)
);

CREATE TABLE employee (
  id BIGINT NOT NULL PRIMARY KEY,
  firstname VARCHAR(50),
  surname  VARCHAR(50),
  departmentId BIGINT
);

ALTER TABLE employee
ADD FOREIGN KEY (departmentId) REFERENCES department (id);


CREATE TABLE organisation (
  id BIGINT NOT NULL,
  name VARCHAR(500) NOT NULL,
  url VARCHAR(800) NOT NULL,
  lat FLOAT NOT NULL,
  lng FLOAT NOT NULL,
  postcode VARCHAR(20) NOT NULL,
  type VARCHAR(45) NULL,
  summary VARCHAR(1000) NULL,
  branch VARCHAR(300) NULL,
  tags VARCHAR(100) NULL,
  PRIMARY KEY (id));
