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
    public class Community implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "community_id", type = IdType.AUTO)
      private Integer communityId;

      /**
     * 小区名称
     */
      private String communityName;

      /**
     * 楼栋数量
     */
      private Integer termCount;

      /**
     * 序号
     */
      private Integer seq;

      /**
     * 创建人
     */
      private String creater;

      /**
     * 创建时间
     */
        @TableField(fill = FieldFill.INSERT)
      private Date createTime;

      /**
     * 经度
     */
      private Float lng;

      /**
     * 维度
     */
      private Float lat;


}
