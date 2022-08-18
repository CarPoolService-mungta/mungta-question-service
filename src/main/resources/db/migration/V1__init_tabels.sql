create table question
(
    id            bigint auto_increment
        primary key,
    createdDate   datetime     null,
    modifiedDate  datetime     null,
    displayStatus varchar(255) null,
    body          varchar(255) null,
    title         varchar(255) null,
    userId        bigint       null
);

create table response
(
    id           bigint auto_increment
        primary key,
    createdDate  datetime     null,
    modifiedDate datetime     null,
    adminId      bigint       null,
    body         varchar(255) null,
    title        varchar(255) null,
    question_id  bigint       null,
    constraint FKm4d4mcfd15oqapqsnivylwvwh
        foreign key (question_id) references question (id)
);

