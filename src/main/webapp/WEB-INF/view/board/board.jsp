<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <c:import url="/head"/>
</head>

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
            <ul class="row">
                <li class="col-1 col-md-1 d-none d-md-block">번호</li>
                <li class="col-6 col-md-6">제목</li>
                <li class="col-3 col-md-3">작성자</li>
                <li class="col-2 col-md-2 d-none d-md-block">작성일</li>
            </ul>
            <ul class="row">
                <li class="col-1 col-md-1 d-none d-md-block">번호</li>
                <li class="col-6 col-md-6">제목</li>
                <li class="col-3 col-md-3">작성자</li>
                <li class="col-2 col-md-2 d-none d-md-block">작성일</li>
            </ul>
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
                                <select id="inputState" name="search" class="form-control selectpicker">
                                    <option value="TP_title">제목</option>
                                    <option value="subject_content">제목+본문</option>
                                    <option value="subject_writer">작성자</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-5 col-md-4">
                            <input type="text" class="form-control">
                        </div>
                        <div class="form-group col-3 col-md-1">
                            <button type="submit" class="btn btn-success form-control"><i class="icon-line-search"></i></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

</body>
<script>
    const write = () => {
        location.href = 'write';
    }
</script>
</html>
