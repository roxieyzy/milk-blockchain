<template>

  <el-row class="login" type="flex" :style="login" justify="center" align="middle">
    <el-col
      :xs="14"
      :sm="12"
      :md="10"
      :lg="8"
      :xl="6"
    >
      <el-form
        label-position="left"
        :model="loginForm"
        ref="loginForm"
        label-width="80px"
        class="login-form"
      >
        <div style="height: 100px;text-align: left;">
          <span style="font-size: 30px;">食品溯源系统</span>
          <div>
            <el-radio-group v-model="loginForm.loginType">
              <el-radio-button  label="1">管理员</el-radio-button>
              <el-radio-button   label="2">生产商</el-radio-button>
              <el-radio-button   label="3">运输商</el-radio-button>
              <el-radio-button   label="4">销售商</el-radio-button>
            </el-radio-group>
          </div>
        </div>
        <el-form-item
          label="账户"
          prop="adminName"
        >
          <el-input v-model="loginForm.account"></el-input>
        </el-form-item>
        <el-form-item
          label="密码"
          prop="password"
        >
          <el-input
            type="password"
            v-model="loginForm.password"
          ></el-input>
        </el-form-item>

        <el-form-item>
          <el-button
            class="green"
            @click="submitForm"
          >登录</el-button>
<!--          <el-button class="green" @click="register()">注册</el-button>-->
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>

</template>

<script>

import axios from 'axios'
export default {

  data () {
		var checkPhone = (rule, value, callback) => {
		  const phoneReg = /^1[3|4|5|7|8][0-9]{9}$/
		  if (!value) {
		    return callback(new Error('电话号码不能为空'))
		  }
		  setTimeout(() => {
		    // Number.isInteger是es6验证数字是否为整数的方法,但是我实际用的时候输入的数字总是识别成字符串
		    // 所以我就在前面加了一个+实现隐式转换

		    if (!Number.isInteger(+value)) {
		      callback(new Error('请输入数字值'))
		    } else {
		      if (phoneReg.test(value)) {
		        callback()
		      } else {
		        callback(new Error('电话号码格式不正确'))
		      }
		    }
		  }, 100)
		}
    return {
      login: {
        backgroundImage: "url(" + require("../assets/img/login04.png") + ")",
        backgroundRepeat: "no-repeat",
        backgroundSize: "cover",
        height: "100vh",
      },
      // 表单数据
      loginForm: {
        account : null,
        password : null,
        loginType:1
      },
      formData:{
        logintype:1
      },
			user:null,
      admin:null,
			familyTouPic:'',
			familyPhone:'',
			oldId:'',
      customer:'',
      customerId:'',
      name:'',
      customerPhone:'',
      // 表单验证规则
      rules: {
      //   userName: [
			// 	{ required: true, message: '请输入账户', trigger: 'blur' },
      //     { min: 4, max: 20, message: '账户长度为4-20个字符', trigger: 'blur' },
			//   { required: true, trigger: 'blur'}
			// ],
        adminName: [
          { required: true, message: '请输入账户', trigger: 'blur' },
          { min: 4, max: 20, message: '账户长度为4-20个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 4, max: 20, message: '密码长度为4-20个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
	  resetForm(){
		  this.loginForm.account=''
		  this.loginForm.password=''
	  },
    // register(){
	  //   this.$router.push("/userRegister");
    // },
      submitForm (formName) {

        for(let key in this.loginForm){
          // console.log("111",this.loginForm[key])
          if(this.loginForm[key] == null || this.loginForm[key] == "" || this.loginForm[key] == undefined  ){
            this.$message({
              type: 'error',
              message: '信息填写不完整'
            })
            return;
          }
        }

        console.log(formName)
        var _this = this
        if(this.loginForm.loginType == 1) {
          axios.post('/restful/user/userLogin',
            _this.loginForm,
            {
              headers: {
                'content-type': 'application/json'
              },
              withCredentials: true
            }).then(function (response) {
            console.log(response)
            if (response.data.result) {
              _this.user = response.data.data;
              console.log("用户登录")
              localStorage.setItem('token', true)
              localStorage.setItem('loginType', "1")
              localStorage.setItem('user_name', _this.user.username)
              localStorage.setItem('account', _this.user.account)
              localStorage.setItem('user_id', _this.user.userid)
              _this.$router.push({path: '/userHome'})
            } else {
              _this.$message({
                type: 'error',
                message: '账户或密码错误'
              })
            }
          })
            .catch(function (error) {
              console.log(error)
            })


        }
        if(this.loginForm.loginType == 2) {
          axios.post('restful/produce/produceLogin',
            {paccount:_this.loginForm.account,ppassword:_this.loginForm.password},
            {
              headers: {
                'content-type': 'application/json'
              },
              withCredentials: true
            }).then(function (response) {
            console.log(response)
            if (response.data.result) {
              _this.user = response.data.data;
              console.log("用户登录")
              localStorage.setItem('token', true)
              localStorage.setItem('loginType', "2")
              localStorage.setItem('user_name', _this.user.pname)
              localStorage.setItem('account', _this.user.paccount)
              localStorage.setItem('user_id', _this.user.pid)
              _this.$router.push({path: '/produceHome'})
            } else {
              _this.$message({
                type: 'error',
                message: '账户或密码错误'
              })
            }
          })
            .catch(function (error) {
              console.log(error)
            })
        }else if(this.loginForm.loginType == 3){
          axios.post('restful/traceSport/tranportLogin',
            {
              taccount:this.loginForm.account,
              tpassword:this.loginForm.password
            },
            {
              headers: {
                'content-type': 'application/json'
              },
              withCredentials: true
            }).then(function (response) {
            console.log(response)
            if (response.data.result) {
              _this.user = response.data.data;
              console.log("用户登录")
              localStorage.setItem('token', true)
              localStorage.setItem('loginType', "3")
              localStorage.setItem('user_name', _this.user.tname)
              localStorage.setItem('account', _this.user.taccount)
              localStorage.setItem('user_id', _this.user.tid)
              _this.$router.push({path: '/transportHome'})
            }
          })
            } else if(this.loginForm.loginType == 4) {
              axios.post('restful/sale/saleLogin',
                {
                  saccount: this.loginForm.account,
                  spassword: this.loginForm.password
                },
                {
                  headers: {
                    'content-type': 'application/json'
                  },
                  withCredentials: true
                }).then(function (response) {
                console.log(response)
                if (response.data.result) {
                  _this.user = response.data.data;
                  console.log("用户登录")
                  localStorage.setItem('token', true)
                  localStorage.setItem('loginType', "4")
                  localStorage.setItem('user_name', _this.user.sname)
                  localStorage.setItem('account', _this.user.saccount)
                  localStorage.setItem('user_id', _this.user.sid)
                  _this.$router.push({path: '/saleHome'})
                } else {
                  _this.$message({
                    type: 'error',
                    message: '账户或密码错误'
                  })
                }
              })
                .catch(function (error) {
                  console.log(error)
                })
            }
        }

  }
}
</script>

<style scoped>

/*.login {*/
/*  height: 100vh;*/
/*  background-color: #52BAB5;*/
/*}*/

.login-form {
  padding: 25px;
  border-radius: 15px;
  background-color: #fff;
}
.green{
  background-color: #409EFF;
  color: #fff;
}
</style>
