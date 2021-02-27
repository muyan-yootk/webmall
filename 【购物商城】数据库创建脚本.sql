DROP DATABASE IF EXISTS mall ;
CREATE DATABASE mall CHARACTER SET UTF8 ;
USE mall ;
-- 创建数据表
CREATE TABLE member  (
   mid                varchar(50)         not null,
   name               varchar(30),
   password           varchar(32),
   level              int,
   CONSTRAINT pk_mid PRIMARY KEY (MID)
) engine=innodb ;
-- 创建商品信息表
CREATE TABLE goods (
	gid			bigint AUTO_INCREMENT ,
	name		varchar(50) ,
	price		double ,
	photo		varchar(100) ,
	CONSTRAINT pk_gid10 PRIMARY KEY(GID) 
) engine=innodb ;
-- 创建购物车表
CREATE TABLE shopcar (
   	scid	bigint AUTO_INCREMENT ,
	mid		varchar(50) ,
	gid 	bigint ,
	amount	int ,
	CONSTRAINT fk_scid PRIMARY KEY(scid) ,
	CONSTRAINT fk_mid FOREIGN KEY(mid) REFERENCES member(mid) ,
	CONSTRAINT fk_gid FOREIGN KEY(gid) REFERENCES goods(gid)
) engine=innodb ; 
-- 创建省份表
CREATE TABLE province (
   pid                  bigint AUTO_INCREMENT ,
   title                varchar(50) not null,
   CONSTRAINT pk_pid PRIMARY KEY (pid)
) engine=innodb;
-- 创建城市表
CREATE TABLE city (
   cid                  bigint AUTO_INCREMENT ,
   pid                  bigint,
   title                varchar(50) not null,
   CONSTRAINT pk_cid primary key (cid) ,
   CONSTRAINT fk_pid FOREIGN KEY(pid) REFERENCES province(pid) ON DELETE CASCADE
) engine=innodb;

-- 创建订单表
CREATE TABLE orders (
   oid                  bigint AUTO_INCREMENT ,
   mid                  varchar(50),
   pid                 	bigint,
   cid                 	bigint,
   subdate              datetime,
   price                double,
   note                 text,
   name					varchar(50) ,
   phone				varchar(50) ,
   address				varchar(200) ,
   CONSTRAINT pk_oid PRIMARY KEY (oid) ,
   CONSTRAINT fk_mid5 FOREIGN KEY(mid) REFERENCES member(mid) ON DELETE CASCADE ,
   CONSTRAINT fk_pid5 FOREIGN KEY(pid) REFERENCES province(pid) ON DELETE CASCADE, 
   CONSTRAINT fk_cid5 FOREIGN KEY(cid) REFERENCES city(pid) ON DELETE CASCADE 
) engine=innodb;
-- 创建订单详情
CREATE TABLE details (
   dtid                 bigint AUTO_INCREMENT ,
   oid                  bigint,
   gid                  bigint,
   amount           	bigint,
   CONSTRAINT pk_dtid PRIMARY KEY (dtid) ,
   CONSTRAINT fk_oid5 FOREIGN KEY(oid) REFERENCES orders(oid) ON DELETE CASCADE ,
   CONSTRAINT fk_gid5 FOREIGN KEY(gid) REFERENCES goods(gid) ON DELETE CASCADE
) engine=innodb;

-- 增加用户：admin/hello
INSERT INTO member(mid,name,level,password) VALUES ('admin','管理员',1,'942AFC60271E48737EB1F32001667A2A') ;
-- 增加用户：user/hello
INSERT INTO member(mid,name,level,password) VALUES ('user','用户',0,'942AFC60271E48737EB1F32001667A2A') ;

INSERT INTO province (title) VALUES 
 ('其他'), ('北京'), ('重庆'), ('福建'), ('甘肃'), ('广东'),
 ('广西'), ('贵州'), ('海南'), ('河北'), ('黑龙江'), ('河南'),
 ('香港'), ('湖北'), ('湖南'), ('江苏'), ('江西'), ('吉林'),
 ('辽宁'), ('澳门'), ('内蒙古'), ('宁夏'), ('青海'), ('山东'),
 ('上海'), ('山西'), ('陕西'), ('四川'), ('台湾'), ('天津'),
 ('新疆'), ('西藏'), ('云南'), ('浙江'), ('安徽');

