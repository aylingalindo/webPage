CREATE database WomanIn;

USE  WomanIn;
CREATE DATABASE IF NOT EXISTS WomanIn;

CREATE TABLE IF NOT EXISTS TB_CatStatus(
	id_status 		INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	`status` 		VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS TB_User (
	id_user 		INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	first_name 		VARCHAR(20) NOT NULL,
	mid_name 		VARCHAR(20),
	p_lastname 		VARCHAR(20) NOT NULL,
	m_lastname 		VARCHAR(20),
	birthdate 		DATE NOT NULL, 
	email 			VARCHAR(100) UNIQUE KEY NOT NULL,
	username 		VARCHAR(30) NOT NULL,
	`password` 		VARCHAR(30) NOT NULL,
	profile_img 	VARCHAR(2000),
	cover_img 		VARCHAR(2000),
	singup_date 	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	city 			VARCHAR(20) NOT NULL,
	state 			VARCHAR(20) NOT NULL,
	country 		VARCHAR(20) NOT NULL,
	occupation 		VARCHAR(20),
	user_status 		INTEGER UNSIGNED,
	FOREIGN KEY(user_status) 
		REFERENCES TB_CatStatus(id_status)
);


CREATE TABLE IF NOT EXISTS TB_Posts(
	id_post 		INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	title 			VARCHAR(30) NOT NULL,
	description 		VARCHAR(50) NOT NULL,
	date_posted 		TIMESTAMP,
	media 			VARCHAR(2000),
	post_status 		INTEGER UNSIGNED NOT NULL,
	post_user 		INTEGER UNSIGNED NOT NULL,
    -- AY: le agregue el id_category y su foreign key 
    id_category	   INT,
	FOREIGN KEY(post_status) 
		REFERENCES TB_CatStatus(id_status),
	FOREIGN KEY(post_user) 
		REFERENCES TB_User(id_user),
	-- AY: la fk en cuestion
	FOREIGN KEY(id_category) 
		REFERENCES TB_Category(id_category)
);

CREATE TABLE IF NOT EXISTS TB_Likes(
	id_like 		INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	date 			TIMESTAMP,
	active 			ENUM('active', 'inactive') NOT NULL,
	like_post 		INTEGER UNSIGNED NOT NULL,
	like_user 		INTEGER UNSIGNED NOT NULL,
	FOREIGN KEY(like_post) 
		REFERENCES TB_Posts(id_post),
	FOREIGN KEY(like_user) 
		REFERENCES TB_User(id_user)
);

CREATE TABLE IF NOT EXISTS TB_Community(
	id_community 		INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	user_sender 		INTEGER UNSIGNED NOT NULL,
	user_reciever  		INTEGER UNSIGNED NOT NULL, 
	community_status 	INTEGER UNSIGNED NOT NULL,
	FOREIGN KEY(user_sender) 
		REFERENCES TB_User(id_user),
	FOREIGN KEY(user_reciever) 
		REFERENCES TB_User(id_user),
	FOREIGN KEY(community_status) 
		REFERENCES TB_CatStatus(id_status)
);

CREATE TABLE IF NOT EXISTS TB_Category(
	id_category INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	category VARCHAR(50) NOT NULL
);
-- AY: le quite el unsigned a tabla de categorias pq me daba problemas 
/* 
ALTER TABLE tb_category 
	drop primary key,
	drop column id_category

ALTER TABLE tb_category
	add column id_category int primary key auto_increment */


-- AY: borre esta tabla 
-- DROP table tb_postcategory
CREATE TABLE IF NOT EXISTS TB_PostCategory(
	id_postCategoy		INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	id_post			INT UNSIGNED NOT NULL,
	id_category		INT UNSIGNED NOT NULL,
	FOREIGN KEY(id_post) 
		REFERENCES TB_Posts(id_post),
	FOREIGN KEY(id_category) 
		REFERENCES TB_Category(id_category)
);


INSERT INTO TB_User(first_name, p_lastname, m_lastname, birthdate, email, username, `password`, singup_date, city, state, country, occupation )
			VALUES('Lola', 'Perez', 'Perez', current_date(), 'lola@email.com', 'lolola', 123, current_timestamp(), 'mty', 'nl', 'mx', 'estudiante');
            
            INSERT INTO TB_User(first_name, p_lastname, m_lastname, birthdate, email, username, `password`, singup_date, city, state, country, occupation )
			VALUES('" + + "', '" + + "', '" + + "', '" + + "', '" + + "', '" + + "', '" + + "', current_timestamp(), '" + + "', '" + + "'', '" + + "', " + + "');
            
            "INSERT INTO TB_User(first_name, p_lastname, m_lastname, birthdate, email, username, `password`, singup_date, city, state, country, occupation )\n" +
"			VALUES('" + + "', '" + + "', '" + + "', '" + + "', '" + + "', '" + + "', '" + + "', current_timestamp(), '" + + "', '" + + "'', '" + + "', " + + "');"

select * from TB_User;


ALTER TABLE `TB_User` 
MODIFY COLUMN `city` varchar(20) DEFAULT NULL;

ALTER TABLE `TB_User`
MODIFY COLUMN `state` varchar(20) DEFAULT NULL;

ALTER TABLE `TB_User`
MODIFY COLUMN `country` varchar(20) DEFAULT NULL;

ALTER TABLE `TB_User`
MODIFY COLUMN `occupation` varchar(20) DEFAULT NULL;

INSERT INTO TB_User(first_name, p_lastname, m_lastname, birthdate, email, profile_img, username, `password`) VALUES('Aylin','Galindo','Acosta','2023-04-14','aylin@correo.com','codicampusexpert.png','aylin123','Fdsmdfr1.');

ALTER TABLE `TB_User`
Drop COLUMN `mid_name`;

SELECT * FROM TB_User WHERE username = 'lolola' AND `password` = '123';

-- Modificaciones de mich
ALTER TABLE tb_user MODIFY COLUMN occupation VARCHAR(20) DEFAULT "";