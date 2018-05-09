create database if not exists cms
  default character set utf8mb4
  collate utf8mb4_general_ci;


drop table if exists spu;
drop table if exists user;
drop table if exists cms_page;
drop table if exists component;

drop table if exists white_code;
drop table if exists project;


create table if not exists project (
  `project_id`   varchar(64) primary key,
  `project_name` varchar(64)            NOT NULL,
  `create_time`  datetime default now() NOT NULL,
  `platform_id`  int                    not null
);

create table if not exists user (
  `user_id`    varchar(64) primary key                NOT NULL,
  `user_name`  varchar(64) character set utf8mb4      NOT NULL,
  `password`   VARCHAR(128)                           NOT NULL,
  `project_id` varchar(64)                            NOT NULL,
  foreign key (project_id) references project (project_id)
    on update cascade
    on delete cascade

);

create table if not exists white_code (
  `id` varchar(128) not null primary key
);


create table if not exists component (
  `component_id`   varchar(64) primary key             NOT NULL
  comment '组件ID',
  `component_name` varchar(64) default 'component'
  comment '组件名称',
  `type`           varchar(64) default 'text'
  comment '组件的类型比如 text banner',
  `create_time`    DATETIME default now()              NOT NULL
  comment '组件创建的时间',
  `create_by`      varchar(64) default 'admin'
  comment '组件创建者',
  `category`       varchar(64) comment '组件类目，用来判断哪个项目',
  `icon`           varchar(64) comment '图标',
  `label`          varchar(64) comment '',
  `title`          varchar(64) comment '',
  `placeholder`    varchar(64) comment '占位图名称',
  `style`          varchar(3000) comment '组件样式'
);


create table if not exists cms_page (
  `page_id`     varchar(64)                                             NOT NULL
  COMMENT '页面唯一ID',
  `page_name`   varchar(64)                                             not null
  COMMENT '页面名称 需要保证唯一',
  primary key (page_id),

  `status`      varchar(64)character set utf8mb4 default 'draft'
  comment '页面状态目前就三种 上线、下线、未上线',
  `create_time` datetime DEFAULT NOW()                                  not null
  COMMENT '页面创建时间',

  `min_version` int comment '最低支持版本号',
  `type`        varchar(64)character set utf8mb4 comment '页面类型',
  `platform`    varchar(16) character set utf8mb4                       NOT NULL
  comment '页面适用的平台 app 小程序 或者都要',
  `editor`      varchar(64) character set utf8mb4                       NOT NULL
  comment '页面的修改人',
  `create_by`   varchar(64) character set utf8mb4                       NOT NULL
  comment '页面的创建人',

  `project_id`  varchar(64)                                             NOT NULL
  comment '项目ID',
  foreign key (project_id) references project (project_id)
    on delete cascade
    on update cascade,

  `page_info`   text(8192) character set utf8mb4 comment '页面数据'
);

create table if not exists spu (
  `spu_id`  INTEGER primary key,
  `shop_id` INTEGER,
  `page_id` varchar(64),
  foreign key (page_id) references cms_page (page_id)
    on delete cascade
    on update cascade

);

set @test_id = 'aaaa-bbbbbbb-ccccccccc';
set @tubobo = 'aaaaaaaaaaaaaaaaaaaaaaaa';
set @jutong = 'bbbbbbbbbbbbbbbbbbbbbbbb';
set @gongyinglian = 'cccccccccccccccccccc';
#默认测试项目
insert into project values (@test_id, '啦啦', now(), 0);

insert into project values (@tubobo, '四级严选', now(), 3);

insert into project values (@jutong, '聚童', now(), 1);

insert into project values (@gongyinglian, '车配供应链', now(), 2);

#默认用户admin
insert into user values ('00', 'admin', 'admin123', @test_id);


insert into user values ('01', 'mark', 'mark123', @gongyinglian)


