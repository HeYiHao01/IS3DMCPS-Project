
/* Drop Tables */

DROP TABLE is_battery_statics CASCADE CONSTRAINTS;
DROP TABLE is_camera CASCADE CONSTRAINTS;
DROP TABLE is_car_rec CASCADE CONSTRAINTS;
DROP TABLE is_chargingplace_rec CASCADE CONSTRAINTS;
DROP TABLE is_repair_rec CASCADE CONSTRAINTS;
DROP TABLE is_faults CASCADE CONSTRAINTS;
DROP TABLE is_maintain_rec CASCADE CONSTRAINTS;
DROP TABLE is_device CASCADE CONSTRAINTS;
DROP TABLE is_knowledge CASCADE CONSTRAINTS;
DROP TABLE is_maintain_plan CASCADE CONSTRAINTS;
DROP TABLE is_device_code CASCADE CONSTRAINTS;
DROP TABLE is_patrol_rec CASCADE CONSTRAINTS;
DROP TABLE is_patrol CASCADE CONSTRAINTS;
DROP TABLE is_scene CASCADE CONSTRAINTS;




/* Create Tables */

-- 电池次数统计
CREATE TABLE is_battery_statics
(
	-- 编号
	id varchar2(64) NOT NULL,
	-- 电池id
	battery_id varchar2(64),
	-- 充电次数 
	charging_time number(5),
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
	remarks nvarchar2(500),
	PRIMARY KEY (id)
);


-- 摄像头
CREATE TABLE is_camera
(
	-- 摄像头代码
	camera_code varchar2(64) NOT NULL,
	-- 摄像头名称
	camera_name varchar2(64),
	-- IP地址
	IP varchar2(15),
	-- 视频流地址
	RTSP varchar2(200),
	-- 父级编号
	parent_code varchar2(64) NOT NULL,
	-- 所有父级编号
	parent_codes varchar2(1000) NOT NULL,
	-- 本节排序号
	tree_sort number(10,0) NOT NULL,
	-- 所有级别排序号
	tree_sorts varchar2(1000) NOT NULL,
	-- 是否最末级
	tree_leaf char(1) NOT NULL,
	-- 层次级别
	tree_level number(4) NOT NULL,
	-- 全节点名
	tree_names varchar2(1000) NOT NULL,
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
	remarks nvarchar2(500),
	PRIMARY KEY (camera_code)
);


-- 小车运行记录
CREATE TABLE is_car_rec
(
	-- 编号
	id varchar2(64) NOT NULL,
	-- 位置
	location varchar2(64),
	-- 时间
	time date,
	-- 运行状态
	run_status varchar2(2),
	-- 载货状态
	carry_status varchar2(64),
	-- 小车id
	car_id varchar2(64),
	-- 电池id
	battery_id varchar2(64),
	-- 电量
	quantity number(3),
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
	remarks nvarchar2(500),
	PRIMARY KEY (id)
);


-- 电池充电位运行记录
CREATE TABLE is_chargingplace_rec
(
	-- 编号
	id varchar2(64) NOT NULL,
	-- 电池位id
	charing_id varchar2(64),
	-- 是否在充电
	is_charging varchar2(3),
	-- 电池的id
	battery_id varchar2(64),
	-- 充电装置id
	charging_dev_id varchar2(64),
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
	remarks nvarchar2(500),
	PRIMARY KEY (id)
);


-- 设备
CREATE TABLE is_device
(
	-- 设备编号
	device_no varchar2(64) NOT NULL,
	-- 设备代码
	device_code varchar2(64) NOT NULL,
	-- 运行状态
	run_status varchar2(1),
	-- 使用状态
	use_status varchar2(1),
	-- 出厂日期
	production_date date,
	-- 启用部门
	office_code varchar2(64),
	-- 启用时间
	use_date date,
	-- 安装地点
	install_location varchar2(60),
	-- 定位
	location varchar2(60),
	-- 供应商
	Supplier varchar2(60),
	-- 报警编号
	warn_code varchar2(64),
	-- 父级编号
	parent_code varchar2(64) NOT NULL,
	-- 所有父级编号
	parent_codes varchar2(1000) NOT NULL,
	-- 本节排序号
	tree_sort number(10,0) NOT NULL,
	-- 所有级别排序号
	tree_sorts varchar2(1000) NOT NULL,
	-- 是否最末级
	tree_leaf char(1) NOT NULL,
	-- 层次级别
	tree_level number(4) NOT NULL,
	-- 全节点名
	tree_names varchar2(1000) NOT NULL,
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
	remarks nvarchar2(500),
	PRIMARY KEY (device_no)
);


