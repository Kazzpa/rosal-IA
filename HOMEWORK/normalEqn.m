function thetas = normalEqn(x,y)
  thetas = inv(x' * x ) * x' * y;
endfunction