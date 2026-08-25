package com.southwind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.southwind.entity.Community;
import com.southwind.entity.InOutRecord;
import com.southwind.entity.Person;
import com.southwind.form.InOutQueryForm;
import com.southwind.mapper.CommunityMapper;
import com.southwind.mapper.InOutRecordMapper;
import com.southwind.mapper.PersonMapper;
import com.southwind.service.InOutRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.southwind.vo.ChartVO;
import com.southwind.vo.InOutRecordVO;
import com.southwind.vo.PageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2023-07-26
 */
@Service
public class InOutRecordServiceImpl extends ServiceImpl<InOutRecordMapper, InOutRecord> implements InOutRecordService {

    @Autowired
    private InOutRecordMapper inOutRecordMapper;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private CommunityMapper communityMapper;

    @Override
    public Map chart() {
        List<ChartVO> chartVOList = this.inOutRecordMapper.chart();
        List<String> names = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        List<ChartVO> list = new ArrayList<>();
        for (ChartVO chartVO : chartVOList) {
            names.add(chartVO.getName());
            nums.add(chartVO.getValue());
            list.add(chartVO);
        }
        Map<String,List> map = new HashMap<>();
        map.put("names", names);
        map.put("nums", nums);
        map.put("list", list);
        return map;
    }

    @Override
    public PageVO inOutRecordList(InOutQueryForm inOutQueryForm) {
        Page<InOutRecord> page = new Page<>(inOutQueryForm.getPage(),inOutQueryForm.getLimit());
        QueryWrapper<Person> personQueryWrapper = new QueryWrapper<>();
        personQueryWrapper.like(StringUtils.isNotBlank(inOutQueryForm.getUserName()), "user_name", inOutQueryForm.getUserName());
        List<Person> personList = this.personMapper.selectList(personQueryWrapper);
        List<Integer> idList = new ArrayList<>();
        for (Person person : personList) {
            idList.add(person.getPersonId());
        }
        QueryWrapper<InOutRecord> inOutRecordQueryWrapper = new QueryWrapper<>();
        inOutRecordQueryWrapper.in(idList.size() > 0, "person_id",idList);
        inOutRecordQueryWrapper.between(StringUtils.isNotBlank(inOutQueryForm.getStartDate()) && StringUtils.isNotBlank(inOutQueryForm.getEndDate()), "in_time", inOutQueryForm.getStartDate(), inOutQueryForm.getEndDate());
        Page<InOutRecord> resultPage = this.inOutRecordMapper.selectPage(page, inOutRecordQueryWrapper);
        PageVO pageVO = new PageVO();
        pageVO.setTotalCount(resultPage.getTotal());
        pageVO.setPageSize(resultPage.getSize());
        pageVO.setCurrPage(resultPage.getCurrent());
        pageVO.setTotalPage(resultPage.getPages());
        List<InOutRecordVO> list = new ArrayList<>();
        for (InOutRecord record : resultPage.getRecords()) {
            InOutRecordVO inOutRecordVO = new InOutRecordVO();
            BeanUtils.copyProperties(record, inOutRecordVO);
            Community community = this.communityMapper.selectById(record.getCommunityId());
            inOutRecordVO.setCommunityName(community.getCommunityName());
            Person person = this.personMapper.selectById(record.getPersonId());
            inOutRecordVO.setTermName(person.getTermName());
            inOutRecordVO.setHouseNo(person.getHouseNo());
            inOutRecordVO.setUserName(person.getUserName());
            list.add(inOutRecordVO);
        }
        pageVO.setList(list);
        return pageVO;
    }
}
