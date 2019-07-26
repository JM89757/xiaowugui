app.controller('payController', function ($scope, payService) {
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

            }
        )
    }
});