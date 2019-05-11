
/* Drop Tables */

IF ObJECt_ID('[is_battery_status]') IS NOT NULL DROP TABLE [is_battery_status];
IF ObJECt_ID('[is_camera]') IS NOT NULL DROP TABLE [is_camera];
IF ObJECt_ID('[is_car_count]') IS NOT NULL DROP TABLE [is_car_count];
IF ObJECt_ID('[is_car_rec]') IS NOT NULL DROP TABLE [is_car_rec];
IF ObJECt_ID('[is_car_status]') IS NOT NULL DROP TABLE [is_car_status];
IF ObJECt_ID('[is_repair_rec]') IS NOT NULL DROP TABLE [is_repair_rec];
IF ObJECt_ID('[is_faults]') IS NOT NULL DROP TABLE [is_faults];
IF ObJECt_ID('[is_maintain_rec]') IS NOT NULL DROP TABLE [is_maintain_rec];
IF ObJECt_ID('[is_maintain]') IS NOT NULL DROP TABLE [is_maintain];
IF ObJECt_ID('[is_patrol_rec]') IS NOT NULL DROP TABLE [is_patrol_rec];
IF ObJECt_ID('[is_patrol]') IS NOT NULL DROP TABLE [is_patrol];
IF ObJECt_ID('[is_device]') IS NOT NULL DROP TABLE [is_device];
IF ObJECt_ID('[is_knowledge]') IS NOT NULL DROP TABLE [is_knowledge];
IF ObJECt_ID('[IsDeviceDeviceCode]') IS NOT NULL DROP TABLE [is_device_code];
IF ObJECt_ID('[is_rebat_status]') IS NOT NULL DROP TABLE [is_rebat_status];
IF ObJECt_ID('[is_scene]') IS NOT NULL DROP TABLE [is_scene];




/* Create Tables */

-- 电池状态
CREATE TABLE [is_battery_status]
(
	-- 设备编号
	[device_no] varchar(10) NOT NULL,
	-- 电池位置
	[location] varchar(60),
	-- 充电次数 
	[charging_time] decimal(5),
	PRIMARY KEY ([device_no])
);


-- 摄像头
CREATE TABLE [is_camera]
(
	-- 设备编号
	[device_no] varchar(10) NOT NULL,
	-- 摄像头名称
	[camera_name] varchar(64),
	-- IP地址
	[IP] varchar(15),
	-- 视频流地址
	[RTSP] varchar(200),
	-- 状态
	[status] char(1) DEFAULT '0' NOT NULL,
	-- 创建者
	[create_by] varchar(64) NOT NULL,
	-- 创建时间
	[create_date] datetime NOT NULL,
	-- 更新者
	[update_by] varchar(64) NOT NULL,
	-- 更新时间
	[update_date] datetime NOT NULL,
	-- 备注信息
	[remarks] nvarchar(60),
	PRIMARY KEY ([device_no])
);


-- 穿梭车运行统计
CREATE TABLE [is_car_count]
(
	-- 统计日期
	[count_date] char(10) NOT NULL,
	-- 设备编号
	[device_no] varchar(10) NOT NULL,
	-- 故障统计
	[err_count] int,
	-- 行走故障统计
	[moveerr_count] int,
	-- 升降故障统计
	[updownerr_count] int,
	-- 转向故障统计
	[turnerr_count] int,
	-- 行走里程
	[move_mileage] float,
	-- 升降次数
	[updown_count] int,
	-- 转向次数
	[turn_count] int,
	-- 充电次数
	[rechange_count] int,
	-- 工作时长
	[work_time] bigint,
	PRIMARY KEY ([count_date], [device_no])
);


-- 穿梭车运行记录
CREATE TABLE [is_car_rec]
(
	-- ID
	[id] varchar(64) NOT NULL,
	-- 设备编号
	[device_no] varchar(10),
	-- 记录时间
	[record_time] datetime,
	-- 行
	[row_no] int,
	-- 列
	[column_no] int,
	-- 层
	[layer_no] int,
	-- 角度
	[angle] int,
	-- 托盘状态
	[plt] varchar(1),
	PRIMARY KEY ([id])
);


