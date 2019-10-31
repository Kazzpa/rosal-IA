function [theta, J_history] = gradientDescent(X, y, theta, alpha, num_iters)

m = length(y); % number of training examples
J_history = zeros(num_iters, 1);

for iter = 1:num_iters
  
    theta = theta - alpha* (1/m) * (X' * (X * theta - y));
    
    fprintf('Theta at iteration %d:\t%.6f\t%.6f\t%.6f\n', iter,theta(1,1),theta(2,1),theta(3,1));
    J_history(iter) = costFunction(theta, X, y);
    
end

end