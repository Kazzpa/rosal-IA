clear, clc, close all;
%Punto 1, cargar datos y visualizar con plotData
data = load("data_15.txt");
X = data(:, 1:2);
y = data(:, 3);

%plotData(X, y);

%% Setup the parameters you will use for this exercise
input_layer_size  = 2;  % 20x20 Input Images of Digits
hidden_layer_size = 5;   % 25 hidden units
num_labels = 2;          % 10 labels, from 1 to 10   
                          % (note that we have mapped "0" to label 10)
                          
m = size(X, 1);
% dim: hidden X (input + 1) 5 x 3
Theta1 =  randInitializeWeights(input_layer_size, hidden_layer_size);
% dim: num_labels X (hidden +1 ) 2 x 6
Theta2 =  randInitializeWeights(hidden_layer_size, num_labels);


% Unroll parameters 
nn_params = [Theta1(:) ; Theta2(:)];