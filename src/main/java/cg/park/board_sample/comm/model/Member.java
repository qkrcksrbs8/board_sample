package cg.park.board_sample.comm.model;

import lombok.Data;

import java.util.Date;

@Data
public class Member {

    private Integer memberNo;
    private String memberId;
    private String password;
    private String name;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;

}
