/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  miche
 * Created: 19 may 2023
 */

/*PROCEDURE PARA LA PAGINACION: Obtiene el numero de paginas que hay en total*/
DELIMITER //
CREATE PROCEDURE Pagination(IN postPerPage INT, OUT numPages INT)
BEGIN
    DECLARE totalPosts INT;
    SELECT COUNT(*) INTO totalPosts FROM tb_Posts;
    SET numPages = CEIL(totalPosts / postPerPage);
END //
DELIMITER ;


/*PROCEDURE PARA LA BUSQUEDA NORMAL*/
DELIMITER //
CREATE PROCEDURE normalSearch(IN op CHAR(1), IN word VARCHAR(150))
BEGIN
    DECLARE searchText VARCHAR(255);
    SET searchText = CONCAT('%', word, '%');

    IF op = 'A' THEN
        SELECT post.id_post, post.id_category, post.title, post.description, post.media, post.post_status, post.post_user, 
                userInfo.first_name, userInfo.p_lastname , userInfo.profile_img
        FROM TB_Posts post 
        INNER JOIN TB_User userInfo 
        ON userInfo.id_user = post.post_user
        WHERE (title LIKE searchText) OR (description LIKE searchText) ;
    END IF;

    IF
END //
DELIMITER ;

/*PROCEDURE PARA LA BUSQUEDA AVANZADA*/
DELIMITER //
CREATE PROCEDURE advancedSearch(IN word VARCHAR(150), IN category VARCHAR(50), IN initialDate DATE, IN finalDate DATE)
BEGIN
    DECLARE searchText VARCHAR(255);
    SET searchText = CONCAT('%', word, '%');
    
    DECLARE categoryBehaviour VARCHAR(50);
       
    /*Para cuando NO se elige categoria, ni fecha de inicio ni final. Busqueda normal*/
    IF (category IS NULL) AND (initialDate IS NULL) AND (finalDate IS NULL) THEN 
        SELECT post.id_post, post.id_category, post.title, post.description, post.media, post.post_status, post.post_user, 
                userInfo.first_name, userInfo.p_lastname , userInfo.profile_img
        FROM TB_Posts post 
        INNER JOIN TB_User userInfo 
        ON userInfo.id_user = post.post_user
        WHERE (title LIKE searchText) OR (description LIKE searchText) AND (post.post_status = 1);
    END IF;

    /*Para cuando SOLO CATEGORIA, no fechas*/
    IF (initialDate IS NULL) AND (finalDate IS NULL) THEN 
        SELECT post.id_post, post.id_category, post.title, post.description, post.media, post.post_status, post.post_user, 
                userInfo.first_name, userInfo.p_lastname , userInfo.profile_img, cat.category
        FROM TB_Posts post 
        INNER JOIN TB_User userInfo 
        ON userInfo.id_user = post.post_user
        INNER JOIN TB_Category cat
        ON post.id_category = cat.id_category
        WHERE ((title LIKE searchText) OR (description LIKE searchText)) AND category = cat.category;
    END IF;

    /*Para cuando "De una fecha en adelante" + categoria*/
    IF (category IS NOT NULL) AND (initialDate IS NOT NULL) AND (finalDate IS NULL)  THEN 
        SELECT post.id_post, post.id_category, post.title, post.description, post.media, post.post_status, post.post_user, 
                userInfo.first_name, userInfo.p_lastname , userInfo.profile_img, cat.category
        FROM TB_Posts post 
        INNER JOIN TB_User userInfo 
        ON userInfo.id_user = post.post_user
        INNER JOIN TB_Category cat
        ON post.id_category = cat.id_category
        WHERE ((title LIKE searchText) OR (description LIKE searchText)) AND category = cat.category;
    END IF;
    
    /*Para cuando "De una fecha en adelante" - categoria*/
    IF (category IS NOT NULL) AND (initialDate IS NOT NULL) AND (finalDate IS NULL)  THEN 
        SELECT post.id_post, post.id_category, post.title, post.description, post.media, post.post_status, post.post_user, 
                userInfo.first_name, userInfo.p_lastname , userInfo.profile_img, cat.category
        FROM TB_Posts post 
        INNER JOIN TB_User userInfo 
        ON userInfo.id_user = post.post_user
        INNER JOIN TB_Category cat
        ON post.id_category = cat.id_category
        WHERE ((title LIKE searchText) OR (description LIKE searchText) AND );
    END IF;
    

END //
DELIMITER ;

/*PROCEDURE PARA BUSCAR POR CATEGORIAS*/
DELIMITER //
CREATE PROCEDURE categoryFilter(IN category VARCHAR(50))
BEGIN
    SELECT post.id_post, post.id_category, post.title, post.description, post.media, post.post_status, post.post_user, 
            userInfo.first_name, userInfo.p_lastname , userInfo.profile_img, cat.category
    FROM TB_Posts post 
    INNER JOIN TB_User userInfo 
    ON userInfo.id_user = post.post_user
    INNER JOIN TB_Category cat
    ON post.id_category = cat.id_category
    WHERE category LIKE cat.category; 

END //
DELIMITER ;


/*
DELIMITER //
CREATE PROCEDURE Pagination(IN Op CHAR, IN postPerPage INT, IN page INT, OUT numPages INT)
BEGIN
    DECLARE totalPosts INT;
    DECLARE firstPost INT;
        SET firstPost = (page - 1) * postperPage;

    IF Op = 'A'  THEN-- Get total pages for pagination
        SELECT COUNT(*) INTO totalPosts FROM tb_Posts;
        SET numPages = CEIL(totalPosts / postPerPage);
    END IF;
    /*
    IF Op = 'B' THEN -- Limit the posts per pagination
        SELECT post.id_post, post.id_category, post.title, post.description, post.media, post.post_status, post.post_user, userInfo.first_name, userInfo.p_lastname , userInfo.profile_img 
        FROM TB_Posts post 
        INNER JOIN TB_User userInfo 
            ON userInfo.id_user = post.post_user 
        LIMIT firstPost, postPerPage;
     END IF;
        */
END //
DELIMITER ;
*/