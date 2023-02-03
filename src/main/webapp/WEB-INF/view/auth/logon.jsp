<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<c:import url="/head"/>
<c:import url="/header"/>
<body>

<section id="content">
    <div class="tabs divcenter nobottommargin clearfix" id="tab-login-register" style="max-width: 500px;">

        <div class="tab-container">

            <div class="card nobottommargin">
                <div class="card-body" style="padding: 40px;">
                    <h3>회원 가입</h3>

                    <form id="register-form" name="register-form" class="nobottommargin" action="/TDLSpring/register.do" method="post">

                        <div class="col_full">
                            <label>아이디:</label>
                            <input type="text" class="form-control" required/>
                            <input type="button" class="button button-3d button-green" value="중복체크" onclick="duplicateId();">
                        </div>

                        <div class="col_full">
                            <label>패스워드:</label>
                            <input type="password" class="form-control" value="" required/>
                        </div>

                        <div class="col_full">
                            <label>패스워드 확인:</label>
                            <input type="password" class="form-control" value="" required/>
                        </div>

                        <div class="col_full">
                            <label>이름:</label>
                            <input type="text" class="form-control" required/>
                        </div>

                        <center>
                            <div class="col_full nobottommargin" >
                                <button type="button" class="button button-3d button-green nomargin" value="register" onclick="logon();">가입하기</button>
                            </div>
                        </center>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
    const logon = () => {
        alert('회원가입!');
    }
    const duplicateId = () => {
        alert('아이디 중복체크!');
    }
</script>
</html>