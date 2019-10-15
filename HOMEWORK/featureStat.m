function stat = featureStat (x)
stat(1) = mean(x);
stat(2) = std(x);
stat(3) = min(x);
stat(4) = max(x);
endfunction