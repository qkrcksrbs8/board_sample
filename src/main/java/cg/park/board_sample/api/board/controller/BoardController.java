package cg.park.board_sample.api.board.controller;

import cg.park.board_sample.api.board.model.Board;
import cg.park.board_sample.api.board.service.BoardService;
import cg.park.board_sample.comm.util.HttpRequestHelper;
import cg.park.board_sample.comm.util.Message;
import cg.park.board_sample.comm.util.PagingUtil;
import cg.park.board_sample.comm.util.ResponseMav;
import com.sun.rowset.internal.Row;
import javafx.scene.control.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/board")
@Controller
public class BoardController {
//2023-01-27 releases

    @Autowired
    BoardService boardService;

    @GetMapping("")
    public ResponseMav list(Board board) {
        HttpServletRequest request = HttpRequestHelper.getCurrentRequest();

        int count = boardService.countBy(board);
        PagingUtil paging = new PagingUtil
                            .Builder()
                            .setQueryString(request.getQueryString())
                            .setCurrentPage(board.getPageNum())
                            .setTotalCount(count)
                            .setBlockCount(board.getBlockCount())
                            .setBlockPage(board.getBlockPage())
                            .setPageUrl(request.getRequestURI())
                            .build();

        board.setStartCount(paging.getStartCount());
        board.setEndCount(paging.getEndCount());

        return new ResponseMav("board/board")
                .set("count", count)
                .set("list", boardService.findAll(board))
                .set("board", board)
                .set("paging", paging.getPagingHtml());
    }

    @GetMapping("/write")
    public ResponseMav write() {
        return new ResponseMav("/board/write");
    }

    @PostMapping("/write")
    public ResponseEntity<Message> write(Board board) {
        return new ResponseEntity<>(boardService.save(board), HttpStatus.OK);
    }

    @GetMapping("/write/{boardNo}")
    public ResponseMav update(@PathVariable("boardNo") Integer boardNo) {
        return new ResponseMav("/board/write")
                .set("board", boardService.findByBoardNo(boardNo))
                .set("boardNo", boardNo);
    }

    @PostMapping("/write/{boardNo}")
    public ResponseEntity<Message> update(@PathVariable("boardNo") Integer boardNo, Board board) {
        return new ResponseEntity<>(boardService.update(board.boardNo(boardNo)), HttpStatus.OK);
    }

    @GetMapping("/{boardNo}")
    public ResponseMav detail(@PathVariable("boardNo") Integer boardNo) {
        return new ResponseMav("/board/detail")
                .set("board", boardService.findByBoardNo(boardNo))
                .set("boardNo", boardNo);
    }

    @DeleteMapping("/{boardNo}")
    public ResponseEntity<Message> delete(@PathVariable("boardNo") Integer boardNo) {
        return new ResponseEntity<>(boardService.delete(boardNo) , HttpStatus.OK);
    }

    @GetMapping( "/excelSampleDown")
    public void excelSampleDown(HttpServletResponse response) {

//      import
//        <dependency>
//			<groupId>org.apache.poi</groupId>
//			<artifactId>poi-ooxml</artifactId>
//			<version>4.1.0</version>
//		</dependency>

//        SXSSFWorkbook workbook = new SXSSFWorkbook();
//        workbook.setCompressTempFiles(true);
//        SXSSFSheet sheet = workbook.createSheet();
//        sheet.setRandomAccessWindowSize(100);
//
//        CellStyle headerStyle = workbook.createCellStyle();
//        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        headerStyle.setAlignment(HorizontalAlignment.CENTER);
//
//        CellStyle bodyStyle1 = workbook.createCellStyle();
//        bodyStyle1.setAlignment(HorizontalAlignment.CENTER);
//
//        CellStyle bodyStyle2 = workbook.createCellStyle();
//        bodyStyle2.setAlignment(HorizontalAlignment.LEFT);
//
//        Row headerRow = sheet.createRow(0);
//
//        String[] headerTexts = {"이름", "조직명", "주소", "전화번호"};
//        int[] headerWidth = {4000, 6000, 8000, 6000};
//        for (int i = 0; i < headerTexts.length; i++) {
//            String text = headerTexts[i];
//
//            Cell cell = headerRow.createCell(i);
//            cell.setCellStyle(headerStyle);
//            cell.setCellValue(text);
//
//            sheet.setColumnWidth(i, headerWidth[i]);
//        }
//
//        response.setContentType("ms-vnd/excel");
//        response.setHeader("Content-Disposition", "attachment;filename=seller_management_sample.xlsx");
//
//        try {
//            workbook.write(response.getOutputStream());
//            workbook.close();
//        } catch (IOException e) {
//            logger.error(e);
//        }
    }

}
