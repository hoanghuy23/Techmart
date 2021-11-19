app.controller('category-ctrl', function ($scope, $http) {
    $scope.categories = [];
    $scope.cates = [];
    $scope.childCates = [];
    $scope.form = {};
    $scope.treetype = 'P';

    //load categories list
    $scope.initialize = function(){
        $scope.categories = [];
        $http.get(`/rest/categories`).then(resp => {
            resp.data.forEach(e => {
                $scope.categories.push({
                    "id": e.id,
                    "parent_id": e.category == null ? null : e.category.id,
                    "name": e.name,
                    "status": e.status
                });
            });
            var myTreeData = getTree($scope.categories, 'id', 'parent_id');
            $scope.tree_data = myTreeData;
        });
    }
    //load parent category
    $scope.initParent = function (){
        $http.get("/rest/categories/parent").then(resp => {
            $scope.cates = resp.data;
        });
    }
    //load child category
    $scope.initChild = function (){
        if($scope.treetype == 'S'){
            let cate = $scope.form.category.category;
            $http.get(`/rest/categories/sub/${cate.id}`).then(resp => {
                $scope.childCates = resp.data;
            });
        }
    }
    //set level of category
    $scope.setTreeType = function (type){
        $scope.treetype = type;
        if(type == 'P'){
            $("#parentC").attr("disabled",true);
            $("#child").attr("disabled",true);
            $scope.cates = [];
            $scope.form = {};
        }else if(type == 'C'){
            document.getElementById("parentC").removeAttribute("disabled");
            $("#child").attr("disabled",true);
            $scope.initParent();
            $scope.childCates = [];
        }else{
            document.getElementById("parentC").removeAttribute("disabled");
            document.getElementById("child").removeAttribute("disabled");
            $scope.initParent();
            if($scope.form.category !== undefined && $scope.form.category !== null
                && $scope.form.category.category !== null && $scope.form.category.category !== undefined){
                $scope.initChild();
            }
        }

    }

    $scope.initialize();
    //function edit
    $scope.edit = async function (item){
        await $http.get(`/rest/categories/${item.id}`).then(resp => {
            if(resp.data.category === null){
                $('input:radio[name=group1][value=P]').click();
            }else if(resp.data.category.category === null){
                $('input:radio[name=group1][value=C]').click();
            }else{
                $('input:radio[name=group1][value=S]').click();
                $scope.form = resp.data;
                $scope.initChild()
            }
            $scope.form = resp.data;
        })
        document.getElementById("editt").click();
    }
    //function delete on table
    $scope.onDelete = async function (item){
        $scope.form = {
            'id': item.id,
            'name': item.name,
            'status': false,
            'category': item.category
        }
        $scope.delete();
    }

    $scope.reset = function (){
        $scope.form = {};
        $('input:radio[name=group1][value=P]').click();
    }

    $scope.create = function (){
        var item = angular.copy($scope.form);
        item.status = true;
        var category = item;
        if($scope.treetype == 'C' || $scope.treetype == "S"){
            category = {
                'name': item.name,
                'status': true,
                'category':item.category
            }
        }
        $http.post(`/rest/categories`, category).then(resp => {
            $scope.initialize();
            $scope.reset();
            alert('New category was created successfully!')
        }).catch(error => {
            alert('Error when creating new category!');
            console.log('Error', error);
        })
    }

    $scope.update = function (){
        var item = angular.copy($scope.form);
        var category = item;
        if($scope.treetype == 'C' || $scope.treetype == "S"){
            category = {
                'id': item.id,
                'name': item.name,
                'status': true,
                'category':item.category
            }
        }
        $http.put(`/rest/categories/${category.id}`, category).then(resp => {
            $scope.initialize();
            alert('Category was updated successfully!')
        }).catch(error => {
            alert('Error when updating this category!');
            console.log('Error', error);
        })
    }
    //function delete on form
    $scope.delete = function (){
        var result = confirm("Are you sure to delete?");
        if(result){
            var item = angular.copy($scope.form);
            var category = {
                'id': item.id,
                'name': item.name,
                'status': false,
                'category': null
            };
            if($scope.treetype == 'C' || $scope.treetype == "S"){
                category = {
                    'id': item.id,
                    'name': item.name,
                    'status': false,
                    'category':item.category
                }
            }
            $http.put(`/rest/categories/${category.id}`, category).then(resp => {
                $scope.initialize();
                $scope.reset();
                alert('Category was deleted successfully!')
            }).catch(error => {
                alert('Error when deleting this category!');
                console.log('Error', error);
            })
        }
        $scope.form = {};
    }
    //set data to data tree
    function getTree(data, primaryIdName, parentIdName) {
        if (!data || data.length == 0 || !primaryIdName || !parentIdName)
            return [];

        var tree = [],
            rootIds = [],
            item = data[0],
            primaryKey = item[primaryIdName],
            treeObjs = {},
            tempChildren = {},
            parentId,
            parent,
            len = data.length,
            i = 0;

        while (i < len) {
            item = data[i++];
            primaryKey = item[primaryIdName];

            if (tempChildren[primaryKey]) {
                item.children = tempChildren[primaryKey];
                delete tempChildren[primaryKey];
            }

            treeObjs[primaryKey] = item;
            parentId = item[parentIdName];

            if (parentId) {
                parent = treeObjs[parentId];

                if (!parent) {
                    var siblings = tempChildren[parentId];
                    if (siblings) {
                        siblings.push(item);
                    }
                    else {
                        tempChildren[parentId] = [item];
                    }
                }
                else if (parent.children) {
                    parent.children.push(item);
                }
                else {
                    parent.children = [item];
                }
            }
            else {
                rootIds.push(primaryKey);
            }
        }

        for (var i = 0; i < rootIds.length; i++) {
            tree.push(treeObjs[rootIds[i]]);
        };

        return tree;
    }
});