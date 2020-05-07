package com.admxj.spring.boot.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.util.Date;

/**
 * @author jin.xiang
 * @version Id: WhiteConfigVO, v 0.1 2020/4/22 11:02 上午 jin.xiang Exp $
 */
@ColumnWidth(16)
@Data
public class WhiteConfigVO {

    /**
     * 主键
     */
    private long id;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 最近一次修改时间
     */
    private Date gmtModified;

    /**
     * 是否删除(0:未删除 / 1:已删除)
     */
    private int isDelete;

    /**
     * 出发类型
     */
    private String depType;

    /**
     * 出发编码
     */
    @ExcelProperty(index = 0, value = "出发编码")
    private String depCode;

    /**
     * 到达类型
     */
    private String arrType;

    /**
     * 到达编码
     */
    @ExcelProperty(index = 1, value = "到达编码")
    private String arrCode;

    /**
     * 出行类型  OW：单程 RT：往返 缺口程：OJ  全部 ALL
     */
    @ExcelProperty(index = 2, value = "出行类型")
    private String travelType;

    /**
     * 资源类型
     */
    @ExcelProperty(index = 3, value = "资源类型")
    private String resourceType;

    /**
     * gds
     */
    @ExcelProperty(index = 4, value = "gds")
    private String gds;

    /**
     * merchantId
     */
    @ExcelProperty(index = 5, value = "供应商")
    private String merchantId;

    /**
     * 供应商名称
     */
    private String merchantName;

    /**
     * 状态 有效1/无效0
     */
    @ExcelProperty(index = 6, value = "状态")
    private Integer status;

    /**
     * 预设开始天数
     */
    @ExcelProperty(index = 7, value = "预设开始天数")
    private Integer presellStart;

    /**
     * 预设结束天数
     */
    @ExcelProperty(index = 8, value = "预设结束天数")
    private Integer presellEnd;
    /**
     * pcc编码
     */
    @ExcelProperty(index = 9, value = "pcc编码")
    private String pcc;

    /**
     * 旅行日期开始时间
     */
    @ExcelProperty(index = 10, value = "旅行日期开始时间")
    private Date travelBeginTime;

    /**
     * 旅行日期结束时间
     */
    @ExcelProperty(index = 11, value = "旅行日期结束时间")
    private Date travelEndTime;

    /**
     * 生效日期开始时间
     */
    @ExcelProperty(index = 12, value = "生效日期开始时间")
    private Date effectBeginTime;

    /**
     * 生效日期结束时间
     */
    @ExcelProperty(index = 13, value = "生效日期结束时间")
    private Date effectEndTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * env
     */
    private String env;

    /**
     * remark
     */
    private String remark;

    /**
     * 版本
     */
    private Long version;

    /**
     * 是否选择PCC， 0不选择、1选择
     */
    private int selectPcc;

    /**
     * 操作人
     */
    private String operator;

}
