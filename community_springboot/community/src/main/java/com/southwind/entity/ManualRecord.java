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
 * @since 2023-07-27
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class ManualRecord implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "manual_record_id", type = IdType.AUTO)
      private Integer manualRecordId;

      /**
     * 小区ID
     */
      private Integer communityId;

      /**
     * 访客姓名
     */
      private String visitor;

      /**
     * 联系电话
     */
      private String mobile;

      /**
     * 身份证号码
     */
      private String cardNo;

      /**
     * 楼栋名称
     */
      private String termName;

      /**
     * 房号
     */
      private String houseNo;

      /**
     * 受访者
     */
      private String interviewee;

      /**
     * 备注
     */
      private String remark;

      /**
     * 进场时间
     */
      private Date inTime;

      /**
     * 出场时间
     */
      private Date outTime;

      /**
     * 登记人
     */
      private String userName;

      /**
     * 登记时间
     */
        @TableField(fill = FieldFill.INSERT)
      private Date signTime;


}
