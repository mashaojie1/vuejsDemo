new Vue({
    el: '#app',
    data: {
        userList: [], //用于列表迭代
        user: {}
    },
    methods:{
        getList: function () {
            //查询
            var url = 'http://localhost:8080/vuejsDemo/user/findAll.do'
            //中转this
            var _this = this
            //调用axios的get请求
            axios.get(url).then(function (res) {
                console.log(res.data)
                //赋值
                _this.userList = res.data
            })
        },
        findById:function(id){
            //alert(id)
            //请求根据id获取用户信息
            //查询
            //var url = 'http://localhost:8080/vuejsDemo/user/findById.do?id='+id
            var url = 'http://localhost:8080/vuejsDemo/user/findById.do'
            //中转this
            var _this = this
            //调用axios的get请求
            axios.get(url,{
                params: {
                    id: id
                }
            }).then(function (res) {
                console.log(res.data)
                //给model赋值
                _this.user = res.data
                //弹窗
                $('#myModal').modal('show')
            })
        },
        update:function () {
            //alert(JSON.stringify(this.user))
            //后台的参数就是user对象
            var url = 'http://localhost:8080/vuejsDemo/user/update.do'
            //中转this
            var _this = this
            //调用axios的get请求
            axios.post(url,_this.user).then(function (res) {
                //提醒更新成功
                alert('更新成功')
                //刷新列表
                _this.getList()
            })
        }
    },
    created: function () {
        //调用列表方法
        this.getList()
    }
})