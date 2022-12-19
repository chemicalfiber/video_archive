/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : video_archive

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 19/12/2022 17:25:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grant
-- ----------------------------
DROP TABLE IF EXISTS `grant`;
CREATE TABLE `grant`  (
  `g_id` int(1) NOT NULL COMMENT '权限ID',
  `g_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限描述',
  PRIMARY KEY (`g_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of grant
-- ----------------------------
INSERT INTO `grant` VALUES (1, '管理员');
INSERT INTO `grant` VALUES (2, '创作者');
INSERT INTO `grant` VALUES (3, '观众');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `u_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `u_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `u_nick_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `u_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `u_grant` int(1) NOT NULL DEFAULT 3 COMMENT '用户权限',
  PRIMARY KEY (`u_id`) USING BTREE,
  INDEX `grant`(`u_grant`) USING BTREE,
  CONSTRAINT `grant` FOREIGN KEY (`u_grant`) REFERENCES `grant` (`g_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'Admin', 'admin', 1);
INSERT INTO `user` VALUES (2, 'ChemicalFiber', '化学纤维', 'ChemicalFiber', 2);
INSERT INTO `user` VALUES (3, 'LunarMeal', 'Doc丶泛酱', 'hjl2022', 2);
INSERT INTO `user` VALUES (4, 'user', 'user', '1145141919810', 3);
INSERT INTO `user` VALUES (5, 'tt', 'ta', 'p@ssw0rfd', 2);
INSERT INTO `user` VALUES (6, 'ceshi', '测试用户', 'CESHIMIMA', 2);
INSERT INTO `user` VALUES (7, 'ceshi4', '测试用户445', '123456', 2);
INSERT INTO `user` VALUES (8, 'user1', '观众老爷233', 'mimamima', 3);
INSERT INTO `user` VALUES (9, 'jorunajong', 'CompanyWhite', 'Test56588323', 2);

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`  (
  `v_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '视频ID',
  `v_creator_id` int(10) NOT NULL COMMENT '创作者ID',
  `v_title` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频标题',
  `v_introduction` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频简介',
  `v_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频分区',
  `v_thumbnail` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频缩略图链接',
  `v_play_link` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频可直接播放地址',
  `v_publication_date` date NOT NULL COMMENT '发布日期',
  `v_bili_link` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'bilibili视频链接',
  PRIMARY KEY (`v_id`) USING BTREE,
  INDEX `creator`(`v_creator_id`) USING BTREE,
  CONSTRAINT `creator` FOREIGN KEY (`v_creator_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES (1, 2, '【AI绘画】90%的电脑都能用!没显卡 用CPU跑AI绘画吧! 无需特殊网络 低硬件要求-NovelAI SDWebUI-纤维想法多', '系列视频《纤维想法多》更新！想玩AI绘画却没有显卡？今天UP主为你带来仅使用CPU运行AI绘画框架的方法，让你随时随地都能画出你的小姐姐！\n\n无需特殊网络，超低硬件需求，100%本地运行，独立部署，让AI完全属于你～\n\n视频中用到的PDF文档：https://wwk.lanzoue.com/iTgSQ0f5j4kj\n\n同时欢迎加入群聊：131370108（光圈科技试验场beta）和我们一起进行技术探讨！', '知识', '/static/img/cover.png', 'http://192.168.1.10/chfs/shared/%E7%BA%A4%E7%BB%B4%E6%83%B3%E6%B3%95%E5%A4%9A/%E7%94%A8CPU%E6%9D%A5%E8%B7%91AI%E7%94%BB%E5%9B%BE/%E7%94%A8CPU%E6%9D%A5%E8%B7%91AI%E7%94%BB%E5%9B%BE.mp4', '2022-11-14', 'https://www.bilibili.com/video/BV1id4y1c7xM/');
INSERT INTO `video` VALUES (2, 2, '只需一行代码，从此软件装D盘｜比修改注册表更安全的方法-封装系统的骚操作', '系列视频：《封装系统的骚操作》更新！本视频将会教你如何更安全地“暗渡陈仓”将软件安装到D盘，比以前的修改注册表更稳定哦！\n\n同时欢迎各位加入qq群：630219512（光圈科技试验场）和我们一起讨论封装系统的技巧！\n\n视频中的背景音乐名字会在左上角出现，请留意哦！', '知识', 'http://192.168.1.10/chfs/shared/%E5%B0%81%E8%A3%85%E7%B3%BB%E7%BB%9F%E7%9A%84%E9%AA%9A%E6%93%8D%E4%BD%9C/%E4%B8%80%E8%A1%8C%E4%BB%A3%E7%A0%81%E8%AE%A9%E8%BD%AF%E4%BB%B6%E8%A3%85D%E7%9B%98/16-9.jpg', 'http://192.168.1.10/chfs/shared/%E5%B0%81%E8%A3%85%E7%B3%BB%E7%BB%9F%E7%9A%84%E9%AA%9A%E6%93%8D%E4%BD%9C/%E4%B8%80%E8%A1%8C%E4%BB%A3%E7%A0%81%E8%AE%A9%E8%BD%AF%E4%BB%B6%E8%A3%85D%E7%9B%98/music%E7%89%88%E6%9C%AC.mp4', '2022-08-21', 'https://www.bilibili.com/video/BV1HU4y1k7Q4/');
INSERT INTO `video` VALUES (3, 2, '拯救你的浏览器主页！解决主页劫持 硬核重置你的浏览器主页｜封装系统主页修改、劫持怎么办？-封装系统的骚操作', '浏览器主页总是被恶意劫持篡改，这让我们感到十分困扰，如何解决？\n\n让我来教你硬核地重置你浏览器的主页！\n\n视频中使用的脚本和Win RAR：https://wwn.lanzouf.com/b0dy1ijof\n\n密码:31kz\n\n封面图背景来源：https://natewren.com/themes/wallpaper2/moonshinepro/moonshine_wall_5.jpg，使用 署名-非商业性使用 4.0 国际 (CC BY-NC 4.0) 授权\n\n背景音乐来自YouTube音乐库：Russian Dance-Joey Pecoraro，Accordion-Andrew Huang', '知识', 'http://192.168.1.10/chfs/shared/%E5%B0%81%E8%A3%85%E7%B3%BB%E7%BB%9F%E7%9A%84%E9%AA%9A%E6%93%8D%E4%BD%9C/%E6%95%91%E5%9B%9E%E4%BD%A0%E7%9A%84%E4%B8%BB%E9%A1%B5/16_9.jpg', 'http://192.168.1.10/chfs/shared/%E5%B0%81%E8%A3%85%E7%B3%BB%E7%BB%9F%E7%9A%84%E9%AA%9A%E6%93%8D%E4%BD%9C/%E6%95%91%E5%9B%9E%E4%BD%A0%E7%9A%84%E4%B8%BB%E9%A1%B5/%E6%B7%BB%E5%8A%A0%E4%BA%86BGM.mp4', '2022-05-25', 'https://www.bilibili.com/video/BV1rY4y157Co/');
INSERT INTO `video` VALUES (4, 3, '你见过控制台界面的“象棋”程序吗？我用C++将它实现出来了', '用C++实现的控制台象棋程序\r\n在GitHub上开源：https://github.com/LunarMeal/Chess\r\nup主要个三连或关注不过分吧', '科技', 'http://192.168.1.10/chfs/shared/LunarMealCover/chess.jpg', 'null', '2022-05-02', 'https://www.bilibili.com/video/BV1BL4y1c7K1/');
INSERT INTO `video` VALUES (5, 3, '什么是指针？对C/C++的指针类型详解', '转型做做科普了(╹ڡ╹ )', '科技', 'http://192.168.1.10/chfs/shared/LunarMealCover/pointer.jpg', 'null', '2022-04-30', 'https://www.bilibili.com/video/BV1cv4y1K7Mu/');
INSERT INTO `video` VALUES (6, 3, '你知道变量命名的规范吗？', '发个小tips', '科技', 'http://192.168.1.10/chfs/shared/LunarMealCover/varname.jpg', 'null', '2022-04-21', 'https://www.bilibili.com/video/BV1yL4y1V7bH/');
INSERT INTO `video` VALUES (7, 3, '看到这样的博士，戈登忽然就不想走了', '求关注(～￣▽￣)～', '游戏', 'http://192.168.1.10/chfs/shared/LunarMealCover/Gordon.jpg', 'null', '2021-11-04', 'https://www.bilibili.com/video/BV1r3411k7nu/');
INSERT INTO `video` VALUES (8, 3, '【求生之路2】杰哥同款', '给作者一个关注可以吗？(～￣▽￣)～', '游戏', 'http://192.168.1.10/chfs/shared/LunarMealCover/jie.jpg', 'null', '2021-10-16', 'https://www.bilibili.com/video/BV1hq4y157sW/');
INSERT INTO `video` VALUES (9, 3, '【CSGO】鱿 鱼 游 戏', '只替换了人物模型，下期准备做个地图，还原鱿鱼游戏里的玩法，喜欢的话给作者一个关注吧(～￣▽￣)～', '游戏', 'http://192.168.1.10/chfs/shared/LunarMealCover/SquidGame.jpg', 'null', '2021-10-13', 'https://www.bilibili.com/video/BV1BP4y1t7R5/');
INSERT INTO `video` VALUES (10, 3, '这么Kawai的博士，你爱了吗？', '视频类型：blender+MMD动画\r\n模型：Doc丶泛酱\r\n场景：stage\r\nBGM：8bit Adventurer\r\n随手做的一个bl动画，因为是第一次做，所以有很多不好的地方惹。给作者一个小小的关注吧(～￣▽￣)～', '动画', 'http://192.168.1.10/chfs/shared/LunarMealCover/kawai.jpg', 'null', '2021-10-10', 'https://www.bilibili.com/video/BV1LR4y1H7uf/');
INSERT INTO `video` VALUES (11, 3, '【求生之路】我把绀野纯子做成了witch的MOD', '应一些粉丝的要求，完成了纯子的MOD，下期做多惠替换hunter的MOD，各位可以给个三连吗？喜欢的话给作者一个关注哦(～￣▽￣)～', '游戏', 'http://192.168.1.10/chfs/shared/LunarMealCover/witch.jpg', 'null', '2021-10-06', 'https://www.bilibili.com/video/BV14341117eH/');
INSERT INTO `video` VALUES (12, 3, '【自我介绍】来自一位博士身份的VUP的自述', '对之前出现的少女做一个简单的背景介绍，解答一些观众对我做的人物形象产生的疑问，相当于补充说明了。准确说我并不想全职当VUP,只是有时直播会用上虚拟形象而已，一般直播的时候也不会说太多话，各位看看就行，我主要是做MOD的，VUP只是相当于副业了，喜欢支持我的话可以给个关注吗？(～￣▽￣)～', '动画', 'http://192.168.1.10/chfs/shared/LunarMealCover/self.jpg', 'null', '2021-10-01', 'https://www.bilibili.com/video/BV1Xq4y1P7rV/');
INSERT INTO `video` VALUES (13, 3, '【求生之路2】我把《佐贺偶像》中的源樱做成了MOD', '各位可以点个关注吗？谢谢了，点赞过1000就把MOD发布到创意工坊', '游戏', 'http://192.168.1.10/chfs/shared/LunarMealCover/idol.jpg', 'null', '2021-09-26', 'https://www.bilibili.com/video/BV1CP4y1h7wT/');
INSERT INTO `video` VALUES (14, 3, '【CSGO】我 佛 慈 悲', '作者可以求个关注吗？\\(￣︶￣*\\))', '游戏', 'http://192.168.1.10/chfs/shared/LunarMealCover/monk.jpg', 'null', '2021-09-13', 'https://www.bilibili.com/video/BV1fq4y1Z77z/');
INSERT INTO `video` VALUES (15, 3, '人类高质量Gman', '随手做的一个SFM动画', '动画', 'http://192.168.1.10/chfs/shared/LunarMealCover/gman.jpg', 'null\'', '2021-08-16', 'https://www.bilibili.com/video/BV1CQ4y1275q');
INSERT INTO `video` VALUES (16, 3, '【熟肉】【半条命】丁真在联合帝国上的演讲', '花了几个小时做的，各位三连加关注支持一下吧', '动画', 'http://192.168.1.10/chfs/shared/LunarMealCover/tenzin.jpg', 'null', '2021-08-28', 'https://www.bilibili.com/video/BV1Fg411V7H8/');
INSERT INTO `video` VALUES (17, 3, '【求生之路】盘点在求生联机中的那些搞事行为（上）', '求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，求三连，最好再给个关注吧', '游戏', 'http://192.168.1.10/chfs/shared/LunarMealCover/l4d.jpg', 'null', '2020-02-11', 'https://www.bilibili.com/video/BV1s741157jG/');
INSERT INTO `video` VALUES (18, 2, '只要用鼠标点几下就能解决问题了！让我们为上次的工具加一个图形界面(GUI)吧！Java程序设计 Java编程技巧 批量文件命名-纤维想法多', '上次的程序似乎不是很友好呢……因为我们要在终端输入一长串命令才能让我们的小程序跑起来，不如添加一个GUI（图形用户界面）？只需要用鼠标点点点就能解决我们的问题了！\n\n前情提要：\n\nhttps://b23.tv/av547638091\n\n\n\n视频中的源代码：\n\nhttps://wwx.lanzoui.com/b0dxa921e\n\n密码:62cc （成品JAR包在对应的压缩包内的out文件夹下）\n\n注意⚠️你需要安装Java运行环境（JRE）才能运行这个JAR包⚠️', '知识', 'http://192.168.1.10/chfs/shared/%E7%BA%A4%E7%BB%B4%E6%83%B3%E6%B3%95%E5%A4%9A/%E6%96%87%E4%BB%B6%E5%89%8D%E7%BC%80%E5%A4%84%E7%90%86%E5%99%A8GUI/16-9-chs.png', 'http://192.168.1.10/chfs/shared/%E7%BA%A4%E7%BB%B4%E6%83%B3%E6%B3%95%E5%A4%9A/%E6%96%87%E4%BB%B6%E5%89%8D%E7%BC%80%E5%A4%84%E7%90%86%E5%99%A8GUI/%E6%96%87%E4%BB%B6%E5%90%8D%E5%89%8D%E7%BC%80%E5%A4%84%E7%90%86%E5%99%A8-GUI.mp4', '2021-10-11', 'https://www.bilibili.com/video/BV19v411g7Qf/');
INSERT INTO `video` VALUES (19, 2, '一个5KB的小工具 不止能省5分钟! 带你手写一个文件名处理工具 批量文件重命名 java编程技巧-纤维想法多', '批量重命名文件是一件非常头疼的事，今天我就来带大家手写一个文件名处理工具，帮大家节省不必要浪费的时间\n\n全新系列视频-《纤维想法多》：从生活中发现问题，并尝试设计程序去解决它！\n\n项目开源地址：\n\nGitHub：https://github.com/chemicalfiber/file-name-prefix-processor\n\n码云：https://gitee.com/chemicalfiber/file-name-prefix-processor', '知识', 'http://192.168.1.10/chfs/shared/%E7%BA%A4%E7%BB%B4%E6%83%B3%E6%B3%95%E5%A4%9A/%E6%96%87%E4%BB%B6%E5%89%8D%E7%BC%80%E5%A4%84%E7%90%86%E5%99%A8/n-16-9-chs.png', 'http://192.168.1.10/chfs/shared/%E7%BA%A4%E7%BB%B4%E6%83%B3%E6%B3%95%E5%A4%9A/%E6%96%87%E4%BB%B6%E5%89%8D%E7%BC%80%E5%A4%84%E7%90%86%E5%99%A8/%E6%96%87%E4%BB%B6%E5%90%8D%E5%89%8D%E7%BC%80%E5%A4%84%E7%90%86%E5%99%A8-%E7%AC%AC%E4%BA%8C%E7%89%88.mp4', '2021-08-27', 'https://www.bilibili.com/video/BV1Bq4y1K7Uz/');
INSERT INTO `video` VALUES (20, 2, '这次！来硬的！彻底清除驱动总裁2.0捆绑和残留项目-封装系统的骚操作', '终于...找到彻底干掉驱动总裁2.0的方法了！\n\n开源项目+下载地址：\n\n【GitHub】https://github.com/chemicalfiber/Anti-DrvCeo\n\n【码云】https://gitee.com/chemicalfiber/Anti-DrvCeo\n\n同时欢迎各位持续关注系列视频：《封装系统的骚操作》，在本系列视频中，up主将教大家如何更进一步地调整优化你封装的系统，而且有些技巧还能用在你当前使用中的系统哦！不局限于系统封装的(≧▽≦)', '知识', 'http://192.168.1.10/chfs/shared/%E5%B0%81%E8%A3%85%E7%B3%BB%E7%BB%9F%E7%9A%84%E9%AA%9A%E6%93%8D%E4%BD%9C/%E5%8D%B8%E8%BD%BD%E9%A9%B1%E5%8A%A8%E6%80%BB%E8%A3%81/AU3%E8%84%9A%E6%9C%AC%E6%96%B9%E5%BC%8F/bilibili.jpg', 'http://192.168.1.10/chfs/shared/%E5%B0%81%E8%A3%85%E7%B3%BB%E7%BB%9F%E7%9A%84%E9%AA%9A%E6%93%8D%E4%BD%9C/%E5%8D%B8%E8%BD%BD%E9%A9%B1%E5%8A%A8%E6%80%BB%E8%A3%81/AU3%E8%84%9A%E6%9C%AC%E6%96%B9%E5%BC%8F/AutoIt%E8%84%9A%E6%9C%AC%E6%96%B9%E5%BC%8F%EF%BC%88music%EF%BC%89_Video_Export/AutoIt%E8%84%9A%E6%9C%AC%E6%96%B9%E5%BC%8F%EF%BC%88music%EF%BC%89.mp4', '2021-02-26', 'https://www.bilibili.com/video/BV1eA411M7Sc/');
INSERT INTO `video` VALUES (21, 2, '紧急更新-解决驱动总裁2.0捆绑安装-封装系统的骚操作', '《封装系统的骚操作》更新！这次带来的是如何解决驱动总裁的捆绑安装的方法，还你一个纯净的系统！\n\n脚本成品地址：https://wwx.lanzoui.com/ingPYlkq3wh\n\n同时欢迎各位持续关注系列视频：《封装系统的骚操作》，在本系列视频中，up主将教大家如何更进一步地调整优化你封装的系统，而且有些技巧还能用在你当前使用中的系统哦！不局限于系统封装的(≧▽≦)\n\n当然，也欢迎各位加入qq群：630219512（光圈科技试验场）和我们一起讨论封装系统的技巧！', '知识', 'http://192.168.1.10/chfs/shared/%E5%B0%81%E8%A3%85%E7%B3%BB%E7%BB%9F%E7%9A%84%E9%AA%9A%E6%93%8D%E4%BD%9C/%E5%8D%B8%E8%BD%BD%E9%A9%B1%E5%8A%A8%E6%80%BB%E8%A3%81/2.0/YouTube.jpg', 'http://192.168.1.10/chfs/shared/%E5%B0%81%E8%A3%85%E7%B3%BB%E7%BB%9F%E7%9A%84%E9%AA%9A%E6%93%8D%E4%BD%9C/%E5%8D%B8%E8%BD%BD%E9%A9%B1%E5%8A%A8%E6%80%BB%E8%A3%81/2.0/%E5%8D%B8%E8%BD%BD%E9%A9%B1%E5%8A%A8%E6%80%BB%E8%A3%812.0%EF%BC%88music%EF%BC%89_Video_Export/%E5%8D%B8%E8%BD%BD%E9%A9%B1%E5%8A%A8%E6%80%BB%E8%A3%812.0%EF%BC%88music%EF%BC%89.mp4', '2021-02-12', 'https://www.bilibili.com/video/BV1Zp4y1p799/');
INSERT INTO `video` VALUES (22, 2, '仅10行代码！解决驱动总裁强制捆绑安装问题-封装系统的骚操作', '《封装系统的骚操作》更新！这次带来的是如何解决驱动总裁的捆绑安装的方法，还你一个纯净的系统！\n\n脚本成品地址：https://wwx.lanzoui.com/iv1wml3mmwf\n\n同时欢迎各位持续关注系列视频：《封装系统的骚操作》，在本系列视频中，up主将教大家如何更进一步地调整优化你封装的系统，而且有些技巧还能用在你当前使用中的系统哦！不局限于系统封装的(≧▽≦)\n\n当然，也欢迎各位加入qq群：630219512（光圈科技试验场）和我们一起讨论封装系统的技巧！', '知识', 'http://192.168.1.10/chfs/shared/%E5%B0%81%E8%A3%85%E7%B3%BB%E7%BB%9F%E7%9A%84%E9%AA%9A%E6%93%8D%E4%BD%9C/%E5%8D%B8%E8%BD%BD%E9%A9%B1%E5%8A%A8%E6%80%BB%E8%A3%81/YouTube.jpg', 'http://192.168.1.10/chfs/shared/%E5%B0%81%E8%A3%85%E7%B3%BB%E7%BB%9F%E7%9A%84%E9%AA%9A%E6%93%8D%E4%BD%9C/%E5%8D%B8%E8%BD%BD%E9%A9%B1%E5%8A%A8%E6%80%BB%E8%A3%81/%E8%A7%A3%E5%86%B3%E9%A9%B1%E5%8A%A8%E6%80%BB%E8%A3%81%E6%8D%86%E7%BB%91%EF%BC%88music%EF%BC%89_Video_Export/%E8%A7%A3%E5%86%B3%E9%A9%B1%E5%8A%A8%E6%80%BB%E8%A3%81%E6%8D%86%E7%BB%91%EF%BC%88music%EF%BC%89.mp4', '2021-01-31', 'https://www.bilibili.com/video/BV1dT4y1P7mt/');
INSERT INTO `video` VALUES (23, 2, '零基础学封装系统-Windows8封装教程-定制属于你自己的系统-Windows8篇（有BGM+字幕版，细节齐全解说充分）', '因字数限制，视频中的演示文稿（简报）和所需文件的下载链接详见评论区。\n\n封面人物Windows8娘「窗邊愛」（窓辺あい），素材来源…我也忘了在哪找的图。 \n\nWindows10近年来的更新似乎并没有照顾好老机型和追求稳定的用户们，而Windows7又已经停止安全技术支持，那么为什么不试试史上蓝屏率最低的Windows8呢？\n\n本视频旨在帮助没有任何基础的小白制作属于自己的Windows8，即学即会哦！\n\n更多进阶教程尽在《封装系统的骚操作》系列，可以点开我的主页或者搜索“封装系统的骚操作”进行观看！', '知识', 'http://192.168.1.10/chfs/shared/Windows%208%E5%B0%81%E8%A3%85%E6%95%99%E7%A8%8B/YouTube.jpg', 'http://192.168.1.10/chfs/shared/Windows%208%E5%B0%81%E8%A3%85%E6%95%99%E7%A8%8B/%E6%88%90%E7%89%87/%E6%A6%82%E8%BF%B0_Video_Export/%E6%A6%82%E8%BF%B0.mp4', '2020-09-20', 'https://www.bilibili.com/video/BV1aD4y1o7re/');
INSERT INTO `video` VALUES (24, 2, '强制安装软件到D盘？拯救你那空间不足的C盘！-封装系统的骚操作', '这是系列视频《封装系统的骚操作》第4个视频！C盘爆红？空间告罄？不要担心！跟随up的步伐，把软件安装到其它磁盘就好！这是Windows自带的功能，并不需要任何第三方工具辅助。\n\n视频中的BGM：はちみつれもん - 儚き人の为のカンタータ(off vocal)\n\n欢迎加入QQ群：630219512（光圈科技试验场）和我们讨论系统封装的技巧，1054274371（光圈科技办公室）和我们讨论Office软件的应用技巧。', '知识', 'http://192.168.1.10/chfs/shared/%E5%B0%81%E8%A3%85%E7%B3%BB%E7%BB%9F%E7%9A%84%E9%AA%9A%E6%93%8D%E4%BD%9C/%E5%AE%89%E8%A3%85%E8%BD%AF%E4%BB%B6%E5%88%B0D%E7%9B%98/YouTube%E5%B0%81%E9%9D%A2.png', 'http://192.168.1.10/chfs/shared/%E5%B0%81%E8%A3%85%E7%B3%BB%E7%BB%9F%E7%9A%84%E9%AA%9A%E6%93%8D%E4%BD%9C/%E5%AE%89%E8%A3%85%E8%BD%AF%E4%BB%B6%E5%88%B0D%E7%9B%98/%E5%AE%89%E8%A3%85%E8%BD%AF%E4%BB%B6%E5%88%B0%E5%85%B6%E5%AE%83%E7%A3%81%E7%9B%98%EF%BC%88bilibili%EF%BC%89_Video_Export/%E5%AE%89%E8%A3%85%E8%BD%AF%E4%BB%B6%E5%88%B0%E5%85%B6%E5%AE%83%E7%A3%81%E7%9B%98%EF%BC%88bilibili%EF%BC%89.mp4', '2020-03-17', 'https://www.bilibili.com/video/BV1fE411573t/');
INSERT INTO `video` VALUES (25, 2, '科技区up注意！我声明了版权的视频居然被其它平台的营销号转走了！快去检查自己的视频有没有被盗', '营销号ID：淳儿的计算机密码箱\n\n今天感到既愤怒，又心酸无奈，自己辛辛苦苦做几年视频，攒下的粉丝量竟然不够用一个偷视频的营销号多。\n\n各位科技区的up们记得去其它地方看看自己的视频是否被盗。', '生活', 'http://192.168.1.10/chfs/shared/%E8%A7%86%E9%A2%91%E8%A2%AB%E7%9B%97/%E5%BC%80%E7%AB%AF%E6%8E%A7%E8%AF%89/%E5%B0%81%E9%9D%A2.png', 'http://192.168.1.10/chfs/shared/%E8%A7%86%E9%A2%91%E8%A2%AB%E7%9B%97/%E5%BC%80%E7%AB%AF%E6%8E%A7%E8%AF%89/1_Video_Export/1.mp4', '2020-03-01', 'https://www.bilibili.com/video/BV1CE411779q/');
INSERT INTO `video` VALUES (26, 2, '最容易学的PPT变体动画/平滑动画教程-PPT进阶应用教程。来感受PowerPoint的魅力吧！', '这是新开的坑，也是一个新系列：PowerPoint的进阶应用和新功能探索！希望大家喜欢！\n\n这次使用了新的编码方式，每一节视频都是强压缩，保证1080P画质+60FPS也不需要多少流量（最长的30分钟的“实战”节也仅有143.5M，比以前缩小了3/4），并且！是60FPS！所以这次大家可以放心地开最高画质了（*＾3＾）\n\n视频中出现的BGM全部写在了评论区，大家按需复制收听。演示文稿分享链接也放在了评论区，按需下载。\n\n如果可以的话，加入群聊1054274371（光圈科技办公室）和我们一起讨论！', '知识', 'http://192.168.1.10/chfs/shared/PPT%E5%8F%98%E4%BD%93%E5%8A%A8%E7%94%BB/YouTube.png', 'http://192.168.1.10/chfs/shared/PPT%E5%8F%98%E4%BD%93%E5%8A%A8%E7%94%BB/%E6%88%90%E7%89%87/%E2%80%9C%E5%B9%B3%E6%BB%91%E2%80%9D%E4%B8%8A%E6%89%8B%E6%93%8D%E4%BD%9C%EF%BC%88bilibili%EF%BC%89_Video_Export/%E2%80%9C%E5%B9%B3%E6%BB%91%E2%80%9D%E4%B8%8A%E6%89%8B%E6%93%8D%E4%BD%9C%EF%BC%88bilibili%EF%BC%89.mp4', '2020-02-27', 'https://www.bilibili.com/video/BV1w7411K7no/');
INSERT INTO `video` VALUES (27, 2, '安装软件要密码，防熊孩子的好做法-封装系统的骚操作', '系列视频：《封装系统的骚操作》更新！年关将至，家里肯定少不了亲戚串门，熊孩子们就...随之而来，那么如何保护自己的电脑不被熊孩子乱塞奇奇怪怪的软件呢？跟着up主做就对了！\n\n同时欢迎各位持续关注系列视频：《封装系统的骚操作》，在本系列视频中，up主将教大家如何更进一步地调整优化你封装的系统，而且有些技巧还能用在你当前使用中的系统哦！不局限于系统封装的(≧▽≦)\n\n当然，也欢迎各位加入qq群：630219512（光圈科技试验场）和我们一起讨论封装系统的技巧！', '知识', 'http://192.168.1.10/chfs/shared/%E5%B0%81%E8%A3%85%E7%B3%BB%E7%BB%9F%E7%9A%84%E9%AA%9A%E6%93%8D%E4%BD%9C/%E9%98%B2%E7%86%8A%E5%AD%A9%E5%AD%90/YouTube.jpg', 'http://192.168.1.10/chfs/shared/%E5%B0%81%E8%A3%85%E7%B3%BB%E7%BB%9F%E7%9A%84%E9%AA%9A%E6%93%8D%E4%BD%9C/%E9%98%B2%E7%86%8A%E5%AD%A9%E5%AD%90/%E9%98%B2%E7%86%8A%E5%AD%A9%E5%AD%90%EF%BC%88bilibili%EF%BC%89_Video_Export/%E9%98%B2%E7%86%8A%E5%AD%A9%E5%AD%90%EF%BC%88bilibili%EF%BC%89.mp4', '2020-01-18', 'https://www.bilibili.com/video/BV1h7411i7gA/');
INSERT INTO `video` VALUES (28, 2, '再见，奈奈美-Windows 7今日停止更新，我想说...', '今天是Windows 7停止安全支持的日子，所以就做一回杂谈吧，说说我对微软停更Windows 7的看法。\n\n因为是第一次做杂谈类视频，可能在剪辑上稍显生疏，请各位多多包涵！\n\n视频中提到的主题包下载地址：https://www.lanzoui.com/i8n7j8f 解压后双击安装就能用。\n\n最后，R.I.P. Windows 7 2009.7.14-2020.1.14', '知识', 'http://192.168.1.10/chfs/shared/%E5%86%8D%E8%A7%81%E5%A5%88%E5%A5%88%E7%BE%8E/%E5%B0%81%E9%9D%A2%EF%BC%88%E4%B8%BB%E9%A2%98%E6%8F%90%E9%86%92%EF%BC%89.jpg', 'http://192.168.1.10/chfs/shared/%E5%86%8D%E8%A7%81%E5%A5%88%E5%A5%88%E7%BE%8E/%E5%86%8D%E8%A7%81%E5%A5%88%E5%A5%88%E7%BE%8E%EF%BC%88%E5%93%94%E5%93%A9%E5%93%94%E5%93%A9%EF%BC%89_Video_Export/%E5%86%8D%E8%A7%81%E5%A5%88%E5%A5%88%E7%BE%8E%EF%BC%88%E5%93%94%E5%93%A9%E5%93%94%E5%93%A9%EF%BC%89.mp4', '2020-01-14', 'https://www.bilibili.com/video/BV13J411H7J7/');
INSERT INTO `video` VALUES (29, 2, '再也不怕别人不会用我做的系统啦！创建你的系统说明文档-制作系统使用说明书-封装系统的骚操作', '系列视频：《封装系统的骚操作》更新！在本视频中，up主将带你做出自己封装的系统的说明文档，让你的用户更了解你做出来的系统特性，减少各种不必要的提问。\n\n同时欢迎各位持续关注系列视频：《封装系统的骚操作》，在本系列视频中，up主将教大家如何更进一步地调整优化你封装的系统，而且有些技巧还能用在你当前使用中的系统哦！不局限于系统封装的(≧▽≦)\n\n当然，也欢迎各位加入qq群：630219512（光圈科技试验场）和我们一起讨论封装系统的技巧！', '科技', 'http://192.168.1.10/chfs/shared/%E5%B0%81%E8%A3%85%E7%B3%BB%E7%BB%9F%E7%9A%84%E9%AA%9A%E6%93%8D%E4%BD%9C/%E7%B3%BB%E7%BB%9F%E8%AF%B4%E6%98%8E%E6%96%87%E6%A1%A3/%E8%AF%B4%E6%98%8E%E6%96%87%E6%A1%A3.jpg', 'http://192.168.1.10/chfs/shared/%E5%B0%81%E8%A3%85%E7%B3%BB%E7%BB%9F%E7%9A%84%E9%AA%9A%E6%93%8D%E4%BD%9C/%E7%B3%BB%E7%BB%9F%E8%AF%B4%E6%98%8E%E6%96%87%E6%A1%A3/%E5%88%9B%E5%BB%BA%E8%AF%B4%E6%98%8E%E6%96%87%E4%BB%B6%EF%BC%88bilbili%EF%BC%89_Video_Export/%E5%88%9B%E5%BB%BA%E8%AF%B4%E6%98%8E%E6%96%87%E4%BB%B6%EF%BC%88bilbili%EF%BC%89.mp4', '2019-12-11', 'https://www.bilibili.com/video/BV1MJ41117Jw/');
INSERT INTO `video` VALUES (30, 2, '让流氓软件再也装不上你的电脑！装了也无法运行！防流氓软件的方法-封装系统的骚操作', '这是全新的系列视频：《封装系统的骚操作》，在本系列视频中，up主将教大家如何更进一步地调整优化你封装的系统，而且有些技巧还能用在你当前使用中的系统哦！不局限于系统封装的(≧▽≦)\n\n同时欢迎各位加入qq群：630219512（光圈科技试验场）和我们一起讨论封装系统的技巧！\n\n本操作需要涉及到“组策略”，建议使用专业版Windows系统，家庭版用户也可以参考评论区的“Windows10家庭版如何开启组策略？”\n\n视频中的背景音乐名字会在左上角出现，请留意哦！', '科技', 'http://192.168.1.10/chfs/shared/%E5%B0%81%E8%A3%85%E7%B3%BB%E7%BB%9F%E7%9A%84%E9%AA%9A%E6%93%8D%E4%BD%9C/%E9%98%B2%E6%B5%81%E6%B0%93%E8%BD%AF%E4%BB%B6/%E5%B0%81%E9%9D%A2.jpg', 'http://192.168.1.10/chfs/shared/%E5%B0%81%E8%A3%85%E7%B3%BB%E7%BB%9F%E7%9A%84%E9%AA%9A%E6%93%8D%E4%BD%9C/%E9%98%B2%E6%B5%81%E6%B0%93%E8%BD%AF%E4%BB%B6/%E9%98%B2%E6%B5%81%E6%B0%93%E8%BD%AF%E4%BB%B6%EF%BC%88bilibili%EF%BC%89_Video_Export/%E9%98%B2%E6%B5%81%E6%B0%93%E8%BD%AF%E4%BB%B6%EF%BC%88bilibili%EF%BC%89.mp4', '2019-10-13', 'https://www.bilibili.com/video/BV1ME411d73j/');
INSERT INTO `video` VALUES (37, 2, '最新视频', '最新视频的简介', '日常', 'http://192.168.1.10/chfs/shared/mac%E7%BE%8E%E5%8C%96110.jpg', 'http://192.168.1.10/chfs/shared/%E4%BC%A0%E9%80%81%E9%97%A818%E5%8F%B7%E5%AE%9E%E9%AA%8C%E5%AE%A4.mp4', '2022-12-18', 'https://www.bilibili.com/video/BV1Rt4y127hf');
INSERT INTO `video` VALUES (38, 2, '【PayDay】5头！ 只用手柄通关霍斯顿越狱记', '手柄5头越狱记 有手柄实像通关', '游戏', 'http://192.168.1.10/chfs/shared/mac%E7%BE%8E%E5%8C%9610.jpg', 'http://192.168.1.10/chfs/shared/%E6%94%B6%E8%8E%B7%E6%97%A52%E6%89%8B%E6%9F%84%E6%8C%91%E6%88%985%E5%A4%B4%E8%B6%8A%E7%8B%B1.mp4', '2022-12-18', 'https://www.bilibili.com/video/BV1F541147Du/?spm_id_from=333.999.0.0');

SET FOREIGN_KEY_CHECKS = 1;
