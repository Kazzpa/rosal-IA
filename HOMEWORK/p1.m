%cargamos y normalizamos los valores necesarios
data = load('ex1data2.txt');
X = data(:, 1:2); y = data(:, 3);
X_norm = featureNormalization(X);
m = length(X);
X_norm =[ones(m, 1), X_norm]; 
theta = [0,0,0]';
X = X_norm;
% Indicar el porcentaje de división en la variable percent, el valor 0,7 indica 70% para entrenameinto y el resto para test
percent=0.7;
%Obtener el modelo para el conjunto de entrenamiento X_train, y_train
[X_train,y_train, X_test, y_test] = holdout(X,y,m,percent);

%Inicializar alfa y el número de iteraciones
iterations = 200;
alpha = [0.3,0.1,0.03,0.01];

figure();%Crear figura para comparar como avanza con alpha distinto

%theta = zeros(2,1); % initialize fitting parameters
[theta, J_history] = gradientDescent(X_train, y_train, theta, alpha(1), iterations);
plot(1:length(J_history), J_history, '-b;0.3;', 'LineWidth', 2);
hold on;
theta1 = theta;
bestTheta = theta1;
bestCost = J_history(length(J_history));

theta = [0,0,0]';
[theta, J_history] = gradientDescent(X_train, y_train, theta, alpha(2), iterations);
plot(1:length(J_history), J_history, '-r;0.1;', 'LineWidth', 2);
theta2 = theta;
actualCost = J_history(length(J_history));
if actualCost < bestCost 
  bestCost = actualCost;
  bestTheta = theta2;
endif

theta = [0,0,0]';
[theta, J_history] = gradientDescent(X_train, y_train, theta, alpha(3), iterations);
plot(1:length(J_history), J_history, '-g;0.03;', 'LineWidth', 2);
theta3 = theta;
actualCost = J_history(length(J_history));
if actualCost < bestCost 
  bestCost = actualCost;
  bestTheta = theta3;
endif

theta = [0,0,0]';
[theta, J_history] = gradientDescent(X_train, y_train, theta, alpha(4), iterations);
plot(1:length(J_history), J_history, '-m;0.01;', 'LineWidth', 2);
hold off;
actualCost = J_history(length(J_history));
if actualCost < bestCost 
  bestCost = actualCost;
  bestTheta = theta;
endif

%Dibujo la función de coste correspondiente a J_history

xlabel('Number of iterations');%Título del eje X
ylabel('Cost J');%Título del eje Y

%calcular prediccion con theta mejor
x1 = 1650;
x2 = 3;
aux = X;
X = data(:, 1:2);

x1 = (x1-mean(X(:,1)))/std(X(:,1));
x2 = (x2-mean(X(:,2)))/std(X(:,2));
input = [1,x1,x2];
price = input * bestTheta;
fprintf("DESCENSO GRAD:\nPrecio para 1650 metros y 3 habitaciones: %d\n",price);
