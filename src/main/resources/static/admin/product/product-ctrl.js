app.controller("product-ctrl", function($rootScope,$scope, $http) {

    $scope.items = [];
    $scope.form = {};
    $scope.cates = [];
    $scope.subCates =[];
    $scope.prodCates= [];
    $scope.cat ={};


    $scope.initialize = function() {
        // load categories
        $http.get("/rest/categories/parent").then(resp => {
            $scope.cates = resp.data;
        });


    }

    // load product from category id
    $scope.initProductList = function(){
        let cate = $scope.form.category;
        console.log('category id: ',cate.id.id);
        $http.get(`/rest/products/${cate.id.id}`).then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.createDate = new Date(item.createDate);
            })
            console.log('productList:',$scope.items);
        });

        document.getElementById("product-list").style.display = "block";
    }

    // load sub category
    $scope.initCategoriesList = function (){
        let cate = $scope.form.category.category.category;
        console.log('cate: ',cate);
        console.log('sub category id: ',cate.id);
        $http.get(`/rest/categories/sub/${cate.id}`).then(resp => {
            $scope.subCates = resp.data;
        });
        document.getElementById("subCates").removeAttribute("disabled");
    }

    //load product category
    $scope.initProdCategories = function (){
        let subCate = $scope.form.category.category;
        console.log('sub category id: ',subCate.id);
        $http.get(`/rest/categories/sub/${subCate.id}`).then(resp => {
            $scope.prodCates = resp.data;
        });
        document.getElementById("prodCates").removeAttribute("disabled");
    }


    // Init
     $scope.initialize();

    //Reset form
    $scope.reset = function() {
        $scope.form = {
            createDate: new Date(),
            image: 'cloud-upload.jpg',
            available: true
        }
    }

    $scope.catParent = 0;
    $scope.catSub = 0;
    $scope.form.image = '';
    //Edit form
    $scope.edit = function(item) {
         $scope.form = angular.copy(item);
         $scope.form.image = $scope.form.photos[0].name;
         document.getElementById("editt").click();
         $scope.initCategoriesList();
         $scope.initProdCategories();
         $("#parentCates").attr("disabled",true);
         $("#subCates").attr("disabled",true);
         // $("#create").attr("disabled",true);
         document.getElementById("prodCates").removeAttribute("disabled");
    }

    //Create new product
    $scope.create = function() {
        var item = angular.copy($scope.form);
        $http.post(`/rest/products`, item).then(resp => {
            resp.data.createDate = new Date(resp.data.createDate);
            $scope.items.push(resp.data);
            $rootScope.tempProductId = resp.data.id;//
            $scope.reset();
            alert('New product was created successfully!');
            location.reload();
        }).catch(error => {
            alert('Error when creating new product!');
            console.log('Error', error);
        })
    }

    //Update product
    $scope.update = function() {
        let item = angular.copy($scope.form);
        $http.put(`/rest/products/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items[index] = item;
            alert('Product was updated successfully!');
        }).catch(error => {
            alert('Error when updating this product!');
            console.log('Error', error);
        })
    }

    //Delete product
    $scope.delete = function(item) {
        $http.delete(`/rest/products/${item.id}`).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items.splice(index, 1);
            $scope.reset();
            $scope.initialize();
            alert('Product was deleted successfully!');
        }).catch(error => {
            alert('Error when deleting this product!');
            console.log('Error', error);
        })
    }

    //Upload image
    $scope.imageChanged = function(files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.form.image = resp.data.name;
            alert('Change image successfully!');
        }).catch(error => {
            alert('Error when uploading image');
            console.log('Error', error);
        })

    }

    $scope.viewImage = function(item){
        $rootScope.tempProductId = item.id;
        console.log($rootScope.tempProductId);
        $http.get(`/rest/photos/prd/${item.id}`).then(resp => {
            $rootScope.photos = resp.data;
            console.log($scope.photos);
        }).catch(error => {
            alert('Error when collect images for this product!');
            console.log('Error', error);
        })
        window.location.href="#!image";
    }

    $scope.pager = {
        page: 0,
        size: 10,
        get items() {
            var start = this.page * this.size;
            return $scope.items.slice(start, start + this.size)
        },
        get count() {
            return Math.ceil(1.0 * $scope.items.length / this.size);
        },
        first() {
            this.page = 0;
        },
        prev() {
            this.page--;
            if (this.page < 0) {
                this.last();
            }
        },
        next() {
            this.page++;
            if (this.page >= this.count) {
                this.first();
            }
        },
        last() {
            this.page = this.count - 1;
        }
    }
      $scope.propertyName = 'id';
      $scope.reverse = false;

	  $scope.sortBy = function(propertyName) {
        $scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse : true;
        $scope.propertyName = propertyName;
      };

	// views
	$scope.view = function(item) {
        $scope.form = angular.copy(item);

		console.log(item);
	}
})

app.controller("image-ctrl",function ($rootScope,$scope,$http){
    $scope.photo = {};
    var url = `http://localhost:8080/rest/photos/product-image`;

    $scope.setMain = function(photo){
        photo.productId = $rootScope.tempProductId;
        console.log('productId',photo.productId);
        let tempMainPhoto = $rootScope.photos[0];
        document.getElementById("photo--"+tempMainPhoto.name).classList.remove("red-background");
        $http.post(`/rest/photos/main/${photo.id}/${photo.productId}`)
            .then(resp => {
                $scope.photo = resp.data;
                console.log("main:",$scope.photo.name);
                document.getElementById("photo--"+$scope.photo.name).classList.add("red-background");
            }).catch(error => {
            swal('Error when change main for image');
            console.log('Error', error);
        });
        swal({
            title: "Done!",
            text: "You set this image is main for this product!",
            icon: "success",
            buttons: true,
            dangerMode: true,
        })
    }

    $scope.url = function(filename) {
        return `${url}/${filename}`;
    }

    $scope.upload = function(files) {
        var form = new FormData();
        for (var i = 0; i < files.length; i++) {
            form.append("files", files[i]);
        }
        $http.post(url+`/${$rootScope.tempProductId}`, form, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            let value = resp.data;
            console.log("value name: ",value);
            $rootScope.photos.push(...resp.data);
            swal({
                title: "Done!",
                text: "You have added photos successfully!",
                icon: "success",
                buttons: true,
                dangerMode: true,
            })
        }).catch(error => {
            swal({
                title: "Error!",
                text: "Error" + error,
                icon: "error",
                buttons: true,
                dangerMode: true,
            })
        })
    };


    $scope.delete = function(photoId) {
        $http.delete(`/rest/photos/${photoId}`).then(resp => {
            console.log('photo id: ',photoId);
            let photoItem = $rootScope.photos.find(photo => photo.id === photoId);
            document.getElementById("photo--"+photoItem.name).classList.add("item-disabled");
            document.getElementById("mainButton--"+photoItem.name).classList.add("button-hidden");
            document.getElementById("mainButton--"+photoItem.name).classList.remove("button-hide");
            document.getElementById("deleteButton--"+photoItem.name).classList.add("button-hidden");
            document.getElementById("deleteButton--"+photoItem.name).classList.remove("button-hide");
            document.getElementById("activeButton--"+photoItem.name).classList.add("button-hide");

            swal({
                title: "Done!",
                text: "You have set this image disabled!",
                icon: "success",
                buttons: true,
                dangerMode: true,
            })
        }).catch(error => {
            swal({
                title: "Error!",
                text: "Error" + error,
                icon: "error",
                buttons: true,
                dangerMode: true,
            })
        })
    }


    $scope.active = function(photoId){
        $http.put(`/rest/photos/${photoId}`).then(resp => {
            console.log('re-active photo id: ',photoId);
            let photoItem = $rootScope.photos.find(photo => photo.id === photoId);
            document.getElementById("photo--"+photoItem.name).classList.remove("item-disabled");
            document.getElementById("mainButton--"+photoItem.name).classList.add("button-hide");
            document.getElementById("mainButton--"+photoItem.name).classList.remove("button-hidden");
            document.getElementById("deleteButton--"+photoItem.name).classList.add("button-hide");
            document.getElementById("deleteButton--"+photoItem.name).classList.remove("button-hidden");
            document.getElementById("activeButton--"+photoItem.name).classList.add("button-hidden");
            document.getElementById("activeButton--"+photoItem.name).classList.remove("button-hide");

            swal({
                title: "Done!",
                text: "You have set this image active!",
                icon: "success",
                buttons: true,
                dangerMode: true,
            })
        }).catch(error => {
            swal({
                title: "Error!",
                text: "Error" + error,
                icon: "error",
                buttons: true,
                dangerMode: true,
            })
        })
    }
})