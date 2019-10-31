function visualizarDatos(data, theta1, theta2, theta3, theta4)
  data1 = data(find(data(:,7)==1),:);
  data2 = data(find(data(:,7)==2),:);
  data3 = data(find(data(:,7)==3),:);
  figure();
  subplot(221);
  hist(data(:,8));
  xlabel('MGP');
  %grafica desplazamiento
  subplot(222);
  t = min(data(:,2)):max(data(:,2));
  f = theta1(2,1)*t+theta1(1,1);
  %Dibujo la función de coste correspondiente a J_history
  
  plot(data1(:,2), data1(:,8), 'xr');
  hold on;
  plot(data2(:,2), data2(:,8), 'xg');
  plot(data3(:,2), data3(:,8), 'xb');
  plot(t, f, "-b");
  hold off;
  xlabel('Desplazamiento');%Título del eje X
  ylabel('MPG');%Título del eje Y
  legend("USA","EUROPA","JAPON");
  
  %grafica peso
  subplot(223);
  t = min(data(:,4)):max(data(:,4));
  f = theta2(2,1)*t+theta2(1,1);
  %Dibujo la función de coste correspondiente a J_history
  
  plot(data1(:,4), data1(:,8), 'xr');
  hold on;
  plot(data2(:,4), data2(:,8), 'xg');
  plot(data3(:,4), data3(:,8), 'xb');
  plot(t, f, "-b");
  hold off;
  xlabel('Peso');%Título del eje X
  ylabel('MPG');%Título del eje Y
  legend("USA","EUROPA","JAPON");
  
  %grafica Aceleracion
  subplot(224);
  t = min(data(:,5)):max(data(:,5));
  f = theta3(2,1)*t+theta3(1,1);
  %Dibujo la función de coste correspondiente a J_history
  
  plot(data1(:,5), data1(:,8), 'xr');
  hold on;
  plot(data2(:,5), data2(:,8), 'xg');
  plot(data3(:,5), data3(:,8), 'xb');
  plot(t, f, "-b");
  hold off;
  xlabel('Aceleracion');%Título del eje X
  ylabel('MPG');%Título del eje Y
  legend("USA","EUROPA","JAPON");
endfunction
