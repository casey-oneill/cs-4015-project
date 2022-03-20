DELETE FROM books;
DELETE FROM users;

INSERT INTO users (user_id, email, full_name, password, phone, username) VALUES ('1','pmollins@unb.ca','Pat Mollins','123456','5062594065','pmollins');
INSERT INTO users (user_id, email, full_name, password, phone, username) VALUES ('2','bin.liao@unb.ca','Bin Liao','123456','5064168899','bliao');
INSERT INTO users (user_id, email, full_name, password, phone, username) VALUES ('3','casey.oneill@unb.ca','Casey Oniell','123456','5068794521','coniell');
INSERT INTO users (user_id, email, full_name, password, phone, username) VALUES ('4','logan.fitzpatrick@unb.ca','Logan Fitzpatrick','123456','5064658921','lfitzpatrick');
INSERT INTO users (user_id, email, full_name, password, phone, username) VALUES ('5','qihao.guo@unb.ca','Qihao Guo','123456','5064561236','qguo');

INSERT INTO books (book_type, id, authors, description, photo_urls, price, title, user_id, digital_format, digital_url, book_condition) 
            VALUES ('HARDCOVER','1','Comer','Description of book.','https://img.valorebooks.com/FULL/97/9780/978013/9780136061274.jpg','8.86','Computer Networks and Internets','1','NULL','NULL','LIKENEW');
INSERT INTO books (book_type, id, authors, description, photo_urls, price, title, user_id, digital_format, digital_url, book_condition)
            VALUES ('PAPERBACK','2','Connolly, Begg','Description of book.','https://img.valorebooks.com/FULL/97/9780/978032/9780321523068.jpg','19.12','Database Systems: A Practical Approach to Design, Implementation and Management (5th Edition)','1','NULL','NULL','FINE');
INSERT INTO books (book_type, id, authors, description, photo_urls, price, title, user_id, digital_format, digital_url, book_condition)
            VALUES ('DIGITAL','3','Valacich, George, Hoffer','Description of book.','https://img.valorebooks.com/FULL/97/9780/978013/9780136084969.jpg','3.55','Essentials of System Analysis and Design','2','PDF','NULL','NULL');
INSERT INTO books (book_type, id, authors, description, photo_urls, price, title, user_id, digital_format, digital_url, book_condition)
            VALUES ('HARDCOVER','4','Guzdial, Ericson','Description of book.','https://img.valorebooks.com/FULL/97/9780/978013/9780136060611.jpg','7.02','Problem Solving with Data Structures Using Java: A Multimedia Approach','3','NULL','NULL','FINE');
INSERT INTO books (book_type, id, authors, description, photo_urls, price, title, user_id, digital_format, digital_url, book_condition)
            VALUES ('DIGITAL','5','Tony Gaddis','Description of book.','https://img.valorebooks.com/FULL/97/9780/978032/9780321512918.jpg','2.62','Starting Out with Games and Graphics in C++','4','PDF','NULL','NULL');
INSERT INTO books (book_type, id, authors, description, photo_urls, price, title, user_id, digital_format, digital_url, book_condition) 
            VALUES ('HARDCOVER','6','Harrell, Ghosh, Bowden','Description of book.','https://img.valorebooks.com/FULL/97/9780/978007/9780073401300.jpg','53.63','Simulation Using Promodel','4','NULL','NULL','POOR');
INSERT INTO books (book_type, id, authors, description, photo_urls, price, title, user_id, digital_format, digital_url, book_condition)
            VALUES ('PAPERBACK','7','Motiwalla, Thompson','Description of book.','https://img.valorebooks.com/FULL/97/9780/978013/9780132145763.jpg','19.96','Enterprise Systems for Management (2nd Edition)','5','NULL','NULL','GOOD');