-- 穿梭车状态
CREATE TABLE [is_car_status]
(
	-- 设备编号
	[device_no] varchar(10) NOT NULL,
	-- 行
	[row_no] int,
	-- 列
	[column_no] int,
	-- 层
	[layer_no] int,
	-- 角度
	[angle] int,
	-- 托盘状态
	[plt] varchar(1),
	-- 电压
	[bat_voltage] float,
	-- 电流
	[bat_current] float,
	-- 运行状态
	[run_state] char(1),
	-- 任务状态
	[work_state] char(1),
	-- 故障1
	[err01] int,
	-- 故障2
	[err02] int,
	-- 故障3
	[err03] int,
	-- 故障4
	[err04] int,
	-- 故障5
	[err05] int,
	-- 开始运行时间
	[work_starttime] datetime,
	PRIMARY KEY ([device_no])
);


-- 设备
CREATE TABLE [is_device]
(
	-- ID
	[id] varchar(64) NOT NULL,
	-- 设备代码ID
	[device_code_id] varchar(64) NOT NULL,
	-- 设备编号
	[device_no] varchar(10) NOT NULL,
	-- 制造厂商
	[Manufacturer] varchar(60),
	-- 出厂日期
	[production_date] date,
	-- 供应商
	[Supplier] varchar(60),
	-- 启用部门
	[use_office] varchar(60),
	-- 启用时间
	[use_date] date,
	-- 安装地点
	[install_location] varchar(60),
	-- 定位X
	[location_x] float,
	-- 定位Y
	[location_y] float,
	-- 定位Z
	[location_z] float,
	-- 父级编号
	[parent_code] varchar(64) NOT NULL,
	-- 全节点名
	[tree_names] varchar(1000) NOT NULL,
	-- 层次级别
	[tree_level] decimal(4) NOT NULL,
	-- 是否最末级
	[tree_leaf] char(1) NOT NULL,
	-- 所有级别排序号
	[tree_sorts] varchar(1000) NOT NULL,
	-- 本节排序号
	[tree_sort] int NOT NULL,
	-- 所有父级编号
	[parent_codes] varchar(1000) NOT NULL,
	-- 状态
	[status] char(1) DEFAULT '0' NOT NULL,
	-- 创建者
	[create_by] varchar(64) NOT NULL,
	-- 创建时间
	[create_date] datetime NOT NULL,
	-- 更新者
	[update_by] varchar(64) NOT NULL,
	-- 更新时间
	[update_date] datetime NOT NULL,
	-- 备注信息
	[remarks] nvarchar(60),
	PRIMARY KEY ([id])
);


-- 设备代码
CREATE TABLE [is_device_code]
(
	-- ID
	[id] varchar(64) NOT NULL,
	-- 设备代码
	[device_code] varchar(60) NOT NULL,
	-- 设备名称
	[device_name] varchar(60) NOT NULL,
	-- 设备类别
	[device_type] varchar(2),
	-- 型号规格
	[model] varchar(60),
	-- 设备参数
	[parameters] varchar(500),
	-- 设备用途
	[application] varchar(60),
	-- 制造厂商
	[Manufacturer] varchar(60),
	-- 使用年限
	[life] varchar(10),
	-- 年折旧率
	[depreciation] varchar(10),
	-- 三维模型
	[model_url] varchar(200),
	-- 父级编号
	[parent_code] varchar(64) NOT NULL,
	-- 全节点名
	[tree_names] varchar(1000) NOT NULL,
	-- 层次级别
	[tree_level] decimal(4) NOT NULL,
	-- 是否最末级
	[tree_leaf] char(1) NOT NULL,
	-- 所有级别排序号
	[tree_sorts] varchar(1000) NOT NULL,
	-- 本节排序号
	[tree_sort] int NOT NULL,
	-- 所有父级编号
	[parent_codes] varchar(1000) NOT NULL,
	-- 状态
	[status] char(1) DEFAULT '0' NOT NULL,
	-- 创建者
	[create_by] varchar(64) NOT NULL,
	-- 创建时间
	[create_date] datetime NOT NULL,
	-- 更新者
	[update_by] varchar(64) NOT NULL,
	-- 更新时间
	[update_date] datetime NOT NULL,
	-- 备注信息
	[remarks] nvarchar(60),
	PRIMARY KEY ([id])
);


-- 设备故障
CREATE TABLE [is_faults]
(
	-- ID
	[id] varchar(64) NOT NULL,
	-- 设备ID
	[device_id] varchar(64) NOT NULL,
	-- 故障代码
	[faults_code] varchar(2),
	-- 故障时间
	[faults_time] datetime,
	-- 故障名称
	[faults_name] varchar(64),
	-- 故障来源
	[faults_source] varchar(1),
	-- 故障原因
	[reason] varchar(200),
	-- 解决方案
	[knowledge_id] varchar(64) NOT NULL,
	-- 状态
	[status] char(1) DEFAULT '0' NOT NULL,
	-- 创建者
	[create_by] varchar(64) NOT NULL,
	-- 创建时间
	[create_date] datetime NOT NULL,
	-- 更新者
	[update_by] varchar(64) NOT NULL,
	-- 更新时间
	[update_date] datetime NOT NULL,
	-- 备注信息
	[remarks] nvarchar(60),
	PRIMARY KEY ([id])
);


