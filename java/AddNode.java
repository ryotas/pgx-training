import oracle.pg.nosql.*; 
import oracle.kv.*; 
import oracle.kv.table.TableOperation;
import oracle.pgx.config.*;
import oracle.pgx.common.types.*;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.Edge;

public class AddNode
{
  public static void main(String[] szArgs) throws Exception
  {

    String[] hhosts = new String[1];
    hhosts[0]          = "node1:5000";
    String szStoreName = "kvstore";
    String szGraphName = "my_graph";
    String szNodeName  = szArgs[0]; // "ULTRA MAN"
    String szCreator   = szArgs[1]; // "Yamanaka"

    KVStoreConfig kconfig = new KVStoreConfig(szStoreName, hhosts);
    OraclePropertyGraph opg = OraclePropertyGraph.getInstance(kconfig, szGraphName);

    Long maxVertexID = opg.getMaxVertexID();
    System.out.println("Max vertex ID: " + maxVertexID);

    Vertex v1 = opg.addVertex(maxVertexID + 1);
    v1.setProperty("name", szNodeName);
    v1.setProperty("creator", szCreator);

    opg.commit();

    System.out.println("Added 1 vertex: " + opg.getVertex(maxVertexID + 1));
    
    opg.shutdown();
  }
}
