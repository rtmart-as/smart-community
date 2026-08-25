package com.southwind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.southwind.entity.Community;
import com.southwind.form.CommunityListForm;
import com.southwind.mapper.CommunityMapper;
import com.southwind.mapper.PersonMapper;
import com.southwind.service.CommunityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.southwind.vo.CommunityVO;
import com.southwind.vo.PageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2023-07-26
 */
@Service
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper, Community> implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;
    @Autowired
    private PersonMapper personMapper;

    @Override
    public PageVO communityList(CommunityListForm communityListForm) {
        Page<Community> page = new Page<>(communityListForm.getPage(), communityListForm.getLimit());
        QueryWrapper<Community> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(communityListForm.getCommunityName()),"community_name", communityListForm.getCommunityName());
        Page<Community> resultPage = this.communityMapper.selectPage(page, queryWrapper);
        PageVO pageVO = new PageVO();
        List<CommunityVO> list = new ArrayList<>();
        for (Community record : resultPage.getRecords()) {
            CommunityVO communityVO = new CommunityVO();
            BeanUtils.copyProperties(record, communityVO);
            communityVO.setPersonCnt(this.personMapper.getCountByCommunityId(record.getCommunityId()));
            list.add(communityVO);
        }
        pageVO.setList(list);
        pageVO.setTotalCount(resultPage.getTotal());
        pageVO.setPageSize(resultPage.getSize());
        pageVO.setCurrPage(resultPage.getCurrent());
        pageVO.setTotalPage(resultPage.getPages());
        return pageVO;
    }
}
