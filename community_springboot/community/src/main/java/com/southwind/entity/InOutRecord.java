package com.southwind.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author admin
 * @since 2023-07-26
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class InOutRecord implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "in_out_record_id", type = IdType.AUTO)
      private Integer inOutRecordId;

      /**
     * 人员ID
     */
      private Integer personId;

      /**
     * 小区ID
     */
      private Integer communityId;

      /**
     * 进场时间
     */
        @TableField(fill = FieldFill.INSERT)
      private Date inTime;

      /**
     * 出场时间
     */
        @TableField(fill = FieldFill.UPDATE)
      private Date outTime;

      /**
     * 入场图片
     */
      private String inPic;

      /**
     * 出场图片
     */
      private String outPic;


}
