package com.techinnovate.compliancedashboard.activity;

import com.techinnovate.compliancedashboard.activity.requests.AddComplianceRuleRequest;
import com.techinnovate.compliancedashboard.activity.results.AddComplianceRuleResult;
import com.techinnovate.compliancedashboard.converters.ModelConverter;
import com.techinnovate.compliancedashboard.dynamodb.ComplianceRuleDao;
import com.techinnovate.compliancedashboard.dynamodb.models.ComplianceRuleRecord;
import com.techinnovate.compliancedashboard.exceptions.InvalidAttributeValueException;
import com.techinnovate.compliancedashboard.models.ComplianceRulesModel;
import com.techinnovate.compliancedashboard.utils.ComplianceDashboardServiceUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Implementation of the AddComplianceRuleActivity for the ComplianceDashboard's AddComplianceRule API.
 * <p>
 * This API allows the user to create a new compliance rule.
 */
public class AddComplianceRuleActivity {
    private final Logger log = LogManager.getLogger();
    private final ComplianceRuleDao complianceRuleDao;
    /**
     * Instantiates a new AddComplianceRuleActivity object.
     *
     * @param complianceRuleDao ComplianceRuleDao to access the compliance rules table.
     */
    @Inject
    public AddComplianceRuleActivity(ComplianceRuleDao complianceRuleDao) {
        this.complianceRuleDao = complianceRuleDao;
    }

    /**
     * This method handles the incoming request by persisting a new compliance rule
     * with the provided details from the request.
     * <p>
     * It then returns the newly created compliance rule.
     * <p>
     * If any of the provided attributes have invalid values, throws an InvalidAttributeValueException.
     *
     * @param addComplianceRuleRequest request object containing the compliance rule details
     * @return addComplianceRuleResult result object containing the API defined {@link ComplianceRulesModel}
     */
    public AddComplianceRuleResult handleRequest(final AddComplianceRuleRequest addComplianceRuleRequest) {
        log.info("Received AddComplianceRuleRequest {}", addComplianceRuleRequest);

        validateAttributes(addComplianceRuleRequest);

        ComplianceRuleRecord newRule = new ComplianceRuleRecord();
        newRule.setRuleId(ComplianceDashboardServiceUtils.generateRuleId());
        newRule.setRuleName(addComplianceRuleRequest.getRuleName());
        newRule.setDescription(addComplianceRuleRequest.getDescription());
        newRule.setFramework(addComplianceRuleRequest.getFramework());
        newRule.setThreshold(addComplianceRuleRequest.getThreshold());
        newRule.setAction(addComplianceRuleRequest.getAction());
        newRule.setBackground(addComplianceRuleRequest.getBackground());

        String now = ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT);
        newRule.setCreatedAt(now);
        newRule.setUpdatedAt(now);

        complianceRuleDao.saveComplianceRule(newRule);

        ComplianceRulesModel ruleModel = new ModelConverter().toComplianceRulesModel(newRule);
        return AddComplianceRuleResult.builder()
                .withComplianceRule(ruleModel)
                .build();
    }

    private void validateAttributes(AddComplianceRuleRequest request) {
        if (!ComplianceDashboardServiceUtils.isValidString(request.getRuleName())) {
            throw new InvalidAttributeValueException("Rule name [" + request.getRuleName() +
                    "] contains illegal characters");
        }
        if (!ComplianceDashboardServiceUtils.isValidString(request.getDescription())) {
            throw new InvalidAttributeValueException("Description [" + request.getDescription() +
                    "] contains illegal characters");
        }
        if (!ComplianceDashboardServiceUtils.isValidString(request.getFramework())) {
            throw new InvalidAttributeValueException("Framework [" + request.getFramework() +
                    "] contains illegal characters");
        }
        if (!ComplianceDashboardServiceUtils.isValidString(request.getAction())) {
            throw new InvalidAttributeValueException("Action [" + request.getAction() +
                    "] contains illegal characters");
        }
        if (!ComplianceDashboardServiceUtils.isValidString(request.getBackground())) {
            throw new InvalidAttributeValueException("Background [" + request.getBackground() +
                    "] contains illegal characters");
        }
    }
}