clear, clc, close all;
%Punto 1, cargar datos y visualizar con plotData
data = load("data_15.txt");
X = data(:, 1:2);
y = data(:, 3);

%plotData(X, y);

%% Setup the parameters you will use for this exercise
input_layer_size  = 2;  % 20x20 Input Images of Digits
hidden_layer_size = 2 ;   % 5 hidden units
num_labels = 1;          % 2 labels  cuenta el 0
                          
m = size(X, 1);
% dim: hidden X (input + 1) 5 x 3
Theta1 =  randInitializeWeights(input_layer_size, hidden_layer_size);
% dim: num_labels X (hidden +1 ) 2 x 6
Theta2 =  randInitializeWeights(hidden_layer_size, num_labels);
InitialTheta1 = [-0.0893, -0.0789, 0.0147;
                 0.1198, -0.1122, 0.0916];
InitialTheta2 = [0.0406, -0.0743, -0.0315];
fprintf("Desenrollando parametros..\n");

% Unroll parameters 
%nn_params = [Theta1(:) ; Theta2(:)];
nn_params = [InitialTheta1(:) ; InitialTheta2(:)];

lambda = 1;

fprintf("Calculando coste..\n");
[J grad]= nnCostFunction(nn_params, input_layer_size, hidden_layer_size, num_labels, X, y, lambda);
fprintf("Calculado el coste: %f\n",J);
fprintf("Calculado el grad:\n");
grad

%plot_decision_boundary(InitialTheta1,InitialTheta2,X,y);
fprintf("Pintada la frontera de decision\n",J);
printf("\nAccuracy percent: \n");
MAE = accuracy(Theta1, Theta2, X, y);
MAE
options = optimset('GradObj', 'on','MaxIter', 100);
%nn_params = fminunc(@(t)(nnCostFunction(t,input_layer_size,hidden_layer_size,num_labels,X,y)), nn_params, options);

%Reshape thetas
Theta1 =  reshape(nn_params(1:hidden_layer_size * (input_layer_size +1)),
  hidden_layer_size,(input_layer_size +1));;
Theta2 = reshape(nn_params(((hidden_layer_size * (input_layer_size +1)) +1):
  end),num_labels,(hidden_layer_size +1));
  printf("Thetas tras la optimizacion.\n");
Theta1
printf("\n");
Theta2
printf("\n");

plot_decision_boundary(Theta1,Theta2,X,y);

printf("\nAccuracy percent: \n");
MAE = accuracy(Theta1, Theta2, X, y);
MAE


%Sizes for all the hidden layer neurons
hidden_layer_sizes= [];

for i=1:length(hidden_layer_sizes)
  printf("Comienzo de la iteracion\n");
  %Size of the current hidden layer
  hidden_layer_size=hidden_layer_sizes(i);

  
  %Generate new Thetas
  initial_Theta1 = randInitializeWeights(input_layer_size,hidden_layer_size);
  initial_Theta2 = randInitializeWeights(hidden_layer_size,num_labels);
  
  % Unroll parameters
  initial_nn_params = [initial_Theta1(:) ; initial_Theta2(:)];

  %Gradient Descent
  options = optimset('GradObj', 'on','MaxIter', 100);
  nn_params = fminunc(@(t)(nnCostFunction(t,input_layer_size,hidden_layer_size,num_labels,X,y)), initial_nn_params, options);

  %Reshape Thetas
  Theta1 =  reshape(nn_params(1:hidden_layer_size * (input_layer_size +1)),
   hidden_layer_size,(input_layer_size +1));;
  Theta2 = reshape(nn_params(((hidden_layer_size * (input_layer_size +1)) +1):
   end),num_labels,(hidden_layer_size +1));

  printf("Thetas tras la optimizacion para %d neuronas.\n",hidden_layer_size);
  Theta1
  printf("\n");
  Theta2
  printf("\n");

  
  %We plot the data
  plotData(X,y);
  plot_decision_boundary(Theta1, Theta2, X, y);

  printf("\nAccuracy percent con %d neuronas: \n",hidden_layer_size);
  MAE = accuracy(Theta1, Theta2, X, y);
  MAE
  
  printf("Fin de la iteracion\n");
endfor
hidden_layer_size = 10;
options = optimset('GradObj', 'on','MaxIter', 100);
%Generate new Thetas
  initial_Theta1 = randInitializeWeights(input_layer_size,hidden_layer_size);
  initial_Theta2 = randInitializeWeights(hidden_layer_size,num_labels);
  nn_params = [initial_Theta1(:) ; initial_Theta2(:)];
nn_params = fminunc(@(t)(nnCostFunction(t,input_layer_size,hidden_layer_size,num_labels,X,y)), nn_params, options);

%Reshape thetas
Theta1 =  reshape(nn_params(1:hidden_layer_size * (input_layer_size +1)),
  hidden_layer_size,(input_layer_size +1));;
Theta2 = reshape(nn_params(((hidden_layer_size * (input_layer_size +1)) +1):
  end),num_labels,(hidden_layer_size +1));
  printf("Thetas tras la optimizacion.\n");
Theta1
printf("\n");
Theta2
printf("\n");

plot_decision_boundary(Theta1,Theta2,X,y);

printf("\nAccuracy percent: \n");
MAE = accuracy(Theta1, Theta2, X, y);
MAE

