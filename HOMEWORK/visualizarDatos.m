function visualizarDatos(data,theta)
  data1 = find(data(:,7)==1);
  data2 = find(data(:,7)==2);
  data3 = find(data(:,7)==3);
  figure();


  %Dibujo la función de coste correspondiente a J_history
  
  plot(1:length(data1(:,2)), data1(:,2), '-r', 'LineWidth', 2);
  hold on;
  plot(1:length(data1(:,2)), data2(:,2), '-g', 'LineWidth', 2);
  plot(1:length(data1(:,2)), data3(:,2), '-b', 'LineWidth', 2);
  hold off;
  xlabel('Number of iterations');%Título del eje X
  ylabel('Cost J');%Título del eje Y
endfunction
