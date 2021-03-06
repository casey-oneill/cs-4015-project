CREATE TABLE users(
    user_id     BIGINT(20)      NOT NULL AUTO_INCREMENT,
    email       VARCHAR(255),
    full_name   VARCHAR(255),
    password    VARCHAR(255),
    phone       VARCHAR(255),
    username    VARCHAR(255),
    PRIMARY KEY(user_id),
    UNIQUE (username)
);

CREATE TABLE books(
    book_type   VARCHAR(31)     NOT NULL,
    id          BIGINT(20)      NOT NULL AUTO_INCREMENT,
    authors     VARCHAR(255),
    description longtext,
    photo_urls  VARCHAR(255),
    price       double          NOT NULL,
    title       VARCHAR(255),
    user_id     BIGINT(20)      NOT NULL,
    digital_format  VARCHAR(255),
    digital_url VARCHAR(255),
    book_condition  VARCHAR(255),
    PRIMARY KEY(id)
);

INSERT INTO users (email, full_name, password, phone, username) VALUES ('pmollins@unb.ca','Pat Mollins','123456','5062594065','pmollins');
INSERT INTO users (email, full_name, password, phone, username) VALUES ('bin.liao@unb.ca','Bin Liao','123456','5064168899','bliao');
INSERT INTO users (email, full_name, password, phone, username) VALUES ('casey.oneill@unb.ca','Casey Oniell','123456','5068794521','coniell');
INSERT INTO users (email, full_name, password, phone, username) VALUES ('logan.fitzpatrick@unb.ca','Logan Fitzpatrick','123456','5064658921','lfitzpatrick');
INSERT INTO users (email, full_name, password, phone, username) VALUES ('qihao.guo@unb.ca','Qihao Guo','123456','5064561236','qguo');

INSERT INTO books (book_type, authors, description, photo_urls, price, title, user_id, digital_format, digital_url, book_condition)
            VALUES ('HARDCOVER','Comer','Description of book.','https://img.valorebooks.com/FULL/97/9780/978013/9780136061274.jpg','8.86','Computer Networks and Internets','1','NULL','NULL','LIKENEW');
INSERT INTO books (book_type, authors, description, photo_urls, price, title, user_id, digital_format, digital_url, book_condition)
            VALUES ('PAPERBACK','Connolly, Begg','Description of book.','https://img.valorebooks.com/FULL/97/9780/978032/9780321523068.jpg','19.12','Database Systems: A Practical Approach to Design, Implementation and Management (5th Edition)','1','NULL','NULL','FINE');
INSERT INTO books (book_type, authors, description, photo_urls, price, title, user_id, digital_format, digital_url, book_condition)
            VALUES ('DIGITAL','Valacich, George, Hoffer','Description of book.','https://img.valorebooks.com/FULL/97/9780/978013/9780136084969.jpg','3.55','Essentials of System Analysis and Design','2','PDF','NULL','NULL');
INSERT INTO books (book_type,  authors, description, photo_urls, price, title, user_id, digital_format, digital_url, book_condition)
            VALUES ('HARDCOVER','Guzdial, Ericson','Description of book.','https://img.valorebooks.com/FULL/97/9780/978013/9780136060611.jpg','7.02','Problem Solving with Data Structures Using Java: A Multimedia Approach','3','NULL','NULL','FINE');
INSERT INTO books (book_type, authors, description, photo_urls, price, title, user_id, digital_format, digital_url, book_condition)
            VALUES ('DIGITAL','Tony Gaddis','Description of book.','https://img.valorebooks.com/FULL/97/9780/978032/9780321512918.jpg','2.62','Starting Out with Games and Graphics in C++','4','PDF','NULL','NULL');
INSERT INTO books (book_type, authors, description, photo_urls, price, title, user_id, digital_format, digital_url, book_condition)
            VALUES ('HARDCOVER','Harrell, Ghosh, Bowden','Description of book.','https://img.valorebooks.com/FULL/97/9780/978007/9780073401300.jpg','53.63','Simulation Using Promodel','4','NULL','NULL','POOR');
INSERT INTO books (book_type, authors, description, photo_urls, price, title, user_id, digital_format, digital_url, book_condition)
            VALUES ('PAPERBACK','Motiwalla, Thompson','Description of book.','https://img.valorebooks.com/FULL/97/9780/978013/9780132145763.jpg','19.96','Enterprise Systems for Management (2nd Edition)','5','NULL','NULL','GOOD');
