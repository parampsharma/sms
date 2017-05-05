(function () {
    'use strict';

    angular
        .module('smsApp')
        .factory('Sales', Sales);

    Sales.$inject = ['$resource'];

    function Sales ($resource) {
        var service = $resource('api/sales/:id', {}, {
            'query': {method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'save': { method:'POST' },
            'update': { method:'PUT' },
            'delete':{ method:'DELETE'}
            
        });

        return service;
    }
    
})();





