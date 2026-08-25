package com.southwind.mapper;

import com.southwind.entity.Person;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2023-07-26
 */
public interface PersonMapper extends BaseMapper<Person> {

    @Select({
            "select count(*) from person where community_id = #{communityId} "
    })
    public Integer getCountByCommunityId(Integer communityId);

}
