<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<c:import url="/head"/>
<c:import url="/header"/>
<style>
    #pop{
        width: 501px;
        height: 171px;
        background: #ffffff;
        position: absolute;
        top:31%;
        left:36%;
        border: 1px solid #000000;
        text-align: center;
    }
    #excelUpload{
        position: absolute; width: 31px; height: 31px; padding: 0; margin: -1px; overflow: hidden; clip:rect(0,0,0,0); border: 0;
    }
    #fileButton { display: inline-block; padding: .5em .74em; color: #999; font-size: inherit; line-height: normal; vertical-align: middle; background-color: #fdfdfd; cursor: pointer; border: 1px solid #ebebeb; border-bottom-color: #e2e2e2; border-radius: .25em; }
    #filebox {
        margin-top: 41px;
        margin-bottom: 31px;
    }


    #pop2{
        width: 501px;
        height: 171px;
        background: #ffffff;
        position: absolute;
        top:31%;
        left:36%;
        border: 1px solid #000000;
        text-align: center;
    }
    #excelUpload2{
        position: absolute; width: 31px; height: 31px; padding: 0; margin: -1px; overflow: hidden; clip:rect(0,0,0,0); border: 0;
    }
    #fileButton2 { display: inline-block; padding: .5em .74em; color: #999; font-size: inherit; line-height: normal; vertical-align: middle; background-color: #fdfdfd; cursor: pointer; border: 1px solid #ebebeb; border-bottom-color: #e2e2e2; border-radius: .25em; }
    #filebox2 {
        margin-top: 41px;
        margin-bottom: 31px;
    }
</style>
<body>
<section id="content">
    <div class="container">
        <div class="board-list-wrap board-list-header">
            <ul class="row">
                <li class="col-1 col-md-1 d-none d-md-block">번호</li>
                <li class="col-6 col-md-6">제목</li>
                <li class="col-3 col-md-3">작성자</li>
                <li class="col-2 col-md-2 d-none d-md-block">작성일</li>
            </ul>
        </div>
        <div class="board-list-wrap borard-list-con">
            <c:choose>
            <c:when test="${fn:length(list) > 0 }">
            <c:set var="no" value="${count}" />
            <c:forEach var="row" items="${list }">
            <ul class="row">
                <li class="col-1 col-md-1 d-none d-md-block">
                    ${no}
                </li>
                <li class="col-6 col-md-6">
                    <a href="/board/${row.boardNo}">${row.title}</a>
                </li>
                <li class="col-3 col-md-3">
                    ${row.createdBy}
                </li>
                <li class="col-2 col-md-2 d-none d-md-block">
                    <fmt:formatDate value="${row.createdDate}" pattern="YYYY-MM-DD" type="date"/>
                </li>
            </ul>
            <c:set var="no" value="${no - 1 }" />
            </c:forEach>
            </c:when>
            <c:otherwise>
            <tr>
                <td class="col-12 col-md-12">게시글이 없습니다.</td>
            </tr>
            </c:otherwise>
            </c:choose>
        </div>
    </div>
</section>

<!-- 자유게시판 list page paging -->
<section id="content" class="board-list-paging">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <ul class="pagination">
                    <li class="page-item"><a class="page-link" href="#">&laquo;</a></li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item"><a class="page-link" href="#">4</a></li>
                    <li class="page-item"><a class="page-link" href="#">5</a></li>
                    <li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
                </ul>
            </div>
        </div>
    </div>
</section>

${paging}

<!-- 자유게시판 list page search -->
<section id="content" class="board-list-search-wrap">
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-2 nopadding form-row">
                <div class="col-4 col-md-12">
                    <button class="btn btn-success write-btn btn-block" onclick="location.href='/board/write'; return false;">글쓰기</button>
                    <button class="btn btn-success write-btn btn-block" onclick="excelPop();">엑셀 업로드</button>
                    <button class="btn btn-success write-btn btn-block" onclick="excelPop2();">동영상 업로드</button>
                </div>
            </div>
            <div class="col-12 col-md-10  nopadding">
                <form>
                    <div class="form-row">
                        <div class="form-group  col-4 col-md-3 offset-md-4">
                            <div class="white-section">
                                <select id="searchStatus" name="search" class="form-control selectpicker">
                                    <option value="title">제목</option>
                                    <option value="detail">내용</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-5 col-md-4">
                            <input type="text" id="searchText" class="form-control" value="${board.title}${board.detail}">
                        </div>
                        <div class="form-group col-3 col-md-1">
                            <button type="button" class="btn btn-success write-btn btn-block" onclick="board();"><i class="icon-line-search"></i></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>


