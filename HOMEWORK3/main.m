clear, clc, close all;
%---APARTADO 1----
% cargar datos y visualizar con plotData

data = load("data_15.txt");
X = data(:, 1:2);
y = data(:, 3);
%plotData(X, y);

%---APARTADO 2----
%% Setup the parameters you will use for this exercise

input_layer_size  = 2;  % 20x20 Input Images of Digits
hidden_layer_size = 2 ;   % 5 hidden units
num_labels = 1;          % 2 labels  cuenta el 0
                          
m = size(X, 1);
% dim: hidden X (input + 1) 5 x 3
%Theta1 =  randInitializeWeights(input_layer_size, hidden_layer_size);
% dim: num_labels X (hidden +1 ) 2 x 6
%Theta2 =  randInitializeWeights(hidden_layer_size, num_labels);

InitialTheta1 = [-0.0893, -0.0789, 0.0147;
                 0.1198, -0.1122, 0.0916];
InitialTheta2 = [0.0406, -0.0743, -0.0315];
Theta1 = InitialTheta1;
Theta2 = InitialTheta2;
fprintf("Desenrollando parametros..\n");

% Unroll parameters 
%nn_params = [Theta1(:) ; Theta2(:)];
nn_params = [InitialTheta1(:) ; InitialTheta2(:)];

lambda = 1;
fprintf("Coste Esperado: 0.6932\n");
[J grad]= nnCostFunction(nn_params, input_layer_size, hidden_layer_size, num_labels, X, y, lambda);
fprintf("Calculado el coste: %f\n",J);
fprintf("Gradiente esperado \n0.000140\t0.000134\t-0.000026\n-0.000209\t0.000175\t0.000198\n");
fprintf("Calculado el grad:\n");
grad

%plot_decision_boundary(InitialTheta1,InitialTheta2,X,y);
fprintf("Pintada la frontera de decision\n",J);
printf("\nAccuracy percent: \n");
pred = predict(Theta1, Theta2, X);

fprintf('Exactitud con %d neuronas: %f\n', hidden_layer_size, mean(pred == y)*100);


%options = optimset('GradObj', 'on','MaxIter', 100);
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

plot_decision_boundary(Theta1,Theta2,X,y,"Apartado 2, neuronas: 2");

printf("\nAccuracy percent: \n");
predictions = predict(Theta1,Theta2,X);
%Then we measure how right our predictions are
tasa = mean(double(round(predictions') == y))*100;
tasa(1);

%---APARTADO 3----

%Sizes for all the hidden layer neurons
%hidden_layer_sizes= [1,2,3,4,5,10];
hidden_layer_sizes= [10];
titulo = {"Apartado 3 - neuronas: 1", "Apartado 3 - neuronas: 2", "Apartado 3 - neuronas: 3"...
, "Apartado 3 - neuronas: 4", "Apartado 3 - neuronas: 5", "Apartado 3 - neuronas: 20"...
, "Apartado 3 - neuronas: 50"};
for i=1:length(hidden_layer_sizes)
  %Size of the current hidden layer
  hidden_layer_size=hidden_layer_sizes(i);
  printf("Comienzo de la iteracion con %d  neuronas\n",hidden_layer_size);

  
  %Generate new Thetas
  initial_Theta1 = randInitializeWeights(input_layer_size,hidden_layer_size);
  initial_Theta2 = randInitializeWeights(hidden_layer_size,num_labels);
  
  % Unroll parameters
  initial_nn_params = [initial_Theta1(:) ; initial_Theta2(:)];

  %Gradient Descent
  options = optimset('GradObj', 'on','MaxIter', 100);
  lambda = 1;
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
  %plotData(X,y);
  plot_decision_boundary(Theta1, Theta2, X, y,titulo(i));

  printf("\nAccuracy percent con %d neuronas: \n",hidden_layer_size);
 pred = predict(Theta1, Theta2, X);

fprintf('Exactitud con %d neuronas: %f\n', hidden_layer_size, mean(pred == y)*100);


  
  printf("Fin de la iteracion\n");
endfor


%---APARTADO 4----
printf("Apartado 4\n");
hidden_layer_size=10;
lambdas = [0.01,0.03,0.1,0.3,1,3];
%Generate new Thetas
initial_Theta1 = randInitializeWeights(input_layer_size,hidden_layer_size);
initial_Theta2 = randInitializeWeights(hidden_layer_size,num_labels);
  
% Unroll parameters
initial_nn_params = [initial_Theta1(:) ; initial_Theta2(:)];

for i=1:length(lambdas)
  %Size of the current hidden layer
  lambda = lambdas(i);
  printf("Comienzo de la iteracion para lamdba : %.2f\n",lambda);

  %Gradient Descent
  options = optimset('GradObj', 'on','MaxIter', 100);

  nn_params = fminunc(@(t)(nnCostFunction(t,input_layer_size,hidden_layer_size,num_labels,X,y,lambda)), initial_nn_params, options);
  %Reshape Thetas
  Theta1 =  reshape(nn_params(1:hidden_layer_size * (input_layer_size +1)),
   hidden_layer_size,(input_layer_size +1));;
  Theta2 = reshape(nn_params(((hidden_layer_size * (input_layer_size +1)) +1):
   end),num_labels,(hidden_layer_size +1));

  printf("Thetas tras la optimizacion para 10 neuronas y lamdba %.3f\n",lambda);
  Theta1
  printf("\n");
  Theta2
  printf("\n");

  
  %We plot the data
  %plotData(X,y);

  printf("\nAccuracy percent con %d neuronas: \n",hidden_layer_size);
  pred = predict(Theta1, Theta2, X);

  fprintf('Exactitud con %d neuronas: %f\n', hidden_layer_size, mean(pred == y)*100);


  
  printf("Fin de la iteracion\n");
  titulo = strcat("Apartado 4 neuronas: 10 con lambda: ",num2str(lambda));
  plot_decision_boundary(Theta1,Theta2,X,y,titulo);
  printf("\nAccuracy percent: \n");
  pred = predict(Theta1, Theta2, X);
  fprintf('Exactitud con %d neuronas: %f\n', hidden_layer_size, mean(pred == y)*100);



endfor