INSERT INTO city (pid,title) VALUES 
 (1,'其他'),(35,'合肥'), (35,'安庆'), (35,'蚌埠'), (35,'亳州'), (35,'巢湖'), (35,'滁州'),
 (35,'阜阳'), (35,'贵池'), (35,'淮北'), (35,'淮化'), (35,'淮南'), (35,'黄山'),
 (35,'九华山'), (35,'六安'), (35,'马鞍山'), (35,'宿州'), (35,'铜陵'), (35,'屯溪'),
 (35,'芜湖'), (35,'宣城'), (2,'北京'), (3,'重庆'), (4,'福州'), (4,'福安'),
 (4,'龙岩'), (4,'南平'), (4,'宁德'), (4,'莆田'), (4,'泉州'), (4,'三明'),
 (4,'邵武'), (4,'石狮'), (4,'永安'), (4,'武夷山'), (4,'厦门'), (4,'漳州'),
 (5,'兰州'), (5,'白银'), (5,'定西'), (5,'敦煌'), (5,'甘南'), (5,'金昌');

INSERT INTO city (pid,title) VALUES 
 (5,'酒泉'), (5,'临夏'), (5,'平凉'), (5,'天水'), (5,'武都'), (5,'武威'),
 (5,'西峰'), (5,'张掖'), (6,'广州'), (6,'潮阳'), (6,'潮州'), (6,'澄海'),
 (6,'东莞'), (6,'佛山'), (6,'河源'), (6,'惠州'), (6,'江门'), (6,'揭阳'),
 (6,'开平'), (6,'茂名'), (6,'梅州'), (6,'清远'), (6,'汕头'), (6,'汕尾'),
 (6,'韶关'), (6,'深圳'), (6,'顺德'), (6,'阳江'), (6,'英德'), (6,'云浮'),
 (6,'增城'), (6,'湛江'), (6,'肇庆'), (6,'中山'), (6,'珠海'), (7,'南宁');

INSERT INTO city (pid,title) VALUES 
 (7,'百色'), (7,'北海'), (7,'桂林'), (7,'防城港'), (7,'河池'), (7,'贺州'),
 (7,'柳州'), (7,'钦州'), (7,'梧州'), (7,'玉林'), (8,'贵阳'), (8,'安顺'),
 (8,'毕节'), (8,'都匀'), (8,'凯里'), (8,'六盘水'), (8,'铜仁'), (8,'兴义'),
 (8,'玉屏'), (8,'遵义'), (9,'海口'), (9,'儋县'), (9,'陵水'), (9,'琼海'),
 (9,'三亚'), (9,'五指山'), (9,'万宁'), (10,'石家庄'), (10,'保定'), (10,'北戴河'),
 (10,'沧州'), (10,'承德'), (10,'丰润'), (10,'邯郸'), (10,'衡水'), (10,'廊坊');

INSERT INTO city (pid,title) VALUES 
 (10,'南戴河'), (10,'秦皇岛'), (10,'唐山'), (10,'新城'), (10,'邢台'), (10,'张家口'),
 (11,'哈尔滨'), (11,'北安'), (11,'大庆'), (11,'大兴安岭'), (11,'鹤岗'), (11,'黑河'),
 (11,'佳木斯'), (11,'鸡西'), (11,'牡丹江'), (11,'齐齐哈尔'), (11,'七台河'), (11,'双鸭山'),
 (11,'绥化'), (11,'伊春'), (12,'郑州'), (12,'安阳'), (12,'鹤壁'), (12,'潢川'),
 (12,'焦作'), (12,'济源'), (12,'开封'), (12,'漯河'), (12,'洛阳'), (12,'南阳'), (12,'平顶山'),
 (12,'濮阳'), (12,'三门峡'), (12,'商丘'), (12,'新乡');

