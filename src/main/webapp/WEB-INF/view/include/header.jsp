<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header id="header" class="split-menu">
    <div id="header-wrap">
        <div class="container clearfix">
            <div id="primary-menu-trigger"><i class="icon-reorder"></i></div>
            <!-- Logo
            ============================================= -->
            <div id="logo">
                <a href="/" class="standard-logo" data-dark-logo="/css/images/logo.png">
                    <img src="/images/logo.png" alt="게시판 로고">
                </a>
            </div><!-- #logo end -->
            <!-- Primary Navigation
            ============================================= -->
            <nav id="primary-menu" class="with-arrows clearfix style-5">
                <ul>
                    <li><a href="/board"><div><i class="icon-clipboard-list"></i>자유게시판</div></a></li>
                </ul>
                <ul>
                    <c:if test="${empty member.memberId}">
                    <li><a href="/auth/login"><div><i class="icon-power-off"></i>로그인</div></a></li>
                        <li><a href="/auth/logon"><div><i class="icon-line-circle-plus"></i>회원가입</div></a></li>
                    </c:if>
                    <c:if test="${not empty member.memberId}">
                        <li><a href="/" onclick="logout();"><div><i class="icon-power-off"></i>로그아웃</div></a></li>
                    </c:if>
                </ul>
            </nav><!-- #primary-menu end -->
        </div>
    </div>
</header><!-- #header end -->
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
    const logout = () => {
        $.ajax ({
            type  : 'DELETE',
            url : '/auth/login' ,
            dataType : 'json',
            error : function() {
                alert('잠시 후 다시 시도해주세요.');
                location.href = '/';
                return false;
            }
        });
    }

</script>