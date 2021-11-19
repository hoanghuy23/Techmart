app = angular.module("site-app", []);
app.controller("register-ctrl", function ($scope, $http){
    $scope.userDetail = {};
    $scope.account = {};
    $scope.authority = {};
    const iconLoading = document.getElementById("loading");

    const showLoading = () =>{
        iconLoading.style.display = "flex"; 
    }
    const hideLoading = () => {
        iconLoading.style.display = "none";
    }
    $scope.register = async function (){
        if($scope.userDetail.length != 0 && $scope.account.length != 0
            && $scope.account.password == $("#comfirm_password").val()){
            showLoading();
            let udData = {};
            let acData = {};
            let acUser = {};
            await $http.get(`/rest/userdetails/email/${$scope.userDetail.email}`).then(resp => {
                udData = resp.data;
            })
            let account = angular.copy($scope.account);
            if(udData.length != 0){
                await  $http.get(`/rest/accounts/info/${udData.id}`).then(resp => {
                    acData = resp.data;
                })
                if(acData.length != 0){
                    swal("","Email này đã được sử dụng","warning")
                    hideLoading();
                    return false;
                }
                $scope.account = {
                    username: account.username,
                    password: account.password,
                    status: false,
                    info: udData
                }
            }else{
                await $http.post(`/rest/userdetails`, $scope.userDetail).then(resp => {
                    $scope.account = {
                        username: account.username,
                        password: account.password,
                        status: false,
                        info: resp.data
                    }
                })
            }
            await  $http.get(`/rest/accounts/username/${$scope.account.username}`).then(resp => {
                acUser = resp.data;
            })
            if(acUser.length != 0){
                swal("","Username này đã tồn tại","warning")
                hideLoading();
                return false;
            }
            $scope.authority = {
                account: account,
                role: {id: "USER"}
            }
            await $http.post(`/rest/accounts`, $scope.account);
            await $http.post(`/rest/authorities`, $scope.authority);
            swal("","Đăng ký tài khoản thành công vui lòng kiểm tra email để kích hoạt tài khoản","success")
            hideLoading();
        }

    }
});