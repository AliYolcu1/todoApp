package com.aliyo.todoapp.repository;

import com.aliyo.todoapp.model.TaskList;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskListRepository extends CouchbaseRepository<TaskList, String> {
    List<TaskList> findByUserId(String userId);

    Optional<TaskList> findByUserIdAndIsDefaultTrue(String userId);
}
