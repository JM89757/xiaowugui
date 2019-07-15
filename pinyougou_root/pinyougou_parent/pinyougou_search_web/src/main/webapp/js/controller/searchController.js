app.controller('searchController', function ($scope, $location, searchService) {

    $scope.searchMap = {
        'keywords': '',
        'category': '',
        'brand': '',
        'spec': {},
        'price': '',
        'pageNo': 1,
        'pageSize': 40,
        'sort': '',
        'sortField': ''
    };

    $scope.loadKeywords = function () {
        // $scope.searchMap.keywords = $location.search()['keywords'];
        $scope.searchMap.keywords = $location.search().keywords;
        $scope.search();
    };


    $scope.keywordsIsBrand = function () {
        for (var i = 0; i < $scope.resultMap.brandList.length; i++) {
            if ($scope.searchMap.keywords.indexOf($scope.resultMap.brandList[i].text) >= 0) {
                return true;
            }
        }
        return false;
    };


    /*   $scope.equal = function (pageNo) {
           return $scope.searchMap.pageNo === pageNo;
       };*/


    /*
        $scope.isTopPage = function () {
            return $scope.searchMap.pageNo === 1;
        };

        $scope.isEndPage = function () {
            return $scope.searchMap.pageNo === $scope.resultMap.totalPages;
        };
    */

    $scope.sortSearch = function (sortField, sort) {
        $scope.searchMap.sortField = sortField;
        $scope.searchMap.sort = sort;
        $scope.search();
    };

    $scope.queryByPage = function (pageNo) {
        if (pageNo < 1 || pageNo > $scope.resultMap.totalPages) {
            return;
        }
        $scope.searchMap.pageNo = pageNo;
        $scope.search();
    };


    //搜索
    $scope.search = function () {
        $scope.searchMap.pageNo = parseInt($scope.searchMap.pageNo);
        searchService.search($scope.searchMap).success(
            function (response) {
                $scope.resultMap = response;
                buildPageLabel();
            }
        );
    };

    /*let buildPageLabel = function () {
        $scope.pageLabel = [];
        let maxPageNo = $scope.resultMap.totalPages;
        let pageNo = $scope.resultMap.pageNo;
        let pageNo1 = $scope.searchMap.pageNo;
        let firstPage = 1;
        let lastPage = maxPageNo;
        // let lastPage = $scope.resultMap.totalPages;
        $scope.firstDot = true;
        $scope.lastDot = true;
        if (maxPageNo > 5) {
            if (pageNo <= 3) {
                lastPage = 5;
                $scope.firstDot = false;
            } else if (pageNo >= maxPageNo - 2) {
                firstPage = maxPageNo - 4;
                $scope.lastDot = false;
            } else {
                firstPage = pageNo1 - 2;
                lastPage = pageNo1 + 2;
            }
        } else {
            $scope.firstDot = false;
            $scope.lastDot = false;
        }
        for (let i = firstPage; i <= lastPage; i++) {
            $scope.pageLabel.push(i);
        }
    };*/


    let buildPageLabel = function () {
        //构建分页栏
        $scope.pageLabel = [];
        let firstPage = 1;//开始页码
        let lastPage = $scope.resultMap.totalPages;//截止页码
        $scope.firstDot = true;//前面有点
        $scope.lastDot = true;//后边有点

        if ($scope.resultMap.totalPages > 5) {  //如果页码数量大于5

            if ($scope.searchMap.pageNo <= 3) {//如果当前页码小于等于3 ，显示前5页
                lastPage = 5;
                $scope.firstDot = false;//前面没点
            } else if ($scope.searchMap.pageNo >= $scope.resultMap.totalPages - 2) {//显示后5页
                firstPage = $scope.resultMap.totalPages - 4;
                $scope.lastDot = false;//后边没点
            } else {  //显示以当前页为中心的5页
                firstPage = $scope.searchMap.pageNo - 2;
                lastPage = $scope.searchMap.pageNo + 2;
            }
        } else {
            $scope.firstDot = false;//前面无点
            $scope.lastDot = false;//后边无点
        }


        //构建页码
        for (let i = firstPage; i <= lastPage; i++) {
            $scope.pageLabel.push(i);
        }
    };


    $scope.addSearchItem = function (key, value) {

        if (key === 'category' || key === 'brand' || key === 'price') {
            $scope.searchMap[key] = value;

        } else {
            $scope.searchMap.spec[key] = value;
        }
        $scope.search();
    };

    $scope.removeSearchItem = function (key) {
        if (key === 'category' || key === 'brand' || key === 'price') {
            $scope.searchMap[key] = "";
        } else {
            delete $scope.searchMap.spec[key];
        }
        $scope.search();
    };


});