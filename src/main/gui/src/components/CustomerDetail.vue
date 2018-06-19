<template>
  <div class="customer-detail container">
    <Spin size="large" fix v-show="loading || saving"></Spin>
    <h1 class="page-header">{{user.id}} - {{name}}</h1>
    <Form ref="user" :model="user" :rules="formRules" style="width: 500px" :label-width="80" @submit.prevent="addUser">
      <FormItem label="姓名" prop="name">
        <Input :readonly="!editable" v-model="user.name"/>
      </FormItem>
      <FormItem label="电话" prop="phone">
        <Input :readonly="!editable" v-model="user.phone"/>
      </FormItem>
      <FormItem label="邮箱" prop="email">
        <Input :readonly="!editable" v-model="user.email"/>
      </FormItem>
      <FormItem label="年龄" prop="age">
        <InputNumber :readonly="!editable" v-model="user.age"/>
      </FormItem>
      <FormItem label="学历" prop="level">
        <Input :readonly="!editable" v-model="user.level"/>
      </FormItem>
      <FormItem label="毕业院校" prop="school">
        <Input :readonly="!editable" v-model="user.school"/>
      </FormItem>
      <FormItem label="职业" prop="position">
        <Input :readonly="!editable" v-model="user.position"/>
      </FormItem>
      <FormItem label="公司" prop="companyId">
        <Select :readonly="!editable" v-model="user.companyId">
          <Option v-for="company in companies" :value="company.id" :key="company.id">{{ company.name }}</Option>
        </Select>
      </FormItem>
      <FormItem label="个人简介" prop="desc">
        <Input :readonly="!editable" type="textarea" v-model="user.desc"/>
      </FormItem>
      <Button type="primary" @click="edit" v-show="!editable">编辑</Button>
      <Button type="primary" @click="save" v-show="editable">保存</Button>
      <Button type="error" @click="unEdit" v-show="editable">取消</Button>
    </Form>
  </div>
</template>

<script>
  export default {
    name: "CustomerDetail",
    data() {
      return {
        loading: false,
        user: {},
        name: '',
        companies: [],
        editable: false,
        formRules: {
          name: [
            {required: true, message: "姓名不能为空", trigger: 'blur'}
          ],
          email: [
            {required: true, message: "邮箱不能为空", trigger: 'blur'}
          ],
          age: [
            {type: 'number', required: true, message: "年龄不正确", trigger: 'blur'}
          ],
          companyId: [
            {type: 'number', required: true, message: "请选择一个公司", trigger: 'change'},
          ]
        },
        saving: false
      }
    },

    methods: {
      getCustomer(id) {
        this.loading = true;
        this.$http.get("/api/user/" + id).then(data => {
          this.loading = false;
          this.user = data.body;
          this.name = this.user.name;
        })
      },
      getAllCompanies() {
        this.$http.get("/api/companies").then(data => {
          this.companies = data.body;
        })
      },
      edit() {
        this.editable = true;
      },
      unEdit() {
        this.editable = false;
      },
      save() {
        this.saving = true;
        this.$refs.user.validate((valid) => {
          if (valid) {
            this.$http.put("/api/user/" + this.user.id, this.user).then(data => {
              this.saving = false;
              if(data.statusText === 'OK') {
                this.$Message.success("修改用户信息成功");
              }
            })
          }
        })
      }
    },
    created() {
      this.getAllCompanies();
    },
    mounted() {
      this.getCustomer(this.$route.params.id);
    }
  }
</script>

<style scoped>

</style>
