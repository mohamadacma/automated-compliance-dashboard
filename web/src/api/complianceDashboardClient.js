import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

export default class ComplianceDashboardClient extends BindingClass {
    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'addComplianceRule', 'updateComplianceRule', 'deleteComplianceRule', 'getComplianceStatus', 'searchViolations'];
        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }


    async addComplianceRule(ruleName, description, framework, threshold, action, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can add a compliance rule.");
            const response = await this.axiosClient.post(`rules`, {
                ruleName,
                description,
                framework,
                threshold,
                action
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.rule;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async updateComplianceRule(ruleId, ruleName, description, framework, threshold, action, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can update compliance rules.");
            const response = await this.axiosClient.put(`rules/${ruleId}`, {
                ruleName,
                description,
                framework,
                threshold,
                action
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.rule;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async deleteComplianceRule(ruleId, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can delete compliance rules.");
            const response = await this.axiosClient.delete(`rules/${ruleId}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async getComplianceStatus(framework, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can view compliance status.");
            const response = await this.axiosClient.get(`compliance-status?framework=${framework}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.status;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async searchViolations(framework, severity, status, errorCallback) {
        try {
            const queryParams = new URLSearchParams();
            if (framework) queryParams.append('framework', framework);
            if (severity) queryParams.append('severity', severity);
            if (status) queryParams.append('status', status);

            const queryString = queryParams.toString();
            const response = await this.axiosClient.get(`violations/search?${queryString}`);
            return response.data.violations;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

}