<template>
  <div class="roles">

    <el-form status-icon label-width="100px" ref="formRef" :model="passwordForm" >
      <el-form-item label="密码" prop="temperature">
        <el-input type="password" v-model="passwordForm.password1" placeholder="请输入新密码"  />
      </el-form-item>
      <el-form-item label="密码确认" prop="envHumidity">
        <el-input type="password" v-model="passwordForm.password2" placeholder="请输入再次输入密码" maxlength="20"  />
      </el-form-item>
      <div style="text-align:center">
        <el-button type="primary" @click="updatePassword">确认修改</el-button>
      </div>

    </el-form>

  </div>

</template>

<script>
// 引入
import axios from 'axios'

export default {
  data () {
    return {
      searchText: '', // 角色查询内容
      customerDialogVisible: false,
      oldEditDialogVisible: false,
      passwordForm:{
        password1:null,
        password2:null
      },

      popoverRole: true,
      total: 0,
      pageSize: 10,
      currentPage: 1,

      // 控制分配权限对话框的展示和隐藏
      isShowRoleDialog: false,
      // 树形控件的数据
      rightsTree: [],

      // Tree树形控件，默认使用数据中的 label 属性作为节点名称，使用 children 属性指定该接待你的子节点
      // 但是，如果我们自己的数据结构与默认的结构不同，可以通过 defaultProps 来修改默认的两个属性
      defaultProps: {
        // children: 'children',
        label: 'authName'
      },
      // 当前被分配权限的角色id
      roleId: -1,
      rules: {
      }
    }
  },
  created () {
    this.searchList()
  },
  methods: {

    clearPassword(){
      this.passwordForm.password1 = null,
      this.passwordForm.password2 = null
    },

    updatePassword(){
      var _this = this
      if(this.passwordForm.password1 == null || this.passwordForm.password1 == "" || this.passwordForm.password1 != this.passwordForm.password2 || this.passwordForm.password1 == undefined ){
        _this.$message({
          type: 'error',
          message: '请检查密码后重新输入'
        })
        return  false;
      }

      let loginType = localStorage.getItem('loginType');
      if(loginType == "1"){
        axios.post('/restful/user/updatePassword',
          {
            password:this.passwordForm.password1,userid:localStorage.getItem("user_id")
          },
          {
            headers: {
              'content-type': 'application/json'
            },
            withCredentials: true
          }).then(function (response) {
          if (response.data.result) {
            _this.$message({
              type: 'success',
              message: '修改成功'
            })
          } else {
            _this.$message({
              type: 'error',
              message: '修改失败'
            })
          }
        })
          .catch(function (error) {
            console.log(error)
          })
      }else if (loginType == "2"){
        axios.post('/restful/produce/updatePassword',
          {
            ppassword:this.passwordForm.password1,pid:localStorage.getItem("user_id")
          },
          {
            headers: {
              'content-type': 'application/json'
            },
            withCredentials: true
          }).then(function (response) {
          if (response.data.result) {
            _this.$message({
              type: 'success',
              message: '修改成功'
            })
          } else {
            _this.$message({
              type: 'error',
              message: '修改失败'
            })
          }
        })
          .catch(function (error) {
            console.log(error)
          })
      }else if (loginType == "3"){
        axios.post('/restful/traceSport/updatePassword',
          {
            tpassword:this.passwordForm.password1,tid:localStorage.getItem("user_id")
          },
          {
            headers: {
              'content-type': 'application/json'
            },
            withCredentials: true
          }).then(function (response) {
          if (response.data.result) {
            _this.$message({
              type: 'success',
              message: '修改成功'
            })
          } else {
            _this.$message({
              type: 'error',
              message: '修改失败'
            })
          }
        })
          .catch(function (error) {
            console.log(error)
          })
      }else if (loginType == "4"){
        axios.post('/restful/sale/updatePassword',
          {
            spassword:this.passwordForm.password1,sid:localStorage.getItem("user_id")
          },
          {
            headers: {
              'content-type': 'application/json'
            },
            withCredentials: true
          }).then(function (response) {
          if (response.data.result) {
            _this.$message({
              type: 'success',
              message: '修改成功'
            })
          } else {
            _this.$message({
              type: 'error',
              message: '修改失败'
            })
          }
        })
          .catch(function (error) {
            console.log(error)
          })
      }
      this.clearPassword();
    },
    // 查询
    searchList () {
      var _this = this
      axios.post('/restful/trace/queryTrace',
        {
          // 'familyName': familyName === '' ? '' : _this.searchText
        },
        {
          headers: {
            'content-type': 'application/json'
          },
          withCredentials: true
        }).then(function (response) {
        _this.FormList = response.data.data
        _this.total = response.data.total
        console.log(_this.FormList)
      })
        .catch(function (error) {
          console.log(error)
        })

    },
    // 添加
    add() {
      this.addForm.pname = localStorage.getItem("user_name");
      var _this = this
      axios.post('restful/produce/insertData',
        _this.addForm,
        {
          headers: {
            'content-type': 'application/json'
          },
          withCredentials: true
        }).then(function (response) {
        console.log(response)
        if (response.data.result) {
          console.log(response)
          _this.customerDialogVisible = false
          _this.searchList()
          _this.$message({
            type: 'success',
            message: '添加成功'
          })
        } else {
          _this.$message({
            type: 'error',
            message: '添加失败'
          })
        }
      })
        .catch(function (error) {
          console.log(error)
        })
    },
    // 关闭添加角色对话框
    closeRoleAddDialog () {
      // 重置表单
      this.$refs.addaddFormRef.resetFields()
    },
    // 编辑
    editData () {
      this.editForm.tname = localStorage.getItem("user_name");
      var _this = this
      axios.post('restful/traceSport/updateData', _this.editForm,
        {
          headers: {
            'content-type': 'application/json'
          },
          withCredentials: true
        }).then(function (response) {
        console.log(response)
        if (response.data.result) {
          _this.oldEditDialogVisible = false
          _this.$message({
            type: 'success',
            message: '编辑信息成功'
          })
          _this.searchList()
        } else {
          _this.$message({
            type: 'error',
            message: '编辑信息失败'
          })
        }
      })
        .catch(function (error) {
          console.log(error)
        })
    },
    // 删除
    async deleteThis (id) {
      console.log(id)

      var _this = this
      try {
        await _this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
        axios.post('restful/produce/deleteOne', {'traid': id},
          {
            headers: {
              'content-type': 'application/json'
            },
            withCredentials: true
          }).then(function (response) {
          if (response.data.result) {
            _this.$message({
              type: 'success',
              message: '删除信息成功'
            })
          }else {
            _this.$message({
              type: 'error',
              message: '删除失败'
            })
          }
          _this.searchList()
        })
          .catch(function (error) {
            console.log(error)
          })
      } catch (err) {
        // 取消删除
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      }
    },
    // 编辑信息
    handleeditData (index, row) {
      this.oldEditDialogVisible = true
      this.editForm = Object.assign({}, row) // 解决修改弹出框数据时，表格数据会发生同步改变
      // console.log(row)
    },
    handleSizeChange (val) {
      console.log(`每页 ${val} 条`)
      this.pageSize = val
    },
    handleCurrentChange (val) {
      console.log(`当前页: ${val}`)
      this.currentPage = val
    },
    changePage (val) {
      console.log(`当前页: ${val}`)
      this.currentPage = val
    },
    // 展示分配权限对话框
    showRoleDialog (curRole) {
      // curRole 表示当前角色的数据，children属性是当前角色拥有的权限
      console.log('showRoleDialog', curRole)
      // 展示对话框
      this.isShowRoleDialog = true
      // 暂存角色id
      this.roleId = curRole.id
    },

    // 给角色分配权限
    async assignRights () {
    }
  }
}

</script>

<style lang="less" scoped>
.sys-research {
  height: 60px;
  padding: 20px 20px 0;
}

.level1 {
  border-bottom: 1px dotted #ccc;
  padding: 10px 0;

  &:last-child {
    border: 0;
  }
}

.level2 {
  padding: 5px 0;
}

.level3 {
  margin: 3px;
}

</style>

