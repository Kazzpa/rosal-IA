function [cost, grad] = costFunctionReg(theta,X,y,lambda)
  m = length(y);
  h = sigmoid(X * theta);
  
  cost = (-1 / m) * sum(y .* log(h) + (1-y) .* log(1-h))+ (lambda/(2*m)) *sum(theta(2:length(theta)).^2);
  grad = (1 / m) * X' * (h - y) + (lambda / m) * [0; theta(2:length(theta))]; 
   
endfunction
