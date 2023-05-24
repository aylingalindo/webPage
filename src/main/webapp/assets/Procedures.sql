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
END //
DELIMITER ;



/**************************************************************************************************************************/

    

/**************************************************************************************************/


CREATE PROCEDURE advancedSearch(IN word VARCHAR(150), IN categoryy VARCHAR(50), IN initialDate DATE, IN finalDate DATE)
BEGIN
    DECLARE searchText VARCHAR(255);
    SET searchText = CONCAT('%', word, '%');

    /*Para cuando no se elige fecha de inicio ni final. Busqueda normal*/
    IF (initialDate IS NULL) AND (finalDate IS NULL) THEN 
        SELECT 'BUSQUEDA NORMAL' AS resultado FROM DUAL;
    END IF;

    /*Para cuando "De una fecha en adelante" + categoria*/
    IF (initialDate IS NOT NULL) AND (finalDate IS NULL) THEN 
        SELECT 'DE FECHA EN ADELANTE' AS resultado FROM DUAL;
    END IF;

    /*Para cuando "De fecha hacia atras" + categoria*/
    IF (initialDate IS NULL) AND (finalDate IS NOT NULL) THEN 
        SELECT 'DE FECHA HACIA ATRAS' AS resultado FROM DUAL;
    END IF;

    /*Para cuando "De fecha a fecha" + categoria*/
    IF (initialDate IS NOT NULL) AND (finalDate IS NOT NULL) THEN 
        SELECT 'DE FECHA A FECHA' AS resultado FROM DUAL;
    END IF;
END //

DELIMITER ;
    

/**************************************************************************************************/
DELIMITER //
CREATE PROCEDURE advancedSearch(IN word VARCHAR(150), IN categoryy VARCHAR(50), IN initialDate DATE, IN finalDate DATE)
BEGIN
    DECLARE searchText VARCHAR(255);
    SET searchText = CONCAT('%', word, '%');

    /*Para cuando no se elige fecha de inicio ni final. Busqueda normal*/
    IF (initialDate IS NULL) AND (finalDate IS NULL) THEN 
        SELECT 'BUSQUEDA NORMAL' AS resultado, post.id_post, post.id_category, post.title, post.description, post.media, post.post_status, post.post_user, 
                userInfo.first_name, userInfo.p_lastname , userInfo.profile_img, cat.category
        FROM TB_Posts post 
        INNER JOIN TB_User userInfo 
        ON userInfo.id_user = post.post_user
        INNER JOIN TB_Category cat
        ON post.id_category = cat.id_category
        WHERE (post.post_status = 1) AND (title LIKE searchText OR description LIKE searchText) 
            AND (cat.category = IFNULL(categoryy, cat.category))
        ORDER BY post.id_post DESC ;
    END IF;

    /*Para cuando "De fecha hacia adelante" + categoria*/
    IF (initialDate IS NOT NULL) AND (finalDate IS NULL) THEN 
        SELECT 'DE FECHA HACIA ADELANTE' AS resultado, post.id_post, post.id_category, post.title, post.description, post.media, post.post_status, post.post_user, 
                userInfo.first_name, userInfo.p_lastname , userInfo.profile_img, cat.category
        FROM TB_Posts post 
        INNER JOIN TB_User userInfo 
        ON userInfo.id_user = post.post_user
        INNER JOIN TB_Category cat
        ON post.id_category = cat.id_category
        WHERE (post.post_status = 1) AND (title LIKE searchText OR description LIKE searchText) 
            AND (cat.category = IFNULL(categoryy, cat.category)) AND (post.date_posted >= initialDate) 
        ORDER BY post.id_post DESC ;
    END IF;

    /*Para cuando "De fecha hacia atras" + categoria*/
    IF (initialDate IS NULL) AND (finalDate IS NOT NULL) THEN 
        SELECT 'DE FECHA HACIA ATRAS' AS resultado, post.id_post, post.id_category, post.title, post.description, post.media, post.post_status, post.post_user, 
                userInfo.first_name, userInfo.p_lastname , userInfo.profile_img, cat.category
        FROM TB_Posts post 
        INNER JOIN TB_User userInfo 
        ON userInfo.id_user = post.post_user
        INNER JOIN TB_Category cat
        ON post.id_category = cat.id_category
        WHERE (post.post_status = 1) AND (title LIKE searchText OR description LIKE searchText) 
            AND (cat.category = IFNULL(categoryy, cat.category)) AND (post.date_posted <= finalDate) 
        ORDER BY post.id_post DESC ;
    END IF;

     /*Para cuando "De fecha a fecha" + categoria*/
    IF (initialDate IS NOT NULL) AND (finalDate IS NOT NULL) THEN 
        SELECT 'DE FECHA A FECHA' AS resultado, post.id_post, post.id_category, post.title, post.description, post.media, post.post_status, post.post_user, 
                userInfo.first_name, userInfo.p_lastname , userInfo.profile_img, cat.category
        FROM TB_Posts post 
        INNER JOIN TB_User userInfo 
        ON userInfo.id_user = post.post_user
        INNER JOIN TB_Category cat
        ON post.id_category = cat.id_category
        WHERE (post.post_status = 1) AND (title LIKE searchText OR description LIKE searchText) 
            AND (cat.category = IFNULL(categoryy, cat.category)) AND (post.date_posted >= initialDate AND post.date_posted <= finalDate) 
        ORDER BY post.id_post DESC ;
    END IF;
