CREATE DATABASE IF NOT EXISTS travel_agency;

USE travel_agency;

DROP TABLE IF EXISTS discount;
DROP TABLE IF EXISTS tour_purchase;
DROP TABLE IF EXISTS statuses;
DROP TABLE IF EXISTS tour;
DROP TABLE IF EXISTS tour_type;
DROP TABLE IF EXISTS hotel_type;
DROP TABLE IF EXISTS user_account;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS roles;








CREATE TABLE roles(

                      # id has the INTEGER type (other name is INT), it is the primary key
        id INT NOT NULL PRIMARY KEY,

                      # name has the VARCHAR type - a string with a variable length
                          # names values should not be repeated (UNIQUE)
                          role_name ENUM('admin','user','moderator')
);

INSERT INTO roles VALUES(0, 'admin');
INSERT INTO roles VALUES(1, 'user');
INSERT INTO roles VALUES(2, 'moderator');

CREATE TABLE account(

-- 'AUTO_INCREMENT' means id is autoincrement field
-- (from 1 up to Integer.MAX_VALUE with the step 1)
                        id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,

-- 'UNIQUE' means logins values should not be repeated in login column of table
                        login VARCHAR(30) NOT NULL UNIQUE,

-- not null string columns
                        password VARCHAR(30) NOT NULL,
-- this declaration contains the foreign key constraint
-- role_id in users table is associated with id in roles table
-- role_id of user = id of role
                        role_id INT NOT NULL,
                        FOREIGN KEY (role_id) REFERENCES roles(id)
                            ON DELETE CASCADE
                            ON UPDATE RESTRICT
);

INSERT INTO account(login,password,role_id) VALUES('admin', 'pass',0);


CREATE TABLE user_account(
                             account_id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                             first_name VARCHAR(30) NOT NULL,
                             last_name VARCHAR(30) NOT NULL,
                             is_blocked BOOLEAN NOT NULL DEFAULT FALSE,
                             email VARCHAR(50) NOT NULL UNIQUE,
                             discount INTEGER NOT NULL DEFAULT (0),
                             FOREIGN KEY (account_id) REFERENCES account(id)
                                 ON DELETE CASCADE
                                 #---------------CHANGE PAROL
                                 ON UPDATE CASCADE
);

CREATE TABLE tour_type(

                          # id has the INTEGER type (other name is INT), it is the primary key
        id INT NOT NULL PRIMARY KEY,

                          # name has the VARCHAR type - a string with a variable length
                              # names values should not be repeated (UNIQUE)
                              tour_type_name ENUM('shopping','excursion','vacation') NOT NULL
);



INSERT INTO tour_type VALUES (0,'vacation');
INSERT INTO tour_type VALUES (1,'excursion');
INSERT INTO tour_type VALUES (2,'shopping');


CREATE TABLE hotel_type(

                           # id has the INTEGER type (other name is INT), it is the primary key
        id INT NOT NULL PRIMARY KEY,

                           # name has the VARCHAR type - a string with a variable length
                               # names values should not be repeated (UNIQUE)
                               hotel_type_name ENUM('*','**','***','****','*****') NOT NULL

);


INSERT INTO hotel_type VALUES (0,'*');
INSERT INTO hotel_type VALUES (1,'**');
INSERT INTO hotel_type VALUES (2,'***');
INSERT INTO hotel_type VALUES (3,'****');
INSERT INTO hotel_type VALUES (4,'*****');


CREATE TABLE tour(
                     id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     tour_name VARCHAR(30) NOT NULL,
                     tour_type_id INT NOT NULL,
                     hotel_type_id INT NOT NULL,
                     number_of_participants INTEGER UNSIGNED NOT NULL DEFAULT 1,
                     number_of_tours INTEGER UNSIGNED NOT NULL DEFAULT 100,
                     price DECIMAL(6,2) NOT NULL,
                     is_hot TINYINT(1) NOT NULL DEFAULT FALSE,
                     is_deleted TINYINT(1) NOT NULL DEFAULT FALSE,
                     FOREIGN KEY (tour_type_id) REFERENCES tour_type(id),
                     FOREIGN KEY (hotel_type_id) REFERENCES hotel_type(id)
                         ON DELETE CASCADE
                         ON UPDATE RESTRICT
);

CREATE TABLE statuses(

                         # id has the INTEGER type (other name is INT), it is the primary key
        id INT NOT NULL PRIMARY KEY,

                         # name has the VARCHAR type - a string with a variable length
                             # names values should not be repeated (UNIQUE)
                             status_name ENUM('paid','registered','cancelled') NOT NULL
);

INSERT INTO statuses VALUES(0, 'registered');
INSERT INTO statuses VALUES(1, 'paid');
INSERT INTO statuses VALUES(2, 'cancelled');


CREATE TABLE discount(
                         id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         step INTEGER NOT NULL,
                         max INTEGER NOT NULL
);

#default discount
INSERT INTO discount(id,step,max) VALUES (1,5,20);



CREATE TABLE tour_purchase(
                              id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                              price DECIMAL(6,2) NOT NULL,
                              status_id INT NOT NULL DEFAULT 0,
                              account_user_id BIGINT NOT NULL,
                              tour_id BIGINT NOT NULL,
                              number_of_tours INTEGER UNSIGNED NOT NULL,
                              date_of_purchase DATETIME DEFAULT (NOW()),
                              FOREIGN KEY (status_id) REFERENCES statuses(id)
                                  ON DELETE CASCADE
                                  ON UPDATE RESTRICT,
                              FOREIGN KEY (account_user_id)  REFERENCES user_account(account_id)
                                  ON DELETE CASCADE
                                  ON UPDATE CASCADE,
                              FOREIGN KEY (tour_id) REFERENCES tour(id)
                                  ON DELETE CASCADE
                                  ON UPDATE CASCADE
);

