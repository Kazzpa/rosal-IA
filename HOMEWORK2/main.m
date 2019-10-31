clear;
close all;
%Problema 1
%Punto 1 Cargada de datos y visualizacion
data = load("data_flower.txt");
x = data(:,1:2);
y = data(:,3);
plotData(x,y);
