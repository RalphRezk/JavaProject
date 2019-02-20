<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimal-ui" />

<title>Introduction</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.2/angular.min.js"
	type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.2/angular-animate.min.js"
	type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.2/angular-route.min.js"
	type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.2/angular-aria.min.js"
	type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.2/angular-messages.min.js"
	type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angular_material/1.1.9/angular-material.min.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/angular_material/1.1.9/angular-material.min.css">
<link href="resources/css/index.css" rel="stylesheet">
<script src="resources/js/index.js" type="text/javascript"></script>
</head>

<body ng-app="intro" layout="column" ng-controller="QuestionController as ctrl" flex>
	<md-toolbar class="md-primary">
		<div class="md-toolbar-tools">
			<h2 flex md-truncate" style="color:white">QCM - Ralph Rezkallah</h2>
			<span>{{correctAnswers}}/{{ctrl.getTotalCorrectAnswers()}} Correct Answers - {{incorrectAnswers}} Incorrect</span>
		</div>
	</md-toolbar>
	<md-content layout-padding flex> 
		<md-select name="difficulty" ng-model="filter" ng-disabled="adding" placeholder="Difficulty" flex>
			<md-option value="">All</md-option>
			<md-option value="Easy">Easy</md-option>
			<md-option value="Medium">Medium</md-option>
			<md-option value="Hard">Hard</md-option>
		</md-select>
		<md-card ng-repeat="q in questions | filter:{difficulty:filter}"> 
			<md-card-header>
				<md-card-header-text> 
					<span class="md-title">{{q.question}}</span>
					<span class="md-subhead">{{q.difficulty}}</span>
				</md-card-header-text>
			</md-card-header>
				<div ng-switch on="q.type" layout-padding>
				<div ng-switch-when="MultipleChoiceQuestion" layout-padding>
					<div>
						<div layout="row" layout-wrap flex>
							<div flex="100" ng-repeat="o in q.options">
								<md-checkbox class="md-accent" value="{{o.id}}" ng-click="ctrl.checkAnswer(o, q)">{{o.option}}</md-checkbox>
							</div>
						</div>
					</div>
				</div>
				<div ng-switch-default></div>
				</div>
		</md-card> 
	</md-content>
	<a href="add">
		<md-button class="md-fab md-fab-bottom-right md-primary" aria-label="Use Android">
          	<md-icon md-svg-src="resources/images/ic_add.svg"></md-icon>
    	</md-button>
	</a>
</body>
<hhtml>