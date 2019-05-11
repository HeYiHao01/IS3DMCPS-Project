package com.jeesite.modules.isopc.listener;

import static com.jeesite.modules.isopc.listener.OPCConfiguration.getCLSIDConnectionInfomation;

import java.util.concurrent.Executors;
import org.jinterop.dcom.common.JIException;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.da.AccessBase;
import org.openscada.opc.lib.da.Group;
import org.openscada.opc.lib.da.Item;
import org.openscada.opc.lib.da.Server;
import org.openscada.opc.lib.da.SyncAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 功能：OPC通信线程 描述：通过循环读取心跳和状态控制字，按照商议好的交互流程读写变量
 */
public class CarOPCComm {
    private static Logger logger = LoggerFactory.getLogger(CarOPCComm.class);
    private Item Bat_Current;
	private Item Bat_Voltage;
    private Item Car_Angle;
    private Item Car_Column;
    private Item Car_ID;
    private Item Car_Layer;
    private Item Car_Mode;
    private Item Car_Row;
    private Item Car_RunState;
    private Item Car_WorkState;
    private Item Car_Zone;
    private Item CarPlt;
    private Item Err01;
    private Item Err02;
    private Item Err03;
    private Item Err04;
    private Item Err05;
    private static String OPCId;
    private Server server;
    /**
     * 单例模式
     */
    private static class SingletonHolder {
        static final CarOPCComm doOPCComm = new CarOPCComm();
    }
    public static CarOPCComm getInstance() {
 //   	setOPCId(OPCId);
       return SingletonHolder.doOPCComm;
    }
	/**
     * 启动server 创建一个监控线程 创建一个写入线程
     */
    public void init() throws Exception {
        // 加载配置文件
        final ConnectionInformation ci = getCLSIDConnectionInfomation();
        // 创建server
        final Server server = new Server(ci, Executors.newSingleThreadScheduledExecutor());
        try {
            // 启动server
            server.connect();
            logger.info("This is {} message.", "OPCserver connect success");
            this.server = server;
            // 同步读取，500ms一次
            final AccessBase access = new SyncAccess(server, 500);
            DataCallBack_Car  dataCallBack_Car=new DataCallBack_Car();
            access.addItem(OPCId+"Bat_Current",dataCallBack_Car );
            access.addItem(OPCId+"Bat_Voltage",dataCallBack_Car );
            access.addItem(OPCId+"Car_Angle",dataCallBack_Car );
            access.addItem(OPCId+"Car_Column",dataCallBack_Car );
            access.addItem(OPCId+"Car_ID",dataCallBack_Car );
            access.addItem(OPCId+"Car_Layer", dataCallBack_Car);
            access.addItem(OPCId+"Car_Mode", dataCallBack_Car);
            access.addItem(OPCId+"Car_Row", dataCallBack_Car);
            access.addItem(OPCId+"Car_RunState", dataCallBack_Car);
            access.addItem(OPCId+"Car_WorkState", dataCallBack_Car);
            access.addItem(OPCId+"Car_Zone", dataCallBack_Car);
            access.addItem(OPCId+"CarPlt", dataCallBack_Car);
            access.addItem(OPCId+"Err01", dataCallBack_Car);
            access.addItem(OPCId+"Err02", dataCallBack_Car);
            access.addItem(OPCId+"Err03", dataCallBack_Car);
            access.addItem(OPCId+"Err04", dataCallBack_Car);
            access.addItem(OPCId+"Err05", dataCallBack_Car);
            // 添加一个组
            final Group group = server.addGroup(OPCId+"Car");
            Bat_Current = group.addItem(OPCId+"Bat_Current");
            Bat_Voltage = group.addItem(OPCId+"Bat_Voltage");
            Car_Angle = group.addItem(OPCId+"Car_Angle");
            Car_Column = group.addItem(OPCId+"Car_Column");
            Car_ID = group.addItem(OPCId+"Car_ID");
            Car_Layer = group.addItem(OPCId+"Car_Layer");
            Car_Mode = group.addItem(OPCId+"Car_Mode");
            Car_Row = group.addItem(OPCId+"Car_Row");
            Car_RunState = group.addItem(OPCId+"Car_RunState");
            Car_WorkState = group.addItem(OPCId+"Car_WorkState");
            Car_Zone = group.addItem(OPCId+"Car_Zone");
            CarPlt = group.addItem(OPCId+"CarPlt");
            Err01 = group.addItem(OPCId+"Err01");
            Err02 = group.addItem(OPCId+"Err02");
            Err03 = group.addItem(OPCId+"Err03");
            Err04 = group.addItem(OPCId+"Err04");
            Err05 = group.addItem(OPCId+"Err05");
                       // start reading
            access.bind();
        } catch (final JIException e) {
            System.out.println(String.format("%08X: %s", e.getErrorCode(), server.getErrorMessage(e.getErrorCode())));
        }
    }
    public Item getBat_Current() {
		return Bat_Current;
	}
	public Item getBat_Voltage() {
		return Bat_Voltage;
	}
	public Item getCar_Angle() {
		return Car_Angle;
	}
	public Item getCar_Column() {
		return Car_Column;
	}
	public Item getCar_ID() {
		return Car_ID;
	}
	public Item getCar_Layer() {
		return Car_Layer;
	}
	public Item getCar_Mode() {
		return Car_Mode;
	}
	public Item getCar_Row() {
		return Car_Row;
	}
	public Item getCar_RunState() {
		return Car_RunState;
	}
	public Item getCar_WorkState() {
		return Car_WorkState;
	}
	public Item getCar_Zone() {
		return Car_Zone;
	}
	public Item getCarPlt() {
		return CarPlt;
	}
	public Item getErr01() {
		return Err01;
	}
	public Item getErr02() {
		return Err02;
	}
	public Item getErr03() {
		return Err03;
	}
	public Item getErr04() {
		return Err04;
	}
	public Item getErr05() {
		return Err05;
	}
    public String getOPCId() {
		return OPCId;
	}
	public static void setOPCId(String oPCId) {
		OPCId = oPCId;
	}
	public Server getServer() {
		return server;
	}

}