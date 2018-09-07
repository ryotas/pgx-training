import oracle.pg.nosql.*; 
import oracle.kv.*; 
import oracle.kv.table.TableOperation;
import oracle.pgx.config.*;
import oracle.pgx.common.types.*;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.Edge;

public class AddEdge
{
  public static void main(String[] szArgs) throws Exception
  {

    String[] hhosts = new String[1];
    hhosts[0]          = "node1:5000";
    String szStoreName = "kvstore";
    String szGraphName = "my_graph";
    String szSNodeID   = szArgs[0]; // "1"
    String szRelation  = szArgs[1]; // "admires"
    String szTNodeID   = szArgs[2]; // "85"

    KVStoreConfig kconfig = new KVStoreConfig(szStoreName, hhosts);
    OraclePropertyGraph opg = OraclePropertyGraph.getInstance(kconfig, szGraphName);

    Vertex v1 = opg.getVertex(szSNodeID);
    Vertex v2 = opg.getVertex(szTNodeID);
    
    Long maxEdgeID = opg.getMaxEdgeID();
    Edge e1 = opg.addEdge(maxEdgeID + 1, v1, v2, szRelation);

    opg.commit();

    System.out.println("Added 1 edge: " + opg.getEdge(maxEdgeID + 1));
    
    opg.shutdown();
  }
}
