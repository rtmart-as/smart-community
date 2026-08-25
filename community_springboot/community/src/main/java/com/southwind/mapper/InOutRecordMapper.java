package com.southwind.mapper;

import com.southwind.entity.InOutRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.southwind.vo.ChartVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2023-07-26
 */
public interface InOutRecordMapper extends BaseMapper<InOutRecord> {

    @Select({
            "select count(*) value,c.community_name name from community c,person p where c.community_id = p.community_id group by c.community_id"
    })
    public List<ChartVO> chart();

    @Select({
            "select * from in_out_record where " +
                    "community_id=#{communityId} and " +
                    "person_id=#{personId} and out_time is null"
    })
    public InOutRecord getInOutRecord(InOutRecord inOutRecord);

}
