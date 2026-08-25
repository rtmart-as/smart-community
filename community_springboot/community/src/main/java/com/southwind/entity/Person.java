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
    public class Person implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 用户id
     */
        @TableId(value = "person_id", type = IdType.AUTO)
      private Integer personId;

      /**
     * 小区id
     */
      private Integer communityId;

      /**
     * 所在楼栋
     */
      private String termName;

      /**
     * 房号
     */
      private String houseNo;

      /**
     * 姓名
     */
      private String userName;

      /**
     * 性别(男/女)
     */
      private String sex;

      /**
     * 手机号
     */
      private String mobile;

      /**
     * 人脸图片
     */
      private String faceUrl;

      /**
     * 人员类别（常住居民，企业员工，居住性质)
     */
      private String personType;

      /**
     * 类别(1：未验证，2：已验证)
     */
      private Integer state;

      /**
     * 创建者
     */
      private String creater;

      /**
     * 创建时间
     */
        @TableField(fill = FieldFill.INSERT)
      private Date createTime;

      /**
     * 备注
     */
      private String remark;

    private String faceBase;


}