INSERT INTO city (pid,title) VALUES 
 (12,'信阳'), (12,'许昌'), (12,'周口'), (12,'驻马店'), (13,'香港'), (13,'九龙'),
 (13,'新界'), (14,'武汉'), (14,'恩施'), (14,'鄂州'), (14,'黄冈'), (14,'黄石'),
 (14,'荆门'), (14,'荆州'), (14,'潜江'), (14,'十堰'), (14,'随州'), (14,'武穴'), (14,'仙桃'),
 (14,'咸宁'), (14,'襄阳'), (14,'襄樊'), (14,'孝感'), (14,'宜昌'), (15,'长沙'), (15,'常德'),
 (15,'郴州'), (15,'衡阳'), (15,'怀化'), (15,'吉首'), (15,'娄底'), (15,'邵阳'), (15,'湘潭'),
 (15,'益阳'), (15,'岳阳'), (15,'永州');

INSERT INTO city (pid,title) VALUES 
 (15,'张家界'), (15,'株洲'), (16,'南京'), (16,'常熟'), (16,'常州'), (16,'海门'),
 (16,'淮安'), (16,'江都'), (16,'江阴'), (16,'昆山'), (16,'连云港'), (16,'南通'),
 (16,'启东'), (16,'沭阳'), (16,'宿迁'), (16,'苏州'), (16,'太仓'), (16,'泰州'),
 (16,'同里'), (16,'无锡'), (16,'徐州'), (16,'盐城'), (16,'扬州'), (16,'宜兴'),
 (16,'仪征'), (16,'张家港'), (16,'镇江'), (16,'周庄'), (17,'南昌'), (17,'抚州'),
 (17,'赣州'), (17,'吉安'), (17,'景德镇'), (17,'井冈山'), (17,'九江'), (17,'庐山');

INSERT INTO city (pid,title) VALUES 
 (17,'萍乡'), (17,'上饶'), (17,'新余'), (17,'宜春'), (17,'鹰潭'), (18,'长春'),
 (18,'白城'), (18,'白山'), (18,'珲春'), (18,'辽源'), (18,'梅河'), (18,'吉林'),
 (18,'四平'), (18,'松原'), (18,'通化'), (18,'延吉'), (19,'沈阳'), (19,'鞍山'),
 (19,'本溪'), (19,'朝阳'), (19,'大连'), (19,'丹东'), (19,'抚顺'), (19,'阜新'),
 (19,'葫芦岛'), (19,'锦州'), (19,'辽阳'), (19,'盘锦'), (19,'铁岭'), (19,'营口'),
 (20,'澳门'), (21,'呼和浩特'), (21,'阿拉善盟'), (21,'包头'), (21,'赤峰'), (21,'东胜');

INSERT INTO city (pid,title) VALUES 
 (21,'海拉尔'), (21,'集宁'), (21,'临河'), (21,'通辽'), (21,'乌海'), (21,'乌兰浩特'),
 (21,'锡林浩特'), (22,'银川'), (22,'固原'), (22,'石嘴山'), (22,'吴忠'), (23,'西宁'),
 (23,'德令哈'), (23,'格尔木'), (23,'共和'), (23,'海东'), (23,'海晏'), (23,'玛沁'),
 (23,'同仁'), (23,'玉树'), (24,'济南'), (24,'滨州'), (24,'兖州'), (24,'德州'),
 (24,'东营'), (24,'菏泽'), (24,'济宁'), (24,'莱芜'), (24,'聊城'), (24,'临沂'),
 (24,'蓬莱'), (24,'青岛'), (24,'曲阜'), (24,'日照'), (24,'泰安');

INSERT INTO city (pid,title) VALUES 
 (24,'潍坊'), (24,'威海'), (24,'烟台'), (24,'枣庄'), (24,'淄博'), (25,'上海'),
 (25,'崇明'), (25,'朱家角'), (26,'太原'), (26,'长治'), (26,'大同'), (26,'候马'),
 (26,'晋城'), (26,'离石'), (26,'临汾'), (26,'宁武'), (26,'朔州'), (26,'忻州'),
 (26,'阳泉'), (26,'榆次'), (26,'运城'), (27,'西安'), (27,'安康'), (27,'宝鸡'),
 (27,'汉中'), (27,'渭南'), (27,'商州'), (27,'绥德'), (27,'铜川'), (27,'咸阳'),
 (27,'延安'), (27,'榆林'), (28,'成都'), (28,'巴中'), (28,'达州'), (28,'德阳');

