package com.techinnovate.compliancedashboard.converters;

import com.techinnovate.compliancedashboard.dynamodb.models.ComplianceRuleRecord;
import com.techinnovate.compliancedashboard.models.ComplianceRulesModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts between Data and API models.
 */
public class ModelConverter {

    /**
     * Converts a ComplianceRule to a ComplianceRulesModel.
     *
     * @param rule The ComplianceRule to convert
     * @return The converted ComplianceRulesModel
     */
    public ComplianceRulesModel toComplianceRulesModel(ComplianceRuleRecord rule) {
        return ComplianceRulesModel.builder()
                .withRuleId(rule.getRuleId())
                .withRuleName(rule.getRuleName())
                .withDescription(rule.getDescription())
                .withFramework(rule.getFramework())
                .withThreshold(rule.getThreshold())
                .withAction(rule.getAction())
                .withBackground(rule.getBackground())
                .withCreatedAt(rule.getCreatedAt())
                .withUpdatedAt(rule.getUpdatedAt())
                .build();
    }

    /**
     * Converts a list of ComplianceRules to a list of ComplianceRulesModels.
     *
     * @param rules The ComplianceRules to convert
     * @return The converted list of ComplianceRulesModels
     */
    public List<ComplianceRulesModel> toComplianceRulesModelList(List<ComplianceRuleRecord> rules) {
        List<ComplianceRulesModel> ruleModels = new ArrayList<>();

        for (ComplianceRuleRecord rule : rules) {
            ruleModels.add(toComplianceRulesModel(rule));
        }

        return ruleModels;
    }

    /**
     * Converts a ComplianceRulesModel to a ComplianceRule.
     *
     * @param model The ComplianceRulesModel to convert
     * @return The converted ComplianceRule
     */
    public ComplianceRuleRecord toComplianceRule(ComplianceRulesModel model) {
        ComplianceRuleRecord rule = new ComplianceRuleRecord();
        rule.setRuleId(model.getRuleId());
        rule.setRuleName(model.getRuleName());
        rule.setDescription(model.getDescription());
        rule.setFramework(model.getFramework());
        rule.setThreshold(model.getThreshold());
        rule.setAction(model.getAction());
        rule.setBackground(model.getBackground());
        rule.setCreatedAt(model.getCreatedAt());
        rule.setUpdatedAt(model.getUpdatedAt());
        return rule;
    }
}