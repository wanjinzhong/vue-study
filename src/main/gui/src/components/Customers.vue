<template>
  <div class="customers container">
    <h1 class="page-header">用户管理系统</h1>
    <Table :loading="loading" stripe :columns="userCol" :data="userData"></Table>
  </div>
</template>

<script>
  import Input from "iview/src/components/input/input"

  export default {
    name: "Customers",
    components: {Input},
    data() {
      return {
        userCol: [
          {
            title: "姓名",
            key: "name"
          },
          {
            title: "电话",
            key: "phone"
          },
          {
            title: "邮箱",
            key: "email"
          },
          {
            title: "操作",
            key: 'actions',
            render: (h, params) => {
              return h('div',[
                h('router-link', {
                  props: {
                    type: 'primary',
                    size: 'small',
                    to: "/customer/" + params.row.id
                  }
                  // on: {
                  //   click: () => {
                  //     this.$router.push("/customer/" + params.row.id);
                  //   }
                  // }
                }, '查看')
                // h('Button', {
                //   props: {
                //     type: 'text',
                //     size: 'small',
                //   }
                // })
              ]);
            }
          }
        ],
        userData: [
        ],
        loading: true
      }
    },
    methods: {
      getCustomers() {
        this.$http.get("/api/users").then(data => {
          this.loading = false;
          this.userData = data.body
        })
      }
    },
    mounted() {
      this.getCustomers();
    }
  }
</script>

<style scoped>

</style>
