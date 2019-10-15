function stat = estadisticas (x)
  for i = 1:7
    stat(i,:) = featureStat(x(:,i));
  endfor
endfunction