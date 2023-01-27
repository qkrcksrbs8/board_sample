<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<c:import url="/head"/>
<c:import url="/header"/>

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
</script>
</html>
