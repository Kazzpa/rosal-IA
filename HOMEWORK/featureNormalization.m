function X = featureNormalization(X)
  media = mean(X);
  desviacion = std(X);
  X= (X - media)./desviacion;
endfunction
