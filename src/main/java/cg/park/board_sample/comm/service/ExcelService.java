package cg.park.board_sample.comm.service;

import cg.park.board_sample.comm.util.Message;
import cg.park.board_sample.comm.util.Param;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class ExcelService {

    public Message extensionCheck(MultipartHttpServletRequest request, String field) {
        try {
            if (null == request.getFile(field) || null == request.getFile(field).getOriginalFilename())
                return new Message(false, "첨부 가능한 파일이 아닙니다. 첨부 가능한 파일을 확인해 주세요.", null);

            if (0 > request.getFile(field).getOriginalFilename().lastIndexOf(".xlsx"))
                return new Message(false, ".xlsx 확장자만 업로드 가능합니다.", null);

            return new Message(true, "OK", new Param(request.getFile(field).getOriginalFilename().toString()));
        }
        catch (Exception e) {
            return new Message(false, "잠시 후 시도해주세요.", null);
        }
    }

}
