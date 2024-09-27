package com.techinnovate.compliancedashboard.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.techinnovate.compliancedashboard.activity.requests.AddComplianceRuleRequest;
import com.techinnovate.compliancedashboard.activity.results.AddComplianceRuleResult;

public class AddComplianceRuleLambda
        extends LambdaActivityRunner<AddComplianceRuleRequest, AddComplianceRuleResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddComplianceRuleRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddComplianceRuleRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    AddComplianceRuleRequest unauthenticatedRequest = input.fromBody(AddComplianceRuleRequest.class);
                    return input.fromUserClaims(claims ->
                            AddComplianceRuleRequest.builder()
                                    .withRuleName(unauthenticatedRequest.getRuleName())
                                    .withDescription(unauthenticatedRequest.getDescription())
                                    .withFramework(unauthenticatedRequest.getFramework())
                                    .withThreshold(unauthenticatedRequest.getThreshold())
                                    .withAction(unauthenticatedRequest.getAction())
                                    .withBackground(unauthenticatedRequest.getBackground())
                                    .withCreatedBy(claims.get("email"))
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideAddComplianceRuleActivity().handleRequest(request)
        );
    }
}
