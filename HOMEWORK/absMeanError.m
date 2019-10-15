function error = absMeanError(x,y,theta)
  error = 0;
  for i=1:length(x)
    x_predicted = x(i,:)*theta;
    error = error + abs( x_predicted - y(i));
  endfor
  error = error / length(x);
endfunction
