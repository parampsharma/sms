(function() {
    'use strict';

    angular
        .module('smsApp')
        .controller('SalesManagementNewController',SalesManagementNewController);

    SalesManagementNewController.$inject = ['$stateParams', 'entity', 'Sales', 'JhiLanguageService','$state','toastr','$http'];

    function SalesManagementNewController ($stateParams, entity, Sales, JhiLanguageService,$state,toastr,$http) {
        var vm = this;

        vm.clear = clear;
        vm.languages = null;
        vm.save = save;
        vm.site = entity;
        
       
        JhiLanguageService.getAll().then(function (languages) {
            vm.languages = languages;
            
        });

        function clear () {
        	$state.go('sales-management');
        }

        function onSaveSuccess (result,headers) {
            vm.isSaving = false;
            $state.go('sales-management');
            //toastr.success("Site: " +vm.site.siteName+ " save successfully ");
            vm.headerMsg = headers('responseHeaderMessage');
            toastr.success(vm.headerMsg);
            console.log(vm.headerMsg);
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        
        function save () {
            vm.isSaving = true;
            Sales.save(vm.site, onSaveSuccess, onSaveError);
            }
        
        
    }
})();
