app = angular.module("app", [])
app.controller("authorize-ctrl", function($scope, $http){
    $scope.roles = [];
    $scope.admins = [];
    $scope.authorities = [];

    $scope.initialize = function(){
        $http.get("/rest/relos").then(resp => {
            $scope.roles = resp.data;
        })
        $http.get("/rest/accounts?admin=true").then(resp => {
            $scope.admins = resp.data;
        })
        $http.get("/rest/authorities?admin=true").then(resp => {
            $scope.authorities = resp.data;
        })
    }
    $scope.authority_of = function(acc, role){
        if($scope.authorities){
            return $scope.authorities.find(ur => ur.account.username = acc.username
                    && ur.role.id ==  role.id);
        }
    }
    $scope.authority_changed = function(acc, role){
        var authority = $scope.authority_of(acc, role);
        if(authority){

        }else{
            authority = {account: acc, role: role};

        }
    }
    $scope.grant_authority = function(authority){
        $http.post(`/rest/authorities`, authority).then(resp => {
            $scope.authorities.pust(resp.data)
            alert('Cấp quyền thành công!')
        }).catch(error => {
            alert('Cấp quyền thất bại');
            console.log("Error", error);
        })
    }

    $scope.revoke_authority = function(authority){
        $http.delete(`/rest/authorities/${authority.id}`).then(resp => {
            var index = $scope.authorities.findIndex(a => a.id == authority.id);
            $scope.authorities.splice(index, 1);
            alert('Xóa quyền thành công');
        }).catch(error => {
            alert('Xóa quyên thất bại');
            console.log("Error", error);
        })
    }
    $scope.initialize();
});