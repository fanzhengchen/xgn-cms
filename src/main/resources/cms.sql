create database if not exists cms
  default character set utf8mb4
  collate utf8mb4_general_ci;

drop table if exists page;
drop table if exists project;
drop table if exists component;
drop table if exists user;

set @tu_bo_bo = 0;
set @si_ji = 1;
set @tu_cao = 2;

create table if not exists user (
  `userId`   INT PRIMARY KEY                   not null AUTO_INCREMENT,
  `userName` varchar(40) character set utf8mb4 NOT NULL,
  `priority` BIGINT unsigned                            default 0
  comment '标识项目权限，采用状态压缩',
  `password` VARCHAR(128)                      NOT NULL
);


create table if not exists project (
  `projectId`  INT primary key   auto_increment,
  `prjectName` varchar(40)            NOT NULL,
  `createTime` datetime default now() NOT NULL
);


create table if not exists component (
  `componentId`   int primary key      AUTO_INCREMENT NOT NULL
  comment '组件ID',
  `componentName` varchar(40) default 'component'
  comment '组件名称',
  `type`          varchar(40) default 'text'
  comment '组件的类型比如 text banner',
  `createTime`    DATETIME default now()              NOT NULL
  comment '组件创建的时间',
  `createBy`      varchar(40) default 'admin'
  comment '组件创建者',
  `category`      varchar(40) comment '组件类目，用来判断哪个项目',
  `icon`          varchar(40) comment '图标',
  `label`         varchar(40) comment '',
  `title`         varchar(40) comment '',
  `placeholder`   varchar(40) comment '占位图名称',
  `style`         varchar(3000) comment '组件样式'
);


create table if not exists page (
  `pageId`     int primary key    AUTO_INCREMENT                       NOT NULL
  COMMENT '页面唯一ID',
  `pageName`   varchar(40)                                             not null
  COMMENT '页面名称 需要保证唯一',
  `status`     enum ('online', 'offline', 'draft') character set utf8mb4 default 'draft'
  comment '页面状态目前就三种 上线、下线、未上线',
  `createTime` datetime DEFAULT NOW()                                  not null
  COMMENT '页面创建时间',

  `type`       varchar(40)character set utf8mb4 comment '页面类型',
  `beginTime`  datetime default NOW()                                  not null
  comment '生效时间 暂时可以不用',
  `endTime`    datetime default NOW()                                  not null
  comment '失效时间 暂时可以不用',
  `platform`   enum ('app', 'mini', 'appOrMini') character set utf8mb4 NOT NULL
  comment '页面适用的平台 app 小程序 或者都要',
  `editor`     varchar(40) character set utf8mb4                       NOT NULL
  comment '页面的修改人',
  `createBy`   varchar(40) character set utf8mb4                       NOT NULL
  comment '页面的创建人',

  `projectId`  int                                                     NOT NULL
  comment '项目ID',
  foreign key (projectId) references project (projectId)

);


