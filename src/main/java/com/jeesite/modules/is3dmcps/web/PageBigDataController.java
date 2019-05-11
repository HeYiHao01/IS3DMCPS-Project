package com.jeesite.modules.is3dmcps.web;

import java.util.List;
import java.util.Map;

import com.jeesite.common.collect.MapUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.web.BaseController;

/**
 * 
 * @author ZX
 *大数据统计分析展示界面
 */
@CrossOrigin
@RestController
@RequestMapping(value = "static")
public class PageBigDataController extends BaseController{

    /**
     * 生产信息分析界面
     * Post:
     * {“analysisType”:”InventoryInfo”,”timeCoordinate”:” Monthly”,” timeDomainYear”:2018}
     * 或
     * {“analysisType”:”InventoryInfo”,”timeCoordinate”:”Daily”,”timeDomainYear”:2019,”timeDomainMonth”:5}
     * @return
     * Json:
     *  [{“brand”:”兰州（细支珍品）烟丝”,“totalWeightThis”:25000,“totalWeightLast”:24000,”finishWeight”:23000,”timeVariable”:10},
     * {“brand”:”兰州（细支珍品）烟丝”,“totalWeightThis”:25000,“totalWeightLast”:24000,”finishWeight”:23000,”timeVariable”:11}]
     */
	@RequestMapping(value = {"productInfoAnalyse", ""})
	public List<Map<String, Object>> productInfoAnalyse() {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		
        Map<String, Object> map = MapUtils.newHashMap();
        String brand;
        int totalWeightThis;
        int totalWeightLast;
        int finishWeight;
        int timeVariable;
        brand="兰州（细支珍品）烟丝";
        totalWeightThis=25000;
        totalWeightLast=24000;
        finishWeight=23000;
        timeVariable=10;
        map.put("brand",brand);
        map.put("totalWeightThis",totalWeightThis);
        map.put("totalWeightLast",totalWeightLast);
        map.put("finishWeight",finishWeight);
        map.put("timeVariable",timeVariable);
        mapList.add(map);
        
        Map<String, Object> map1 = MapUtils.newHashMap();
        String brand1;
        int totalWeightThis1;
        int totalWeightLast1;
        int finishWeight1;
        int timeVariable1;
        brand1="兰州（硬吉祥）烟丝";
        totalWeightThis1=26000;
        totalWeightLast1=21000;
        finishWeight1=19000;
        timeVariable1=9;
        map1.put("brand",brand1);
        map1.put("totalWeightThis",totalWeightThis1);
        map1.put("totalWeightLast",totalWeightLast1);
        map1.put("finishWeight",finishWeight1);
        map1.put("timeVariable",timeVariable1);
        mapList.add(map1);
        
        Map<String, Object> map2 = MapUtils.newHashMap();
        String brand2;
        int totalWeightThis2;
        int totalWeightLast2;
        int finishWeight2;
        int timeVariable2;
        brand2="兰州（硬珍品）烟丝";
        totalWeightThis2=23000;
        totalWeightLast2=20000;
        finishWeight2=18000;
        timeVariable2=11;
        map2.put("brand",brand2);
        map2.put("totalWeightThis",totalWeightThis2);
        map2.put("totalWeightLast",totalWeightLast2);
        map2.put("finishWeight",finishWeight2);
        map2.put("timeVariable",timeVariable2);
        mapList.add(map2);
        
        Map<String, Object> map3 = MapUtils.newHashMap();
        String brand3;
        int totalWeightThis3;
        int totalWeightLast3;
        int finishWeight3;
        int timeVariable3;
        brand3="兰州（细支珍品）烟丝";
        totalWeightThis3=28000;
        totalWeightLast3=20000;
        finishWeight3=20000;
        timeVariable3=12;
        map3.put("brand",brand3);
        map3.put("totalWeightThis",totalWeightThis3);
        map3.put("totalWeightLast",totalWeightLast3);
        map3.put("finishWeight",finishWeight3);
        map3.put("timeVariable",timeVariable3);
        mapList.add(map3);
        
        Map<String, Object> map4 = MapUtils.newHashMap();
        String brand4;
        int totalWeightThis4;
        int totalWeightLast4;
        int finishWeight4;
        int timeVariable4;
        brand4="兰州（硬吉祥）烟丝";
        totalWeightThis4=21000;
        totalWeightLast4=19000;
        finishWeight4=17000;
        timeVariable4=8;
        map4.put("brand",brand4);
        map4.put("totalWeightThis",totalWeightThis4);
        map4.put("totalWeightLast",totalWeightLast4);
        map4.put("finishWeight",finishWeight4);
        map4.put("timeVariable",timeVariable4);
        mapList.add(map4);
        
        Map<String, Object> map5 = MapUtils.newHashMap();
        String brand5;
        int totalWeightThis5;
        int totalWeightLast5;
        int finishWeight5;
        int timeVariable5;
        brand5="兰州（硬珍品）烟丝";
        totalWeightThis5=27000;
        totalWeightLast5=24000;
        finishWeight5=21000;
        timeVariable5=12;
        map5.put("brand",brand5);
        map5.put("totalWeightThis",totalWeightThis5);
        map5.put("totalWeightLast",totalWeightLast5);
        map5.put("finishWeight",finishWeight5);
        map5.put("timeVariable",timeVariable5);
        mapList.add(map5);
        
		return mapList;
	}
    /**
     * 小车运行统计
     * Json:
 	 *{“timeVariable”:11,“taskTimeCountThis”:22,”taskTimeCountLast”:33}
     * @return
     */
    @RequestMapping(value = {"carRunStatics", ""})
    public List<Map<String, Object>> carRunStatics() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        Map<String, Object> map = MapUtils.newHashMap();
        int timeVariable;
        int taskTimeCountThis;
        int taskTimeCountLast;
        timeVariable=11;
        taskTimeCountThis=21;
        taskTimeCountLast=23;
        map.put("timeVariable",timeVariable);
        map.put("taskTimeCountThis",taskTimeCountThis);
        map.put("taskTimeCountLast", taskTimeCountLast);
        mapList.add(map);
        
