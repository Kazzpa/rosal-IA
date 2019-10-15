function p = predict(theta,X)
  %X = [ ones(length(X),1),X];
  p = round(sigmoid(X*theta));
endfunction
