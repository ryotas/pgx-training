import oracle.pg.nosql.*; 
import oracle.kv.*; 
import oracle.kv.table.TableOperation;
import oracle.pgx.config.*;
import oracle.pgx.common.types.*;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.Edge;

public class CreateGraph 
{
  public static void main(String[] szArgs) throws Exception
  {

    String[] hhosts = new String[1];
    hhosts[0]          = szArgs[0]; // "cluster02:5000";
    String szStoreName = szArgs[1]; // "kvstore"
    String szGraphName = szArgs[2]; // "my_graph"

    KVStoreConfig kconfig = new KVStoreConfig(szStoreName, hhosts);
    OraclePropertyGraph opg = OraclePropertyGraph.getInstance(kconfig, szGraphName);

    opg.clearRepository();

    System.out.println("\nGraph Created! -- " + szGraphName + "\n");
    
    opg.shutdown();
  }
}
