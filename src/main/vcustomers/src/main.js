// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import VueRouter from "vue-router"
import Customers from "./components/Customers"
import About from "./components/About"
import iView from 'iview'; // 导入组件库
import 'iview/dist/styles/iview.css'; // 导入样式
import VueResource from 'vue-resource'
import AddUser from "./components/AddUser"
import CustomerDetail from "./components/CustomerDetail"

Vue.use(VueRouter)
Vue.use(iView)
Vue.use(VueResource)
Vue.config.productionTip = false

const router = new VueRouter({
  mode: "history",
  base: __dirname,
  routes: [
    {path: "/", component: Customers},
    {path: "/about", component: About},
    {path: "/addUser", component: AddUser},
    {path: "/customer/:id", component: CustomerDetail}
  ]
})
/* eslint-disable no-new */
new Vue({
  router,
  el: '#app',
  template: `
  <div>
         <nav class="navbar navbar-default">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
           <a class="navbar-brand" href="#">用户管理系统</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><router-link to="/">主页</router-link></li>
              <li><router-link to="/about">关于</router-link></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><router-link to="/addUser">添加用户</router-link></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    <router-view></router-view>
</div>
  `
})