END //
DELIMITER ;

/*OPCION 1: BUSQUEDA NORMAL*/
DELIMITER //
CREATE PROCEDURE advancedSearchNoDate(IN word VARCHAR(150), IN categoryy VARCHAR(50), IN initialDate DATE, IN finalDate DATE)
BEGIN
    DECLARE searchText VARCHAR(255);
    SET searchText = CONCAT('%', word, '%');
    /*Para cuando no se elige fecha de inicio ni final. Busqueda normal*/
    IF (initialDate IS NULL) AND (finalDate IS NULL) THEN 
        SELECT 'DE FECHA HACIA ATRAS' AS resultado, post.id_post, post.id_category, post.title, post.description, post.media, post.post_status, post.post_user, 
                userInfo.first_name, userInfo.p_lastname , userInfo.profile_img, cat.category
        FROM TB_Posts post 
        INNER JOIN TB_User userInfo 
        ON userInfo.id_user = post.post_user
        INNER JOIN TB_Category cat
        ON post.id_category = cat.id_category
        WHERE (post.post_status = 1) AND (title LIKE searchText OR description LIKE searchText) 
            AND (cat.category = IFNULL(categoryy, cat.category))
        ORDER BY post.id_post DESC ;
    END IF;
END //
DELIMITER ;
/*OPCION 2: FECHA HACIA ADELANTE*/
DELIMITER //

CREATE PROCEDURE advancedSearchDateToPresent(IN word VARCHAR(150), IN categoryy VARCHAR(50), IN initialDate DATE, IN finalDate DATE)
BEGIN
    DECLARE searchText VARCHAR(255);
    SET searchText = CONCAT('%', word, '%');
    /*Para cuando "De fecha hacia adelante" + categoria*/
    IF (initialDate IS NOT NULL) AND (finalDate IS NULL) THEN 
        SELECT 'DE FECHA HACIA ATRAS' AS resultado, post.id_post, post.id_category, post.title, post.description, post.media, post.post_status, post.post_user, 
                userInfo.first_name, userInfo.p_lastname , userInfo.profile_img, cat.category
        FROM TB_Posts post 
        INNER JOIN TB_User userInfo 
        ON userInfo.id_user = post.post_user
        INNER JOIN TB_Category cat
        ON post.id_category = cat.id_category
        WHERE (post.post_status = 1) AND (title LIKE searchText OR description LIKE searchText) 
            AND (cat.category = IFNULL(categoryy, cat.category)) AND (post.date_posted >= initialDate) 
        ORDER BY post.id_post DESC ;
    END IF;
END //
DELIMITER ;

/*OPCION 3: FECHA HACIA ATRAS*/
DELIMITER //

CREATE PROCEDURE advancedSearchDateToPast(IN word VARCHAR(150), IN categoryy VARCHAR(50), IN initialDate DATE, IN finalDate DATE)
BEGIN
    DECLARE searchText VARCHAR(255);
    SET searchText = CONCAT('%', word, '%');
    /*Para cuando "De fecha hacia atras" + categoria*/
    IF (initialDate IS NULL) AND (finalDate IS NOT NULL) THEN 
        SELECT 'DE FECHA HACIA ATRAS' AS resultado, post.id_post, post.id_category, post.title, post.description, post.media, post.post_status, post.post_user, 
                userInfo.first_name, userInfo.p_lastname , userInfo.profile_img, cat.category
        FROM TB_Posts post 
        INNER JOIN TB_User userInfo 
        ON userInfo.id_user = post.post_user
        INNER JOIN TB_Category cat
        ON post.id_category = cat.id_category
        WHERE (post.post_status = 1) AND (title LIKE searchText OR description LIKE searchText) 
            AND (cat.category = IFNULL(categoryy, cat.category)) AND (post.date_posted <= finalDate) 
        ORDER BY post.id_post DESC ;
    END IF;
END //
DELIMITER ;

/*OPCION 4: FECHA A FECHA*/
DELIMITER //

CREATE PROCEDURE advancedSearchDateToDate(IN word VARCHAR(150), IN categoryy VARCHAR(50), IN initialDate DATE, IN finalDate DATE)
BEGIN
    DECLARE searchText VARCHAR(255);
    SET searchText = CONCAT('%', word, '%');
    /*Para cuando "De fecha a fecha" + categoria*/
    IF (initialDate IS NOT NULL) AND (finalDate IS NOT NULL) THEN 
        SELECT 'DE FECHA A FECHA' AS resultado, post.id_post, post.id_category, post.title, post.description, post.media, post.post_status, post.post_user, 
                userInfo.first_name, userInfo.p_lastname , userInfo.profile_img, cat.category
        FROM TB_Posts post 
        INNER JOIN TB_User userInfo 
        ON userInfo.id_user = post.post_user
        INNER JOIN TB_Category cat
        ON post.id_category = cat.id_category
        WHERE (post.post_status = 1) AND (title LIKE searchText OR description LIKE searchText) 
            AND (cat.category = IFNULL(categoryy, cat.category)) AND (post.date_posted >= initialDate AND post.date_posted <= finalDate) 
        ORDER BY post.id_post DESC ;
    END IF;
END //
DELIMITER ;