clear;
close all;
clc;
%Problema 1
%Punto 1 Cargada de datos y visualizacion
data = load("data_flower.txt");
X = data(:,1:2);
y = data(:,3);
plotData(X,y);
% Problema 2 Implemente una regresión Logistica

%  Setup the data matrix appropriately, and add ones for the intercept term
m = length(y);
X = [ones(m,1),X];

% Initialize fitting parameters
theta = [0,0,0]';
fprintf('Función coste:\n\n');
[cost, grad] = costFunction(theta, X, y);
fprintf('Cost at initial theta (zeros): %f\n', cost);
fprintf('Gradient at initial theta (zeros): \n');
fprintf(' %f \n', grad);
iterations = 10;
alpha = 1;
fprintf("Función descenso del gradiente para num_iter=%f y alfa=%f\n\n",iterations, alpha);
[theta, J_history] = gradientDescent(X, y, theta, alpha, iterations);

figure();%Crear figura

plot(1:length(J_history), J_history, '-b', 'LineWidth', 2);
xlabel('Number of iterations');%Título del eje X
ylabel('Cost J');%Título del eje Y
plotData(X(:,2:3),y);
fprintf('Función coste:\n\n');
[cost, grad] = costFunction(theta, X, y);
fprintf('Cost at initial theta (zeros): %f\n', cost);
fprintf('Gradient at initial theta (zeros): \n');
fprintf(' %f \n', grad);
prediccion = predict(theta, X);
tasaAcierto = mean((prediccion==y))*100;
fprintf("Tasa acierto: %.2f\n",tasaAcierto);
% Plot Boundary
% Only need 2 points to define a line, so choose two endpoints (min,max) of X1 and calculate X2
plotData(X,y);
plotDecisionBoundary(theta,X,y);

%Problema 3
X = data(:,1:2);
X = mapFeature(X(:,1),X(:,2));
theta = zeros(28,1);
fprintf('Función coste:\n\n');
lambda = 1;
[cost, grad] = costFunctionReg(theta, X, y, lambda);
fprintf('Cost at initial theta (zeros): %f\n', cost);
fprintf('Gradient at initial theta (zeros): \n');
fprintf(' %f  ', grad);
fprintf("\n");

iterations = 920;
alpha = 0.0000001;
lambda = 1 ;
fprintf("Función descenso del gradiente para num_iter=%f y alfa=%f\n\n",iterations, alpha);
[theta, J_history] = gradientDescentReg(X, y, theta, alpha, iterations, lambda);

fprintf('Función coste:\n\n');
[cost, grad] = costFunctionReg(theta, X, y, lambda);
fprintf('Cost at initial theta (zeros): %f\n', cost);
fprintf('Gradient at initial theta (zeros): \n');
fprintf(' %f  ', grad);
fprintf("\n");
prediccion = predict(theta, X);
tasaAcierto = mean((prediccion==y))*100;
fprintf("Tasa acierto: %.2f\n",tasaAcierto);
figure();%Crear figura

plot(1:length(J_history), J_history, '-b', 'LineWidth', 2);
xlabel('Number of iterations');%Título del eje X
% Plot Boundary
X_mapped = X;
X = data(:,1:2);
plotData(X,y);
X = X_mapped(:,2:28);
plotDecisionBoundary(theta, X, y);
hold on;
title(sprintf('lambda = %g', lambda))

% Labels and Legend

legend('y = 1', 'y = 0', 'Decision boundary')
hold off;