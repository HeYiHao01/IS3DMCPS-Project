
/* Drop Tables */

DROP TABLE is_battery_status CASCADE CONSTRAINTS;
DROP TABLE is_camera CASCADE CONSTRAINTS;
DROP TABLE is_car_count CASCADE CONSTRAINTS;
DROP TABLE is_car_rec CASCADE CONSTRAINTS;
DROP TABLE is_car_status CASCADE CONSTRAINTS;
DROP TABLE is_device_use CASCADE CONSTRAINTS;
DROP TABLE is_repair_rec CASCADE CONSTRAINTS;
DROP TABLE is_faults CASCADE CONSTRAINTS;
DROP TABLE is_patrol_rec CASCADE CONSTRAINTS;
DROP TABLE is_patrol CASCADE CONSTRAINTS;
DROP TABLE is_rebat_status CASCADE CONSTRAINTS;
DROP TABLE is_device CASCADE CONSTRAINTS;
DROP TABLE is_maintain_rec CASCADE CONSTRAINTS;
DROP TABLE is_maintain CASCADE CONSTRAINTS;
DROP TABLE is_knowledge CASCADE CONSTRAINTS;
DROP TABLE is_device_code CASCADE CONSTRAINTS;
DROP TABLE is_scene CASCADE CONSTRAINTS;




/* Create Tables */

-- 电池状态
CREATE TABLE is_battery_status
(
	-- 设备ID
	device_id varchar2(64) NOT NULL,
	-- 设备名称
	device_name varchar2(80) NOT NULL,
	-- 电池位置
	location varchar2(60) NOT NULL,
	-- 充电次数 
	charging_time number(5),
	PRIMARY KEY (device_id)
);


-- 摄像头
CREATE TABLE is_camera
(
	-- ID
	id varchar2(64) NOT NULL,
	-- 摄像头名称
	camera_name varchar2(64) NOT NULL,
	-- IP地址
	IP varchar2(15) NOT NULL,
	-- 视频流地址
	RTSP varchar2(200),
	PRIMARY KEY (id)
);


-- 穿梭车运行统计
CREATE TABLE is_car_count
(
	-- ID
	id varchar2(64) NOT NULL,
	-- 设备ID
	device_id varchar2(64) NOT NULL,
	-- 设备名称
	device_name varchar2(80) NOT NULL,
	-- 统计日期
	count_date char(10) NOT NULL,
	-- 故障统计
	err_count number(10,0),
	-- 行走故障统计
	moveerr_count number(10,0),
	-- 升降故障统计
	updownerr_count number(10,0),
	-- 转向故障统计
	turnerr_count number(10,0),
	-- 行走里程
	move_mileage float,
	-- 升降次数
	updown_count number(10,0),
	-- 转向次数
	turn_count number(10,0),
	-- 充电次数
	rechange_count number(10,0),
	-- 工作时长
	work_time number(19,0),
	PRIMARY KEY (id)
);


-- 穿梭车运行记录
CREATE TABLE is_car_rec
(
	-- ID
	id varchar2(64) NOT NULL,
	-- 设备ID
	device_id varchar2(64) NOT NULL,
	-- 设备名称
	device_name varchar2(80) NOT NULL,
	-- 记录时间
	record_time timestamp NOT NULL,
	-- 行
	row_no number(10,0),
	-- 列
	column_no number(10,0),
	-- 层
	layer_no number(10,0),
	-- 角度
	angle number(10,0),
	-- 托盘状态
	plt varchar2(1),
	PRIMARY KEY (id)
);


-- 穿梭车状态
CREATE TABLE is_car_status
(
	-- 设备ID
	device_id varchar2(64) NOT NULL,
	-- 设备名称
	device_name varchar2(80) NOT NULL,
	-- OPCID
	opc_id varchar2(64) NOT NULL,
	-- 行
	row_no number(10,0),
	-- 列
	column_no number(10,0),
	-- 层
	layer_no number(10,0),
	-- 角度
	angle number(10,0),
	-- 托盘状态
	plt varchar2(1),
	-- 电压
	bat_voltage float,
	-- 电流
	bat_current float,
	-- 运行状态
	run_state char(1),
	-- 任务状态
	work_state char(1),
	-- 故障1
	err01 number(10,0),
	-- 故障2
	err02 number(10,0),
	-- 故障3
	err03 number(10,0),
	-- 故障4
	err04 number(10,0),
	-- 故障5
	err05 number(10,0),
	-- 开始运行时间
	work_starttime timestamp,
	PRIMARY KEY (device_id)
);


