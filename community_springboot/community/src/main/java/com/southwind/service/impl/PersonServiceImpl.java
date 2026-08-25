package com.southwind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.southwind.entity.Person;
import com.southwind.form.PersonListForm;
import com.southwind.mapper.CommunityMapper;
import com.southwind.mapper.PersonMapper;
import com.southwind.service.PersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.southwind.vo.PageVO;
import com.southwind.vo.PersonVO;
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
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private CommunityMapper communityMapper;

    @Override
    public PageVO personList(PersonListForm personListForm) {
        Page<Person> page = new Page<>(personListForm.getPage(), personListForm.getLimit());
        QueryWrapper<Person> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(personListForm.getUserName()), "user_name", personListForm.getUserName())
                .eq(personListForm.getCommunityId() != null, "community_id",personListForm.getCommunityId())
                .eq(StringUtils.isNotBlank(personListForm.getMobile()), "mobile",personListForm.getMobile());
        Page<Person> resultPage = this.personMapper.selectPage(page, queryWrapper);
        PageVO pageVO = new PageVO();
        pageVO.setTotalCount(resultPage.getTotal());
        pageVO.setPageSize(resultPage.getSize());
        pageVO.setCurrPage(resultPage.getCurrent());
        pageVO.setTotalPage(resultPage.getPages());
        List<PersonVO> list = new ArrayList<>();
        for (Person record : resultPage.getRecords()) {
            PersonVO personVO = new PersonVO();
            BeanUtils.copyProperties(record, personVO);
            personVO.setCommunityName(this.communityMapper.selectById(record.getCommunityId()).getCommunityName());
            list.add(personVO);
        }
        pageVO.setList(list);
        return pageVO;
    }
}
