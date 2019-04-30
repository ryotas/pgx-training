CS = g.createChangeSet();
RS = g.queryPgql(" \
  SELECT a, b, p \
  WHERE (a)-[:has_number]->(p)<-[:has_number]-(b), id(a) != id(b) \
")
i = 1
for (r in RS) {
  a = r.getVertex(1).getId();
  b = r.getVertex(2).getId();
  p = r.getVertex(3).getId();
  n = r.getVertex(3).getProperty("number");
  CS.addEdge(1000 + i, a, b).setLabel("same_number_as").setProperty("number", n);
  CS.removeVertex(p);
  i++;
}
g = CS.build()