-- 设备
CREATE TABLE is_device
(
	-- ID
	id varchar2(64) NOT NULL,
	-- 设备代码ID
	device_code_id varchar2(64) NOT NULL,
	-- 设备代码名称
	device_code_name varchar2(60) NOT NULL,
	-- 设备编号
	device_no varchar2(10),
	-- 制造厂商
	Manufacturer varchar2(60),
	-- 出厂日期
	production_date date,
	-- 供应商
	Supplier varchar2(60),
	-- 启用部门
	use_office varchar2(60),
	-- 启用时间
	use_date date,
	-- 安装地点
	install_location varchar2(60),
	-- 定位X
	location_x float,
	-- 定位Y
	location_y float,
	-- 定位Z
	location_z float,
	-- 设备状态
	device_status varchar2(1),
	-- 状态
	status char(1) DEFAULT '0' NOT NULL,
	-- 创建者
	create_by varchar2(64) NOT NULL,
	-- 创建时间
	create_date timestamp NOT NULL,
	-- 更新者
	update_by varchar2(64) NOT NULL,
	-- 更新时间
	update_date timestamp NOT NULL,
	-- 备注信息
	remarks nvarchar2(60),
	-- 设备ID
	device_id varchar2(64),
	-- 设备名称
	device_name varchar2(80),
	-- 类别
	type varchar2(2) NOT NULL,
	PRIMARY KEY (id)
);


-- 设备代码
CREATE TABLE is_device_code
(
	-- ID
	id varchar2(64) NOT NULL,
	-- 代码
	code varchar2(60) NOT NULL,
	-- 名称
	name varchar2(60) NOT NULL,
	-- 类别
	type varchar2(2) NOT NULL,
	-- 型号规格
	model varchar2(60),
	-- 设备参数
	parameters varchar2(500),
	-- 设备用途
	application varchar2(60),
	-- 制造厂商
	Manufacturer varchar2(60),
	-- 使用年限
	life varchar2(10),
	-- 年折旧率
	depreciation varchar2(10),
	-- 三维模型
	model_url varchar2(200),
	-- 父级编号
	parent_code varchar2(64) NOT NULL,
	-- 全节点名
	tree_names varchar2(1000) NOT NULL,
	-- 层次级别
	tree_level number(4) NOT NULL,
	-- 是否最末级
	tree_leaf char(1) NOT NULL,
	-- 所有级别排序号
	tree_sorts varchar2(1000) NOT NULL,
	-- 本节排序号
	tree_sort number(10,0) NOT NULL,
	-- 所有父级编号
	parent_codes varchar2(1000) NOT NULL,
	-- 状态
	status char(1) DEFAULT '0' NOT NULL,
	-- 创建者
	create_by varchar2(64) NOT NULL,
	-- 创建时间
	create_date timestamp NOT NULL,
	-- 更新者
	update_by varchar2(64) NOT NULL,
	-- 更新时间
	update_date timestamp NOT NULL,
	-- 备注信息
	remarks nvarchar2(60),
	PRIMARY KEY (id)
);


-- 设备使用记录
CREATE TABLE is_device_use
(
	-- ID
	id varchar2(64),
	-- 设备ID
	device_id varchar2(64) NOT NULL,
	-- 设备名称
	device_name varchar2(80) NOT NULL,
	-- 操作类型
	type varchar2(1) NOT NULL,
	-- 操作人员
	operator varchar2(20),
	-- 操作时间
	operate_time date,
	-- 状态
	status char(1) DEFAULT '0' NOT NULL,
	-- 创建者
	create_by varchar2(64) NOT NULL,
	-- 创建时间
	create_date timestamp NOT NULL,
	-- 更新者
	update_by varchar2(64) NOT NULL,
	-- 更新时间
	update_date timestamp NOT NULL,
	-- 备注信息
	remarks nvarchar2(60)
);


-- 设备故障
CREATE TABLE is_faults
(
	-- ID
	id varchar2(64) NOT NULL,
	-- 故障名称
	name varchar2(64) NOT NULL,
	-- 设备ID
	device_id varchar2(64) NOT NULL,
	-- 设备名称
	device_name varchar2(80) NOT NULL,
	-- 故障代码
	faults_code varchar2(20),
	-- 故障时间
	faults_time timestamp,
	-- 报修人员
	operator varchar2(20),
	-- 故障原因
	reason varchar2(200),
	-- 推荐方案
	knowledge_id varchar2(64),
	-- 状态
	status char(1) DEFAULT '0' NOT NULL,
	-- 创建者
	create_by varchar2(64) NOT NULL,
	-- 创建时间
	create_date timestamp NOT NULL,
	-- 更新者
	update_by varchar2(64) NOT NULL,
	-- 更新时间
	update_date timestamp NOT NULL,
	-- 备注信息
	remarks nvarchar2(60),
	PRIMARY KEY (id)
);


