var app = angular.module('blog', [ ]);

app.service("getService",function($http,$q){
   this.getAnything = function(url){
        var deferred = $q.defer();
	$http.get(url,{header:{"Content-Type":"application/json"}}
	).then(function(data){
	    deferred.resolve(data);
	});
	    return deferred.promise;
	}
});

app.service("postService",function($http,$q){
	this.addAnything=function(url,obj){
		var deferred = $q.defer();
		$http.post(url,obj,{header:{"Content-Type":"application/json"}}
		).then(function(data){
			deferred.resolve(data);
		});
		return deferred.promise;
		}
});

app.service("putService",function($http,$q){
	this.editAnything=function(urli,obj){
		var deferred = $q.defer();
		$http({
			url:urli,
			data:obj,
			method:"PUT",
			header:{"Content-Type":"application/json","Access-Control-Allow-Origin":"*"}
		}).then(function(data){
			deferred.resolve(data);
		});
		return deferred.promise;
		}
});

app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.defaults.withCredentials = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
		
    }]);

   

app.controller('HomeController',['$scope','getService','postService','putService','$http',function($scope,getService,postService,putService,$http) {
  $scope.helloWorld = 'Aplicatii Web cu suport Java!';
  $scope.drinks=['Vodka','Scotch','Tequila','Absint'];
  
  getService.getAnything("http://localhost:8080/persoana").then(function(data){
	  $scope.persoane = data.data;
  });
  
  
  getService.getAnything("http://localhost:8080/pet").then(function(data){
	  $scope.pets = data.data;
  });
  
  getService.getAnything("http://localhost:8080/bar").then(function(data){
	  $scope.bars = data.data;
  });
  
  getService.getAnything("http://localhost:8080/produces").then(function(data){
	  $scope.produces = data.data;
  });
  
  
  $scope.editForm=function(obj,method){
	  switch(method){
		case 'persoana':
			$scope.idEdit1=obj.id;
			$scope.nameEdit1=obj.name;
		break;
	    case 'pet':
		  $scope.idEdit2=obj.id;
		  $scope.nameEdit2=obj.name;
		  $scope.speciesEdit=obj.species;
		  $scope.ageEdit=obj.age;
	    break;
		case 'bar':
		  $scope.idEdit3=obj.id;
		  $scope.nameEdit3=obj.name;
		  $scope.locationEdit=obj.location;
		  $scope.maxEdit=obj.maxNumberCustomers;
	    break;
		case 'produce':
		  $scope.idEdit4=obj.id;
		  $scope.nameEdit4=obj.name;
		  $scope.quantityEdit=obj.quantity;
	    break;
	  }
  };
  
  $scope.edit=function(obj,method){
	  switch(method){
		  case 'persoana':
			/*var url='http://localhost:8080/persoana/update';
			putService.editAnything(url,obj).then(function(data){
				console.log(url);
			});*/
			angular.forEach($scope.persoane,function(value,key){
				if(value.id == $scope.idEdit1){
					value.name=$scope.nameEdit1;
				}
			});
		  break;
		  case 'pet':
			/*var url='http://localhost:8080/pet/update';
			putService.editAnything(url,obj).then(function(data){
				console.log(url);
			});*/
			angular.forEach($scope.pets,function(value,key){
				if(value.id == $scope.idEdit2){
					value.name=$scope.nameEdit2;
					value.species=$scope.speciesEdit;
					value.age=$scope.ageEdit;
				}
			});
		  break;
		  case 'bar':
			/*var url='http://localhost:8080/bar/update';
			putService.editAnything(url,obj).then(function(data){
				console.log(url);
			});*/
			angular.forEach($scope.bars,function(value,key){
				if(value.id == $scope.idEdit3){
					value.name=$scope.nameEdit3;
					value.location=$scope.locationEdit;
					value.maxNumberCustomers=$scope.maxEdit;
					value.drinks[$scope.idDrinkEdit]=$scope.selected_drink;
				}
			});
		  break;
		  case 'produce':
		  
			/*var url='http://localhost:8080/pet/update';
			putService.editAnything(url,obj).then(function(data){
				console.log(url);
			});*/
			angular.forEach($scope.produces,function(value,key){
				if(value.id == $scope.idEdit4){
					value.name=$scope.nameEdit4;
					value.quantity=$scope.quantityEdit;
				}
			});
			
		  break;
	  }
  };
  
  $scope.change=function(){
	  $scope.bars[0].drinks[0]=$scope.selected_drink;
  }
  
  $scope.add=function(obj,method){
	  switch(method){
		  case 'persoana' :
			/*var url='http://localhost:8080/persoana/add';
			var obj1={id:parseInt($scope.persoane[$scope.persoane.length-1].id)+1,name:$scope.nameAdd2};
			postService.addAnything(url,obj1).then(function(data){
				console.log(url);
			});*/
			$scope.persoane.push({id:parseInt($scope.persoane[$scope.persoane.length-1].id)+1,name:$scope.nameAdd1});
		  break;
		  case 'pet' :
			/*var url='http://localhost:8080/pet/add';
			postService.addAnything(url,obj).then(function(data){
				console.log(url);
			});*/
			$scope.pets.push({id:parseInt($scope.pets[$scope.pets.length-1].id)+1,name:$scope.nameAdd2,species:$scope.speciesAdd2,age:$scope.ageAdd2});
		  break;
		  case 'bar' :
			/*var url='http://localhost:8080/bar/add';
			postService.addAnything(url,obj).then(function(data){
				console.log(url);
			});*/
			var drinksBar=JSON.parse(JSON.stringify($scope.drinkBar));
			$scope.bars.push({id:parseInt($scope.bars[$scope.bars.length-1].id)+1,name:$scope.nameAdd3,maxNumberCustomers:$scope.maxAdd,location:$scope.locationAdd,drinks:drinksBar});
		  break;
		   case 'produce' :
			/*var url='http://localhost:8080/pet/add';
			postService.addAnything(url,obj).then(function(data){
				console.log(url);
			});*/
			$scope.produces.push({id:parseInt($scope.produces[$scope.produces.length-1].id)+1,name:$scope.nameAdd4,quantity:$scope.quantityAdd});
		  break;
	  }
  }
  
  $scope.delete=function(obj,method){
	  switch(method){
		  case 'persoana' :
			/*var url='http://localhost:8080/persoana/delete';
				$http({
					url:'http://localhost:8080/persoana/' + obj,
					method: "DELETE"
				})
				.then(
						function successCallback(response) {
					//code goes here 
				},
				function errorCallback(response) {
				//angular.element('[data-id=' + id + ']').remove();
				});*/
			$scope.persoane.splice(obj-1,1);
		  break;
		  case 'pet' :
			/*var url='http://localhost:8080/pet/delete';
				$http.delete(url)
				.then(
						function successCallback(response) {
					code goes here 
				},
				function errorCallback(response) {
				angular.element('[data-id=' + id + ']').remove();
				});
			*/
			$scope.pets.splice(obj-1,1);
		  break;
		  case 'bar' :
			/*var url='http://localhost:8080/bar/delete';
				$http.delete(url)
				.then(
						function successCallback(response) {
					code goes here 
				},
				function errorCallback(response) {
				angular.element('[data-id=' + id + ']').remove();
				});
			*/
			$scope.bars.splice(obj-1,1);
		  break;
		  case 'produce' :
			/*var url='http://localhost:8080/pet/delete';
				$http.delete(url)
				.then(
						function successCallback(response) {
					code goes here 
				},
				function errorCallback(response) {
				angular.element('[data-id=' + id + ']').remove();
				});
			*/
			$scope.produces.splice(obj-1,1);
		  break;
	  }
  }
  
  
  $scope.openn=function(obj,method){
	  switch(method){
		  case 'persoana':
			swal({   title:"Persoana",text:"id: "+obj.id+"\n\nNume: "+obj.name,closeOnConfirm: true, showCancelButton: false }
                ,function(isConfirm) {});
		  break;
		  case 'pet':
			swal({   title:"Pet",text:"id: "+obj.id+"\n\nNume: "+obj.name+'\n\n Specie: '+obj.species+'\n\n Ani: '+obj.age,closeOnConfirm: true, showCancelButton: false }
                ,function(isConfirm) {});
		  break;
		  case 'bar':
			function drinks(){
                  var drink="";
                  angular.forEach(obj.drinks,function(value,key){
                      drink=drink+value+'\n';
                  });
                  
                  return drink;
              }
			swal({   title:"Bar",text:"id: "+obj.id+"\n\nNume: "+obj.name+'\n\n Location: '+obj.location+'\n\n Max Number of Customers: '+obj.maxNumberCustomers+'\n\n Bauturi: '+drinks(),closeOnConfirm: true, showCancelButton: false }
                ,function(isConfirm) {});
		  break;
		  case 'produce':
			swal({   title:"Produce",text:"id: "+obj.id+"\n\nNume: "+obj.name+'\n\n Cantitate: '+obj.quantity,closeOnConfirm: true, showCancelButton: false }
                ,function(isConfirm) {});
		  break;
	  }
  };
}]);


















