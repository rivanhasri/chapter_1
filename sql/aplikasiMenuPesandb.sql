CREATE TABLE users (
    id uuid default gen_random_uuid(),
	username varchar(100) NOT NULL,
	email_address varchar(100) NOT NULL,
	"password" varchar(100) NOT NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id)
);

insert into users(username,email_address,"password") 
values ('rivan01','rivan@gmail.com','haha'),
('hasri02','hasri@gmail.com','hihi'),
('hapis03','hapis@gmail.com','huhu');

CREATE TABLE merchant (
    id uuid default gen_random_uuid(),
	merchant_name varchar(255) NOT NULL,
	merchant_location varchar(255) NOT NULL,
	"open" bool NOT NULL,
	CONSTRAINT merchant_pkey PRIMARY KEY (id)
);

insert into merchant(merchant_name,merchant_location,"open") 
values ('Merchant A','jakarta',TRUE),
('Merchant B','tangerang',TRUE),
('Merchant C','bandung',TRUE);

CREATE TABLE product (
    id uuid default gen_random_uuid(),
	product_name varchar(255) NOT NULL,
	price decimal(15, 1) NOT NULL,
	merchant_id uuid NOT NULL,
	CONSTRAINT product_pkey PRIMARY KEY (id),
	CONSTRAINT merchant_id_constraint FOREIGN KEY (merchant_id) REFERENCES merchant(id)
);

insert into product(product_name,price,merchant_id) 
values ('Nasi Goreng',10000.0,'43206aac-39b9-4347-b465-1ca5b9318f3e'),
('Es Teh',5000.0,'10684629-5287-45d2-a8c4-cd31c10ef895'),
('Bubur Ayam',12000.0,'dada2903-b1ef-4688-9703-c49fd25e0c95');

SELECT * FROM merchant;

CREATE TABLE orders (
    id uuid default gen_random_uuid(),
	order_time date NOT NULL,
	destination_address varchar(255) NOT NULL,
	user_id uuid NOT NULL,
	completed bool NOT NULL,
	CONSTRAINT orders_pkey PRIMARY KEY (id),
	CONSTRAINT user_id_constraint FOREIGN KEY (user_id) REFERENCES users(id)
);

SELECT * FROM users;

insert into orders(order_time,destination_address,user_id,completed) 
values ('2023-04-13','jakarta','5dad9c6b-ef37-4858-a48e-3927f4a5c9ee',TRUE),
('2023-09-13','jakarta','896f82c3-5fef-4395-91f5-dbef50aaf4b7',TRUE),
('2023-09-13','jakarta','896f82c3-5fef-4395-91f5-dbef50aaf4b7',TRUE);

CREATE TABLE order_detail (
    id uuid default gen_random_uuid(),
	order_id uuid NOT NULL,
	product_id uuid NOT NULL,
	quantity integer NOT NULL,
	CONSTRAINT order_detail_pkey PRIMARY KEY (id),
	CONSTRAINT product_id_constraint FOREIGN KEY (product_id) REFERENCES product(id),
	CONSTRAINT order_id_constraint FOREIGN KEY (order_id) REFERENCES orders(id)
);

insert into order_detail(order_id,product_id,quantity) 
values ('171cd67c-13ba-44db-aebf-d481da8e7d28','f1cdaf96-5664-4e2d-8b53-c42238614359',2),
('171cd67c-13ba-44db-aebf-d481da8e7d28','e8a25dd5-8477-4c6e-a3e8-6ef1db841604',2),
('e821e7db-f404-4960-9f62-66cc5f639f96','e8a25dd5-8477-4c6e-a3e8-6ef1db841604',3);

SELECT * FROM orders;
SELECT * FROM product;
