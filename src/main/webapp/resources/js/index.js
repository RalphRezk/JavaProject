var app = angular.module('intro', [ 'ngMaterial','ngAnimate']);
app.controller('QuestionController', QuestionController);

app.config(function ($mdThemingProvider) {
	
	$mdThemingProvider.theme('default')
		.primaryPalette('blue', {
			'default': '600'
		})
		.accentPalette('blue', {
			'default': '800'
		});
});

function QuestionController($scope, $http) {
	var questions = [];
	var correctAnswersCount = 0;
	var incorrectAnswersCount = 0;

	updateCorrectAnswersUI();

	this.getTotalCorrectAnswers = getTotalCorrectAnswers;
	this.checkAnswer = checkAnswer;

	$http.get('api/public/questions').then(function (success) {
		questions = success.data;
		questions.map(q =>{
			q.answers = [];
		});
		$scope.questions = success.data;
	});

	function getTotalCorrectAnswers(){
		var total = 0;
		for(var i=0; i<questions.length;i++){
			total+=questions[i].totalCorrect;
		}
		return total;
	}

	function checkAnswer(option, question){
		var selected = question.answers;
		var index = selected.indexOf(option);

		if(index==-1){
			question.answers.push(option);
			if(option.valid){
				correctAnswersCount++;
			}else{
				incorrectAnswersCount++;
			}
		}else{
			question.answers.splice(index, 1);
			if(option.valid){
				correctAnswersCount--;
			}else{
				incorrectAnswersCount--;
			}
		}

		console.log(question);
		updateCorrectAnswersUI();
	}

	function updateCorrectAnswersUI(){
		$scope.correctAnswers = correctAnswersCount;
		$scope.incorrectAnswers = incorrectAnswersCount;
	}
}