<div id="pop" style="display: none">
    <form id="fileUploadForm">
        <div id="filebox">
            <input type="text" class="txt" style="width:200px" id="fileName" value=""/>
            <label id="fileButton" for="excelUpload">찾아보기</label>
            <input type="file" id="excelUpload" name="excelUpload" class="btnType1" onchange="uploadExcel();" value="엑셀 업로드">
        </div>
        <div>
            <input type="button" class="btnType1" value="확인" onclick="excelUpload()">
            <input type="button" class="btnType1" value="취소" onclick="excelCancel()">
        </div>
    </form>
</div>

<div id="pop2" style="display: none">
    <form id="fileUploadForm2">
        <div id="filebox2">
            <input type="text" class="txt" style="width:200px" id="fileName2" value=""/>
            <label id="fileButton2" for="excelUpload2">찾아보기</label>
            <input type="file" id="excelUpload2" name="excelUpload2" class="btnType1" onchange="uploadExcel2();" value="동영상 업로드">
        </div>
        <div>
            <input type="button" class="btnType1" value="확인" onclick="videoUpload()">
            <input type="button" class="btnType1" value="취소" onclick="excelCancel2()">
        </div>
    </form>
</div>
</body>

<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
    const write = () => {
        location.href = 'write';
    }

    const board = () => {
        let searchStatus = $('#searchStatus').val();
        let searchText = $('#searchText').val();
        location.href = '/board?'+searchStatus+'='+searchText;
    }

    const excelCancel = () => {
        $("#pop").css("display", "none")
        $('#fileName').val('');
    }

    const excelPop = () => {
        $("#pop").css("display", "block")
    }

    const uploadExcel = () => {
        $("#fileUploadForm").attr("target", "");
        $("#fileUploadForm").attr("action", "/excel/extension/excelUpload");

        let form = $("#fileUploadForm");
        let data = new FormData(form[0]);

        $.ajax ({
            type : "POST",
            enctype : "multipart/form-data",
            url : "/excel/extension/excelUpload",
            data : data,
            processData : false,
            contentType : false,
            cache : false,
            timeout : 60000,
            success : function(data) {
                if (!data.status) {
                    alert(data.message);
                    location.href = '/board';
                    return false;
                }
                $('#fileName').val(data.param.data);
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
    }

    const excelUpload = () => {
        alert('업로드!!');
    }

    const excelCancel2 = () => {
        $("#pop2").css("display", "none")
        $('#fileName2').val('');
    }

    const excelPop2 = () => {
        $("#pop2").css("display", "block")
    }

    const uploadExcel2 = () => {
        $("#fileUploadForm2").attr("target", "");
        $("#fileUploadForm2").attr("action", "/video/extension/upload");

        let form = $("#fileUploadForm2");
        let data = new FormData(form[0]);

        $.ajax ({
            type : "POST",
            // enctype : "multipart/form-data",
            url : "/video/extension/excelUpload2",
            data : data,
            processData : false,
            contentType : false,
            cache : false,
            timeout : 60000,
            success : function(data) {
                if (!data.status) {
                    alert(data.message);
                    location.href = '/board';
                    return false;
                }
                $('#fileName2').val(data.param.data);
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
    }

    function videoUpload() {

        let form = $("#fileUploadForm2");
        let data = new FormData(form[0]);

        $.ajax ({
            type : "POST",
            // enctype : "multipart/form-data",
            url : "/video/save/excelUpload2",
            data : data,
            processData : false,
            contentType : false,
            cache : false,
            timeout : 60000,
            success : function(data) {
                if (!data.status) {
                    alert(data.message);
                    location.href = '/';
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
    }

</script>
</html>
