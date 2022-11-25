-- CREATE TABLE Users (
--     username varchar(255),
--     password varchar(255),
--     billing varchar(255),
--     shipping varchar(255),
--     PRIMARY KEY (username)
-- );

-- insert into Users (username, password, billing, shipping)
-- values ('zicoz', '123', 'someBilling', 'someShipping'),
-- 		('ziya', '456', 'someBilling2', 'someShipping2')
		
-- insert into Users (username, password, billing, shipping)
-- values ('zi', 'c89efdaa54c0f20c7adf612882df0950f5a951637e0307cdcb4c672f298b8bc6', 'someBilling', 'someShipping')


-- CREATE  TABLE ITEMS(
--   ID varchar(100) not null,
--   category varchar(70) not null,
--   brand varchar(70) not null,
--   type1 varchar(70) not null,
--   name varchar(50) not null,
--   price varchar(50) not null,
--   description char not null, 
--   reviews char not null,
--   image char not null)

CREATE TABLE ITEMS (
  ID varchar(100) not null,
  category varchar(70) not null,
  brand varchar(70) not null,
  type1 varchar(70) not null,
  name varchar(50) not null,
  price varchar(50) not null,
  description varchar(100) not null, 
  reviews varchar(100) not null,
  image varchar(400) not null
  );

 

INSERT INTO ITEMS(category, name, ID , description , reviews , image) VALUES 
('A' , 'Item1' , '001', 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Iste illum ad pariatur, maxime magni laudantium, assumenda natus sequi minus exercitationem hic placeat mollitia ullam reiciendis incidunt laborum voluptatem eligendi aliquam quibusdam. Natus, reprehenderit! Exercitationem praesentium necessitatibus sapiente quibusdam, itaque laboriosam dignissimos? Fugiat architecto, magnam voluptatum rerum laborum necessitatibus assumenda natus.', 'very good', 'https://images.unsplash.com/photo-1669114656836-d5b9389a207e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1332&q=80'), 
('B' , 'Item2' , '002', 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Iste illum ad pariatur, maxime magni laudantium, assumenda natus sequi minus exercitationem hic placeat mollitia ullam reiciendis incidunt laborum voluptatem eligendi aliquam quibusdam. Natus, reprehenderit! Exercitationem praesentium necessitatibus sapiente quibusdam, itaque laboriosam dignissimos? Fugiat architecto, magnam voluptatum rerum laborum necessitatibus assumenda natus.', 'very good' ,'https://images.unsplash.com/photo-1669056875150-63fdfeb392a3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1332&q=80'), 
('C' , 'Item3' , '003', 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Iste illum ad pariatur, maxime magni laudantium, assumenda natus sequi minus exercitationem hic placeat mollitia ullam reiciendis incidunt laborum voluptatem eligendi aliquam quibusdam. Natus, reprehenderit! Exercitationem praesentium necessitatibus sapiente quibusdam, itaque laboriosam dignissimos? Fugiat architecto, magnam voluptatum rerum laborum necessitatibus assumenda natus.', 'so so' , 'https://images.unsplash.com/photo-1669041124233-295fb1ab1f4f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1332&q=80'), 
('D' , 'Item4' , '004', 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Iste illum ad pariatur, maxime magni laudantium, assumenda natus sequi minus exercitationem hic placeat mollitia ullam reiciendis incidunt laborum voluptatem eligendi aliquam quibusdam. Natus, reprehenderit! Exercitationem praesentium necessitatibus sapiente quibusdam, itaque laboriosam dignissimos? Fugiat architecto, magnam voluptatum rerum laborum necessitatibus assumenda natus.', 'asda', 'https://images.unsplash.com/photo-1669285265050-21b6b41e1575?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1332&q=80')


CREATE  TABLE ITEMS(
  ID varchar(100) not null,
  category varchar(70) not null,
  brand varchar(70) not null,
  type1 varchar(70) not null,
  name varchar (50) not null,
  price varchar (50) not null,
  description varchar(1000) not null, 
  reviews varchar(100) not null,
  image varchar(400) not null );

  

INSERT INTO ITEMS(category, name, ID , description , reviews , image, brand, price, type1) VALUES 
('A' , 'Item1' , '001', 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Iste illum ad pariatur, maxime magni laudantium, assumenda natus sequi minus exercitationem hic placeat mollitia ullam reiciendis incidunt laborum voluptatem eligendi aliquam quibusdam. Natus, reprehenderit! Exercitationem praesentium necessitatibus sapiente quibusdam, itaque laboriosam dignissimos? Fugiat architecto, magnam voluptatum rerum laborum necessitatibus assumenda natus.', 'very good', 'https://images.unsplash.com/photo-1669114656836-d5b9389a207e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1332&q=80','Brand1','20','item'), 
('B' , 'Item2' , '002', 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Iste illum ad pariatur, maxime magni laudantium, assumenda natus sequi minus exercitationem hic placeat mollitia ullam reiciendis incidunt laborum voluptatem eligendi aliquam quibusdam. Natus, reprehenderit! Exercitationem praesentium necessitatibus sapiente quibusdam, itaque laboriosam dignissimos? Fugiat architecto, magnam voluptatum rerum laborum necessitatibus assumenda natus.', 'very good' ,'https://images.unsplash.com/photo-1669056875150-63fdfeb392a3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1332&q=80','Brand2','30','item'), 
('C' , 'Item3' , '003', 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Iste illum ad pariatur, maxime magni laudantium, assumenda natus sequi minus exercitationem hic placeat mollitia ullam reiciendis incidunt laborum voluptatem eligendi aliquam quibusdam. Natus, reprehenderit! Exercitationem praesentium necessitatibus sapiente quibusdam, itaque laboriosam dignissimos? Fugiat architecto, magnam voluptatum rerum laborum necessitatibus assumenda natus.', 'so so' , 'https://images.unsplash.com/photo-1669041124233-295fb1ab1f4f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1332&q=80','Brand3','40','item'), 
('D' , 'Item4' , '004', 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Iste illum ad pariatur, maxime magni laudantium, assumenda natus sequi minus exercitationem hic placeat mollitia ullam reiciendis incidunt laborum voluptatem eligendi aliquam quibusdam. Natus, reprehenderit! Exercitationem praesentium necessitatibus sapiente quibusdam, itaque laboriosam dignissimos? Fugiat architecto, magnam voluptatum rerum laborum necessitatibus assumenda natus.', 'asda', 'https://images.unsplash.com/photo-1669285265050-21b6b41e1575?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1332&q=80','Brand4','50','item');