INSERT INTO city (pid,title) VALUES 
 (28,'都江堰'), (28,'峨眉山'), (28,'涪陵'), (28,'广安'), (28,'广元'), (28,'九寨沟'),
 (28,'康定'), (28,'乐山'), (28,'泸州'), (28,'马尔康'), (28,'绵阳'), (28,'眉山'),
 (28,'南充'), (28,'内江'), (28,'攀枝花'), (28,'遂宁'), (28,'汶川'), (28,'西昌'),
 (28,'雅安'), (28,'宜宾'), (28,'自贡'), (28,'资阳'), (29,'台北'), (29,'基隆'),
 (29,'台南'), (29,'台中'), (30,'天津'), (31,'乌鲁木齐'), (31,'阿克苏'), (31,'阿勒泰'),
 (31,'阿图什'), (31,'博乐'), (31,'昌吉'), (31,'东山'), (31,'哈密');

INSERT INTO city (pid,title) VALUES 
 (31,'和田'), (31,'喀什'), (31,'克拉玛依'), (31,'库车'), (31,'库尔勒'), (31,'奎屯'),
 (31,'石河子'), (31,'塔城'), (31,'吐鲁番'), (31,'伊宁'), (32,'拉萨'), (32,'阿里'),
 (32,'昌都'), (32,'林芝'), (32,'那曲'), (32,'日喀则'), (32,'山南'), (33,'昆明'),
 (33,'大理'), (33,'保山'), (33,'楚雄'), (33,'东川'), (33,'个旧'),
 (33,'景洪'), (33,'开远'), (33,'临沧'), (33,'丽江'), (33,'六库'), (33,'潞西'),
 (33,'曲靖'), (33,'思茅'), (33,'文山'), (33,'西双版纳'), (33,'玉溪');

INSERT INTO city (pid,title) VALUES 
 (33,'中甸'), (33,'昭通'), (34,'杭州'), (34,'安吉'), (34,'慈溪'), (34,'定海'),
 (34,'奉化'), (34,'海盐'), (34,'黄岩'), (34,'湖州'), (34,'嘉兴'), (34,'金华'),
 (34,'临安'), (34,'临海'), (34,'丽水'), (34,'宁波'), (34,'瓯海'), (34,'平湖'),
 (34,'千岛湖'), (34,'衢州'), (34,'江山'), (34,'瑞安'), (34,'绍兴'), (34,'嵊州'),
 (34,'台州'), (34,'温岭'), (34,'温州'), (34,'舟山') ;

INSERT INTO goods(name,price,photo) VALUES ('Java小白入门',48.6,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('Java就业编程实战',48.6,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('JavaWEB就业编程实战',48.6,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('Java项目构建与代码管理',48.6,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('Spring就业编程实战',48.6,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('SSM就业编程实战',48.6,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('Redis就业编程实战',48.6,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('Netty就业编程实战',48.6,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('HTML5就业编程实战',48.6,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('VUE.JS就业编程实战',48.6,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('SpringBoot就业编程实战',48.6,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('SpringCloud就业编程实战',48.6,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('Java架构师技术垃圾桶',48.6,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('Java开发实战经典',48.6,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('Java开发实战经典',79.8,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('JavaWEB开发实战经典',69.8,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('Android开发实战经典',89.8,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('Oracle开发实战经典',89.8,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('第一行代码Java',88.8,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('Java从入门到精通',65.8,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('SpringBoot微服务开发',65.8,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('SpringCloud微服务开发',65.8,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('Maven从入门到精通',55.8,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('XML编程语言',35.8,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('Ajax异步编程',85.8,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('Ubuntu系统从小白到大神',85.8,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('Nginx使用手册',95.8,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('MyCat服务指南',65.8,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('FastDFS服务指南',65.8,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('Redis缓存数据库',99.8,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('Spring从入门到精通',69.8,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('MyBatis从入门到精通',69.8,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('Shiro从入门到精通',69.8,'nophoto.jpg') ;
INSERT INTO goods(name,price,photo) VALUES ('Rest权威指南',69.8,'nophoto.jpg') ;
 
-- 提交事务
COMMIT ;

