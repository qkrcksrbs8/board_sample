package cg.park.board_sample.comm.service;

import cg.park.board_sample.comm.util.Message;
import cg.park.board_sample.comm.util.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

@Service
public class VideoService {

    public static final Logger logger = Logger.getLogger(String.valueOf(VideoService.class));

    public Message extensionCheck(MultipartHttpServletRequest request, String field) {
        try {
            if (null == request.getFile(field) || null == request.getFile(field).getOriginalFilename())
                return new Message(false, "첨부 가능한 파일이 아닙니다. 첨부 가능한 파일을 확인해 주세요.", null);

            if (0 > request.getFile(field).getOriginalFilename().lastIndexOf(".mp4"))
                return new Message(false, ".mp4 확장자만 업로드 가능합니다.", null);

            return new Message(true, "OK", new Param(request.getFile(field).getOriginalFilename()));
        }
        catch (Exception e) {
            logger.finest(e.toString());
            return new Message(false, "잠시 후 시도해주세요.", null);
        }
    }

    public Message save(MultipartHttpServletRequest request, String field) {
        try {

            String physicalPath = "D:\\pcg\\DES\\sou\\SM\\data\\";
            String relativePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            StringBuilder path = new StringBuilder()
                                        .append(physicalPath)
                                        .append(relativePath)
                                        .append("/");

            File fileDir = new File(path.toString());
            if (!fileDir.exists())
                fileDir.mkdirs();

            File saveFile = new File(path.toString(), request.getFile(field).getOriginalFilename());
            request.getFile(field).transferTo(saveFile);

            if (!saveFile.exists())
                return new Message(false, "파일 생성 실패", null);

            return new Message(true, "OK", new Param(request.getFile(field).getOriginalFilename()));
        }
        catch (Exception e) {
            logger.finest(e.toString());
            return new Message(false, "잠시 후 시도해주세요.", null);
        }
    }

}