-- 知识库
CREATE TABLE [is_knowledge]
(
	-- ID
	[id] varchar(64) NOT NULL,
	-- 设备代码ID
	[device_code_id] varchar(64) NOT NULL,
	-- 知识类型
	[knowledge_type] varchar(2) NOT NULL,
	-- 知识点代码
	[knowledge_code] varchar(2),
	-- 知识内容
	[content] varchar(2000),
	-- 多媒体类型
	[media_type] char(1),
	-- 多媒体地址
	[media_url] varchar(200),
	-- 状态
	[status] char(1) DEFAULT '0' NOT NULL,
	-- 创建者
	[create_by] varchar(64) NOT NULL,
	-- 创建时间
	[create_date] datetime NOT NULL,
	-- 更新者
	[update_by] varchar(64) NOT NULL,
	-- 更新时间
	[update_date] datetime NOT NULL,
	-- 备注信息
	[remarks] nvarchar(60),
	PRIMARY KEY ([id])
);


-- 保养点
CREATE TABLE [is_maintain]
(
	-- ID
	[id] varchar(64) NOT NULL,
	-- 保养名称
	[name] varchar(60) NOT NULL,
	-- 设备ID
	[device_id] varchar(64) NOT NULL,
	-- 保养类型
	[maintain_type] varchar(2) NOT NULL,
	-- 保养内容
	[content] varchar(500),
	-- 间隔时间
	[interval] decimal(4),
	-- 下次保养日期
	[maintain_date] date,
	-- 操作手册
	[knowledge_id] varchar(64) NOT NULL,
	-- 状态
	[status] char(1) DEFAULT '0' NOT NULL,
	-- 创建者
	[create_by] varchar(64) NOT NULL,
	-- 创建时间
	[create_date] datetime NOT NULL,
	-- 更新者
	[update_by] varchar(64) NOT NULL,
	-- 更新时间
	[update_date] datetime NOT NULL,
	-- 备注信息
	[remarks] nvarchar(60),
	PRIMARY KEY ([id])
);


-- 保养记录
CREATE TABLE [is_maintain_rec]
(
	-- ID
	[id] varchar(64) NOT NULL,
	-- 保养点ID
	[maintain_id] varchar(64) NOT NULL,
	-- 保养记录
	[record] varchar(200),
	-- 保养人
	[operator] varchar(20),
	-- 保养时间
	[maintain_date] datetime,
	-- 状态
	[status] char(1) DEFAULT '0' NOT NULL,
	-- 创建者
	[create_by] varchar(64) NOT NULL,
	-- 创建时间
	[create_date] datetime NOT NULL,
	-- 更新者
	[update_by] varchar(64) NOT NULL,
	-- 更新时间
	[update_date] datetime NOT NULL,
	-- 备注信息
	[remarks] nvarchar(60),
	PRIMARY KEY ([id])
);


-- 设备巡检点
CREATE TABLE [is_patrol]
(
	-- ID
	[id] varchar(64) NOT NULL,
	-- 巡检点名称
	[name] varchar(60),
	-- 设备ID
	[device_id] varchar(64) NOT NULL,
	-- 工作类型
	[patrol_type] varchar(1),
	-- 巡检内容
	[content] varchar(200),
	-- 巡检间隔
	[interval] decimal(10),
	-- 巡检手册
	[knowledge_id] varchar(64) NOT NULL,
	-- 状态
	[status] char(1) DEFAULT '0' NOT NULL,
	-- 创建者
	[create_by] varchar(64) NOT NULL,
	-- 创建时间
	[create_date] datetime NOT NULL,
	-- 更新者
	[update_by] varchar(64) NOT NULL,
	-- 更新时间
	[update_date] datetime NOT NULL,
	-- 备注信息
	[remarks] nvarchar(60),
	PRIMARY KEY ([id])
);


