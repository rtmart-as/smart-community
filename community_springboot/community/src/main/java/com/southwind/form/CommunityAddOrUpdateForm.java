package com.southwind.form;

import lombok.Data;

@Data
public class CommunityAddOrUpdateForm {
    private Integer communityId;
    private String communityName;
    private Float lng;
    private Float lat;
    private Integer seq;
    private Integer termCount;
}
