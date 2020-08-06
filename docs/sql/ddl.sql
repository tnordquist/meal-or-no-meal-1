-- noinspection SqlNoDataSourceInspectionForFile

create sequence hibernate_sequence start with 1 increment by 1
create table calendar
(
    calendar_id bigint       not null,
    date        date         not null,
    meal_slot   varchar(255) not null,
    user_id     bigint       not null,
    meal_id     bigint       not null,
    primary key (calendar_id)
);
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
alter table calendar
    add constraint FKcox0v4xnhs8qc7g4m34yuyvkq foreign key (user_id) references user;
alter table calendar
    add constraint FKaxknke3sav892mnm1riggex42 foreign key (meal_id) references meal;
alter table ingredient
    add constraint FKq63cugnorv0lxo3haec3muinn foreign key (meal_id) references meal;
alter table list_item
    add constraint FK28p2pour2c4o6h22voxhpx0o6 foreign key (user_id) references user;
alter table meal
    add constraint FKsg7k1ak53ssup1yacnfx865v1 foreign key (creator_id) references user;
create sequence hibernate_sequence start with 1 increment by 1
create table calendar (calendar_id bigint not null, date date not null, meal_slot varchar(255) not null, user_id bigint not null, meal_id bigint not null, primary key (calendar_id))
create table ingredient (ingredient_id bigint not null, name varchar(100) not null, quantity varchar(50) not null, meal_id bigint, primary key (ingredient_id))
create table list_item (list_item_id bigint not null, name varchar(100) not null, quantity varchar(50) not null, user_id bigint, primary key (list_item_id))
create table meal (meal_id bigint not null, instruction varchar(5000) not null, name varchar(100) not null, prep_time integer, requirements varchar(200), creator_id bigint, primary key (meal_id))
create table user (user_id bigint not null, name varchar(255) not null, oauth_key varchar(255) not null, primary key (user_id))
create index IDXaf96hm5t3niptvpda7xmdbehe on meal (name)
alter table user add constraint UK_gj2fy3dcix7ph7k8684gka40c unique (name)
alter table user add constraint UK_rw9u8m4te7vpjhe2wuvh9imsr unique (oauth_key)
alter table calendar add constraint FKcox0v4xnhs8qc7g4m34yuyvkq foreign key (user_id) references user
alter table calendar add constraint FKaxknke3sav892mnm1riggex42 foreign key (meal_id) references meal
alter table ingredient add constraint FKq63cugnorv0lxo3haec3muinn foreign key (meal_id) references meal
alter table list_item add constraint FK28p2pour2c4o6h22voxhpx0o6 foreign key (user_id) references user
alter table meal add constraint FKsg7k1ak53ssup1yacnfx865v1 foreign key (creator_id) references user
create sequence hibernate_sequence start with 1 increment by 1
create table calendar (calendar_id bigint not null, date date not null, meal_slot varchar(255) not null, user_id bigint not null, meal_id bigint not null, primary key (calendar_id))
create table ingredient (ingredient_id bigint not null, name varchar(100) not null, quantity varchar(50) not null, meal_id bigint, primary key (ingredient_id))
create table list_item (list_item_id bigint not null, name varchar(100) not null, quantity varchar(50) not null, user_id bigint, primary key (list_item_id))
create table meal (meal_id bigint not null, instruction varchar(5000) not null, name varchar(100) not null, prep_time integer, requirements varchar(200), creator_id bigint, primary key (meal_id))
create table user (user_id bigint not null, name varchar(255) not null, oauth_key varchar(255) not null, primary key (user_id))
create index IDXaf96hm5t3niptvpda7xmdbehe on meal (name)
alter table user add constraint UK_gj2fy3dcix7ph7k8684gka40c unique (name)
alter table user add constraint UK_rw9u8m4te7vpjhe2wuvh9imsr unique (oauth_key)
alter table calendar add constraint FKcox0v4xnhs8qc7g4m34yuyvkq foreign key (user_id) references user
alter table calendar add constraint FKaxknke3sav892mnm1riggex42 foreign key (meal_id) references meal
alter table ingredient add constraint FKq63cugnorv0lxo3haec3muinn foreign key (meal_id) references meal
alter table list_item add constraint FK28p2pour2c4o6h22voxhpx0o6 foreign key (user_id) references user
alter table meal add constraint FKsg7k1ak53ssup1yacnfx865v1 foreign key (creator_id) references user
create sequence hibernate_sequence start with 1 increment by 1
create table calendar (calendar_id bigint not null, date date not null, meal_slot varchar(255) not null, user_id bigint not null, meal_id bigint not null, primary key (calendar_id))
create table ingredient (ingredient_id bigint not null, name varchar(100) not null, quantity varchar(50) not null, meal_id bigint, primary key (ingredient_id))
create table list_item (list_item_id bigint not null, name varchar(100) not null, quantity varchar(50) not null, user_id bigint, primary key (list_item_id))
create table meal (meal_id bigint not null, instruction varchar(5000) not null, name varchar(100) not null, prep_time integer, requirements varchar(200), creator_id bigint, primary key (meal_id))
create table user (user_id bigint not null, name varchar(255) not null, oauth_key varchar(255) not null, primary key (user_id))
create index IDXaf96hm5t3niptvpda7xmdbehe on meal (name)
alter table user add constraint UK_gj2fy3dcix7ph7k8684gka40c unique (name)
alter table user add constraint UK_rw9u8m4te7vpjhe2wuvh9imsr unique (oauth_key)
alter table calendar add constraint FKcox0v4xnhs8qc7g4m34yuyvkq foreign key (user_id) references user
alter table calendar add constraint FKaxknke3sav892mnm1riggex42 foreign key (meal_id) references meal
alter table ingredient add constraint FKq63cugnorv0lxo3haec3muinn foreign key (meal_id) references meal
alter table list_item add constraint FK28p2pour2c4o6h22voxhpx0o6 foreign key (user_id) references user
alter table meal add constraint FKsg7k1ak53ssup1yacnfx865v1 foreign key (creator_id) references user
create sequence hibernate_sequence start with 1 increment by 1
create table calendar (calendar_id bigint not null, date date not null, meal_slot varchar(255) not null, user_id bigint not null, meal_id bigint not null, primary key (calendar_id))
create table ingredient (ingredient_id bigint not null, name varchar(100) not null, quantity varchar(50) not null, meal_id bigint, primary key (ingredient_id))
create table list_item (list_item_id bigint not null, name varchar(100) not null, quantity varchar(50) not null, user_id bigint, primary key (list_item_id))
create table meal (meal_id bigint not null, instruction varchar(5000) not null, name varchar(100) not null, prep_time integer, requirements varchar(200), creator_id bigint, primary key (meal_id))
create table user (user_id bigint not null, name varchar(255) not null, oauth_key varchar(255) not null, primary key (user_id))
create index IDXaf96hm5t3niptvpda7xmdbehe on meal (name)
alter table user add constraint UK_gj2fy3dcix7ph7k8684gka40c unique (name)
alter table user add constraint UK_rw9u8m4te7vpjhe2wuvh9imsr unique (oauth_key)
alter table calendar add constraint FKcox0v4xnhs8qc7g4m34yuyvkq foreign key (user_id) references user
alter table calendar add constraint FKaxknke3sav892mnm1riggex42 foreign key (meal_id) references meal
alter table ingredient add constraint FKq63cugnorv0lxo3haec3muinn foreign key (meal_id) references meal
alter table list_item add constraint FK28p2pour2c4o6h22voxhpx0o6 foreign key (user_id) references user
alter table meal add constraint FKsg7k1ak53ssup1yacnfx865v1 foreign key (creator_id) references user
create sequence hibernate_sequence start with 1 increment by 1
create table calendar (calendar_id bigint not null, date date not null, meal_slot varchar(255) not null, user_id bigint not null, meal_id bigint not null, primary key (calendar_id))
create table ingredient (ingredient_id bigint not null, name varchar(100) not null, quantity varchar(50) not null, meal_id bigint, primary key (ingredient_id))
create table list_item (list_item_id bigint not null, name varchar(100) not null, quantity varchar(50) not null, user_id bigint, primary key (list_item_id))
create table meal (meal_id bigint not null, instruction varchar(5000) not null, name varchar(100) not null, prep_time integer, requirements varchar(200), creator_id bigint, primary key (meal_id))
create table user (user_id bigint not null, name varchar(255) not null, oauth_key varchar(255) not null, primary key (user_id))
create index IDXaf96hm5t3niptvpda7xmdbehe on meal (name)
alter table user add constraint UK_gj2fy3dcix7ph7k8684gka40c unique (name)
alter table user add constraint UK_rw9u8m4te7vpjhe2wuvh9imsr unique (oauth_key)
alter table calendar add constraint FKcox0v4xnhs8qc7g4m34yuyvkq foreign key (user_id) references user
alter table calendar add constraint FKaxknke3sav892mnm1riggex42 foreign key (meal_id) references meal
alter table ingredient add constraint FKq63cugnorv0lxo3haec3muinn foreign key (meal_id) references meal
alter table list_item add constraint FK28p2pour2c4o6h22voxhpx0o6 foreign key (user_id) references user
alter table meal add constraint FKsg7k1ak53ssup1yacnfx865v1 foreign key (creator_id) references user
create sequence hibernate_sequence start with 1 increment by 1
create table calendar (calendar_id bigint not null, date date not null, meal_slot varchar(255) not null, user_id bigint not null, meal_id bigint not null, primary key (calendar_id))
create table ingredient (ingredient_id bigint not null, name varchar(100) not null, quantity varchar(50) not null, meal_id bigint, primary key (ingredient_id))
create table list_item (list_item_id bigint not null, name varchar(100) not null, quantity varchar(50) not null, user_id bigint, primary key (list_item_id))
create table meal (meal_id bigint not null, instruction varchar(5000) not null, name varchar(100) not null, prep_time integer, requirements varchar(200), creator_id bigint, primary key (meal_id))
create table user (user_id bigint not null, name varchar(255) not null, oauth_key varchar(255) not null, primary key (user_id))
create index IDXaf96hm5t3niptvpda7xmdbehe on meal (name)
alter table user add constraint UK_gj2fy3dcix7ph7k8684gka40c unique (name)
alter table user add constraint UK_rw9u8m4te7vpjhe2wuvh9imsr unique (oauth_key)
alter table calendar add constraint FKcox0v4xnhs8qc7g4m34yuyvkq foreign key (user_id) references user
alter table calendar add constraint FKaxknke3sav892mnm1riggex42 foreign key (meal_id) references meal
alter table ingredient add constraint FKq63cugnorv0lxo3haec3muinn foreign key (meal_id) references meal
alter table list_item add constraint FK28p2pour2c4o6h22voxhpx0o6 foreign key (user_id) references user
alter table meal add constraint FKsg7k1ak53ssup1yacnfx865v1 foreign key (creator_id) references user
create sequence hibernate_sequence start with 1 increment by 1
create table calendar (calendar_id bigint not null, date date not null, meal_slot integer not null, user_id bigint not null, meal_id bigint not null, primary key (calendar_id))
create table ingredient (ingredient_id bigint not null, name varchar(100) not null, quantity varchar(50) not null, meal_id bigint, primary key (ingredient_id))
create table list_item (list_item_id bigint not null, name varchar(100) not null, quantity varchar(50) not null, user_id bigint, primary key (list_item_id))
create table meal (meal_id bigint not null, instruction varchar(5000) not null, name varchar(100) not null, prep_time integer, requirements varchar(200), creator_id bigint, primary key (meal_id))
create table user (user_id bigint not null, name varchar(255) not null, oauth_key varchar(255) not null, primary key (user_id))
create index IDXaf96hm5t3niptvpda7xmdbehe on meal (name)
alter table user add constraint UK_gj2fy3dcix7ph7k8684gka40c unique (name)
alter table user add constraint UK_rw9u8m4te7vpjhe2wuvh9imsr unique (oauth_key)
alter table calendar add constraint FKcox0v4xnhs8qc7g4m34yuyvkq foreign key (user_id) references user
alter table calendar add constraint FKaxknke3sav892mnm1riggex42 foreign key (meal_id) references meal
alter table ingredient add constraint FKq63cugnorv0lxo3haec3muinn foreign key (meal_id) references meal
alter table list_item add constraint FK28p2pour2c4o6h22voxhpx0o6 foreign key (user_id) references user
alter table meal add constraint FKsg7k1ak53ssup1yacnfx865v1 foreign key (creator_id) references user
create sequence hibernate_sequence start with 1 increment by 1
create table calendar (calendar_id bigint not null, date date not null, meal_slot integer not null, user_id bigint not null, meal_id bigint not null, primary key (calendar_id))
create table ingredient (ingredient_id bigint not null, name varchar(100) not null, quantity varchar(50) not null, meal_id bigint not null, primary key (ingredient_id))
create table list_item (list_item_id bigint not null, name varchar(100) not null, quantity varchar(50) not null, user_id bigint, primary key (list_item_id))
create table meal (meal_id bigint not null, instruction varchar(5000) not null, name varchar(100) not null, prep_time integer, requirements varchar(200), creator_id bigint, primary key (meal_id))
create table user (user_id bigint not null, name varchar(255) not null, oauth_key varchar(255) not null, primary key (user_id))
create index IDXaf96hm5t3niptvpda7xmdbehe on meal (name)
alter table user add constraint UK_gj2fy3dcix7ph7k8684gka40c unique (name)
alter table user add constraint UK_rw9u8m4te7vpjhe2wuvh9imsr unique (oauth_key)
alter table calendar add constraint FKcox0v4xnhs8qc7g4m34yuyvkq foreign key (user_id) references user
alter table calendar add constraint FKaxknke3sav892mnm1riggex42 foreign key (meal_id) references meal
alter table ingredient add constraint FKq63cugnorv0lxo3haec3muinn foreign key (meal_id) references meal
alter table list_item add constraint FK28p2pour2c4o6h22voxhpx0o6 foreign key (user_id) references user
alter table meal add constraint FKsg7k1ak53ssup1yacnfx865v1 foreign key (creator_id) references user
create sequence hibernate_sequence start with 1 increment by 1
create table calendar (calendar_id bigint not null, date DATE not null, meal_slot integer not null, creator_id bigint not null, meal_id bigint not null, primary key (calendar_id))
create table ingredient (ingredient_id bigint not null, name varchar(100) not null, quantity varchar(50) not null, meal_id bigint not null, primary key (ingredient_id))
create table list_item (list_item_id bigint not null, name varchar(100) not null, quantity varchar(50) not null, user_id bigint, primary key (list_item_id))
create table meal (meal_id bigint not null, instruction varchar(5000) not null, name varchar(100) not null, prep_time integer, requirements varchar(200), creator_id bigint, primary key (meal_id))
create table user (user_id bigint not null, name varchar(255) not null, oauth_key varchar(255) not null, primary key (user_id))
create unique index UKkxkmjj83a76p0fblham0qxfem on calendar (creator_id, date, meal_slot)
create index IDXaf96hm5t3niptvpda7xmdbehe on meal (name)
alter table user add constraint UK_gj2fy3dcix7ph7k8684gka40c unique (name)
alter table user add constraint UK_rw9u8m4te7vpjhe2wuvh9imsr unique (oauth_key)
alter table calendar add constraint FKmatrdrpik1rtmtv8yrk0px925 foreign key (creator_id) references user
alter table calendar add constraint FKaxknke3sav892mnm1riggex42 foreign key (meal_id) references meal
alter table ingredient add constraint FKq63cugnorv0lxo3haec3muinn foreign key (meal_id) references meal
alter table list_item add constraint FK28p2pour2c4o6h22voxhpx0o6 foreign key (user_id) references user
alter table meal add constraint FKsg7k1ak53ssup1yacnfx865v1 foreign key (creator_id) references user
create sequence hibernate_sequence start with 1 increment by 1
create table calendar (calendar_id bigint not null, date DATE not null, meal_slot integer not null, creator_id bigint not null, meal_id bigint not null, primary key (calendar_id))
create table ingredient (ingredient_id bigint not null, name varchar(100) not null, quantity varchar(50) not null, meal_id bigint not null, primary key (ingredient_id))
create table list_item (list_item_id bigint not null, name varchar(100) not null, quantity varchar(50) not null, user_id bigint, primary key (list_item_id))
create table meal (meal_id bigint not null, instruction varchar(5000) not null, name varchar(100) not null, prep_time integer, requirements varchar(200), creator_id bigint, primary key (meal_id))
create table user (user_id bigint not null, name varchar(255) not null, oauth_key varchar(255) not null, primary key (user_id))
create unique index UKkxkmjj83a76p0fblham0qxfem on calendar (creator_id, date, meal_slot)
create index IDXaf96hm5t3niptvpda7xmdbehe on meal (name)
alter table user add constraint UK_gj2fy3dcix7ph7k8684gka40c unique (name)
alter table user add constraint UK_rw9u8m4te7vpjhe2wuvh9imsr unique (oauth_key)
alter table calendar add constraint FKmatrdrpik1rtmtv8yrk0px925 foreign key (creator_id) references user
alter table calendar add constraint FKaxknke3sav892mnm1riggex42 foreign key (meal_id) references meal
alter table ingredient add constraint FKq63cugnorv0lxo3haec3muinn foreign key (meal_id) references meal
alter table list_item add constraint FK28p2pour2c4o6h22voxhpx0o6 foreign key (user_id) references user
alter table meal add constraint FKsg7k1ak53ssup1yacnfx865v1 foreign key (creator_id) references user
