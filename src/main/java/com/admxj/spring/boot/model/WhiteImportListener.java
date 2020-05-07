package com.admxj.spring.boot.model;

import com.admxj.spring.boot.utils.DateUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author jin.xiang
 * @version Id: ManualImportListener, v 0.1 2020/4/15 5:51 下午 jin.xiang Exp $
 */
@Service
public class WhiteImportListener extends AnalysisEventListener<WhiteConfigVO> {


    /**
     * 默认失效时间
     */
    public static final Date INVALID_DATE = DateUtil.parseDateNewFormat("2999-12-31 00:00:00");

    /**
     * 旅行和生效默认开始时间
     */
    public static final Date DEFAULT_START_DATE = DateUtil.parseDateNewFormat("2020-05-01 00:00:00");

    /**
     * 失效天数
     */
    public static final int INVALID_DAY = 9999;

    @Override
    public void invoke(WhiteConfigVO data, AnalysisContext context) {
        data.setDepCode(toUpperCase(data.getDepCode()));
        data.setArrCode(toUpperCase(data.getArrCode()));
        data.setDepType("2");
        data.setArrType("2");
        data.setTravelType(toUpperCase(data.getTravelType()));
        data.setResourceType(toUpperCase(data.getResourceType()));
        data.setGds(toUpperCase(data.getGds()));

        if (null == data.getEffectEndTime() || DateUtil.getDiffDays(INVALID_DATE, data.getEffectEndTime()) < 0) {
            data.setEffectEndTime(INVALID_DATE);
        }
        if (null == data.getEffectBeginTime()) {
            data.setEffectBeginTime(DEFAULT_START_DATE);
        }

        if (null == data.getTravelEndTime() || DateUtil.getDiffDays(INVALID_DATE, data.getTravelEndTime()) < 0) {
            data.setTravelEndTime(INVALID_DATE);
        }
        if (null == data.getTravelBeginTime()) {
            data.setTravelBeginTime(DEFAULT_START_DATE);
        }

        if (data.getPresellEnd() == 0) {
            data.setPresellEnd(INVALID_DAY);
        }
        if (null == data.getPcc()) {
            data.setPcc(StringUtils.EMPTY);
        }
        if (null == data.getMerchantId()) {
            data.setMerchantId(StringUtils.EMPTY);
        }
        if (StringUtils.isEmpty(data.getRemark())) {
            data.setRemark(StringUtils.EMPTY);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    private String toUpperCase(String code) {
        if (code == null) {
            return null;
        }
        return code.toUpperCase();
    }
}
