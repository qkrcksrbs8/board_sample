<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<c:import url="/head"/>
<c:import url="/header"/>

<section id="content">
    <div class="container board-view-con">
        <div class="board-view-tit">
            ${board.title }
        </div>
    </div>
</section>

<section id="content">
    <div class="container board-view-con board-view-con1">
        <div class="board-view-info">
            <span><i class="icon-user-edit"></i> 우아앙 </span>&nbsp;&nbsp;&nbsp;&nbsp;
            <span><i class="icon-clock"></i> 오우우우 </span>&nbsp;&nbsp;&nbsp;&nbsp;
            <span><i class="icon-line-eye"></i> 8</span>
        </div>
    </div>
</section>

<section id="content">
    <div class="container board-view-con">
        <div class="board-view-sub">
            <pre>
                ${board.detail }
            </pre>
        </div>
    </div>
</section>

<section id="content">
    <div class="container board-view-con board-view-con1">
        <div class="board-view-sub board-view-utilbtn">
            <button class="btn btn-success" onclick="update('${boardNo}');">글수정</button>
            <button class="btn btn-success" onclick="drop('${boardNo}');">글삭제</button>
            <button class="btn btn-success" onclick="back();">글목록</button>
        </div>
    </div>
</section>


<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>

    const update = (boardNo) => {
        location.href='/board/write/'+boardNo;
    }

    const drop = (boardNo) => {
        $.ajax ({
            type  : 'DELETE',
            url : '/board/'+boardNo ,
            dataType : 'json',
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
                alert('삭제 중 오류가 발생했습니다.');
                return false;
            }
        });
    }

    const back = () => {
        location.href='/board';
    }

</script>
</html>