        Map<String, Object> map1 = MapUtils.newHashMap();
        int timeVariable1;
        int taskTimeCountThis1;
        int taskTimeCountLast1;
        timeVariable1=12;
        taskTimeCountThis1=23;
        taskTimeCountLast1=19;
        map1.put("timeVariable",timeVariable1);
        map1.put("taskTimeCountThis",taskTimeCountThis1);
        map1.put("taskTimeCountLast", taskTimeCountLast1);
        mapList.add(map1);
        
        return mapList;
    }

    /**
     * 小车运行情况
     * Post:deviceID or deviceName
     * @return
     * Json:
     * {“runDistance”:11114582,”liftCount”: 255456,”veerCount”:3646863,”runFaultsCount”:2648,”liftFaultsCount”:687,”veerFaultsCount”:54,”allFaultsCount”:3389}
     */
    @RequestMapping(value = {"carRunStatus", ""})
    public List<Map<String, Object>> carRunStatus() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        Map<String, Object> map = MapUtils.newHashMap();
        int runDistance;
        int liftCount;
        int veerCount;
        int runFaultsCount;
        int liftFaultsCount;
        int veerFaultsCount;
        int allFaultsCount;
        runDistance=11114582;
        liftCount=122018;
        veerCount=2018;
        runFaultsCount=18;
        liftFaultsCount=25;
        veerFaultsCount=3;
        allFaultsCount=40;
        map.put("runDistance",runDistance);
        map.put("liftCount",liftCount);
        map.put("veerCount",veerCount);
        map.put("runFaultsCount",runFaultsCount);
        map.put("liftFaultsCount",liftFaultsCount);
        map.put("veerFaultsCount",veerFaultsCount);
        map.put("allFaultsCount",allFaultsCount);
        mapList.add(map);
        
        Map<String, Object> map1 = MapUtils.newHashMap();
		float runDistance1;
		int liftCount1;
		int veerCount1;
		int runFaultsCount1;
		int liftFaultsCount1;
		int veerFaultsCount1;
		int allFaultsCount1;
		runDistance1 = 11115428;
		liftCount1=256546;
		veerCount1=3676363;
		runFaultsCount1 = 2598;
		liftFaultsCount1 = 477;
		veerFaultsCount1 = 63;
		allFaultsCount1 = 3138;
		map1.put("runDistance",runDistance1);
		map1.put("liftCount",liftCount1);
		map1.put("veerCount", veerCount1);
		map1.put("runFaultsCount",runFaultsCount1);
		map1.put("liftFaultsCount", liftFaultsCount1);
		map1.put("veerFaultsCount", veerFaultsCount1);
		map1.put("allFaultsCount", allFaultsCount1);
		mapList.add(map1);
        return mapList;
    }

    /**
     * 小车历史故障统计
     * @return
     * Json：
     * [{“deviceName”:”CAR01”,” allFaultsCount”:76},{“deviceName”:”CAR02”,” allFaultsCount”:54}]
     */
    @RequestMapping(value = {"carHistoryFault", ""})
    public List<Map<String, Object>> carHistoryFault() {
    	List<Map<String, Object>> mapList = ListUtils.newArrayList();
		Map<String, Object> map1 = MapUtils.newHashMap();
		String deviceName1;
		int allFaultsCount1;
		deviceName1="CAR01";
		allFaultsCount1=76;
		map1.put("deviceName",deviceName1);
		map1.put("allFaultsCount",allFaultsCount1);		
		mapList.add(map1);
		
		Map<String, Object> map2 = MapUtils.newHashMap();
		String deviceName2;
		int allFaultsCount2;
		deviceName2="CAR02";
		allFaultsCount2=78;
		map2.put("deviceName",deviceName2);
		map2.put("allFaultsCount",allFaultsCount2);		
		mapList.add(map2);
		
		Map<String, Object> map3 = MapUtils.newHashMap();
		String deviceName3;
		int allFaultsCount3;
		deviceName3="CAR03";
		allFaultsCount3=59;
		map3.put("deviceName",deviceName3);
		map3.put("allFaultsCount",allFaultsCount3);		
		mapList.add(map3);
		
		Map<String, Object> map4 = MapUtils.newHashMap();
		String deviceName4;
		int allFaultsCount4;
		deviceName4="CAR04";
		allFaultsCount4=68;
		map4.put("deviceName",deviceName4);
		map4.put("allFaultsCount",allFaultsCount4);		
		mapList.add(map4);
		
		Map<String, Object> map6 = MapUtils.newHashMap();
		String deviceName6;
		int allFaultsCount6;
		deviceName6="CAR06";
		allFaultsCount6=75;
		map6.put("deviceName",deviceName6);
		map6.put("allFaultsCount",allFaultsCount6);		
		mapList.add(map6);
		
		return mapList;
    }

    /**
     * 生产线平均生产能力表
     * @return
     * Json:
     *  [{“productionLineName”:”生产线一”,“timeVariable”:11,“productionCapacity”:22000},
     * {“productionLineName”:”生产线一”, “timeVariable”:12,“productionCapacity”:25000}]
     */
    @RequestMapping(value = {"productionLine", ""})
    public List<Map<String, Object>> productionLine() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        for(int i=1;i<13;i++){
            Map<String, Object> map = MapUtils.newHashMap();
            String productionLineName;
            int timeVariable;
            int productionCapacity;
            productionLineName="生产线一";
            timeVariable=i;
            productionCapacity=122018;
            map.put("productionLineName",productionLineName);
            map.put("timeVariable",timeVariable);
            map.put("productionCapacity",productionCapacity);
            mapList.add(map);
        }
        for(int j=1;j<13;j++){
            Map<String, Object> map1 = MapUtils.newHashMap();
            String productionLineName1;
            int timeVariable1;
            int productionCapacity1;
            productionLineName1="生产线二";
            timeVariable1=j;
            productionCapacity1=131029;
            map1.put("productionLineName",productionLineName1);
            map1.put("timeVariable",timeVariable1);
            map1.put("productionCapacity",productionCapacity1);
            mapList.add(map1);
        }
        return mapList;
    }

    /**
     * 月批次出入库耗时情况表
     * Post:
     * {”timeDomainYear”:2019,”timeDomainMonth”:5}
     * @return
     * Json:
     * [{“batch”:”YXZZ19010”,”weight”:9845.11,”timeCost”:18},
     * {“batch”:”YXZZ19011”,”weight”:4235.15,”timeCost”:13}]
     */
    @RequestMapping(value = {"monthlyBatchTime", ""})
    public List<Map<String, Object>> monthlyBatchTime() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        Map<String, Object> map = MapUtils.newHashMap();
        String batch;
        double weight;
        double timeCost;
        batch="YXZZ19013";
        weight=9845.11;
        timeCost=18;
        map.put("batch",batch);
        map.put("weight",weight);
        map.put("timeCost",timeCost);
        mapList.add(map);
        
        Map<String, Object> map1 = MapUtils.newHashMap();
        String batch1;
        double weight1;
        double timeCost1;
        batch1="YXZZ19014";
        weight1=9856.12;
        timeCost1=19;
        map1.put("batch",batch1);
        map1.put("weight",weight1);
        map1.put("timeCost",timeCost1);
        mapList.add(map1);
        
        Map<String, Object> map2 = MapUtils.newHashMap();
        String batch2;
        double weight2;
        double timeCost2;
        batch2="YXZZ19015";
        weight2=9764.21;
        timeCost2=17;
        map2.put("batch",batch2);
        map2.put("weight",weight2);
        map2.put("timeCost",timeCost2);
        mapList.add(map2);
        
        Map<String, Object> map3 = MapUtils.newHashMap();
        String batch3;
        double weight3;
        double timeCost3;
        batch3="YXZZ19016";
        weight3=9986.14;
        timeCost3=20;
        map3.put("batch",batch3);
        map3.put("weight",weight3);
        map3.put("timeCost",timeCost3);
        mapList.add(map3);
        return mapList;
    }
}
	
	