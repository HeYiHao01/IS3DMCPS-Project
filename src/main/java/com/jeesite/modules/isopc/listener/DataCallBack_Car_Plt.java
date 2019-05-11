package com.jeesite.modules.isopc.listener;

import java.awt.Color;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.openscada.opc.lib.da.DataCallback;
import org.openscada.opc.lib.da.Item;
import org.openscada.opc.lib.da.ItemState;


/**
 * 根据控制字：向PLC写入工单信息，保存结果
 */
public class DataCallBack_Car_Plt implements DataCallback {
    private CarOPCComm opc = CarOPCComm.getInstance();

    @Override
    public void changed(Item item, ItemState itemState) {

        // 读取状态变量的值
        try {
            if (itemState.getValue().getType() == JIVariant.VT_I2) {
                short n = itemState.getValue().getObjectAsShort();
                    doProcess(n);
            }
        } catch (JIException e) {
            e.printStackTrace();
        }
    }

    /**
     * 状态控制字： 0：PLC上电，1：允许新工单，2：新任务，3：收到任务，4：准备执行，5：开始执行 6：正常完成，7：异常玩常完成，8：收到完成信号
     */
    private void doProcess(short n) {
        if (n == 0) {
         }
    }


 
 
}