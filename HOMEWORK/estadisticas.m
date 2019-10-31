function stat = estadisticas (x)
  for i = 1:length(x(1,:))
    stat(i,:) = featureStat(x(:,i));
  endfor
endfunction