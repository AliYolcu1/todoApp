package com.aliyo.todoapp.config;


import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@Configuration
@EnableCouchbaseRepositories(basePackages = {"com.aliyo.todoapp.repository"})
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Value("${spring.couchbase.connection-string:localhost}")
    private String connectionString;

    @Value("${spring.couchbase.username:Administrator}")
    private String username;

    @Value("${spring.couchbase.password:password}")
    private String password;

    @Value("${spring.couchbase.bucket:todo-bucket}")
    private String bucketName;

    @Override
    public String getConnectionString() {
        return connectionString;
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getBucketName() {
        return bucketName;
    }

    @Bean
    public Cluster cluster() {
        return Cluster.connect(getConnectionString(), getUserName(), getPassword());
    }

    @Bean
    public Bucket bucket() {
        return cluster().bucket(getBucketName());
    }
}