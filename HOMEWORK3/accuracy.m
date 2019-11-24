function MAE = accuracy(Theta1, Theta2, X, y)
  %We first make our predictions
  predictions = predict(Theta1,Theta2,X);
  %Then we measure how right our predictions are
  MAE = mean(double(round(predictions') == y))*100;
endfunction