-- 巡检记录
CREATE TABLE [is_patrol_rec]
(
	-- ID
	[id] varchar(64) NOT NULL,
	-- 巡检点ID
	[patrol_id] varchar(64) NOT NULL,
	-- 巡检记录
	[record] varchar(200),
	-- 巡检人
	[operator] varchar(20),
	-- 巡检时间
	[patrol_date] datetime,
	-- 状态
	[status] char(1) DEFAULT '0' NOT NULL,
	-- 创建者
	[create_by] varchar(64) NOT NULL,
	-- 创建时间
	[create_date] datetime NOT NULL,
	-- 更新者
	[update_by] varchar(64) NOT NULL,
	-- 更新时间
	[update_date] datetime NOT NULL,
	-- 备注信息
	[remarks] nvarchar(60),
	PRIMARY KEY ([id])
);


-- 快换电池装置状态
CREATE TABLE [is_rebat_status]
(
	-- 设备编号
	[device_no] varchar(10) NOT NULL,
	-- 1#充电位
	[bat01] char(1),
	-- 2#充电位
	[bat02] char(1),
	-- 3#充电位
	[bat03] char(1),
	-- 4#充电位
	[bat04] char(1),
	-- 5#充电位
	[bat05] char(1),
	PRIMARY KEY ([device_no])
);


-- 维修记录
CREATE TABLE [is_repair_rec]
(
	-- ID
	[id] varchar(64) NOT NULL,
	-- 故障ID
	[fault_id] varchar(64) NOT NULL,
	-- 处理过程
	[record] varchar(1000),
	-- 维修结果
	[results] varchar(200),
	-- 维修人
	[operator] varchar(20),
	-- 维修时间
	[repair_time] datetime,
	-- 状态
	[status] char(1) DEFAULT '0' NOT NULL,
	-- 创建者
	[create_by] varchar(64) NOT NULL,
	-- 创建时间
	[create_date] datetime NOT NULL,
	-- 更新者
	[update_by] varchar(64) NOT NULL,
	-- 更新时间
	[update_date] datetime NOT NULL,
	-- 备注信息
	[remarks] nvarchar(60),
	PRIMARY KEY ([id])
);


-- 场景
CREATE TABLE [is_scene]
(
	-- ID
	[id] varchar(64) NOT NULL,
	-- 场景代码
	[scene_code] varchar(64) NOT NULL,
	-- 场景名称
	[scene_name] varchar(64),
	-- 模型地址
	[model_url] varchar(200),
	-- 父级编号
	[parent_code] varchar(64) NOT NULL,
	-- 全节点名
	[tree_names] varchar(1000) NOT NULL,
	-- 层次级别
	[tree_level] decimal(4) NOT NULL,
	-- 是否最末级
	[tree_leaf] char(1) NOT NULL,
	-- 所有级别排序号
	[tree_sorts] varchar(1000) NOT NULL,
	-- 本节排序号
	[tree_sort] int NOT NULL,
	-- 所有父级编号
	[parent_codes] varchar(1000) NOT NULL,
	-- 状态
	[status] char(1) DEFAULT '0' NOT NULL,
	-- 创建者
	[create_by] varchar(64) NOT NULL,
	-- 创建时间
	[create_date] datetime NOT NULL,
	-- 更新者
	[update_by] varchar(64) NOT NULL,
	-- 更新时间
	[update_date] datetime NOT NULL,
	-- 备注信息
	[remarks] nvarchar(60),
	PRIMARY KEY ([id])
);



/* Create Foreign Keys */

ALTER TABLE [is_faults]
	ADD FOREIGN KEY ([device_id])
	REFERENCES [is_device] ([id])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE [is_maintain]
	ADD FOREIGN KEY ([device_id])
	REFERENCES [is_device] ([id])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE [is_patrol]
	ADD FOREIGN KEY ([device_id])
	REFERENCES [is_device] ([id])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE [is_device]
	ADD FOREIGN KEY ([device_code_id])
	REFERENCES [is_device_code] ([id])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE [is_knowledge]
	ADD FOREIGN KEY ([device_code_id])
	REFERENCES [is_device_code] ([id])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE [is_repair_rec]
	ADD FOREIGN KEY ([fault_id])
	REFERENCES [is_faults] ([id])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE [is_faults]
	ADD FOREIGN KEY ([knowledge_id])
	REFERENCES [is_knowledge] ([id])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE [is_maintain]
	ADD FOREIGN KEY ([knowledge_id])
	REFERENCES [is_knowledge] ([id])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE [is_patrol]
	ADD FOREIGN KEY ([knowledge_id])
	REFERENCES [is_knowledge] ([id])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE [is_maintain_rec]
	ADD FOREIGN KEY ([maintain_id])
	REFERENCES [is_maintain] ([id])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE [is_patrol_rec]
	ADD FOREIGN KEY ([patrol_id])
	REFERENCES [is_patrol] ([id])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



