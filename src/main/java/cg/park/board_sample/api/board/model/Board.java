package cg.park.board_sample.api.board.model;

import lombok.Data;

import java.util.Date;

@Data
public class Board extends Paging {

    private Integer boardNo;
    private String title;
    private String detail;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;

    Board(){}
    public Board boardNo(Integer boardNo){
        this.boardNo = boardNo;
        return this;
    }

}
