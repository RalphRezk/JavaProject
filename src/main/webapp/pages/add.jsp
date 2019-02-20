<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimal-ui" />

	<title>Survey</title>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.2/angular.min.js" type="text/javascript"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.2/angular-animate.min.js" type="text/javascript"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.2/angular-route.min.js" type="text/javascript"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.2/angular-aria.min.js" type="text/javascript"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.2/angular-messages.min.js" type="text/javascript"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angular_material/1.1.9/angular-material.min.js" type="text/javascript"></script>
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/angular_material/1.1.9/angular-material.min.css">
	<link href="resources/css/add.css" rel="stylesheet">
	<script src="resources/js/add.js" type="text/javascript"></script>
	
</head>

<body ng-app="add" ng-controller="QuestionController as ctrl" layout="column" flex>
	<md-card>
		<md-card-header>
			<md-card-header-text>
				<span class="md-title">Questions Form</span>
				<span class="md-subhead">Add All Questions</span>
			</md-card-header-text>
		</md-card-header>
		<md-progress-linear ng-show="adding" class="md-accent" md-mode="indeterminate"></md-progress-linear>
		<form name="questionForm" ng-submit="ctrl.addQuestion()" layout-padding>
		
			<div layout-gt-sm="row">
				<md-input-container class="md-block md-accent" flex-gt-sm>
					<input ng-disabled="adding" required name="title" type="text" placeholder="Question Statement" ng-model="question.question"
					/>
				</md-input-container>
			</div>
			
			<div layout-gt-sm="row">
				<md-input-container class="md-block md-accent" flex-gt-sm>
					<md-select required ng-disabled="adding" ng-change="ctrl.selectChanged()" placeholder="Question Difficulty" name="qd" ng-model="question.difficulty"
					    required>
						<md-option value="Easy">Easy</md-option>
						<md-option value="Medium">Medium</md-option>
						<md-option value="Hard">Hard</md-option>
					</md-select>

				</md-input-container>
			</div>

			<div layout-gt-sm="row">
				<md-input-container class="md-block md-accent" flex-gt-sm>
					<md-select required ng-disabled="adding" ng-change="ctrl.selectChanged()" placeholder="Question Type" name="qtype" ng-model="question.type"
					    required>
						<md-option value="MultipleChoiceQuestion">Multiple Choice Question</md-option>
					</md-select>

				</md-input-container>
			</div>

			<div ng-show="question.type=='MultipleChoiceQuestion'">
				<h4>
					Options
					<md-button class="md-raised md-accent" aria-label="add" ng-click="addOption=true" ng-disabled="adding">
						Add
					</md-button>
				</h4>
			</div>
			<div ng-show="addOption==true">
				<md-input-container class="md-block md-accent" flex-gt-sm>
					<input name="option" type="text" placeholder="Option" ng-model="optionToAdd" /> </md-input-container>
				<md-button ng-click="ctrl.confirmAddOption()" class="md-accent">Add</md-button>
				<md-button ng-click="ctrl.cancelAdd()" class="md-warn">Cancel</md-button>
			</div>
			<div ng-show="question.type!='TextQuestion'">
				<md-list class="md-dense">
					<md-list-item ng-repeat="o in question.options" style="height:65px">
						<p>{{o.option}}</p>
						<md-checkbox class="md-accent md-secondary" ng-model="o.valid" value="{{o.id}}">Correct Answer</md-checkbox>
						<md-button ng-click="deleteOption($index)" class="md-secondary md-icon-button" aria-label="del_option">
							<md-icon class="icon-red" md-svg-icon="resources/images/ic_remove.svg"></md-icon>
						</md-button>
						<md-divider></md-divider>
					</md-list-item>
				</md-list>
			</div>
			<md-card-actions layout="row" layout-align="end center">
				<md-button ng-disabled="!questionForm.$valid || question.options.length==0" class="md-raised md-accent padding" type="submit" ng-disabled="adding">Add Question</md-button>
			</md-card-actions>
		</form>
	</md-card>
	<md-content flex>
	<h3 layout-padding>All Questions</h3>
	<md-card ng-repeat="q in questions">
		<md-card-header>
			<md-card-header-text>
				<span class="md-title">{{$index+1}} - {{q.question}}</span>
				 <span class="md-subhead">{{q.difficulty}}</span>
			</md-card-header-text>
		</md-card-header>
		<div ng-switch on="q.type" layout-padding>
			<div ng-switch-when="TextQuestion" layout-padding>
				<md-input-container class="md-block md-accent">
					<label>Answer</label>
					<textarea md-maxlength="400" rows="3" md-select-on-focus cols=""></textarea>
				</md-input-container>
			</div>
			<div ng-switch-when="MultipleChoiceQuestion" layout-padding>
				<div>
					<div layout="row" layout-wrap flex>
						<div flex="100" ng-repeat="o in q.options">
							<md-checkbox class="md-accent" value="{{o.id}}">{{o.option}}</md-checkbox>
						</div>
					</div>
				</div>
			</div>
			<div ng-switch-default></div>
		</div>
		<md-card-actions layout="row" layout-align="end center">
			<md-button ng-disabled = "deleting" class="md-raised md-warn padding" ng-click="ctrl.deleteQuestion(q.id)">Delete</md-button>
		</md-card-actions>
	</md-card>
	</md-content>
	

</body>

</html>