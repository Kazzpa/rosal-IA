function plotData(X,y)
  figure();
  data1_index = find(y==0);
  data2_index = find(y==1);
  data1 = X(data1_index,:);
  data2 = X(data2_index,:);
  plot(data1(:,1), data1(:,2), 'ko');
  hold on;
  plot(data2(:,1), data2(:,2), 'k+');
  xlabel('Exam 1 score');%Título del eje X
  ylabel('Exam 2 score');%Título del eje Y
  legend('admitted','Not admitted');
  hold off;
endfunction
