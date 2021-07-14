drop table if exists stock_table;
CREATE TABLE `stock_table`
(
    id                 int NOT NULL AUTO_INCREMENT,
    stock_name         varchar(125) COMMENT '股票名字',
    stock_num          varchar(125) COMMENT '股票num',
    begin_price        varchar(125) comment '今日开盘价',
    last_day_end_price varchar(125) comment '昨日收盘价',
    curr_price         varchar(125) comment '当前价格',
    day_max_price      varchar(125) comment '今日最高价',
    day_min_price      varchar(125) comment '今日最低价',
    buy_one            varchar(125) comment '竞买价，即“买一”报价',
    sell_one           varchar(125) comment '竞卖价，即“卖一”报价',
    sell_done_num      varchar(125) comment '成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百',
    sell_done_mon      varchar(125) comment '成交金额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万',
    buy_one_num        varchar(125) comment '买一”申请4695股，即47手',
    buy_one_mon        varchar(125) comment '买一报价',
    buy_two_num        varchar(125) comment '买二',
    buy_two_mon        varchar(125) comment '买二',
    buy_three_num      varchar(125) comment '买三',
    buy_three_mon      varchar(125) comment '买三',
    buy_fore_num       varchar(125) comment '买四',
    buy_fore_mon       varchar(125) comment '买四',
    buy_five_num       varchar(125) comment '买五',
    buy_five_mon       varchar(125) comment '买五',
    sell_one_num       varchar(125) comment '“卖一”申报3100股，即31手',
    sell_one_mon       varchar(125) comment '“卖一”报价',
    sell_two_num       varchar(125) comment '卖二',
    sell_two_mon       varchar(125) comment '卖二',
    sell_three_num     varchar(125) comment '卖三',
    sell_three_mon     varchar(125) comment '卖三',
    sell_fore_num      varchar(125) comment '卖四',
    sell_fore_mon      varchar(125) comment '卖四',
    sell_five_num      varchar(125) comment '卖五',
    sell_five_mon      varchar(125) comment '卖五',
    deal_date          datetime comment '卖五',
    create_time        datetime,
    update_time        datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment 'stock_table';
create index idx_stock_table ON stock_table(create_time,deal_date,stock_num,stock_name);
drop table if exists stock_main_table;
CREATE TABLE `stock_main_table`
(
    id                 int NOT NULL AUTO_INCREMENT,
    stock_name         varchar(125) COMMENT '股票名字',
    stock_num          varchar(125) COMMENT '股票num',
    begin_price        varchar(125) comment '今日开盘价',
    last_day_end_price varchar(125) comment '昨日收盘价',
    curr_price         varchar(125) comment '当前价格',
    day_max_price      varchar(125) comment '今日最高价',
    day_min_price      varchar(125) comment '今日最低价',
    buy_one            varchar(125) comment '竞买价，即“买一”报价',
    sell_one           varchar(125) comment '竞卖价，即“卖一”报价',
    sell_done_num      varchar(125) comment '成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百',
    sell_done_mon      varchar(125) comment '成交金额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万',
    deal_date          date comment 'deal_date',
    create_time        datetime,
    update_time        datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment 'stock_main_table';
/*
ALTER TABLE `stock_main_table`
    ADD COLUMN `stock_name_act`  varchar(125) DEFAULT null COMMENT 'stock_name' after stock_num;
ALTER TABLE stock_main_table DROP stock_name_act;*/

create index idx_stock_main_table ON stock_main_table(create_time,deal_date,stock_num);

drop table if exists stock_buy_table;
CREATE TABLE `stock_buy_table`
(
    id            int NOT NULL AUTO_INCREMENT,
    stock_num          varchar(125) COMMENT '股票num',
    buy_one_num   varchar(125) comment '买一”申请4695股，即47手',
    buy_one_mon   varchar(125) comment '买一报价',
    buy_two_num   varchar(125) comment '买二',
    buy_two_mon   varchar(125) comment '买二',
    buy_three_num varchar(125) comment '买三',
    buy_three_mon varchar(125) comment '买三',
    buy_fore_num  varchar(125) comment '买四',
    buy_fore_mon  varchar(125) comment '买四',
    buy_five_num  varchar(125) comment '买五',
    buy_five_mon  varchar(125) comment '买五',
    deal_date  date   comment 'deal_date',
    create_time   datetime,
    update_time   datetime,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment 'stock_buy_table';
create index idx_stock_buy_table ON stock_buy_table(create_time,deal_date,stock_num);

drop table if exists stock_sell_table;
CREATE TABLE `stock_sell_table`
(
    id             int NOT NULL AUTO_INCREMENT,
    stock_num          varchar(125) COMMENT '股票num',
    sell_one_num   varchar(125) comment '“卖一”申报3100股，即31手',
    sell_one_mon   varchar(125) comment '“卖一”报价',
    sell_two_num   varchar(125) comment '卖二',
    sell_two_mon   varchar(125) comment '卖二',
    sell_three_num varchar(125) comment '卖三',
    sell_three_mon varchar(125) comment '卖三',
    sell_fore_num  varchar(125) comment '卖四',
    sell_fore_mon  varchar(125) comment '卖四',
    sell_five_num  varchar(125) comment '卖五',
    sell_five_mon  varchar(125) comment '卖五',
    deal_date    date  comment 'deal_date',
    create_time    datetime,
    update_time    datetime,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment 'stock_sell_table';
create index idx_stock_sell_table ON stock_sell_table(create_time,deal_date,stock_num);

