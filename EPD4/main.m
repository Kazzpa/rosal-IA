%% EPD 7: Machine Learning – Regresión Logística

%% Initialization
clear ; close all; clc

%% Load Data
%  The first two columns contains the exam scores and the third column
%  contains the label.
data = load("ex2data1.txt");
X = data(:,1:2);
y = data(:,3);


%% ==================== EJ1. Plotting ====================
%  We start the exercise by first plotting the data to understand the 
%  the problem we are working with.

fprintf(['Plotting data with + indicating (y = 1) examples and o ' ...
         'indicating (y = 0) examples.\n']);

plotData(X, y);


fprintf('\nProgram paused. Press enter to continue.\n');
pause;


%% ============ EJ3. Compute Cost and Gradient ============
%  In this part of the exercise, you will implement the cost and gradient
%  for logistic regression. You neeed to complete the code in 
%  costFunction.m

%  Setup the data matrix appropriately, and add ones for the intercept term
m = length(y);
X = [ones(m,1),X];

% Initialize fitting parameters
initial_theta = [0;0;0];

% Compute and display initial cost and gradient
[cost, grad] = costFunction(initial_theta, X, y);

fprintf('Cost at initial theta (zeros): %f\n', cost);
fprintf('Gradient at initial theta (zeros): \n');
fprintf(' %f \n', grad);

fprintf('\nProgram paused. Press enter to continue.\n');
pause;


%% ============= EJ4. Optimizing using fminunc. Plotting  =============
%  In this exercise, you will use a built-in function (fminunc) to find the
%  optimal parameters theta.

%  Set options for fminunc
options = optimset('GradObj', 'on', 'MaxIter', 400);

%  Run fminunc to obtain the optimal theta
%  This function will return theta and the cost 
[theta, cost] = ...
	fminunc(@(t)(costFunction(t, X, y)), initial_theta, options);

% Print theta to screen
fprintf("Theta [%d,\t%d,\t%d]\n",theta(1),theta(2),theta(3));

% Plot Boundary
% Only need 2 points to define a line, so choose two endpoints (min,max) of X1 and calculate X2
plotDecisionBoundary(theta,X,y);
    
% Labels and Legend


fprintf('\nProgram paused. Press enter to continue.\n');
pause;

%% ============== EJ5. Predict ==============
%  Predict probability for a student with score 45 on exam 1 
%  and score 85 on exam 2 
prob =  sigmoid([1 45 85] * theta)


%% ============== EJ6. Predict and Accuracies ==============
% Compute accuracy on our training set
p = predict(theta, X);
fprintf('\nExactitud: %f\n', mean( p==y)*100);

fprintf('\nProgram paused. Press enter to continue.\n');
pause;

