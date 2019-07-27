app.controller('payController', function ($scope, $location, payService) {
    $scope.createNative = function () {
        payService.createNative().success(
            function (response) {
                $scope.money = (response.total_fee / 100).toFixed(2);
                $scope.out_trade_no = response.out_trade_no;

                let qr = new QRious({
                    element: document.getElementById('qrious'),
                    size: 250,
                    lever: 'H',
                    value: response.code_url
                });
                queryPayStatus();
            }
        );
    };

    let queryPayStatus = function () {
        payService.queryPayStatus($scope.out_trade_no).success(
            function (response) {
                if (response.success) {
                    location.href = "paysuccess.html#?money=" + $scope.money;
                } else {
                    if(response.message==='二维码超时'){
                        $scope.createNative();
                    }else{
                        location.href="payfail.html";
                    }
                }
            }
        )
    };
    $scope.getMoney = function () {
        return $location.search()['money'];
    }

});