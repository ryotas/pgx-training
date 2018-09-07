import oracle.pg.nosql.*; 
import oracle.kv.*; 
import oracle.kv.table.TableOperation;
import oracle.pgx.config.*;
import oracle.pgx.common.types.*;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.Edge;

public class AddNodeProp
{
  public static void main(String[] szArgs) throws Exception
  {

    String[] hhosts = new String[1];
    hhosts[0]          = szArgs[0]; // "cluster02:5000";
    String szStoreName = szArgs[1]; // "kvstore"
    String szGraphName = szArgs[2]; // "my_graph"
    String szNodeID    = szArgs[3]; // "80"
    String szProperty  = szArgs[4]; // "Yamanaka"
    String szValue     = szArgs[5]; // "Yamanaka"

    KVStoreConfig kconfig = new KVStoreConfig(szStoreName, hhosts);
    OraclePropertyGraph opg = OraclePropertyGraph.getInstance(kconfig, szGraphName);

    Vertex v1 = opg.getVertex(szNodeID);
    v1.setProperty(szProperty, szValue);

    opg.commit();

    System.out.println("Added 1 property: " + opg.getVertex(szNodeID));
    
    opg.shutdown();
  }
}
