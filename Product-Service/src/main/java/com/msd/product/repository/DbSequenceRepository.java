package com.msd.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.msd.product.model.DbSequence;
@Repository
public interface DbSequenceRepository extends MongoRepository<DbSequence, String> {

}
