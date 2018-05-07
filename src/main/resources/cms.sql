create database if not exists cms
  default character set utf8mb4
  collate utf8mb4_general_ci;


drop table if exists page;
drop table if exists component;
drop table if exists user;
drop table if exists whitelist;
drop table if exists project;

create table if not exists project (
  `projectId`   varchar(64) primary key,
  `projectName` varchar(64)            NOT NULL,
  `createTime`  datetime default now() NOT NULL
);

create table if not exists user (
  `userId`    varchar(64) primary key                NOT NULL,
  `userName`  varchar(64) character set utf8mb4      NOT NULL,
  `password`  VARCHAR(128)                           NOT NULL,
  `projectId` varchar(64)                            NOT NULL,
  foreign key (projectId) references project (projectId)

);

create table if not exists whitelist (
  `whiteCode` varchar(128) not null primary key
);


create table if not exists component (
  `componentId`   varchar(64) primary key             NOT NULL
  comment '组件ID',
  `componentName` varchar(64) default 'component'
  comment '组件名称',
  `type`          varchar(64) default 'text'
  comment '组件的类型比如 text banner',
  `createTime`    DATETIME default now()              NOT NULL
  comment '组件创建的时间',
  `createBy`      varchar(64) default 'admin'
  comment '组件创建者',
  `category`      varchar(64) comment '组件类目，用来判断哪个项目',
  `icon`          varchar(64) comment '图标',
  `label`         varchar(64) comment '',
  `title`         varchar(64) comment '',
  `placeholder`   varchar(64) comment '占位图名称',
  `style`         varchar(3000) comment '组件样式'
);


create table if not exists page (
  `pageId`     varchar(64)                                             NOT NULL
  COMMENT '页面唯一ID',
  `pageName`   varchar(64)                                             not null
  COMMENT '页面名称 需要保证唯一',
  primary key (pageId),

  `status`     varchar(64)character set utf8mb4 default 'draft'
  comment '页面状态目前就三种 上线、下线、未上线',
  `createTime` datetime DEFAULT NOW()                                  not null
  COMMENT '页面创建时间',

  `minVersion` int comment '最低支持版本号',
  `type`       varchar(64)character set utf8mb4 comment '页面类型',
  `platform`   varchar(16) character set utf8mb4                       NOT NULL
  comment '页面适用的平台 app 小程序 或者都要',
  `editor`     varchar(64) character set utf8mb4                       NOT NULL
  comment '页面的修改人',
  `createBy`   varchar(64) character set utf8mb4                       NOT NULL
  comment '页面的创建人',

  `projectId`  varchar(64)                                             NOT NULL
  comment '项目ID',
  foreign key (projectId) references project (projectId),

  `pageInfo`   varchar(8192) character set utf8mb4 comment '页面数据'
);

set @test_id = 'aaaa-bbbbbbb-ccccccccc';
#默认测试项目
insert into project values (@test_id, '啦啦', now());

#默认用户admin
insert into user values ('00', 'admin', 'admin123', @test_id);