-- 设备代码
CREATE TABLE is_device_code
(
	-- 设备代码
	device_code varchar2(64) NOT NULL,
	-- 设备名称
	device_name varchar2(60),
	-- 型号规格
	model varchar2(60),
	-- 设备参数
	parameter varchar2(60),
	-- 设备用途
	application varchar2(60),
	-- 制造厂商
	Manufacturer varchar2(60),
	-- 使用年限
	life number(2),
	-- 知识编号
	knowledge_id varchar2(64),
	-- 父级编号
	parent_code varchar2(64) NOT NULL,
	-- 所有父级编号
	parent_codes varchar2(1000) NOT NULL,
	-- 本节排序号
	tree_sort number(10,0) NOT NULL,
	-- 所有级别排序号
	tree_sorts varchar2(1000) NOT NULL,
	-- 是否最末级
	tree_leaf char(1) NOT NULL,
	-- 层次级别
	tree_level number(4) NOT NULL,
	-- 全节点名
	tree_names varchar2(1000) NOT NULL,
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
	remarks nvarchar2(500),
	PRIMARY KEY (device_code)
);


-- 设备故障
CREATE TABLE is_faults
(
	-- 编号
	id varchar2(64) NOT NULL,
	-- 设备编号
	device_no varchar2(64) NOT NULL,
	-- 故障名称
	faults_name varchar2(64),
	-- 故障原因
	faults_reason varchar2(200),
	-- 知识id
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
	remarks nvarchar2(500),
	PRIMARY KEY (id)
);


-- 知识库
CREATE TABLE is_knowledge
(
	-- 编号
	id varchar2(64) NOT NULL,
	-- 设备代码
	device_code varchar2(64) NOT NULL,
	-- 知识类型
	knowledge_type varchar2(2),
	-- 知识内容
	html varchar2(2000),
	-- 图示地址
	url varchar2(200),
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
	remarks nvarchar2(500),
	PRIMARY KEY (id)
);


-- 保养计划
CREATE TABLE is_maintain_plan
(
	-- 编号
	id varchar2(64) NOT NULL,
	-- 设备代码
	device_code varchar2(64) NOT NULL,
	-- 保养类型
	maintain_type varchar2(2),
	-- 知识编号
	knowledge_id varchar2(64),
	-- 保养内容
	content varchar2(500),
	-- 间隔时间
	interval number(4),
	-- 创建者
	create_by varchar2(64) NOT NULL,
	-- 创建时间
	create_date timestamp NOT NULL,
	-- 更新者
	update_by varchar2(64) NOT NULL,
	-- 更新时间
	update_date timestamp NOT NULL,
	-- 备注信息
	remarks nvarchar2(500),
	PRIMARY KEY (id)
);


-- 保养记录
CREATE TABLE is_maintain_rec
(
	-- 编号
	id varchar2(64) NOT NULL,
	-- 设备编号
	device_no varchar2(64) NOT NULL,
	-- 保养计划编号
	plan_id varchar2(64) NOT NULL,
	-- 保养时间
	maintain_date date,
	-- 保养人
	operator varchar2(20),
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
	remarks nvarchar2(500),
	PRIMARY KEY (id)
);


-- 设备巡检点
CREATE TABLE is_patrol
(
	-- 编号
	id varchar2(64) NOT NULL,
	-- 巡检点名称
	name varchar2(60),
	-- 巡检内容
	contant varchar2(200),
	-- 巡检点位置
	location varchar2(60),
	-- 巡检工作类型
	work_type varchar2(1),
	-- 巡检间隔
	interval number(10),
	-- 知识编号
	knowledge_id varchar2(64),
	PRIMARY KEY (id)
);


-- 巡检记录
CREATE TABLE is_patrol_rec
(
	-- 编号
	id varchar2(64) NOT NULL,
	-- 巡检点编号
	patrol_id varchar2(64) NOT NULL,
	-- 巡检时间
	patrol_date date,
	-- 巡检人
	operator varchar2(20),
	-- 是否正常
	is_ok varchar2(1),
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
	remarks nvarchar2(500),
	PRIMARY KEY (id)
);


