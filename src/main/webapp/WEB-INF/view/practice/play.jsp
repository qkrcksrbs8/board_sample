<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<c:import url="/head"/>
<c:import url="/header"/>

<body onkeyup="onQkey(event.keyCode)">
<section id="content">
    <input type="hidden" id="lastKey" value="">
    <div class="container">
        <div class="board-list-wrap board-list-header">
            <ul class="row">
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
            </ul>
        </div>
        <div class="board-list-wrap borard-list-con">

            <ul class="row">
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block" id="li1" onmouseover="lastKeyCode(this.id);">ㅇ</li>
                <li class="col-1 col-md-1 d-none d-md-block" id="li2" onmouseover="lastKeyCode(this.id);">ㅇ</li>
                <li class="col-1 col-md-1 d-none d-md-block" id="li3" onmouseover="lastKeyCode(this.id);">ㅇ</li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
            </ul>
            <ul class="row">
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block" id="li4" onmouseover="lastKeyCode(this.id);">ㅇ</li>
                <li class="col-1 col-md-1 d-none d-md-block" id="li5" >ㅇ</li>
                <li class="col-1 col-md-1 d-none d-md-block" id="li6" onmouseover="lastKeyCode(this.id);">ㅇ</li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
            </ul>
            <ul class="row">
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block" id="li7" onmouseover="lastKeyCode(this.id);">ㅇ</li>
                <li class="col-1 col-md-1 d-none d-md-block" id="li8"onmouseover="lastKeyCode(this.id);">ㅇ</li>
                <li class="col-1 col-md-1 d-none d-md-block" id="li9"onmouseover="lastKeyCode(this.id);">ㅇ</li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
                <li class="col-1 col-md-1 d-none d-md-block"></li>
            </ul>
        </div>
    </div>
</section>

</body>

<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>

    const write = () => {
        location.href = 'write';
    }

    const chageColor = (e) => {

    }


    const onQkey = (q) => {
        let lastKey = $('#lastKey').val();
        if (null != lastKey && "" != lastKey) {
            const element = document.getElementById(lastKey);
            element.style.color = 'red';

        }

    }

    const lastKeyCode = (key) => {
        $('#lastKey').val(key);
    }

</script>
</html>
