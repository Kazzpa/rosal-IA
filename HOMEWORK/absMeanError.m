function error = absMeanError(x,y,theta)
  m = length(y);
  pre = x* theta;
  error = (1/m) * sum(abs(pre-y));
endfunction
