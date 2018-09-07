import oracle.pg.nosql.*;
import oracle.kv.*;
import oracle.kv.table.TableOperation;
import oracle.pgx.config.*;
import oracle.pgx.common.types.*;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.Edge;
import java.util.*;

public class LoadGraph {
  
  public static void main(String[] szArgs) throws Exception 
  {

    String[] hhosts    = new String[1];
    hhosts[0]          = "node1.oracle.com:5000";
    String szStoreName = "kvstore";
    String szGraphName = "connections";
    int dop 	       = 4;	

    KVStoreConfig kconfig = new KVStoreConfig(szStoreName, hhosts);
    OraclePropertyGraph opg = OraclePropertyGraph.getInstance(kconfig, szGraphName);
    opg.clearRepository(); 

    String szOPVFile = "./connections.opv";
    String szOPEFile = "./connections.ope";

    // This object will handle parallel data loading over the property graph
    OraclePropertyGraphDataLoader opgdl = OraclePropertyGraphDataLoader.getInstance();
    opgdl.loadData(opg, szOPVFile, szOPEFile, dop); 
    
    // Count all vertices
    long countV = 0; 
    Iterator<Vertex> vertices = opg.getVertices().iterator();
    
    while (vertices.hasNext()) {
      vertices.next();
      countV++;
    }

    System.out.println("Vertices found: " + countV);

    // Count all edges
    long countE = 0;
    Iterator<Edge> edges = opg.getEdges().iterator();
    while (edges.hasNext()) {
      edges.next();
      countE++;
    }

    System.out.println("Edges found: " + countE);

    opg.shutdown();
    
  }
  
}

