
DROP TABLE iss_door CASCADE CONSTRAINTS;
DROP TABLE iss_door_ctrl CASCADE CONSTRAINTS;
DROP TABLE iss_door_log CASCADE CONSTRAINTS;

-- 门禁通道
CREATE TABLE iss_door
(
	-- 编号
	id varchar2(64) NOT NULL,
	-- 控制器地址
	ctrl_ip varchar2(15),
	-- 通道编号
	door_no char(1),
	-- 通道名称
	door_name varchar2(60),
	-- 控制方式
	door_ctrltype char(1),
	-- 相机地址
	camera_ip char(15),
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


-- 门禁控制器
CREATE TABLE iss_door_ctrl
(
	-- 编号
	id varchar2(64) NOT NULL,
	-- 控制器名称
	ctrl_name varchar2(60),
	-- 控制器类型
	ctrl_type varchar2(2),
	-- 控制器地址
	ctrl_ip varchar2(15),
	-- 控制器端口
	ctrl_port varchar2(10),
	-- 用户名
	ctrl_username nvarchar2(60),
	-- 口令
	ctrl_password varchar2(60),
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
	-- 单位代码
	company_code varchar2(64),
	-- 管理部门代码
	office_code varchar2(64),
	PRIMARY KEY (id)
);


-- 门禁日志
CREATE TABLE iss_door_log
(
	-- 编号
	id varchar2(64) NOT NULL,
	-- 控制器地址
	ctrl_ip varchar2(15),
	-- 控制器名称
	ctrl_name varchar2(60),
	-- 通道编号
	door_no char(1),
	-- 通道名称
	door_name varchar2(60),
	-- 控制方式
	door_ctrltype char(1),
	-- 读卡器编号
	card_reader_no char(1),
	-- 卡号
	card_id varchar2(60),
	-- 刷卡时间
	card_readtime timestamp,
	-- 人员编号
	person_id varchar2(64),
	-- 人员姓名
	person_name nvarchar2(20),
	-- 抓拍编号
	capture_id varchar2(64),
	-- 抓拍时间
	capture_time timestamp,
	-- 开门方式
	open_type char(1),
	-- 开门时间
	open_time timestamp,
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
	-- 单位代码
	company_code varchar2(64),
	-- 管理部门代码
	office_code varchar2(64),
	PRIMARY KEY (id)
);


COMMENT ON TABLE iss_door IS '门禁通道';
COMMENT ON COLUMN iss_door.id IS '编号';
COMMENT ON COLUMN iss_door.ctrl_ip IS '控制器地址';
COMMENT ON COLUMN iss_door.door_no IS '通道编号';
COMMENT ON COLUMN iss_door.door_name IS '通道名称';
COMMENT ON COLUMN iss_door.door_ctrltype IS '控制方式';
COMMENT ON COLUMN iss_door.camera_ip IS '相机地址';
COMMENT ON COLUMN iss_door.status IS '状态';
COMMENT ON COLUMN iss_door.create_by IS '创建者';
COMMENT ON COLUMN iss_door.create_date IS '创建时间';
COMMENT ON COLUMN iss_door.update_by IS '更新者';
COMMENT ON COLUMN iss_door.update_date IS '更新时间';
COMMENT ON COLUMN iss_door.remarks IS '备注信息';
COMMENT ON TABLE iss_door_ctrl IS '门禁控制器';
COMMENT ON COLUMN iss_door_ctrl.id IS '编号';
COMMENT ON COLUMN iss_door_ctrl.ctrl_name IS '控制器名称';
COMMENT ON COLUMN iss_door_ctrl.ctrl_type IS '控制器类型';
COMMENT ON COLUMN iss_door_ctrl.ctrl_ip IS '控制器地址';
COMMENT ON COLUMN iss_door_ctrl.ctrl_port IS '控制器端口';
COMMENT ON COLUMN iss_door_ctrl.ctrl_username IS '用户名';
COMMENT ON COLUMN iss_door_ctrl.ctrl_password IS '口令';
COMMENT ON COLUMN iss_door_ctrl.status IS '状态';
COMMENT ON COLUMN iss_door_ctrl.create_by IS '创建者';
COMMENT ON COLUMN iss_door_ctrl.create_date IS '创建时间';
COMMENT ON COLUMN iss_door_ctrl.update_by IS '更新者';
COMMENT ON COLUMN iss_door_ctrl.update_date IS '更新时间';
COMMENT ON COLUMN iss_door_ctrl.remarks IS '备注信息';
COMMENT ON COLUMN iss_door_ctrl.company_code IS '单位代码';
COMMENT ON COLUMN iss_door_ctrl.office_code IS '管理部门代码';
COMMENT ON TABLE iss_door_log IS '门禁日志';
COMMENT ON COLUMN iss_door_log.id IS '编号';
COMMENT ON COLUMN iss_door_log.ctrl_ip IS '控制器地址';
COMMENT ON COLUMN iss_door_log.ctrl_name IS '控制器名称';
COMMENT ON COLUMN iss_door_log.door_no IS '通道编号';
COMMENT ON COLUMN iss_door_log.door_name IS '通道名称';
COMMENT ON COLUMN iss_door_log.door_ctrltype IS '控制方式';
COMMENT ON COLUMN iss_door_log.card_reader_no IS '读卡器编号';
COMMENT ON COLUMN iss_door_log.card_id IS '卡号';
COMMENT ON COLUMN iss_door_log.card_readtime IS '刷卡时间';
COMMENT ON COLUMN iss_door_log.person_id IS '人员编号';
COMMENT ON COLUMN iss_door_log.person_name IS '人员姓名';
COMMENT ON COLUMN iss_door_log.capture_id IS '抓拍编号';
COMMENT ON COLUMN iss_door_log.capture_time IS '抓拍时间';
COMMENT ON COLUMN iss_door_log.open_type IS '开门方式';
COMMENT ON COLUMN iss_door_log.open_time IS '开门时间';
COMMENT ON COLUMN iss_door_log.status IS '状态';
COMMENT ON COLUMN iss_door_log.create_by IS '创建者';
COMMENT ON COLUMN iss_door_log.create_date IS '创建时间';
COMMENT ON COLUMN iss_door_log.update_by IS '更新者';
COMMENT ON COLUMN iss_door_log.update_date IS '更新时间';
COMMENT ON COLUMN iss_door_log.remarks IS '备注信息';
COMMENT ON COLUMN iss_door_log.company_code IS '单位代码';
COMMENT ON COLUMN iss_door_log.office_code IS '管理部门代码';