-- 维修记录
CREATE TABLE is_repair_rec
(
	-- 编号
	id varchar2(64) NOT NULL,
	-- 设备编号
	device_no varchar2(64) NOT NULL,
	-- 故障编号
	fault_id varchar2(64) NOT NULL,
	-- 维修时间
	repair_time date,
	-- 维修人
	operator varchar2(20),
	-- 处理过程
	process varchar2(1000),
	-- 维修结果
	repair_result varchar2(2),
	-- 图示地址
	url varchar2(200),
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
	remarks nvarchar2(500),
	PRIMARY KEY (id)
);


-- 场景
CREATE TABLE is_scene
(
	-- 场景代码
	scene_code varchar2(64) NOT NULL,
	-- 场景名称
	scene_name varchar2(64),
	-- 三维展示地址
	url varchar2(200),
	-- 父级编号
	parent_code varchar2(64) NOT NULL,
	-- 所有父级编号
	parent_codes varchar2(1000) NOT NULL,
	-- 本节排序号
	tree_sort number(10,0) NOT NULL,
	-- 所有级别排序号
	tree_sorts varchar2(1000) NOT NULL,
	-- 是否最末级
	tree_leaf char(1) NOT NULL,
	-- 层次级别
	tree_level number(4) NOT NULL,
	-- 全节点名
	tree_names varchar2(1000) NOT NULL,
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
	remarks nvarchar2(500),
	PRIMARY KEY (scene_code)
);



/* Create Foreign Keys */

ALTER TABLE is_faults
	ADD FOREIGN KEY (device_no)
	REFERENCES is_device (device_no)
;


ALTER TABLE is_maintain_rec
	ADD FOREIGN KEY (device_no)
	REFERENCES is_device (device_no)
;


ALTER TABLE is_repair_rec
	ADD FOREIGN KEY (device_no)
	REFERENCES is_device (device_no)
;


ALTER TABLE is_device
	ADD FOREIGN KEY (device_code)
	REFERENCES is_device_code (device_code)
;


ALTER TABLE is_knowledge
	ADD FOREIGN KEY (device_code)
	REFERENCES is_device_code (device_code)
;


ALTER TABLE is_maintain_plan
	ADD FOREIGN KEY (device_code)
	REFERENCES is_device_code (device_code)
;


ALTER TABLE is_repair_rec
	ADD FOREIGN KEY (fault_id)
	REFERENCES is_faults (id)
;


ALTER TABLE is_maintain_rec
	ADD FOREIGN KEY (plan_id)
	REFERENCES is_maintain_plan (id)
;


ALTER TABLE is_patrol_rec
	ADD FOREIGN KEY (patrol_id)
	REFERENCES is_patrol (id)
;



/* Comments */

