function [cost, grad] = costFunction(theta,X,y)
  m = length(y);
  h = sigmoid(X * theta);
         
  cost = (1 / m) * sum(y.*log(h) + (1-y).* log(1-h));
  grad = (1 / m) * X' * (h - y);
    
   
endfunction
