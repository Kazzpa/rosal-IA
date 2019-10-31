function J = nnCostFunction(nn_params,  input_layer_size, hidden_layer_size, num_labels, X, y, lambda)
  
  suma = 0;
  inicioT = hidden_layer_size * (input_layer_size + 1);
  theta1 = reshape(nn_params(1:inicioT), hidden_layer_size, (input_layer_size + 1));
  theta2 = reshape(nn_params((inicioT + 1):end), num_labels, (hidden_layer_size + 1));
  
  m = size(X,1);
  
  for i = 1:m
    [a1 a2 a3] = fordward(theta1,theta2,X,i);
    aux = (1:num_labels == y(i));
    h = a3;
    suma = suma + aux * log(h) + (1 - aux) * log(1 - h);
    data3 = a3 - aux';
  endfor
endfunction