COMMENT ON TABLE is_battery_statics IS '电池次数统计';
COMMENT ON COLUMN is_battery_statics.id IS '编号';
COMMENT ON COLUMN is_battery_statics.battery_id IS '电池id';
COMMENT ON COLUMN is_battery_statics.charging_time IS '充电次数 ';
COMMENT ON COLUMN is_battery_statics.status IS '状态';
COMMENT ON COLUMN is_battery_statics.create_by IS '创建者';
COMMENT ON COLUMN is_battery_statics.create_date IS '创建时间';
COMMENT ON COLUMN is_battery_statics.update_by IS '更新者';
COMMENT ON COLUMN is_battery_statics.update_date IS '更新时间';
COMMENT ON COLUMN is_battery_statics.remarks IS '备注信息';
COMMENT ON TABLE is_camera IS '摄像头';
COMMENT ON COLUMN is_camera.camera_code IS '摄像头代码';
COMMENT ON COLUMN is_camera.camera_name IS '摄像头名称';
COMMENT ON COLUMN is_camera.IP IS 'IP地址';
COMMENT ON COLUMN is_camera.RTSP IS '视频流地址';
COMMENT ON COLUMN is_camera.parent_code IS '父级编号';
COMMENT ON COLUMN is_camera.parent_codes IS '所有父级编号';
COMMENT ON COLUMN is_camera.tree_sort IS '本节排序号';
COMMENT ON COLUMN is_camera.tree_sorts IS '所有级别排序号';
COMMENT ON COLUMN is_camera.tree_leaf IS '是否最末级';
COMMENT ON COLUMN is_camera.tree_level IS '层次级别';
COMMENT ON COLUMN is_camera.tree_names IS '全节点名';
COMMENT ON COLUMN is_camera.status IS '状态';
COMMENT ON COLUMN is_camera.create_by IS '创建者';
COMMENT ON COLUMN is_camera.create_date IS '创建时间';
COMMENT ON COLUMN is_camera.update_by IS '更新者';
COMMENT ON COLUMN is_camera.update_date IS '更新时间';
COMMENT ON COLUMN is_camera.remarks IS '备注信息';
COMMENT ON TABLE is_car_rec IS '小车运行记录';
COMMENT ON COLUMN is_car_rec.id IS '编号';
COMMENT ON COLUMN is_car_rec.location IS '位置';
COMMENT ON COLUMN is_car_rec.time IS '时间';
COMMENT ON COLUMN is_car_rec.run_status IS '运行状态';
COMMENT ON COLUMN is_car_rec.carry_status IS '载货状态';
COMMENT ON COLUMN is_car_rec.car_id IS '小车id';
COMMENT ON COLUMN is_car_rec.battery_id IS '电池id';
COMMENT ON COLUMN is_car_rec.quantity IS '电量';
COMMENT ON COLUMN is_car_rec.status IS '状态';
COMMENT ON COLUMN is_car_rec.create_by IS '创建者';
COMMENT ON COLUMN is_car_rec.create_date IS '创建时间';
COMMENT ON COLUMN is_car_rec.update_by IS '更新者';
COMMENT ON COLUMN is_car_rec.update_date IS '更新时间';
COMMENT ON COLUMN is_car_rec.remarks IS '备注信息';
COMMENT ON TABLE is_chargingplace_rec IS '电池充电位运行记录';
COMMENT ON COLUMN is_chargingplace_rec.id IS '编号';
COMMENT ON COLUMN is_chargingplace_rec.charing_id IS '电池位id';
COMMENT ON COLUMN is_chargingplace_rec.is_charging IS '是否在充电';
COMMENT ON COLUMN is_chargingplace_rec.battery_id IS '电池的id';
COMMENT ON COLUMN is_chargingplace_rec.charging_dev_id IS '充电装置id';
COMMENT ON COLUMN is_chargingplace_rec.status IS '状态';
COMMENT ON COLUMN is_chargingplace_rec.create_by IS '创建者';
COMMENT ON COLUMN is_chargingplace_rec.create_date IS '创建时间';
COMMENT ON COLUMN is_chargingplace_rec.update_by IS '更新者';
COMMENT ON COLUMN is_chargingplace_rec.update_date IS '更新时间';
COMMENT ON COLUMN is_chargingplace_rec.remarks IS '备注信息';
COMMENT ON TABLE is_device IS '设备';
COMMENT ON COLUMN is_device.device_no IS '设备编号';
COMMENT ON COLUMN is_device.device_code IS '设备代码';
COMMENT ON COLUMN is_device.run_status IS '运行状态';
COMMENT ON COLUMN is_device.use_status IS '使用状态';
COMMENT ON COLUMN is_device.production_date IS '出厂日期';
COMMENT ON COLUMN is_device.office_code IS '启用部门';
COMMENT ON COLUMN is_device.use_date IS '启用时间';
COMMENT ON COLUMN is_device.install_location IS '安装地点';
COMMENT ON COLUMN is_device.location IS '定位';
COMMENT ON COLUMN is_device.Supplier IS '供应商';
COMMENT ON COLUMN is_device.warn_code IS '报警编号';
COMMENT ON COLUMN is_device.parent_code IS '父级编号';
COMMENT ON COLUMN is_device.parent_codes IS '所有父级编号';
COMMENT ON COLUMN is_device.tree_sort IS '本节排序号';
COMMENT ON COLUMN is_device.tree_sorts IS '所有级别排序号';
COMMENT ON COLUMN is_device.tree_leaf IS '是否最末级';
COMMENT ON COLUMN is_device.tree_level IS '层次级别';
COMMENT ON COLUMN is_device.tree_names IS '全节点名';
COMMENT ON COLUMN is_device.status IS '状态';
COMMENT ON COLUMN is_device.create_by IS '创建者';
COMMENT ON COLUMN is_device.create_date IS '创建时间';
COMMENT ON COLUMN is_device.update_by IS '更新者';
COMMENT ON COLUMN is_device.update_date IS '更新时间';
COMMENT ON COLUMN is_device.remarks IS '备注信息';
COMMENT ON TABLE is_device_code IS '设备代码';
COMMENT ON COLUMN is_device_code.device_code IS '设备代码';
COMMENT ON COLUMN is_device_code.device_name IS '设备名称';
COMMENT ON COLUMN is_device_code.model IS '型号规格';
COMMENT ON COLUMN is_device_code.parameter IS '设备参数';
COMMENT ON COLUMN is_device_code.application IS '设备用途';
COMMENT ON COLUMN is_device_code.Manufacturer IS '制造厂商';
COMMENT ON COLUMN is_device_code.life IS '使用年限';
COMMENT ON COLUMN is_device_code.knowledge_id IS '知识编号';
COMMENT ON COLUMN is_device_code.parent_code IS '父级编号';
COMMENT ON COLUMN is_device_code.parent_codes IS '所有父级编号';
COMMENT ON COLUMN is_device_code.tree_sort IS '本节排序号';
COMMENT ON COLUMN is_device_code.tree_sorts IS '所有级别排序号';
COMMENT ON COLUMN is_device_code.tree_leaf IS '是否最末级';
COMMENT ON COLUMN is_device_code.tree_level IS '层次级别';
COMMENT ON COLUMN is_device_code.tree_names IS '全节点名';
COMMENT ON COLUMN is_device_code.status IS '状态';
COMMENT ON COLUMN is_device_code.create_by IS '创建者';
COMMENT ON COLUMN is_device_code.create_date IS '创建时间';
COMMENT ON COLUMN is_device_code.update_by IS '更新者';
COMMENT ON COLUMN is_device_code.update_date IS '更新时间';
COMMENT ON COLUMN is_device_code.remarks IS '备注信息';
COMMENT ON TABLE is_faults IS '设备故障';
COMMENT ON COLUMN is_faults.id IS '编号';
COMMENT ON COLUMN is_faults.device_no IS '设备编号';
COMMENT ON COLUMN is_faults.faults_name IS '故障名称';
COMMENT ON COLUMN is_faults.faults_reason IS '故障原因';
COMMENT ON COLUMN is_faults.knowledge_id IS '知识id';
COMMENT ON COLUMN is_faults.status IS '状态';
COMMENT ON COLUMN is_faults.create_by IS '创建者';
COMMENT ON COLUMN is_faults.create_date IS '创建时间';
COMMENT ON COLUMN is_faults.update_by IS '更新者';
COMMENT ON COLUMN is_faults.update_date IS '更新时间';
COMMENT ON COLUMN is_faults.remarks IS '备注信息';
COMMENT ON TABLE is_knowledge IS '知识库';
COMMENT ON COLUMN is_knowledge.id IS '编号';
COMMENT ON COLUMN is_knowledge.device_code IS '设备代码';
COMMENT ON COLUMN is_knowledge.knowledge_type IS '知识类型';
COMMENT ON COLUMN is_knowledge.html IS '知识内容';
COMMENT ON COLUMN is_knowledge.url IS '图示地址';
COMMENT ON COLUMN is_knowledge.status IS '状态';
COMMENT ON COLUMN is_knowledge.create_by IS '创建者';
COMMENT ON COLUMN is_knowledge.create_date IS '创建时间';
COMMENT ON COLUMN is_knowledge.update_by IS '更新者';
COMMENT ON COLUMN is_knowledge.update_date IS '更新时间';
COMMENT ON COLUMN is_knowledge.remarks IS '备注信息';
COMMENT ON TABLE is_maintain_plan IS '保养计划';
COMMENT ON COLUMN is_maintain_plan.id IS '编号';
COMMENT ON COLUMN is_maintain_plan.device_code IS '设备代码';
COMMENT ON COLUMN is_maintain_plan.maintain_type IS '保养类型';
COMMENT ON COLUMN is_maintain_plan.knowledge_id IS '知识编号';
COMMENT ON COLUMN is_maintain_plan.content IS '保养内容';
COMMENT ON COLUMN is_maintain_plan.interval IS '间隔时间';
COMMENT ON COLUMN is_maintain_plan.create_by IS '创建者';
COMMENT ON COLUMN is_maintain_plan.create_date IS '创建时间';
COMMENT ON COLUMN is_maintain_plan.update_by IS '更新者';
COMMENT ON COLUMN is_maintain_plan.update_date IS '更新时间';
COMMENT ON COLUMN is_maintain_plan.remarks IS '备注信息';
COMMENT ON TABLE is_maintain_rec IS '保养记录';
COMMENT ON COLUMN is_maintain_rec.id IS '编号';
COMMENT ON COLUMN is_maintain_rec.device_no IS '设备编号';
COMMENT ON COLUMN is_maintain_rec.plan_id IS '保养计划编号';
COMMENT ON COLUMN is_maintain_rec.maintain_date IS '保养时间';
COMMENT ON COLUMN is_maintain_rec.operator IS '保养人';
COMMENT ON COLUMN is_maintain_rec.status IS '状态';
COMMENT ON COLUMN is_maintain_rec.create_by IS '创建者';
COMMENT ON COLUMN is_maintain_rec.create_date IS '创建时间';
COMMENT ON COLUMN is_maintain_rec.update_by IS '更新者';
COMMENT ON COLUMN is_maintain_rec.update_date IS '更新时间';
COMMENT ON COLUMN is_maintain_rec.remarks IS '备注信息';
COMMENT ON TABLE is_patrol IS '设备巡检点';
COMMENT ON COLUMN is_patrol.id IS '编号';
COMMENT ON COLUMN is_patrol.name IS '巡检点名称';
COMMENT ON COLUMN is_patrol.contant IS '巡检内容';
COMMENT ON COLUMN is_patrol.location IS '巡检点位置';
COMMENT ON COLUMN is_patrol.work_type IS '巡检工作类型';
COMMENT ON COLUMN is_patrol.interval IS '巡检间隔';
COMMENT ON COLUMN is_patrol.knowledge_id IS '知识编号';
COMMENT ON TABLE is_patrol_rec IS '巡检记录';
COMMENT ON COLUMN is_patrol_rec.id IS '编号';
COMMENT ON COLUMN is_patrol_rec.patrol_id IS '巡检点编号';
COMMENT ON COLUMN is_patrol_rec.patrol_date IS '巡检时间';
COMMENT ON COLUMN is_patrol_rec.operator IS '巡检人';
COMMENT ON COLUMN is_patrol_rec.is_ok IS '是否正常';
COMMENT ON COLUMN is_patrol_rec.status IS '状态';
COMMENT ON COLUMN is_patrol_rec.create_by IS '创建者';
COMMENT ON COLUMN is_patrol_rec.create_date IS '创建时间';
COMMENT ON COLUMN is_patrol_rec.update_by IS '更新者';
COMMENT ON COLUMN is_patrol_rec.update_date IS '更新时间';
COMMENT ON COLUMN is_patrol_rec.remarks IS '备注信息';
COMMENT ON TABLE is_repair_rec IS '维修记录';
COMMENT ON COLUMN is_repair_rec.id IS '编号';
COMMENT ON COLUMN is_repair_rec.device_no IS '设备编号';
COMMENT ON COLUMN is_repair_rec.fault_id IS '故障编号';
COMMENT ON COLUMN is_repair_rec.repair_time IS '维修时间';
COMMENT ON COLUMN is_repair_rec.operator IS '维修人';
COMMENT ON COLUMN is_repair_rec.process IS '处理过程';
COMMENT ON COLUMN is_repair_rec.repair_result IS '维修结果';
COMMENT ON COLUMN is_repair_rec.url IS '图示地址';
COMMENT ON COLUMN is_repair_rec.status IS '状态';
COMMENT ON COLUMN is_repair_rec.create_by IS '创建者';
COMMENT ON COLUMN is_repair_rec.create_date IS '创建时间';
COMMENT ON COLUMN is_repair_rec.update_by IS '更新者';
COMMENT ON COLUMN is_repair_rec.update_date IS '更新时间';
COMMENT ON COLUMN is_repair_rec.remarks IS '备注信息';
COMMENT ON TABLE is_scene IS '场景';
COMMENT ON COLUMN is_scene.scene_code IS '场景代码';
COMMENT ON COLUMN is_scene.scene_name IS '场景名称';
COMMENT ON COLUMN is_scene.url IS '三维展示地址';
COMMENT ON COLUMN is_scene.parent_code IS '父级编号';
COMMENT ON COLUMN is_scene.parent_codes IS '所有父级编号';
COMMENT ON COLUMN is_scene.tree_sort IS '本节排序号';
COMMENT ON COLUMN is_scene.tree_sorts IS '所有级别排序号';
COMMENT ON COLUMN is_scene.tree_leaf IS '是否最末级';
COMMENT ON COLUMN is_scene.tree_level IS '层次级别';
COMMENT ON COLUMN is_scene.tree_names IS '全节点名';
COMMENT ON COLUMN is_scene.status IS '状态';
COMMENT ON COLUMN is_scene.create_by IS '创建者';
COMMENT ON COLUMN is_scene.create_date IS '创建时间';
COMMENT ON COLUMN is_scene.update_by IS '更新者';
COMMENT ON COLUMN is_scene.update_date IS '更新时间';
COMMENT ON COLUMN is_scene.remarks IS '备注信息';