-- 知识库
CREATE TABLE is_knowledge
(
	-- ID
	id varchar2(64) NOT NULL,
	-- 设备代码ID
	device_code_id varchar2(64),
	-- 设备代码名称
	device_code_name varchar2(60),
	-- 知识类型
	type varchar2(2) NOT NULL,
	-- 知识标题
	title varchar2(400) NOT NULL,
	-- 知识内容
	content varchar2(2000),
	-- 多媒体类型
	media_type char(1),
	-- 多媒体地址
	media_url varchar2(200),
	-- 状态
	status char(1) DEFAULT '0' NOT NULL,
	-- 创建者
	create_by varchar2(64) NOT NULL,
	-- 创建时间
	create_date timestamp NOT NULL,
	-- 更新者
	update_by varchar2(64) NOT NULL,
	-- 更新时间
	update_date timestamp NOT NULL,
	-- 备注信息
	remarks nvarchar2(60),
	PRIMARY KEY (id)
);


-- 保养点
CREATE TABLE is_maintain
(
	-- ID
	id varchar2(64) NOT NULL,
	-- 保养点名称
	name varchar2(60) NOT NULL,
	-- 设备代码ID
	device_code_id varchar2(64) NOT NULL,
	-- 设备代码名称
	device_code_name varchar2(60) NOT NULL,
	-- 保养类型
	type varchar2(2),
	-- 保养内容
	content varchar2(500),
	-- 间隔时间
	interval number(4),
	-- 操作手册
	knowledge_id varchar2(64),
	-- 状态
	status char(1) DEFAULT '0' NOT NULL,
	-- 创建者
	create_by varchar2(64) NOT NULL,
	-- 创建时间
	create_date timestamp NOT NULL,
	-- 更新者
	update_by varchar2(64) NOT NULL,
	-- 更新时间
	update_date timestamp NOT NULL,
	-- 备注信息
	remarks nvarchar2(60),
	PRIMARY KEY (id)
);


-- 保养记录
CREATE TABLE is_maintain_rec
(
	-- ID
	id varchar2(64) NOT NULL,
	-- 保养点ID
	maintain_id varchar2(64) NOT NULL,
	-- 保养点名称
	maintain_name varchar2(60) NOT NULL,
	-- 设备编号
	device_no varchar2(10),
	-- 计划人员
	plan_person varchar2(20),
	-- 计划保养日期
	plan_date date,
	-- 保养记录
	record varchar2(200),
	-- 保养人员
	maintain_person varchar2(20),
	-- 保养时间
	maintain_time timestamp,
	-- 记录状态
	rec_status varchar2(1),
	-- 状态
	status char(1) DEFAULT '0' NOT NULL,
	-- 创建者
	create_by varchar2(64) NOT NULL,
	-- 创建时间
	create_date timestamp NOT NULL,
	-- 更新者
	update_by varchar2(64) NOT NULL,
	-- 更新时间
	update_date timestamp NOT NULL,
	-- 备注信息
	remarks nvarchar2(60),
	PRIMARY KEY (id)
);


-- 巡检点
CREATE TABLE is_patrol
(
	-- ID
	id varchar2(64) NOT NULL,
	-- 巡检点名称
	name varchar2(60) NOT NULL,
	-- 巡检序号
	sort_no number(4),
	-- 设备ID
	device_id varchar2(64) NOT NULL,
	-- 设备名称
	device_name varchar2(80) NOT NULL,
	-- 工作类型
	type varchar2(1),
	-- 巡检间隔
	interval number(10),
	-- 巡检内容
	content varchar2(200),
	-- 巡检手册
	knowledge_id varchar2(64),
	-- 状态
	status char(1) DEFAULT '0' NOT NULL,
	-- 创建者
	create_by varchar2(64) NOT NULL,
	-- 创建时间
	create_date timestamp NOT NULL,
	-- 更新者
	update_by varchar2(64) NOT NULL,
	-- 更新时间
	update_date timestamp NOT NULL,
	-- 备注信息
	remarks nvarchar2(60),
	PRIMARY KEY (id)
);


