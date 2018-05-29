package com.second.credit.spider.capture.enums;

import java.util.*;

/**
 * @note 城市编码枚举
 * @author wangmeng
 * @date 2017年3月15日 下午4:11:01
 */
public enum EnumCity {

    // -----------------------城市枚举------------------------

    BEIJING(1000, "beijing", "BeiJing", 2000, true, true, "北京市", 2),

    CHENGDU(1001, "chengdu", "ChengDu", 2003, true, false, "成都市", 26),

    CHONGQING(1002, "chongqing", "ChongQing", 2004, true, true, "重庆市", 25),

    GUANGZHOU(1003, "guangzhou", "GuangZhou", 2007, true, true, "广州市", 4),

    SHENZHEN(1004, "shenzhen", "ShenZhen", 2006, true, true, "深圳市 ", 3),

    SHANGHAI(1005, "shanghai", "ShangHai", 2013, true, true, "上海市", 1),

    WUHAN(1006, "wuhan", "WuHan", 2005, true, true, "武汉市", 33),

    SUZHOU(1007, "suzhou", "SuZhou", 2010, true, true, "苏州市", 9),

    DONGWAN(1008, "dongwan", "DongWan", 2007, true, true, "东莞市", 16),

    QUANZHOU(1009, "quanzhou", "QuanZhou", 2002, false, true, "泉州市", 32),

    XIAN(1011, "xian", "XiAn", 2014, false, true, "西安市", 0),

    CHANGSHA(1013, "changsha", "ChangSha", 2001, false, true, "长沙市", 29),

    XIAMEN(1014, "xiamen", "XiaMen", 2002, true, true, "厦门市", 27),

    FUZHOU(1015, "fuzhou", "FuZhou", 2002, false, true, "福州市", 31),

    FOSHAN(1016, "foshan", "FoShan", 2007, true, true, "佛山市", 6),

    ZHENGZHOU(1017, "zhengzhou", "ZhengZhou", 2012, true, true, "郑州市", 0),

    NANNING(1018, "nanning", "NanNing", 2008, true, true, "南宁市", 245),

    NINGBO(1019, "ningbo", "NingBo", 2009, true, true, "宁波市", 22),

    // ------------------------省份枚举------------------------

    P_BEIJING(2000, "beijing", "BeiJing", 0, true, true, "北京省", 0),

    P_HUNAN(2001, "hunan", "HuNan", 0, true, true, "湖南省", 0),

    P_FUJIAN(2002, "fujian", "FuJian", 0, true, true, "福建省", 0),

    P_SICHUAN(2003, "sichuan", "SiChuan", 0, true, true, "四川省", 0),

    P_CHONGQING(2004, "chongqing", "ChongQing", 0, true, true, "重庆省", 0),

    P_HUBEI(2005, "hubei", "HuBei", 0, true, true, "湖北省", 0),

    P_SHENZHEN(2006, "shenzhen", "ShenZhen", 0, true, true, "深圳省", 0),

    P_GUANGDONG(2007, "guangdong", "GuangDong", 0, true, true, "广东省", 0),

    P_GUANGXI(2008, "guangxi", "GuangXi", 0, true, true, "广西省", 0),

    P_ZHEJIANG(2009, "zhejiang", "ZheJiang", 0, true, true, "浙江省", 0),

    P_JIANGSU(2010, "jiangsu", "JiangSu", 0, true, true, "江苏省", 0),

    P_SHANXI(2011, "shanxi", "ShanXi", 0, true, true, "山西省", 0),

    P_HENAN(2012, "henan", "HeNan", 0, true, true, "河南省", 0),

    P_SHANGHAI(2013, "shanghai", "ShangHai", 0, true, true, "上海省", 0),

    P_SSHANXI(2014, "sshanxi", "SshanXi", 0, true, true, "陕西省", 0),

    // ------------------------第三方枚举------------------------

    RONG360(9999, "rong360", "Rong360", 0, true, true, "融360", 0);

    /**
     * @note code
     */
    private final int code;

    /**
     * @note 名称
     */
    private final String name;

    /**
     * @note 抓取类的扩展名 eg:InsuranceFetcher_BeiJing
     */
    private final String fetcherName;

    /**
     * @note 父级code 默认为0
     */
    private final int parentCode;

    /**
     * @note true:采用本枚举城市 false:采用父类城市
     */
    private final boolean parentOrChildrenCity;

    /**
     * @note 是否自己支持
     */
    private final boolean selfSupport;

    /**
     * @note code描述
     */
    private final String desc;

    /**
     * @note 融360Code
     */
    private final int rongCode;

    EnumCity(int code, String name, String fetcherName, int parentCode, boolean parentOrChildrenCity, // NOSONAR
            boolean selfSupport, String desc, int rongCode) {
        this.code = code;
        this.name = name;
        this.fetcherName = fetcherName;
        this.parentCode = parentCode;
        this.parentOrChildrenCity = parentOrChildrenCity;
        this.selfSupport = selfSupport;
        this.desc = desc;
        this.rongCode = rongCode;
    }

    public static EnumCity getEnumByCode(int code) {
        for (EnumCity myEnum : EnumSet.allOf(EnumCity.class)) {
            if (myEnum.getCode() == code) {
                return myEnum;
            }
        }
        return null;
    }

    /**
     * @note 是否执行自行抓取
     */
    public static boolean excuteSelfFetcher(EnumCity myEnum) {
        if (myEnum == null || !myEnum.selfSupport) {
            return false;
        }
        return true;
    }

    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getFetcherName() {
        return fetcherName;
    }

    public boolean isSelfSupport() {
        return selfSupport;
    }

    public int getRongCode() {
        return rongCode;
    }

    public static List<Map<String, Object>> pageShow() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (EnumCity city : values()) {
            if (city.getCode() == RONG360.code) {
                continue;
            }
            Map<String, Object> map = new HashMap<>();
            map.put("code", city.getCode());
            map.put("name", city.getDesc());
            list.add(map);
        }
        return list;
    }

    public int getParentCode() {
        return parentCode;
    }

    public boolean isParentOrChildrenCity() {
        return parentOrChildrenCity;
    }

}
