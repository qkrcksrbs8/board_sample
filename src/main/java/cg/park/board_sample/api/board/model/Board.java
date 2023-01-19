package cg.park.board_sample.api.board.model;

import lombok.Data;

import java.util.Date;

@Data
public class Board {

    private Integer boardNo;
    private String title;
    private String detail;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;

}
