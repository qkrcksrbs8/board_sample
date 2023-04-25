package cg.park.board_sample.api.board.model;

import lombok.Data;

@Data
public class Paging {

    private int pageNum = 0;
    private int blockCount = 5;
    private int blockPage = 3;
    private int startCount;
    private int endCount = 0;

}
