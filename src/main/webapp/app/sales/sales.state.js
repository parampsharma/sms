(function() {
    'use strict';

    angular
        .module('smsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('sales-management', {
            parent: 'app',
            url: '/',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/sales/sales.html',
                    controller: 'SalesController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('home');
                    return $translate.refresh();
                }]
            }
        })
        
    	.state('sales-management.new', {
			parent: 'sales-management',
			url: '/new',
			data: {
				authorities: [],
			},
			views: {
				'content@': {
					templateUrl: 'app/sales/sales-management-new.html',
					controller: 'SalesManagementNewController',
					controllerAs: 'vm',
					resolve: {
						entity: function () {
							 return {
								 productName: null,
	                               	                            };
						}
					}
				}
			
			
			
			
			},
			resolve: {
				translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
					$translatePartialLoader.addPart('home');
					return $translate.refresh();
				}]
			}
		});
        
        
        
    }
})();
