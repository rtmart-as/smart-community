package com.southwind.form;

import lombok.Data;

@Data
public class PersonListForm {
    private Long page;
    private Long limit;
    private Integer communityId;
    private String userName;
    private String mobile;
}