-- 巡检记录
CREATE TABLE is_patrol_rec
(
	-- ID
	id varchar2(64) NOT NULL,
	-- 巡检点ID
	patrol_id varchar2(64) NOT NULL,
	-- 巡检点名称
	patrol_name varchar2(60) NOT NULL,
	-- 巡检记录
	record varchar2(200),
	-- 巡检人员
	operator varchar2(20),
	-- 巡检时间
	patrol_time timestamp,
	-- 状态
	status char(1) DEFAULT '0' NOT NULL,
	-- 创建者
	create_by varchar2(64) NOT NULL,
	-- 创建时间
	create_date timestamp NOT NULL,
	-- 更新者
	update_by varchar2(64) NOT NULL,
	-- 更新时间
	update_date timestamp NOT NULL,
	-- 备注信息
	remarks nvarchar2(60),
	PRIMARY KEY (id)
);


-- 快换电池装置状态
CREATE TABLE is_rebat_status
(
	-- 设备ID
	device_id varchar2(64) NOT NULL,
	-- 设备名称
	device_name varchar2(80) NOT NULL,
	-- OPCID
	opc_id varchar2(64) NOT NULL,
	-- 1#充电位
	bat01 char(1),
	-- 2#充电位
	bat02 char(1),
	-- 3#充电位
	bat03 char(1),
	-- 4#充电位
	bat04 char(1),
	-- 5#充电位
	bat05 char(1),
	PRIMARY KEY (device_id)
);


-- 维修记录
CREATE TABLE is_repair_rec
(
	-- ID
	id varchar2(64) NOT NULL,
	-- 故障ID
	faults_id varchar2(64) NOT NULL,
	-- 故障名称
	faults_name varchar2(60) NOT NULL,
	-- 处理过程
	record varchar2(1000),
	-- 维修结果
	results varchar2(200),
	-- 维修人员
	operator varchar2(20),
	-- 维修时间
	repair_time timestamp,
	-- 状态
	status char(1) DEFAULT '0' NOT NULL,
	-- 创建者
	create_by varchar2(64) NOT NULL,
	-- 创建时间
	create_date timestamp NOT NULL,
	-- 更新者
	update_by varchar2(64) NOT NULL,
	-- 更新时间
	update_date timestamp NOT NULL,
	-- 备注信息
	remarks nvarchar2(60),
	PRIMARY KEY (id)
);


-- 场景
CREATE TABLE is_scene
(
	-- ID
	id varchar2(64) NOT NULL,
	-- 场景代码
	scene_code varchar2(64) NOT NULL,
	-- 场景名称
	scene_name varchar2(64) NOT NULL,
	-- 模型地址
	model_url varchar2(200),
	PRIMARY KEY (id)
);



/* Create Foreign Keys */

ALTER TABLE is_car_count
	ADD FOREIGN KEY (device_id)
	REFERENCES is_car_status (device_id)
;


ALTER TABLE is_car_rec
	ADD FOREIGN KEY (device_id)
	REFERENCES is_car_status (device_id)
;


ALTER TABLE is_battery_status
	ADD FOREIGN KEY (device_id)
	REFERENCES is_device (id)
;


ALTER TABLE is_car_status
	ADD FOREIGN KEY (device_id)
	REFERENCES is_device (id)
;


ALTER TABLE is_device
	ADD FOREIGN KEY (device_id)
	REFERENCES is_device (id)
;


ALTER TABLE is_device_use
	ADD FOREIGN KEY (device_id)
	REFERENCES is_device (id)
;


ALTER TABLE is_faults
	ADD FOREIGN KEY (device_id)
	REFERENCES is_device (id)
;


ALTER TABLE is_patrol
	ADD FOREIGN KEY (device_id)
	REFERENCES is_device (id)
;


ALTER TABLE is_rebat_status
	ADD FOREIGN KEY (device_id)
	REFERENCES is_device (id)
;


ALTER TABLE is_device
	ADD FOREIGN KEY (device_code_id)
	REFERENCES is_device_code (id)
;


ALTER TABLE is_knowledge
	ADD FOREIGN KEY (device_code_id)
	REFERENCES is_device_code (id)
;


ALTER TABLE is_maintain
	ADD FOREIGN KEY (device_code_id)
	REFERENCES is_device_code (id)
;


ALTER TABLE is_repair_rec
	ADD FOREIGN KEY (faults_id)
	REFERENCES is_faults (id)
;


ALTER TABLE is_faults
	ADD FOREIGN KEY (knowledge_id)
	REFERENCES is_knowledge (id)
;


ALTER TABLE is_maintain
	ADD FOREIGN KEY (knowledge_id)
	REFERENCES is_knowledge (id)
