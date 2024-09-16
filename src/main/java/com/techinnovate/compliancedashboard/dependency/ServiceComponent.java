package com.techinnovate.compliancedashboard.dependency;

import com.techinnovate.compliancedashboard.activity.*;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Automated Compliance Dashboard.
 */
@Singleton
public interface ServiceComponent {

    /**
     * Provides the relevant activity for adding a compliance rule.
     *
     * @return AddComplianceRuleActivity
     */
    AddComplianceRuleActivity provideAddComplianceRuleActivity();
}

