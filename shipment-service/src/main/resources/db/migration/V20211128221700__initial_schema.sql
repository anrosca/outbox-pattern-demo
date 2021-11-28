create table shipments(
    id varchar(100) primary key,
    order_id varchar(100) not null,
    customer_id bigint not null,
    order_date date not null
);

create table consumed_messages(
    message_id varchar(100) primary key,
    time_of_receiving timestamp not null
);
