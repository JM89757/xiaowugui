app.controller('cartController', function ($scope, cartService) {

    $scope.del = function () {
        if (confirm('Are you sure?')) {
            cartService.del($scope.address.id).success(
                function (data) {
                    if (data.success) {
                        alert(data.message);
                        location.href = "http://localhost:9107/getOrderInfo.html";
                    } else {
                        alert(data.message);
                    }
                }
            );
        }
    };


    $scope.saveAddressList = function () {
        var object = null;
        if ($scope.address.id != null) {
            object = cartService.update($scope.address);
        } else {
            object = cartService.add($scope.address);
        }
        object.success(
            function (data) {
                if (data.success) {
                    alert(data.message);
                    location.href = "http://localhost:9107/getOrderInfo.html";
                } else {
                    alert(data.message);
                }
            }
        );
    };

    /*    $scope.showTooltip = function () {
            $("[data-toggle=tooltip]").tooltip("show");
        };
        $scope.hideTooltip = function () {
            $("[data-toggle=tooltip]").tooltip("hide");
        };*/


    $scope.submitOrder = function () {
        $scope.order.receiverAreaName = $scope.address.address;
        $scope.order.receiverMobile = $scope.address.mobile;
        $scope.order.receiver = $scope.address.contact;
        cartService.submitOrder($scope.order).success(
            function (response) {
                if (response.success) {

                    if ($scope.order.paymentType === '1') {
                        location.href = "pay.html";
                    } else {
                        location.href = "paysuccess.html";
                    }

                } else {
                    alert(response.message);
                }
            }
        )

    };


    $scope.order = {paymentType: '1'};

    $scope.selectPayType = function (type) {
        $scope.order.paymentType = type;
    };


    $scope.selectAddress = function (address) {
        $scope.address = address;
    };

    $scope.isSelectAddress = function (address) {
        return address === $scope.address
    };


    $scope.findAddressList = function () {
        cartService.findAddressList().success(
            function (response) {
                $scope.addressList = response;
                for (let i = 0; i < $scope.addressList.length; i++) {
                    if ($scope.addressList[i].isDefault === '1') {
                        $scope.address = $scope.addressList[i];
                        break;
                    }
                }
            });
    };


    $scope.findCartList = function () {
        cartService.findCartList().success(
            function (response) {
                $scope.cartList = response;
                $scope.totalValue = cartService.sum($scope.cartList);
            }
        );
    };

    $scope.addGoodsToCartList = function (itemId, num) {
        cartService.addGoodsToCartList(itemId, num).success(
            function (response) {
                if (response.success) {
                    $scope.findCartList();
                } else {
                    alert(response.message);
                }
            }
        );
    };

    $scope.showName = function () {
        cartService.showName().success(
            function (response) {
                $scope.loginName = response.loginName;
            }
        );
    };


    /*
        $scope.checkName = function () {
            return loginName !== "anonymousUser";
        };
    */

    // $scope.inCheckName = function () {
    //     return !checkName();
    // };
});