<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<c:import url="/head"/>
<c:import url="/header"/>
<body>
<section id="content">
    <div class="content-wrap nopadding">
        <div class="section nopadding nomargin" style="width: 100%; height: 100%; position: absolute; left: 0; top: 0; background: #fff;"></div>
        <div class="section nobg full-screen nopadding nomargin">
            <div class="container-fluid vertical-middle divcenter clearfix">
                <div class="center">
                    <a href="/"><img class="login-tdl-logo" src="/css/images/logo.png" alt="TDL Logo"></a>
                </div>
                <div class="card divcenter noradius noborder login-form-wrap" style="max-width: 400px;">
                    <div class="card-body" style="padding: 40px;">
                        <form id="login-form" name="login-form" class="nobottommargin" action="login" method="post">
                            <h3>로그인</h3>
                            <div class="col_full">
                                <label>아이디:</label>
                                <input type="text" id="memberId" name="memberId" class="form-control not-dark" required/>
                            </div>
                            <div class="col_full">
                                <label>비밀번호:</label>
                                <input type="password" id="password" name="password" class="form-control not-dark" required/>
                                <label style="color:red;"><b>아이디 또는 비밀번호가 틀렸습니다.</b></label>
                            </div>
                            <div class="col_full nobottommargin">
                                <button class="button button-black nomargin" value="login" onclick="login();">로그인</button>
                            </div>
                        </form>
                    </div>
                </div>
                <br>
                <div class="center dark">
                    <a id="login-home" class="button nomargin" href="/">메인으로</a>
                </div>
                <br>
                <div class="center dark text-black"><small>Copyrights &copy; All Rights Reserved by Canvas Inc.</small></div>
            </div>
        </div>
    </div>
</section>
</body>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
    const login = () => {
        let memberId = $('#memberId').val();
        let password = $('#password').val();

        let param = {
            memberId, password
        }

        $.ajax ({
            type  : 'POST',
            url : '/auth/login' ,
            dataType : 'json',
            data : param,
            success : function(data) {
                if (!data.status) {
                    alert(data.message);
                    return false;
                }
                alert(data.message);
                location.href = '/';
                return false;
            }
            ,fail : function(data) {
                alert(data.message);
                return false;
            }
            ,error : function() {
                alert('잠시 후 다시 시도해주세요.');
                location.href = '/auth/login';
                return false;
            }
        });

    }
</script>
</html>