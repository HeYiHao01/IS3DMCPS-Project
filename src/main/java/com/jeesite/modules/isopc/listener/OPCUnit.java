package com.jeesite.modules.isopc.listener;

import java.text.DecimalFormat;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIArray;
import org.jinterop.dcom.core.JIVariant;
import org.openscada.opc.lib.da.Item;
import org.openscada.opc.lib.da.ItemState;

public class OPCUnit {

    /**
     * 写值到变量
     */
    public static void write(Item item, String val) {
        try {
            JIVariant value = new JIVariant(val);
            item.write(value);
        } catch (JIException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写值到变量:数组
     */
    public static void write(Item item, String[] snArray) {
        try {

            /** 构造写入数据 */
            Long[] integerData = new Long[snArray.length];
            for (int i = 0; i < snArray.length; i++) {
                integerData[i] = Long.valueOf(snArray[i]);
            }
            final JIArray array = new JIArray(integerData, false);
            final JIVariant value = new JIVariant(array);

            item.write(value);
        } catch (JIException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读变量的值 如果是short和int直接返回字符串； 如果是long类型的数组,返回数字内容间加点，对应long，数组，大小为6
     * 如果是float类型的数组,返回数字内容间加逗号，对应float，数组，大小为20
     */
    public static String read(Item item) {
        String result = "";
        try {
            ItemState state = item.read(true);
            int type = state.getValue().getType();

            if (type == JIVariant.VT_UI4) {
                int value = state.getValue().getObjectAsInt();
                return value + "";
            } else if (type == JIVariant.VT_I2) {
                short value = state.getValue().getObjectAsShort();
                return value + "";
            } else if (type == 8195) {
                JIArray jarr = state.getValue().getObjectAsArray();
                Integer[] arr = (Integer[]) jarr.getArrayInstance();
                String value = "";
                for (Integer i : arr) {
                    value = value + i + ".";
                }
                String res = value.substring(0, value.length() - 1);
                // "25.36087601.1.1.18.36"-->"25.36087601.01.0001.18.36"
                String[] array = res.split("[.]");
                String ress = array[0] + "." + array[1] + "." + new DecimalFormat("00").format(Long.valueOf(array[2]))
                        + "." + new DecimalFormat("0000").format(Long.valueOf(array[3])) + "." + array[4] + "."
                        + array[5];
                return ress;

            } else if (type == 8196) {
                JIArray jarr = state.getValue().getObjectAsArray();
                Float[] arr = (Float[]) jarr.getArrayInstance();
                String value = "";
                for (Float f : arr) {
                    value = value + f + ",";
                }
                return value.substring(0, value.length() - 1);
            }

        } catch (JIException e) {
            e.printStackTrace();
        }
        return result;
    }

}