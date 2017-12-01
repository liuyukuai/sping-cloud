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
    <el-form class="login_form" :model="user" :rules="rules2" ref="user" label-position="left" label-width="0px" v-loading="loadingFlag" element-loading-text="页面跳转中">
        <h3 class="title">欢迎登录后台管理系统</h3>
        <el-form-item prop="email">
            <el-input type="text" v-model="user.email" auto-complete="off" placeholder="请输入邮箱地址" @change="changeFlag"></el-input>
        </el-form-item>
        <el-form-item prop="password">
            <el-input type="password" v-model="user.password" auto-complete="off" placeholder="请输入密码"/>
        </el-form-item>
        <el-alert :title="loginMsg" type="error" v-show="loingError"  :closable="false" show-icon>
        </el-alert>
        <el-form-item style="width:100%;">
            <el-button class="login_button" type="primary" style="width:40%;" @click="handleSubmit">登录</el-button>
            <el-button class="login_button" type="primary" style="width:40%;" @click="toReg">注册</el-button>
        </el-form-item>
    </el-form>
</div>
<script>
    const instance = axios.create()
    instance.defaults.headers.post['Content-Type'] = 'application/json'
    new Vue({
        el: '#app',
        data: function() {
            return {
                user: {
                    email: '',
                    password: '',
                    callback:utils.getUrlKey("callback")
                },
                rules2: {
                    email: [
                        { required: true, message: '邮箱不能为空', trigger: 'blur' },
                        {type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur,change'},
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ]
                },
                errorMessage: false,
                loadingFlag: false,
                loginMsg:'',
                loingError:false,
            }
        },
        methods: {
            handleSubmit :function(){
                var that = this
                this.$refs.user.validate(function (valid) {
                    if (valid) {
                       axios.post('/doLogin', JSON.stringify(that.user)).then(function (data) {
                            if(data.status ==200){
                                if(data.data.success =="true" || data.data.success==true ){
                                    that.loingError=false
                                    window.location.href=data.data.data
                                }else {
                                    that.loingError=true
                                    that.loginMsg = data.data.msg
                                }
                            }else {
                                that.loingError=true
                                that.loginMsg ="系统异常，请稍后重试。"
                            }
                       });
                    } else {
                        console.log('error submit!!')
                        return false
                    }
                })
            },
            changeFlag :function () {
                this.errorMessage = false
                this.loingError=false
            },
            toReg :function () {
                window.location.href='/reg';
            }
        }
    })
</script>
</body>
</html>