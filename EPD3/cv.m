function tInd=cv(N,K)
  group = ones(N,1);
  tInd = zeros(N,1);
  nS = accumarray(group(:),1);
  for g = 1:numel(nS)
      h = find(group==g);
      % compute fold id's for every observation in the  group
      q = ceil(K*(1:nS(g))/nS(g));
      % and permute them to try to balance among all groups
      pq = randperm(K);
      % randomly assign the id's to the observations of this group
      randInd = randperm(nS(g));
      tInd(h(randInd))=pq(q);
  end
end