;


ALTER TABLE is_patrol
	ADD FOREIGN KEY (knowledge_id)
	REFERENCES is_knowledge (id)
;


ALTER TABLE is_maintain_rec
	ADD FOREIGN KEY (maintain_id)
	REFERENCES is_maintain (id)
;


ALTER TABLE is_patrol_rec
	ADD FOREIGN KEY (patrol_id)
	REFERENCES is_patrol (id)
;



/* Comments */

COMMENT ON TABLE is_battery_status IS '电池状态';
COMMENT ON COLUMN is_battery_status.device_id IS '设备ID';
COMMENT ON COLUMN is_battery_status.device_name IS '设备名称';
COMMENT ON COLUMN is_battery_status.location IS '电池位置';
COMMENT ON COLUMN is_battery_status.charging_time IS '充电次数 ';
COMMENT ON TABLE is_camera IS '摄像头';
COMMENT ON COLUMN is_camera.id IS 'ID';
COMMENT ON COLUMN is_camera.camera_name IS '摄像头名称';
COMMENT ON COLUMN is_camera.IP IS 'IP地址';
COMMENT ON COLUMN is_camera.RTSP IS '视频流地址';
COMMENT ON TABLE is_car_count IS '穿梭车运行统计';
COMMENT ON COLUMN is_car_count.id IS 'ID';
COMMENT ON COLUMN is_car_count.device_id IS '设备ID';
COMMENT ON COLUMN is_car_count.device_name IS '设备名称';
COMMENT ON COLUMN is_car_count.count_date IS '统计日期';
COMMENT ON COLUMN is_car_count.err_count IS '故障统计';
COMMENT ON COLUMN is_car_count.moveerr_count IS '行走故障统计';
COMMENT ON COLUMN is_car_count.updownerr_count IS '升降故障统计';
COMMENT ON COLUMN is_car_count.turnerr_count IS '转向故障统计';
COMMENT ON COLUMN is_car_count.move_mileage IS '行走里程';
COMMENT ON COLUMN is_car_count.updown_count IS '升降次数';
COMMENT ON COLUMN is_car_count.turn_count IS '转向次数';
COMMENT ON COLUMN is_car_count.rechange_count IS '充电次数';
COMMENT ON COLUMN is_car_count.work_time IS '工作时长';
COMMENT ON TABLE is_car_rec IS '穿梭车运行记录';
COMMENT ON COLUMN is_car_rec.id IS 'ID';
COMMENT ON COLUMN is_car_rec.device_id IS '设备ID';
COMMENT ON COLUMN is_car_rec.device_name IS '设备名称';
COMMENT ON COLUMN is_car_rec.record_time IS '记录时间';
COMMENT ON COLUMN is_car_rec.row_no IS '行';
COMMENT ON COLUMN is_car_rec.column_no IS '列';
COMMENT ON COLUMN is_car_rec.layer_no IS '层';
COMMENT ON COLUMN is_car_rec.angle IS '角度';
COMMENT ON COLUMN is_car_rec.plt IS '托盘状态';
COMMENT ON TABLE is_car_status IS '穿梭车状态';
COMMENT ON COLUMN is_car_status.device_id IS '设备ID';
COMMENT ON COLUMN is_car_status.device_name IS '设备名称';
COMMENT ON COLUMN is_car_status.opc_id IS 'OPCID';
COMMENT ON COLUMN is_car_status.row_no IS '行';
COMMENT ON COLUMN is_car_status.column_no IS '列';
COMMENT ON COLUMN is_car_status.layer_no IS '层';
COMMENT ON COLUMN is_car_status.angle IS '角度';
COMMENT ON COLUMN is_car_status.plt IS '托盘状态';
COMMENT ON COLUMN is_car_status.bat_voltage IS '电压';
COMMENT ON COLUMN is_car_status.bat_current IS '电流';
COMMENT ON COLUMN is_car_status.run_state IS '运行状态';
COMMENT ON COLUMN is_car_status.work_state IS '任务状态';
COMMENT ON COLUMN is_car_status.err01 IS '故障1';
COMMENT ON COLUMN is_car_status.err02 IS '故障2';
COMMENT ON COLUMN is_car_status.err03 IS '故障3';
COMMENT ON COLUMN is_car_status.err04 IS '故障4';
COMMENT ON COLUMN is_car_status.err05 IS '故障5';
COMMENT ON COLUMN is_car_status.work_starttime IS '开始运行时间';
COMMENT ON TABLE is_device IS '设备';
COMMENT ON COLUMN is_device.id IS 'ID';
COMMENT ON COLUMN is_device.device_code_id IS '设备代码ID';
COMMENT ON COLUMN is_device.device_code_name IS '设备代码名称';
COMMENT ON COLUMN is_device.device_no IS '设备编号';
COMMENT ON COLUMN is_device.Manufacturer IS '制造厂商';
COMMENT ON COLUMN is_device.production_date IS '出厂日期';
COMMENT ON COLUMN is_device.Supplier IS '供应商';
COMMENT ON COLUMN is_device.use_office IS '启用部门';
COMMENT ON COLUMN is_device.use_date IS '启用时间';
COMMENT ON COLUMN is_device.install_location IS '安装地点';
COMMENT ON COLUMN is_device.location_x IS '定位X';
COMMENT ON COLUMN is_device.location_y IS '定位Y';
COMMENT ON COLUMN is_device.location_z IS '定位Z';
COMMENT ON COLUMN is_device.device_status IS '设备状态';
COMMENT ON COLUMN is_device.status IS '状态';
COMMENT ON COLUMN is_device.create_by IS '创建者';
COMMENT ON COLUMN is_device.create_date IS '创建时间';
COMMENT ON COLUMN is_device.update_by IS '更新者';
COMMENT ON COLUMN is_device.update_date IS '更新时间';
COMMENT ON COLUMN is_device.remarks IS '备注信息';
COMMENT ON COLUMN is_device.device_id IS '设备ID';
COMMENT ON COLUMN is_device.device_name IS '设备名称';
COMMENT ON COLUMN is_device.type IS '类别';
COMMENT ON TABLE is_device_code IS '设备代码';
COMMENT ON COLUMN is_device_code.id IS 'ID';
COMMENT ON COLUMN is_device_code.code IS '代码';
COMMENT ON COLUMN is_device_code.name IS '名称';
COMMENT ON COLUMN is_device_code.type IS '类别';
COMMENT ON COLUMN is_device_code.model IS '型号规格';
COMMENT ON COLUMN is_device_code.parameters IS '设备参数';
COMMENT ON COLUMN is_device_code.application IS '设备用途';
COMMENT ON COLUMN is_device_code.Manufacturer IS '制造厂商';
COMMENT ON COLUMN is_device_code.life IS '使用年限';
COMMENT ON COLUMN is_device_code.depreciation IS '年折旧率';
COMMENT ON COLUMN is_device_code.model_url IS '三维模型';
COMMENT ON COLUMN is_device_code.parent_code IS '父级编号';
COMMENT ON COLUMN is_device_code.tree_names IS '全节点名';
COMMENT ON COLUMN is_device_code.tree_level IS '层次级别';
COMMENT ON COLUMN is_device_code.tree_leaf IS '是否最末级';
COMMENT ON COLUMN is_device_code.tree_sorts IS '所有级别排序号';
COMMENT ON COLUMN is_device_code.tree_sort IS '本节排序号';
COMMENT ON COLUMN is_device_code.parent_codes IS '所有父级编号';
COMMENT ON COLUMN is_device_code.status IS '状态';
COMMENT ON COLUMN is_device_code.create_by IS '创建者';
COMMENT ON COLUMN is_device_code.create_date IS '创建时间';
COMMENT ON COLUMN is_device_code.update_by IS '更新者';
COMMENT ON COLUMN is_device_code.update_date IS '更新时间';
COMMENT ON COLUMN is_device_code.remarks IS '备注信息';
COMMENT ON TABLE is_device_use IS '设备使用记录';
COMMENT ON COLUMN is_device_use.id IS 'ID';
COMMENT ON COLUMN is_device_use.device_id IS '设备ID';
COMMENT ON COLUMN is_device_use.device_name IS '设备名称';
COMMENT ON COLUMN is_device_use.type IS '操作类型';
COMMENT ON COLUMN is_device_use.operator IS '操作人员';
COMMENT ON COLUMN is_device_use.operate_time IS '操作时间';
COMMENT ON COLUMN is_device_use.status IS '状态';
COMMENT ON COLUMN is_device_use.create_by IS '创建者';
COMMENT ON COLUMN is_device_use.create_date IS '创建时间';
COMMENT ON COLUMN is_device_use.update_by IS '更新者';
COMMENT ON COLUMN is_device_use.update_date IS '更新时间';
COMMENT ON COLUMN is_device_use.remarks IS '备注信息';
COMMENT ON TABLE is_faults IS '设备故障';
COMMENT ON COLUMN is_faults.id IS 'ID';
COMMENT ON COLUMN is_faults.name IS '故障名称';
COMMENT ON COLUMN is_faults.device_id IS '设备ID';
COMMENT ON COLUMN is_faults.device_name IS '设备名称';
COMMENT ON COLUMN is_faults.faults_code IS '故障代码';
COMMENT ON COLUMN is_faults.faults_time IS '故障时间';
COMMENT ON COLUMN is_faults.operator IS '报修人员';
COMMENT ON COLUMN is_faults.reason IS '故障原因';
COMMENT ON COLUMN is_faults.knowledge_id IS '推荐方案';
COMMENT ON COLUMN is_faults.status IS '状态';
COMMENT ON COLUMN is_faults.create_by IS '创建者';
COMMENT ON COLUMN is_faults.create_date IS '创建时间';
COMMENT ON COLUMN is_faults.update_by IS '更新者';
COMMENT ON COLUMN is_faults.update_date IS '更新时间';
COMMENT ON COLUMN is_faults.remarks IS '备注信息';
COMMENT ON TABLE is_knowledge IS '知识库';
COMMENT ON COLUMN is_knowledge.id IS 'ID';
COMMENT ON COLUMN is_knowledge.device_code_id IS '设备代码ID';
COMMENT ON COLUMN is_knowledge.device_code_name IS '设备代码名称';
COMMENT ON COLUMN is_knowledge.type IS '知识类型';
COMMENT ON COLUMN is_knowledge.title IS '知识标题';
COMMENT ON COLUMN is_knowledge.content IS '知识内容';
COMMENT ON COLUMN is_knowledge.media_type IS '多媒体类型';
COMMENT ON COLUMN is_knowledge.media_url IS '多媒体地址';
COMMENT ON COLUMN is_knowledge.status IS '状态';
COMMENT ON COLUMN is_knowledge.create_by IS '创建者';
COMMENT ON COLUMN is_knowledge.create_date IS '创建时间';
COMMENT ON COLUMN is_knowledge.update_by IS '更新者';
COMMENT ON COLUMN is_knowledge.update_date IS '更新时间';
COMMENT ON COLUMN is_knowledge.remarks IS '备注信息';
COMMENT ON TABLE is_maintain IS '保养点';
COMMENT ON COLUMN is_maintain.id IS 'ID';
COMMENT ON COLUMN is_maintain.name IS '保养点名称';
COMMENT ON COLUMN is_maintain.device_code_id IS '设备代码ID';
COMMENT ON COLUMN is_maintain.device_code_name IS '设备代码名称';
COMMENT ON COLUMN is_maintain.type IS '保养类型';
COMMENT ON COLUMN is_maintain.content IS '保养内容';
COMMENT ON COLUMN is_maintain.interval IS '间隔时间';
COMMENT ON COLUMN is_maintain.knowledge_id IS '操作手册';
COMMENT ON COLUMN is_maintain.status IS '状态';
COMMENT ON COLUMN is_maintain.create_by IS '创建者';
COMMENT ON COLUMN is_maintain.create_date IS '创建时间';
COMMENT ON COLUMN is_maintain.update_by IS '更新者';
COMMENT ON COLUMN is_maintain.update_date IS '更新时间';
COMMENT ON COLUMN is_maintain.remarks IS '备注信息';
COMMENT ON TABLE is_maintain_rec IS '保养记录';
COMMENT ON COLUMN is_maintain_rec.id IS 'ID';
COMMENT ON COLUMN is_maintain_rec.maintain_id IS '保养点ID';
COMMENT ON COLUMN is_maintain_rec.maintain_name IS '保养点名称';
COMMENT ON COLUMN is_maintain_rec.device_no IS '设备编号';
COMMENT ON COLUMN is_maintain_rec.plan_person IS '计划人员';
COMMENT ON COLUMN is_maintain_rec.plan_date IS '计划保养日期';
COMMENT ON COLUMN is_maintain_rec.record IS '保养记录';
COMMENT ON COLUMN is_maintain_rec.maintain_person IS '保养人员';
COMMENT ON COLUMN is_maintain_rec.maintain_time IS '保养时间';
COMMENT ON COLUMN is_maintain_rec.rec_status IS '记录状态';
COMMENT ON COLUMN is_maintain_rec.status IS '状态';
COMMENT ON COLUMN is_maintain_rec.create_by IS '创建者';
COMMENT ON COLUMN is_maintain_rec.create_date IS '创建时间';
COMMENT ON COLUMN is_maintain_rec.update_by IS '更新者';
COMMENT ON COLUMN is_maintain_rec.update_date IS '更新时间';
COMMENT ON COLUMN is_maintain_rec.remarks IS '备注信息';
COMMENT ON TABLE is_patrol IS '巡检点';
COMMENT ON COLUMN is_patrol.id IS 'ID';
COMMENT ON COLUMN is_patrol.name IS '巡检点名称';
COMMENT ON COLUMN is_patrol.sort_no IS '巡检序号';
COMMENT ON COLUMN is_patrol.device_id IS '设备ID';
COMMENT ON COLUMN is_patrol.device_name IS '设备名称';
COMMENT ON COLUMN is_patrol.type IS '工作类型';
COMMENT ON COLUMN is_patrol.interval IS '巡检间隔';
COMMENT ON COLUMN is_patrol.content IS '巡检内容';
COMMENT ON COLUMN is_patrol.knowledge_id IS '巡检手册';
COMMENT ON COLUMN is_patrol.status IS '状态';
COMMENT ON COLUMN is_patrol.create_by IS '创建者';
COMMENT ON COLUMN is_patrol.create_date IS '创建时间';
COMMENT ON COLUMN is_patrol.update_by IS '更新者';
COMMENT ON COLUMN is_patrol.update_date IS '更新时间';
COMMENT ON COLUMN is_patrol.remarks IS '备注信息';
COMMENT ON TABLE is_patrol_rec IS '巡检记录';
COMMENT ON COLUMN is_patrol_rec.id IS 'ID';
COMMENT ON COLUMN is_patrol_rec.patrol_id IS '巡检点ID';
COMMENT ON COLUMN is_patrol_rec.patrol_name IS '巡检点名称';
COMMENT ON COLUMN is_patrol_rec.record IS '巡检记录';
COMMENT ON COLUMN is_patrol_rec.operator IS '巡检人员';
COMMENT ON COLUMN is_patrol_rec.patrol_time IS '巡检时间';
COMMENT ON COLUMN is_patrol_rec.status IS '状态';
COMMENT ON COLUMN is_patrol_rec.create_by IS '创建者';
COMMENT ON COLUMN is_patrol_rec.create_date IS '创建时间';
COMMENT ON COLUMN is_patrol_rec.update_by IS '更新者';
COMMENT ON COLUMN is_patrol_rec.update_date IS '更新时间';
COMMENT ON COLUMN is_patrol_rec.remarks IS '备注信息';
COMMENT ON TABLE is_rebat_status IS '快换电池装置状态';
COMMENT ON COLUMN is_rebat_status.device_id IS '设备ID';
COMMENT ON COLUMN is_rebat_status.device_name IS '设备名称';
COMMENT ON COLUMN is_rebat_status.opc_id IS 'OPCID';
COMMENT ON COLUMN is_rebat_status.bat01 IS '1#充电位';
COMMENT ON COLUMN is_rebat_status.bat02 IS '2#充电位';
COMMENT ON COLUMN is_rebat_status.bat03 IS '3#充电位';
COMMENT ON COLUMN is_rebat_status.bat04 IS '4#充电位';
COMMENT ON COLUMN is_rebat_status.bat05 IS '5#充电位';
COMMENT ON TABLE is_repair_rec IS '维修记录';
COMMENT ON COLUMN is_repair_rec.id IS 'ID';
COMMENT ON COLUMN is_repair_rec.faults_id IS '故障ID';
COMMENT ON COLUMN is_repair_rec.faults_name IS '故障名称';
COMMENT ON COLUMN is_repair_rec.record IS '处理过程';
COMMENT ON COLUMN is_repair_rec.results IS '维修结果';
COMMENT ON COLUMN is_repair_rec.operator IS '维修人员';
COMMENT ON COLUMN is_repair_rec.repair_time IS '维修时间';
COMMENT ON COLUMN is_repair_rec.status IS '状态';
COMMENT ON COLUMN is_repair_rec.create_by IS '创建者';
COMMENT ON COLUMN is_repair_rec.create_date IS '创建时间';
COMMENT ON COLUMN is_repair_rec.update_by IS '更新者';
COMMENT ON COLUMN is_repair_rec.update_date IS '更新时间';
COMMENT ON COLUMN is_repair_rec.remarks IS '备注信息';
COMMENT ON TABLE is_scene IS '场景';
COMMENT ON COLUMN is_scene.id IS 'ID';
COMMENT ON COLUMN is_scene.scene_code IS '场景代码';
COMMENT ON COLUMN is_scene.scene_name IS '场景名称';
COMMENT ON COLUMN is_scene.model_url IS '模型地址';



