package com.southwind.vo;

import lombok.Data;

import java.util.Date;

@Data
public class InOutRecordVO {
    private Integer inOutRecordId;
    private Date inTime;
    private Date outTime;
    private String inPic;
    private String outPic;
    private String communityName;
    private String termName;
    private String houseNo;
    private String userName;
}
