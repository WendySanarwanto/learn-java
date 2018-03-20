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

-- Create schedule table
CREATE TABLE `contacts-demo`.schedule (
	id BIGINT NOT NULL AUTO_INCREMENT,
	title varchar(100) NOT NULL,
	beginDate DATE NOT NULL,
	endDate DATE NOT NULL,
	description varchar(255) NULL,
	organiser varchar(20) NOT NULL,
	CONSTRAINT schedule_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci ;

-- Create ScheduleContact table
CREATE TABLE `contacts-demo`.scheduleContact (
	scheduleId BIGINT NOT NULL,
	contactId INT NOT NULL
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci ;
