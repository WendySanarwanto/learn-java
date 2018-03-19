-- Create contact table
CREATE TABLE `contacts-demo`.contact (
	id INT NOT NULL AUTO_INCREMENT,
	name varchar(100) NOT NULL,
	email varchar(100) NOT NULL,
	`type` varchar(10) DEFAULT 'Friend' NOT NULL,
	CONSTRAINT contact_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci ;

