package com.southwind.form;

import lombok.Data;

@Data
public class ManualRecordForm {
    private Integer page;
    private Integer limit;
    private Integer communityId;
    private String mobile;
    private String visitor;
}
