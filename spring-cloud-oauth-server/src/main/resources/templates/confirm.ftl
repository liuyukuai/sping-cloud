<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>欢迎登录后台管理系统</title>
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css"/>
    <link rel="stylesheet" href="/css/login.css"/>
    <script src="https://unpkg.com/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="/js/utils.js"></script>
    <style>
        .el-form-item{
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container" id="app">
    <div  class="login_form" >
        <h3 class="title">欢迎登录后台管理系统</h3>
        <el-form ref="agreeForm"  action="/oauth/authorize" method="post" label-position="left" label-width="0px">
            <el-form-item style="width:100%;">
                <input name="user_oauth_approval" value="true" type="hidden">
                <input name="scope.*" value="true" type="hidden">
                <input style="width: 100%" type="submit" class="el-button login_button el-button--primary" name="submit" value="同意">
            </el-form-item>
        </el-form>
        <el-form ref="agreeForm"  action="/oauth/authorize" method="post" label-position="left" label-width="0px">
            <el-form-item style="width:100%;">
                <input name="user_oauth_approval" value="true" type="hidden">
                <input name="scope.*" value="false" type="hidden">
                <input style="width: 100%" type="submit" class="el-button el-button--warning" name="submit" value="拒绝">
            </el-form-item>
        </el-form>
    </div>



</div>
<script>
    new Vue({
        el: '#app'
    })
</script>
</body>
</html>