function [theta, J_history] = gradientDescentReg(X, y, theta, alpha, num_iters, lambda)

m = length(y); % number of training examples
J_history = zeros(num_iters, 1);

for iter = 1:num_iters
    theta = theta - alpha * (1 / m) * X' * (sigmoid(X * theta) - y) + (lambda / m) * [0; theta(2:length(theta))]; 
    J_history(iter) = costFunctionReg(theta, X, y,lambda);
    
end

end