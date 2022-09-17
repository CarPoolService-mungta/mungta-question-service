create table response
(
    id           bigint auto_increment
        primary key,
    createdDate  datetime     null,
    modifiedDate datetime     null,
    adminId      varchar(255)      null,
    body         varchar(255) null,
    title        varchar(255) null
);

create table question
(
    id            bigint auto_increment
        primary key,
    createdDate   datetime     null,
    modifiedDate  datetime     null,
    displayStatus varchar(255) null,
    body          varchar(255) null,
    title         varchar(255) null,
    userId        varchar(255) null,
    response_id  bigint       null,
    constraint FKm4d4mcfd15oqapqsnivylwvwh
        foreign key (response_id) references response (id)
);


