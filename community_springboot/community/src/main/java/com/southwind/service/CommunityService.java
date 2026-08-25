package com.southwind.service;

import com.southwind.entity.Community;
import com.baomidou.mybatisplus.extension.service.IService;
import com.southwind.form.CommunityListForm;
import com.southwind.vo.PageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2023-07-26
 */
public interface CommunityService extends IService<Community> {

    public PageVO communityList(CommunityListForm communityListForm);

}
