package com.techinnovate.compliancedashboard.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.techinnovate.compliancedashboard.dynamodb.models.ComplianceRuleRecord;
import com.techinnovate.compliancedashboard.exceptions.ComplianceRuleNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Accesses data for a compliance rule using {@link ComplianceRuleRecord} to represent the model in DynamoDB.
 */
@Singleton
public class ComplianceRuleDao {
    private final DynamoDBMapper dynamoDBMapper;

    /**
     * Instantiates a ComplianceRuleDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the ComplianceRules table
     */
    @Inject
    public ComplianceRuleDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDBMapper = dynamoDbMapper;
    }

    /**
     * Retrieves a ComplianceRule by ruleId.
     * If not found, throws ComplianceRuleNotFoundException.
     *
     * @param ruleId The rule to look up
     * @return The corresponding ComplianceRuleRecord if found
     */
    public ComplianceRuleRecord getComplianceRuleById(String ruleId) {
        ComplianceRuleRecord rule = dynamoDBMapper.load(ComplianceRuleRecord.class, ruleId);
        if (rule == null) {
            throw new ComplianceRuleNotFoundException(
                    String.format("Could not find compliance rule with ID '%s'", ruleId));
        }
        return rule;
    }

    /**
     * Saves (creates or updates) the given compliance rule.
     *
     * @param rule The compliance rule to save
     */
    public void saveComplianceRule(ComplianceRuleRecord rule) {
        this.dynamoDBMapper.save(rule);
    }

    /**
     * Retrieves a list of ComplianceRuleRecord objects from the database associated with the given framework.
     * This method uses the FrameworkIndex global secondary index to query the rules by their framework.
     *
     * @param framework The compliance framework to retrieve rules for.
     * @return A list of ComplianceRuleRecord objects associated with the given framework.
     */
    public List<ComplianceRuleRecord> getComplianceRulesByFramework(String framework) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":framework", new AttributeValue().withS(framework));

        DynamoDBQueryExpression<ComplianceRuleRecord> queryExpression = new DynamoDBQueryExpression<ComplianceRuleRecord>()
                .withIndexName("FrameworkIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("framework = :framework")
                .withExpressionAttributeValues(valueMap);

        return dynamoDBMapper.query(ComplianceRuleRecord.class, queryExpression);
    }
}