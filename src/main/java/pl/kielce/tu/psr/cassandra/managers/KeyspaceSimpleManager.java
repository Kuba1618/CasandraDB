package pl.kielce.tu.psr.cassandra.managers;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

public class KeyspaceSimpleManager extends SimpleManager{
	
	private String keyspaceName;
	
	public KeyspaceSimpleManager(CqlSession session, String keyspaceName) {
		super(session);
		this.keyspaceName = keyspaceName;
	}

	public void selectKeyspaces() {
		ResultSet resultSet = session.execute("SELECT keyspace_name FROM system_schema.keyspaces;");
		System.out.print("Keyspaces = ");
		for (Row row : resultSet) {
			System.out.print(row.getString("keyspace_name") + ", ");
		}
		System.out.println();
	}

	public void createKeyspace() {
		executeSimpleStatement("CREATE KEYSPACE IF NOT EXISTS " + keyspaceName + " WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 1};");
	}
	
	public void useKeyspace() {
		executeSimpleStatement("USE " + keyspaceName + ";");
	}

	public void dropKeyspace() {
		executeSimpleStatement("DROP KEYSPACE IF EXISTS " + keyspaceName + ";");
	}	
}
