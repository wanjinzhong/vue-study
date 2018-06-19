<template>
  <div class="addUser container">
    <h1 class="page-header">添加用户</h1>
    <Form ref="user" :model="user" :rules="formRules" style="width: 500px" :label-width="80" @submit.prevent="addUser">
      <FormItem label="姓名" prop="name">
        <Input v-model="user.name"/>
      </FormItem>
      <FormItem label="电话" prop="phone">
        <Input v-model="user.phone"/>
      </FormItem>
      <FormItem label="邮箱" prop="email">
        <Input v-model="user.email"/>
      </FormItem>
      <FormItem label="年龄" prop="age">
        <InputNumber v-model="user.age" />
      </FormItem>
      <FormItem label="学历" prop="level">
        <Input v-model="user.level"/>
      </FormItem>
      <FormItem label="毕业院校" prop="school">
        <Input v-model="user.school"/>
      </FormItem>
      <FormItem label="职业" prop="position">
        <Input v-model="user.position"/>
      </FormItem>
      <FormItem label="公司" prop="companyId">
        <Select v-model="user.companyId">
          <Option v-for="company in companies" :value="company.id" :key="company.id">{{ company.name }}</Option>
        </Select>
      </FormItem>
      <FormItem label="个人简介" prop="desc">
        <Input type="textarea" v-model="user.desc"/>
      </FormItem>
      <Spin size="large" fix v-show="saving"></Spin>
      <Button type="primary" @click="addUser">添加</Button>
    </Form>
  </div>
</template>

<script>
  import Form from "iview/src/components/form"

  export default {
    name: "AddUser",
    components: {Form},
    data() {
      return {
        companies: [],
        user: {
          name:'',
          phone:'',
          email:'',
          age:0,
          companyId:1,
          level: '',
          school: '',
          position: '',
          desc: ''
        },
        formRules: {
          name: [
            {required: true, message: "姓名不能为空", trigger: 'blur'}
          ],
          email: [
            {required: true, message: "邮箱不能为空", trigger: 'blur'}
          ],
          age: [
            {type: 'number',required: true, message: "年龄不正确", trigger: 'blur'}
          ],
          companyId: [
            {type: 'number', required: true, message: "请选择一个公司", trigger: 'change'},
          ]
        },
        saving: false
      }
    },
    methods: {
      getAllCompanies() {
        this.$http.get("/api/companies").then(data => {
          this.companies = data.body;
        })
      },
      addUser() {
        var self = this;
        this.$refs.user.validate((valid) => {
          if (valid) {
            this.saving = true;
            this.$http.post("/api/user", this.user).then(data => {
              if (data.statusText === 'OK') {
                this.$Message.success({
                  content: "添加用户成功",
                  duration: 1.5,
                  onClose: function () {
                    self.$router.push("/");
                  }
                });
              }
            });
          }
        });
      }
    },
    created() {
      this.getAllCompanies();
    }
  }
</script>

<style scoped>
  FormItem{
    width: 400px;
  }
</style>
