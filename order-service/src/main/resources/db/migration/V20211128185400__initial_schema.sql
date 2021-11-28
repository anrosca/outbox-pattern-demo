create table purchase_orders(
    id varchar(100) primary key,
    customer_id bigint not null,
    order_date date not null
);

create table order_lines(
    id varchar(100) primary key,
    purchase_order_id varchar(100) not null references purchase_orders(id),
    item varchar(100) not null,
    quantity int not null,
    status varchar(25) not null,
    total_price double precision not null
);

create table outbox_event(
     id varchar(100) primary key,
     payload jsonb,
     type varchar(100) not null ,
     aggregate_type varchar(100) not null,
     aggregate_id varchar(100) not null
);
