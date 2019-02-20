var app = angular.module('add', ['ngMaterial']);

app.config(function ($mdThemingProvider) {

	$mdThemingProvider.theme('default')
		.primaryPalette('blue', {
			'default': '600'
		})
		.accentPalette('blue', {
			'default': '800'
		});
});

app.controller('QuestionController', QuestionController);

function QuestionController($scope, $http, $mdToast) {

	$http.get('api/public/questions').then(function (success) {
		$scope.questions = success.data;
	});

	this.addQuestion = function () {
		$scope.adding = true;
		console.log($scope.question);
		$http.post('api/public/question', $scope.question).then(function (success) {
			$scope.adding = false;
			$scope.questions = success.data;
			console.log(success.data);
			$scope.addOption = false;
			$scope.optionToAdd = null;
			$scope.question = {};
			$scope.questionForm.$setPristine();
			$scope.questionForm.$setUntouched();
			showToast("Success");
		}, function () {
			$scope.adding = false;
			$scope.addOption = false;
			$scope.optionToAdd = null;
			$scope.question = {};
			$scope.questionForm.$setPristine();
			$scope.questionForm.$setUntouched();
			showToast("Error");
		})
	}

	function showToast(text) {
		$mdToast.show(
			$mdToast.simple()
			.textContent(text)
			.position('bottom')
			.hideDelay(2000)
		);

	}

	this.cancelAdd = function () {
		$scope.addOption = false;
		$scope.optionToAdd = null;
	}

	this.confirmAddOption = function () {
		var option = {
			"id": 0,
			"option": $scope.optionToAdd,
			"valid": false,
			"version": 0
		}
		if (option.option != null) {
			if ($scope.question.options != null)
				$scope.question.options.push(option);
			else {
				$scope.question.options = [];
				$scope.question.options.push(option);
			}
			$scope.optionToAdd = null;
		} else {
			$scope.optionToAdd = null;
			$scope.addOption = true;
		}
	}

	$scope.deleteOption = function (index) {
		$scope.question.options.splice(index, 1);
	}

	this.deleteQuestion = function (id) {
		$scope.deleting = true;
		$http.delete('api/public/question/' + id).then(function (success) {
			$scope.questions = success.data;
			$scope.deleting = false;
		});
	}
}