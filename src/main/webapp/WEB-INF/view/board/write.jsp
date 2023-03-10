<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<c:import url="/head"/>
<c:import url="/header"/>

<body>
<section id="content" class="board-write-con-wrap">
    <div class="container">
        <div class="form-widget">
            <div class="form-result"></div>
            <form class="nobottommargin" id="template-contactform" name="writeform" action="/write" method="post" onsubmit="return save('${boardNo}')">

                <div class="form-process"></div>
                <input type="hidden" name="boardNo" value="">

                <div class="col_full">
                    <label for="title">제목 <small>*</small></label>
                    <input type="text" id="title" name="title" value="${board.title}" class="required sm-form-control" />
                </div>

                <div class="clear"></div>

                <div class="col_full">
                    <label for="detail">내용 <small>*</small></label>
                    <textarea class="required sm-form-control" id="detail" name="detail" rows="6" cols="30">${board.detail}</textarea>
                </div>

                <div class="col_full hidden">
                    <input type="text" id="template-contactform-botcheck" name="template-contactform-botcheck" value="" class="sm-form-control" />
                </div>

                <div class="row">
                    <div class="col-6 col-sm-6">
                        <a href="/board"><button class="btn btn-success">목록으로</button></a>
                    </div>
                    <div class="col-6 col-sm-6 text-right">
                    <c:if test="${not empty boardNo}">
                        <button id="update" class="btn btn-success" type="submit" >글수정</button>
                    </c:if>
                    <c:if test="${not empty boardNo}">
                        <button id="sub" class="btn btn-success" type="submit" >글쓰기</button>
                    </c:if>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>

</body>

<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
    const save = (boardNo) => {
        let title = $('#title').val();
        let detail = $('#detail').val();

        if ('' == title) {
            alert('제목을 입력해주세요.');
            $('#title').focus();
            return false;
        }

        if ('' == detail) {
            alert('내용을 입력해주세요.');
            $('#detail').focus();
            return false;
        }

        let param = {
            title, detail
        }

        let url = '/board/write';

        if ('' != boardNo) {
            url = '/board/write/'+boardNo;
        }

        $.ajax ({
            type : "POST",
            url : url, //해당 ajax연결
            dataType : 'json',
            data : param,
            success : function(data) {
                if (!data.status) {
                    alert(data.message);
                    return false;
                }
                alert(data.message);
                location.href = '/board';
                return false;
            }
            ,fail : function(data) {
                alert(data.message);
                return false;
            }
            ,error : function() {
                alert('저장 중 오류가 발생했습니다.');
                return false;
            }
        });

        return false;
    }
</script>
</html>
