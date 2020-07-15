```sqlite

create sequence hibernate_sequence start with 1 increment by 1;
create table ingredient
(
    ingredient_id bigint       not null,
    name          varchar(100) not null,
    quantity      varchar(50)  not null,
    meal_id       bigint,
    primary key (ingredient_id)
);
create table list_item
(
    list_item_id bigint       not null,
    name         varchar(100) not null,
    quantity     varchar(50)  not null,
    user_id      bigint,
    primary key (list_item_id)
);
create table meal
(
    meal_id      bigint        not null,
    instruction  varchar(5000) not null,
    name         varchar(100)  not null,
    prep_time    integer,
    requirements varchar(200),
    creator_id   bigint,
    primary key (meal_id)
);
create table user
(
    user_id   bigint       not null,
    name      varchar(255) not null,
    oauth_key varchar(255) not null,
    primary key (user_id)
);
create index IDXaf96hm5t3niptvpda7xmdbehe on meal (name);
alter table user
    add constraint UK_gj2fy3dcix7ph7k8684gka40c unique (name);
alter table user
    add constraint UK_rw9u8m4te7vpjhe2wuvh9imsr unique (oauth_key);
alter table ingredient
    add constraint FKq63cugnorv0lxo3haec3muinn foreign key (meal_id) references meal;
alter table list_item
    add constraint FK28p2pour2c4o6h22voxhpx0o6 foreign key (user_id) references user;
alter table meal
    add constraint FKsg7k1ak53ssup1yacnfx865v1 foreign key (creator_id) references user;
```