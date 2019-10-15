%Cargamos los datos

% 1 --> Cylinders: número de cilindros del vehículo.
% 2 --> Displacement: desplazamiento.
% 3 --> Horsepower: potencia en caballos.
% 4 --> Weight: peso del coche en libras
% 5 --> Acceleration: tiempo de pasar de 0 a 60 mph.
% 6 --> Model Year: año del modelo.
% 7 --> Origin: 1-USA, 2-Europa, 3-Japón.
% 8 --> MPG: millas por galón de combustible.
data = load("autos.csv");

%Se dividen las columnas en sus respectivas variables
X = data(:,1:7);
y = data(:,8);
stats = estadisticas(X)';

%2 normal equation
%2.1 normalizar datos
X = featureNormalization(X);
y = featureNormalization(y);
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
fprintf("Error de prediccion con atributo desplazamiento: %f.5\n",error1);

%2.3b equacion normal con atributo peso
m = length(y_train);

xtrain2 = [ones(m,1),X_train(:,4)];
m = length(y_test);
xtest2 = [ones(m,1),X_test(:,4)];
theta2 = normalEqn(xtrain2,y_train);

%calcular el error medio absoluto con el conjunto test
error2 = absMeanError(xtest2,y_test,theta2);
fprintf("Error de prediccion con atributo peso: %f.5\n",error2);

%2.3c equacion normal con atributo aceleracion
m = length(y_train);

xtrain3 = [ones(m,1),X_train(:,5)];
m = length(y_test);
xtest3 = [ones(m,1),X_test(:,5)];
theta3 = normalEqn(xtrain3,y_train);

%calcular el error medio absoluto con el conjunto test
error3 = absMeanError(xtest3,y_test,theta3);
fprintf("Error de prediccion con atributo aceleracion: %f.5\n",error3);

%2.3b equacion normal con todo el conjunto de atributos
m = length(y_train);

xtrain4 = [ones(m,1),X_train(:,:)];
m = length(y_test);
xtest4 = [ones(m,1),X_test(:,:)];
theta4 = normalEqn(xtrain4,y_train);

%calcular el error medio absoluto con el conjunto test
error4 = absMeanError(xtest4,y_test,theta4);
fprintf("Error de prediccion con todos los atributos: %f.5\n",error4);
%3. visualizar datos
visualizarDatos(data,theta1);