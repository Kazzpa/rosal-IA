%Cargamos los datos
close all;clear;
% 1 --> Cylinders: n�mero de cilindros del veh�culo.
% 2 --> Displacement: desplazamiento.
% 3 --> Horsepower: potencia en caballos.
% 4 --> Weight: peso del coche en libras
% 5 --> Acceleration: tiempo de pasar de 0 a 60 mph.
% 6 --> Model Year: a�o del modelo.
% 7 --> Origin: 1-USA, 2-Europa, 3-Jap�n.
% 8 --> MPG: millas por gal�n de combustible.
data = load("autos.csv");

%Se dividen las columnas en sus respectivas variables
X = data(:,1:7);
y = data(:,8);
stats = estadisticas(data)';
fprintf("");
for i=1:4
  fprintf("\n");
  for j=1:8
    fprintf("%.4f  \t",stats(i,j));
  endfor
  
endfor
%2 normal equation
%2.1 normalizar datos
m = length(y);

%2.2 separar en datos de training y test
percent = 0.7;
[X_train,y_train, X_test, y_test] = holdout(X,y,m,percent);

%2.3a equacion normal con atributo desplazamiento
m = length(y_train);

xtrain1 = [ones(m,1),X_train(:,2)];
m = length(y_test);
xtest1 = [ones(m,1),X_test(:,2)];
theta1 = normalEqn(xtrain1,y_train);

%calcular el error medio absoluto con el conjunto test
error1 = absMeanError(xtest1,y_test,theta1);
fprintf("Error de prediccion con atributo desplazamiento: %.5f\n",error1);

%2.3b equacion normal con atributo peso
m = length(y_train);

xtrain2 = [ones(m,1),X_train(:,4)];
m = length(y_test);
xtest2 = [ones(m,1),X_test(:,4)];
theta2 = normalEqn(xtrain2,y_train);

%calcular el error medio absoluto con el conjunto test
error2 = absMeanError(xtest2,y_test,theta2);
fprintf("Error de prediccion con atributo peso: %.5f\n",error2);

%2.3c equacion normal con atributo aceleracion
m = length(y_train);

xtrain3 = [ones(m,1),X_train(:,5)];
m = length(y_test);
xtest3 = [ones(m,1),X_test(:,5)];
theta3 = normalEqn(xtrain3,y_train);

%calcular el error medio absoluto con el conjunto test
error3 = absMeanError(xtest3,y_test,theta3);
fprintf("Error de prediccion con atributo aceleracion: %.5f\n",error3);

%2.3d equacion normal con todo el conjunto de atributos
m = length(y_train);

xtrain4 = [ones(m,1),X_train(:,:)];
m = length(y_test);
xtest4 = [ones(m,1),X_test(:,:)];
theta4 = normalEqn(xtrain4,y_train);

%2.4calcular el error medio absoluto con el conjunto test
error4 = absMeanError(xtest4,y_test,theta4);
fprintf("Error de prediccion con todos los atributos: %.5f\n",error4);
%3. visualizar datos
%visualizarDatos(data,theta1,theta2,theta3,theta4);

%4. Descenso del gradiente
%4a. desplazamiento
alpha = 0.00002;
iterations = 500000;
fprintf("Desplazamiento:\talpha : %.10f\titerations:%d\n",alpha,iterations);
theta_desplazamiento_gd = zeros(2,1);
[theta_desplazamiento_gd, J_history] = gradientDescent([ones(length(X_train),1),X_train(:,2)], y_train, theta_desplazamiento_gd, alpha, iterations);
error = absMeanError(xtest1,y_test,theta_desplazamiento_gd);
fprintf("GD:Error Absoluto medio de prediccion desplazamiento: %.5f\n", error);

%4a. peso
alpha = 0.00000002;
iterations = 200000;
theta_peso_gd = [0,0]';
fprintf("Peso:\talpha : %.10f\titerations:%d\n",alpha,iterations);
[theta_peso_gd, J_history] = ...
gradientDescent([ones(length(X_train),1),X_train(:,4)], y_train, theta_peso_gd, alpha, iterations);
error = absMeanError(xtest2,y_test,theta_peso_gd);
fprintf("GD:Error Absoluto medio de prediccion peso: %.5f\n", error);

%4a. aceleracion
alpha = 0.000002;
iterations = 30000;
theta_aceleracion_gd = [0,0]';
fprintf("Aceleracion\talpha : %.10f\titerations:%d\n",alpha,iterations);
[theta_aceleracion_gd, J_history] =...
gradientDescent([ones(length(X_train),1),X_train(:,5)], y_train, theta_aceleracion_gd, alpha, iterations);
error = absMeanError(xtest3,y_test,theta_aceleracion_gd);
fprintf("GD:Error Absoluto medio de prediccion aceleracion: %.5f\n",error);

%4a. conjunto global
alpha = 0.00000002;
iterations = 200000;
theta_gd = zeros(8,1);
fprintf("Global\talpha : %.10f\titerations:%d\n",alpha,iterations);
[theta_gd, J_history] = gradientDescent([ones(length(X_train),1),X_train], y_train, theta_gd, alpha, iterations);
error = absMeanError(xtest4,y_test,theta_gd);
fprintf("GD:Error de prediccion global: %.5f\n",error);
figure();%Crear figura

plot(1:length(J_history), J_history, '-b', 'LineWidth', 2);
xlabel('Number of iterations');%T�tulo del eje X
ylabel('Cost J');%T�tulo del eje Y