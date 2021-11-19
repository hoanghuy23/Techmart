app.controller("account-ctrl", function($scope, $http) {
	$scope.userDetail = {};
	$scope.account = {};
	$scope.authority = {};
	$scope.items = [];

	$scope.initialize = function() {
		$http.get("/rest/accounts").then(resp => {
			$scope.items = resp.data;
		})
	}
	$scope.initialize();

	$scope.edit = function(acc, ud) {
		$scope.account = angular.copy(acc);
		$scope.userDetail = angular.copy(ud);
		if ($scope.disabledField == true)
			$scope.disabledField = false;
		else
			$scope.disabledField = true;
	}

	$scope.reset = function() {
		$scope.account = {};
		$scope.userDetail = {};
		if ($scope.disabledField == false)
			$scope.disabledField = true;
		else
			$scope.disabledField = false;
	}

	$scope.create = async function() {
		if ($scope.account.length != 0 && $scope.userDetail.length != 0) {
			let udData = {}
			let acData = {}
			let acUser = {}
			await $http.get(`/rest/userdetails/email/${$scope.userDetail.email}`).then(resp => {
				udData = resp.data;
			})
			let account = angular.copy($scope.account);

			if (udData.length != 0) {
				await $http.get(`/rest/accounts/info/${udData.id}`).then(resp => {
					acData = resp.data;
				})
				if (acData.length != 0) {
					swal("", "Email đã tồn tại","warning");
					return false;
				}
				$scope.account = {
					username: account.username,
					passowrd: account.password,
					status: true,
					info: udData
				}
			} else {
				await $http.post(`/rest/userdetails`, $scope.userDetail).then(resp => {
					$scope.account = {
						username: account.username,
						password: account.password,
						status: true,
						info: resp.data
					}
				})
			}
			await $http.get(`/rest/accounts/username/${$scope.account.username}`).then(resp => {
				acUser = resp.data;
			})
			if (acUser.length != 0) {
				swal("", "Account đã tồn tại","warning");
				return false;
			}
			$scope.authority = {
				account: account,
				role: { id: "USER" }
			}
			await $http.post(`/rest/accounts/admin`, $scope.account);
			await $http.post(`/rest/authorities`, $scope.authority);
			swal("", "Tạo tài khoản thành công","success");
			$scope.initialize();
			$scope.reset();
		}
	}
	$scope.update = async function() {
		var userDetail = angular.copy($scope.userDetail);
		var account = angular.copy($scope.account);
		await $http.put(`/rest/userdetails/${userDetail.id}`, userDetail).then(resp => {
			var index = $scope.items.findIndex(u => u.id == userDetail.id);
			$scope.items[index] = userDetail;
		})
		await $http.put(`/rest/accounts/${account.username}`, account).then(resp => {
			var index = $scope.items.findIndex(a => a.username == account.username);
			$scope.items[index] = account;
		})
		swal("","Cập nhật thành công!","success")
		$scope.initialize();
	}
	$scope.delete = function(item) {		
        swal({
            title: "Are you sure ?",
            text: "User will be removed from the list",
            type:"warning",
            confirmButtonText: "Delete",
            closeOnConfirm: false,
            confirmButtonClass: "btn-danger",
            showCancelButton: true            
        },function(){
            $http.delete(`/rest/userdetails/${item.id}`).then(resp => {
                var index = $scope.items.findIndex(u => u.id == item.id);
                $scope.items.splice(index, 1);
                $scope.reset();
                $scope.initialize();
            })
            swal("","Xóa người dùng thành công!","success");
        }) ;
	}    
    $scope.UpStatus = function(item){           
        swal({
            title: "Are you sure ?",
            text: "You want to change this user's status",
            type:"warning",
            confirmButtonText: "Changed",
            closeOnConfirm: false,
            confirmButtonClass: "btn-danger",
            showCancelButton: true            
        },function(){
        	$http.put(`/rest/userdetails/${item.info.id}`, item.info).then(resp => {
                var index = $scope.items.findIndex(u => u.id == item.info.id);
                $scope.items[index] = item.info;
		    })
		    $http.put(`/rest/accounts/${item.username}`, item).then(resp => {
                var index = $scope.items.findIndex(a => a.username == item.username);
                $scope.items[index] = item;             
		     })	
            swal("","You want to change this user's status!","success");	
        }) ;   
		$scope.initialize();
    }
})