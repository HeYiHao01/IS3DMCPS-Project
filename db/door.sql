
DROP TABLE iss_door CASCADE CONSTRAINTS;
DROP TABLE iss_door_ctrl CASCADE CONSTRAINTS;
DROP TABLE iss_door_log CASCADE CONSTRAINTS;

-- �Ž�ͨ��
CREATE TABLE iss_door
(
	-- ���
	id varchar2(64) NOT NULL,
	-- ��������ַ
	ctrl_ip varchar2(15),
	-- ͨ�����
	door_no char(1),
	-- ͨ������
	door_name varchar2(60),
	-- ���Ʒ�ʽ
	door_ctrltype char(1),
	-- �����ַ
	camera_ip char(15),
	-- ״̬
	status char(1) DEFAULT '0' NOT NULL,
	-- ������
	create_by varchar2(64) NOT NULL,
	-- ����ʱ��
	create_date timestamp NOT NULL,
	-- ������
	update_by varchar2(64) NOT NULL,
	-- ����ʱ��
	update_date timestamp NOT NULL,
	-- ��ע��Ϣ
	remarks nvarchar2(500),
	PRIMARY KEY (id)
);


-- �Ž�������
CREATE TABLE iss_door_ctrl
(
	-- ���
	id varchar2(64) NOT NULL,
	-- ����������
	ctrl_name varchar2(60),
	-- ����������
	ctrl_type varchar2(2),
	-- ��������ַ
	ctrl_ip varchar2(15),
	-- �������˿�
	ctrl_port varchar2(10),
	-- �û���
	ctrl_username nvarchar2(60),
	-- ����
	ctrl_password varchar2(60),
	-- ״̬
	status char(1) DEFAULT '0' NOT NULL,
	-- ������
	create_by varchar2(64) NOT NULL,
	-- ����ʱ��
	create_date timestamp NOT NULL,
	-- ������
	update_by varchar2(64) NOT NULL,
	-- ����ʱ��
	update_date timestamp NOT NULL,
	-- ��ע��Ϣ
	remarks nvarchar2(500),
	-- ��λ����
	company_code varchar2(64),
	-- �����Ŵ���
	office_code varchar2(64),
	PRIMARY KEY (id)
);


-- �Ž���־
CREATE TABLE iss_door_log
(
	-- ���
	id varchar2(64) NOT NULL,
	-- ��������ַ
	ctrl_ip varchar2(15),
	-- ����������
	ctrl_name varchar2(60),
	-- ͨ�����
	door_no char(1),
	-- ͨ������
	door_name varchar2(60),
	-- ���Ʒ�ʽ
	door_ctrltype char(1),
	-- ���������
	card_reader_no char(1),
	-- ����
	card_id varchar2(60),
	-- ˢ��ʱ��
	card_readtime timestamp,
	-- ��Ա���
	person_id varchar2(64),
	-- ��Ա����
	person_name nvarchar2(20),
	-- ץ�ı��
	capture_id varchar2(64),
	-- ץ��ʱ��
	capture_time timestamp,
	-- ���ŷ�ʽ
	open_type char(1),
	-- ����ʱ��
	open_time timestamp,
	-- ״̬
	status char(1) DEFAULT '0' NOT NULL,
	-- ������
	create_by varchar2(64) NOT NULL,
	-- ����ʱ��
	create_date timestamp NOT NULL,
	-- ������
	update_by varchar2(64) NOT NULL,
	-- ����ʱ��
	update_date timestamp NOT NULL,
	-- ��ע��Ϣ
	remarks nvarchar2(500),
	-- ��λ����
	company_code varchar2(64),
	-- �����Ŵ���
	office_code varchar2(64),
	PRIMARY KEY (id)
);


