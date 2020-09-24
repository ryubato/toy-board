--create schema BOARDS_SCHEMA;

--use BOARDS_SCHEMA;

create table boards (
    board_id bigint not null auto_increment,
    created_date datetime(6),
    last_modified_date datetime(6),
    content varchar(255),
    recommendation_count bigint,
    title varchar(255),
    view_count bigint,
    writer varchar(255) not null,
    primary key (board_id)
);

create table comments (
    comment_id bigint not null auto_increment,
    content varchar(255),
    writer varchar(255),
    parent_comment_id bigint,
    board_id bigint,
    primary key (comment_id)
);