<template>
  <div class="roles">
    <!-- 查询和添加 -->
<!--    <div class="sys-research">-->
<!--      <el-row class="row-search">-->
<!--        <el-col :span="1">-->
<!--          <el-button plain @click="customerDialogVisible = true">添加生产商信息</el-button>-->
<!--        </el-col>-->
<!--      </el-row>-->
<!--    </div>-->
    <!-- start 添加角色弹窗 -->
<!--    <el-dialog-->
<!--      title="信息添加"-->
<!--      :visible.sync="customerDialogVisible"-->
<!--      @close="closeRoleAddDialog"-->
<!--      width="30%">-->
<!--      <el-form label-width="100px" :model="addForm" :rules="rules" class="demo-ruleForm"  ref="addaddFormRef">-->
<!--        <el-form-item label="生产地址" prop="paddress">-->
<!--          <el-input v-model="addForm.paddress"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="生产时间" prop="ptime">-->
<!--          <el-date-picker-->
<!--            v-model="addForm.ptime"-->
<!--            type="date"-->
<!--            placeholder="选择日期">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->

<!--        <el-form-item label="生产负责人" prop="pperson">-->
<!--          <el-input v-model="addForm.pperson"></el-input>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <span slot="footer" class="dialog-footer">-->
<!--      <el-button @click="customerDialogVisible = false">取 消</el-button>-->
<!--      <el-button type="primary" @click="add()">确 定</el-button>-->
<!--    </span>-->
<!--    </el-dialog>-->
    <!-- end 添加角色弹窗 -->
    <!-- start 编辑角色弹窗 -->
    <el-dialog
      title="编辑信息"
      :visible.sync="oldEditDialogVisible"
      width="30%">
      <el-form label-width="100px" :rules="rules" :model="editForm">
        <el-form-item label="销售人" prop="sperson">
          <el-input v-model="editForm.sperson"></el-input>
        </el-form-item>
        <el-form-item label="销售时间" prop="stime">
          <el-date-picker
            v-model="editForm.stime"
            type="date"
            placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="销售地址" prop="saddress">
          <el-input v-model="editForm.saddress"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
      <el-button @click="oldEditDialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="editData">确 定</el-button>
    </span>
    </el-dialog>
    <!-- end 编辑角色弹窗 -->
    <!-- 表格 -->
    <el-table :data="FormList.slice((currentPage-1)*pageSize,currentPage*pageSize)"
              style="width: 100%" border>
      <!-- 可展开行 -->
      <el-table-column type="index" width="100" align="center"></el-table-column>
      <el-table-column prop="onlykey" label="溯源码"  align="center"></el-table-column>
      <el-table-column prop="pname" label="生产商名称"  align="center"></el-table-column>
      <el-table-column prop="paddress" label="生产地址"  align="center"></el-table-column>
      <el-table-column prop="ptime" label="生产时间"  align="center"></el-table-column>
      <el-table-column prop="pperson" label="生产负责人"  align="center"></el-table-column>
      <el-table-column prop="tname" label="运输商名称"  align="center"></el-table-column>
      <el-table-column prop="tperson" label="运输负责人"  align="center"></el-table-column>
      <el-table-column prop="tbeginaddress" label="出发地"  align="center"></el-table-column>
      <el-table-column prop="tarriveaddress" label="目的地"  align="center"></el-table-column>
      <el-table-column prop="sname" label="销售商名称"  align="center"></el-table-column>
      <el-table-column prop="sperson" label="销售人"  align="center"></el-table-column>
      <el-table-column prop="stime" label="销售时间"  align="center"></el-table-column>
      <el-table-column prop="saddress" label="销售地址"  align="center"></el-table-column>
            <el-table-column label="操作" align="center">
              <template slot-scope="scope">
                <el-button size="mini" plain type="primary" icon="el-icon-edit"
                           @click="handleeditData(scope.$index, scope.row)">编辑
                </el-button>
<!--                <el-button size="mini" plain type="danger" icon="el-icon-delete" @click="deleteThis(scope.row.traid)">删除-->
<!--                </el-button>-->

              </template>
            </el-table-column>
    </el-table>
    <!-- start 分页 -->
    <div style="margin-top: 10px;text-align: center">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="changePage"
        :current-page.sync="currentPage"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
    <!-- end 分页 -->
    <!-- 分配权限对话框 -->
    <el-dialog title="角色授权" :visible.sync="isShowRoleDialog">
      <!--
        Tree 树形控件
          :data="data2" 数据源，数据中通过 label 属性表示树形节点的名称；通过 children 属性来指定该节点的子节点
          show-checkbox 添加该属性后，节点会带有复选框
          :default-expanded-keys="[2, 3]" 设置默认展开的节点key（key对应数据中的id）
          :default-checked-keys="[5]" 设置默认选中的节点key（id）
          node-key="id" 使用数据中的哪个属性来表示每个节点的key值，一般就是唯一的id值
          :props="defaultProps"
          :default-expand-all="true" 是否默认展开所有节点
      -->
      <el-tree
        ref="tree"
        :data="rightsTree"
        :default-expand-all="true"
        node-key="id"
        show-checkbox
        :props="defaultProps"
      >
      </el-tree>

      <div slot="footer" class="dialog-footer">
        <el-button @click="isShowRoleDialog = false">取 消</el-button>
        <el-button type="primary" @click="assignRights">确 定</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>
// 引入
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
      searchText: '', // 角色查询内容
      customerDialogVisible: false,
      oldEditDialogVisible: false,
      addForm: {
        sname:null,
        sperson:null,
        saddress:null,
        stime:null
      },
      editForm: {
        sname:null,
        sperson:null,
        saddress:null,
        stime:null
      },
      popoverRole: true,
      total: 0,
      pageSize: 10,
      currentPage: 1,
      FormList: [{
        userId:null,
        bloodBankName:null,
        userName:null,
        gender:null,
        age:null,
        phone:null,
        pid:null,
        bloodType:null,
        bloodVolume:null,
        addressName:null,
        bloodTypeId:null,
        provinceName:null,
        bloodBankId:null,
        provinceId:null,
        recordId:null,
        dateTime:null
      }],
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
        familyPhone: [
          { validator: checkPhone, trigger: 'blur'}
        ],
        oldPhone: [
          { validator: checkPhone, trigger: 'blur'}
        ]
      }
    }
  },
  created () {
    this.searchList()
  },
  methods: {


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
      this.editForm.sname = localStorage.getItem("user_name");
      var _this = this
      axios.post('restful/sale/updateData', _this.editForm,
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

