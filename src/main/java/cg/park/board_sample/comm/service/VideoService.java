package cg.park.board_sample.comm.service;

import cg.park.board_sample.comm.util.Message;
import cg.park.board_sample.comm.util.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class VideoService {

    public Message extensionCheck(MultipartHttpServletRequest request, String field) {
        try {
            if (null == request.getFile(field) || null == request.getFile(field).getOriginalFilename())
                return new Message(false, "첨부 가능한 파일이 아닙니다. 첨부 가능한 파일을 확인해 주세요.", null);

            if (0 > request.getFile(field).getOriginalFilename().lastIndexOf(".mp4"))
                return new Message(false, ".mp4 확장자만 업로드 가능합니다.", null);

            return new Message(true, "OK", new Param(request.getFile(field).getOriginalFilename().toString()));
        }
        catch (Exception e) {
            return new Message(false, "잠시 후 시도해주세요.", null);
        }
    }

    public Message save(MultipartHttpServletRequest request, String field) {
        try {

            MultipartFile files = request.getFile(field);

            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String formatedNow = now.format(formatter);

            StringBuilder path = new StringBuilder()
                                        .append("D:\\pcg\\DES\\sou\\SM\\data\\")
                                        .append(formatedNow)
                                        .append("/");

            File fileDir = new File(path.toString());
            if (!fileDir.exists())
                fileDir.mkdirs();

            File saveFile = new File(path.toString(), request.getFile(field).getOriginalFilename());
            files.transferTo(saveFile);

            if (!saveFile.exists())
                return new Message(false, "파일 생성 실패", null);

            return new Message(true, "OK", new Param(request.getFile(field).getOriginalFilename().toString()));
        }
        catch (Exception e) {
            return new Message(false, "잠시 후 시도해주세요.", null);
        }
    }

}
