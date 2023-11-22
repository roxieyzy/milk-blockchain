<template>
        <el-menu
        :unique-opened="true"
        :default-active="$route.path"
        class="slider"
        background-color="#2e2727"
        text-color="#f5eded"
        active-text-color="#000"
        router
        >
        <el-menu-item index="/userHome" >
          <i class="el-icon-menu"></i>
          <span slot="title">管理员</span>
        </el-menu-item>

        <el-submenu v-for="(item,index) in menuList" :key="index" :index='item.id'>
          <template slot="title">
<!--            <i :class="item.icon"></i>-->
            <el-icon><document /></el-icon>
            <span>{{item.title}}</span>
          </template>
          <el-menu-item-group>
            <el-menu-item v-for="child in item.children" :key="child.id" :index='child.index'>{{child.childtitle}}</el-menu-item>
          </el-menu-item-group>
        </el-submenu>


      </el-menu>
</template>

<script>
export default {
  name: 'FamilySlider',
  data () {
    return {
      menuList: [
        {
          id: '1',
          title: '用户管理',
          icon: 'el-icon-location',
          children: [
            {
              index: '/produceUserInfo',
              childtitle: '生产商'
            },
            {
              index: '/transportUserInfo',
              childtitle: '运输商'
            },
            {
              index: '/saleUserInfo',
              childtitle: '销售商'
            }
          ]
        },
        {
          id: '2',
          title: '溯源管理',
          icon: 'el-icon-location',
          children: [
            {
              index: '/suyuan',
              childtitle: '溯源信息'
            }
          ]
        },
        {
          id: '3',
          title: '个人信息',
          icon: 'el-icon-location',
          children: [
            {
              index: '/updatePassword1',
              childtitle: '密码修改'
            }
          ]
        }
      ]

    }
  }

}
</script>
<style scoped>
.slider{
  height:100vh;
}
</style>

<style lang="less">
.el-menu{
 .el-submenu{
   .el-menu-item{
     padding-left:92px!important;
   }
 }
}
.el-submenu__title:hover{
   background-color: rgba(82,186,181,.38) !important;
}
.el-menu-item:focus,.el-menu-item:hover{
  background-color: rgba(82,186,181,.38) !important;
}

</style>
