function stat = estadisticas (x)
  for i = 1:8
    stat(i,:) = featureStat(x(:,i));
  endfor
endfunction