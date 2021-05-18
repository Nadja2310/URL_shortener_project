create table URL IF NOT EXISTS(
    ID int not null,
    SHORT_URL varchar(300) not null
    LONG_URL varchar(200) not null
    EXPIRATION_DATE timestamp not null
    USERID int DEFAULT (0)
);