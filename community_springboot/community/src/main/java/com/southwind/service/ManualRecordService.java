package com.southwind.service;

import com.southwind.entity.ManualRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.southwind.form.ManualRecordForm;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2023-07-27
 */
public interface ManualRecordService extends IService<ManualRecord> {
    public Map manualRecordList(ManualRecordForm manualRecordForm);
}