COMMENT ON TABLE iss_door IS '�Ž�ͨ��';
COMMENT ON COLUMN iss_door.id IS '���';
COMMENT ON COLUMN iss_door.ctrl_ip IS '��������ַ';
COMMENT ON COLUMN iss_door.door_no IS 'ͨ�����';
COMMENT ON COLUMN iss_door.door_name IS 'ͨ������';
COMMENT ON COLUMN iss_door.door_ctrltype IS '���Ʒ�ʽ';
COMMENT ON COLUMN iss_door.camera_ip IS '�����ַ';
COMMENT ON COLUMN iss_door.status IS '״̬';
COMMENT ON COLUMN iss_door.create_by IS '������';
COMMENT ON COLUMN iss_door.create_date IS '����ʱ��';
COMMENT ON COLUMN iss_door.update_by IS '������';
COMMENT ON COLUMN iss_door.update_date IS '����ʱ��';
COMMENT ON COLUMN iss_door.remarks IS '��ע��Ϣ';
COMMENT ON TABLE iss_door_ctrl IS '�Ž�������';
COMMENT ON COLUMN iss_door_ctrl.id IS '���';
COMMENT ON COLUMN iss_door_ctrl.ctrl_name IS '����������';
COMMENT ON COLUMN iss_door_ctrl.ctrl_type IS '����������';
COMMENT ON COLUMN iss_door_ctrl.ctrl_ip IS '��������ַ';
COMMENT ON COLUMN iss_door_ctrl.ctrl_port IS '�������˿�';
COMMENT ON COLUMN iss_door_ctrl.ctrl_username IS '�û���';
COMMENT ON COLUMN iss_door_ctrl.ctrl_password IS '����';
COMMENT ON COLUMN iss_door_ctrl.status IS '״̬';
COMMENT ON COLUMN iss_door_ctrl.create_by IS '������';
COMMENT ON COLUMN iss_door_ctrl.create_date IS '����ʱ��';
COMMENT ON COLUMN iss_door_ctrl.update_by IS '������';
COMMENT ON COLUMN iss_door_ctrl.update_date IS '����ʱ��';
COMMENT ON COLUMN iss_door_ctrl.remarks IS '��ע��Ϣ';
COMMENT ON COLUMN iss_door_ctrl.company_code IS '��λ����';
COMMENT ON COLUMN iss_door_ctrl.office_code IS '�����Ŵ���';
COMMENT ON TABLE iss_door_log IS '�Ž���־';
COMMENT ON COLUMN iss_door_log.id IS '���';
COMMENT ON COLUMN iss_door_log.ctrl_ip IS '��������ַ';
COMMENT ON COLUMN iss_door_log.ctrl_name IS '����������';
COMMENT ON COLUMN iss_door_log.door_no IS 'ͨ�����';
COMMENT ON COLUMN iss_door_log.door_name IS 'ͨ������';
COMMENT ON COLUMN iss_door_log.door_ctrltype IS '���Ʒ�ʽ';
COMMENT ON COLUMN iss_door_log.card_reader_no IS '���������';
COMMENT ON COLUMN iss_door_log.card_id IS '����';
COMMENT ON COLUMN iss_door_log.card_readtime IS 'ˢ��ʱ��';
COMMENT ON COLUMN iss_door_log.person_id IS '��Ա���';
COMMENT ON COLUMN iss_door_log.person_name IS '��Ա����';
COMMENT ON COLUMN iss_door_log.capture_id IS 'ץ�ı��';
COMMENT ON COLUMN iss_door_log.capture_time IS 'ץ��ʱ��';
COMMENT ON COLUMN iss_door_log.open_type IS '���ŷ�ʽ';
COMMENT ON COLUMN iss_door_log.open_time IS '����ʱ��';
COMMENT ON COLUMN iss_door_log.status IS '״̬';
COMMENT ON COLUMN iss_door_log.create_by IS '������';
COMMENT ON COLUMN iss_door_log.create_date IS '����ʱ��';
COMMENT ON COLUMN iss_door_log.update_by IS '������';
COMMENT ON COLUMN iss_door_log.update_date IS '����ʱ��';
COMMENT ON COLUMN iss_door_log.remarks IS '��ע��Ϣ';
COMMENT ON COLUMN iss_door_log.company_code IS '��λ����';
COMMENT ON COLUMN iss_door_log.office_code IS '�����Ŵ���';
