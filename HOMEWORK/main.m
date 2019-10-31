data = load('ex1data2.txt');
X = data(:, 1:2); y = data(:, 3);
X_norm = featureNormalization(X);
m = length(X);
X_norm =[ones(m, 1), X_norm]; 
theta = [0,0,0]';
fprintf("Coste del modelo:");
computeCost(X_norm,y,theta)
X=X_norm;
% Indicar el porcentaje de división en la variable percent, el valor 0,7 indica 70% para entrenameinto y el resto para test
percent=0.7;
%m es la longitud del vector y
m= length(y);
%Usar función holdout para obtener el conjunto de entrenamiento y el conjunto de test

[X_train,y_train, X_test, y_test] = holdout(X,y,m,percent);

%Inicializar alfa y el número de iteraciones
iterations = 200;
alpha = 0.03;

%Obtener el modelo para el conjunto de entrenamiento X_train, y_train

%theta = zeros(2,1); % initialize fitting parameters
tic();
[theta, J_history] = gradientDescent(X_train, y_train, theta, alpha, iterations);
fin = toc();
fprintf("GradientDescent time:%f.5\n",fin);
%Dibujar la función de coste

%figure();%Crear figura

%Dibujo la función de coste correspondiente a J_history

%plot(1:length(J_history), J_history, '-b', 'LineWidth', 2);
%xlabel('Number of iterations');%Título del eje X
%ylabel('Cost J');%Título del eje Y

%apartado 5.
x1 = 1650;
x2 = 3;
aux = X;
X = data(:, 1:2);

x1 = (x1-mean(X(:,1)))/std(X(:,1));
x2 = (x2-mean(X(:,2)))/std(X(:,2));
input = [1,x1,x2];
price = input * theta;
fprintf("DESCENSO GRAD:\nPrecio para 1650 metros y 3 habitaciones: %d\n",price);
%apartado 6. utilizar la ecuacion normal y comparar con el anterior modelo
X = aux;
inicio = tic();
thetanormal = inv(X'*X)*X'*y;
fin = toc();
fprintf("Ecuacion Normal time:%f.5\n",fin-inicio);
price2 = input * thetanormal;
fprintf("EC NORMAL:\nPrecio para 1650 metros y 3 habitaciones: %d\n",price2);
