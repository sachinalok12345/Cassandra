package com.chris;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.cassandra.core.WriteOptions;
import org.springframework.data.cassandra.core.CassandraOperations;

/**
 * Created by yijunmao on 11/22/15.
 */

public class CassandraClient {
    private CassandraOperations operations;
    private Cluster cluster;
    private Session session;

    @Required
    public void setOperations(CassandraOperations operations) {
        this.operations = operations;
    }

    @Required
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    @Required
    public void setSession(Session session) {
        this.session = session;
    }


}
