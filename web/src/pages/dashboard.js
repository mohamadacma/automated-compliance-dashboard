import ComplianceDashboardClient from '../api/complianceDashboardClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";

/**
 * Logic needed for the dashboard page of the Compliance Dashboard website.
 */
class Dashboard extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'loadDashboard', 'loadComplianceStatus', 'loadComplianceRules'], this);
        this.header = new Header();
        this.client = new ComplianceDashboardClient();
    }

    /**
     * Add the header to the page and load the Dashboard.
     */
    async mount() {
        await this.header.addHeaderToPage();
         try {
                const identity = await this.client.getIdentity();
                if (!identity) {
                    // Redirect to Cognito login if not authenticated
                    this.client.login();
                    return;
                }
                // Continue with page-specific logic for authenticated users
            } catch (error) {
                console.error('Error checking authentication', error);
            }
        await this.loadDashboard();
    }

    /**
     * Load the Dashboard content.
     */
    async loadDashboard() {
        await this.loadComplianceStatus();
        await this.loadComplianceRules();
    }

    /**
     * Load and display the compliance status.
     */
    async loadComplianceStatus() {
        const statusContainer = document.getElementById('compliance-status');

        // TODO: Replace this with actual data from your client
        const status = {
            compliant: 80,
            nonCompliant: 20
        };

        statusContainer.innerHTML = `
            <div class="status-summary">
                <div class="status-item">
                    <span class="status-label">Compliant:</span>
                    <span class="status-value">${status.compliant}%</span>
                </div>
                <div class="status-item">
                    <span class="status-label">Non-Compliant:</span>
                    <span class="status-value">${status.nonCompliant}%</span>
                </div>
            </div>
        `;
    }

    /**
     * Load and display the compliance rules.
     */
    async loadComplianceRules() {
        const rulesContainer = document.getElementById('rules-list');

        // TODO: Replace this with actual data from your client
        const rules = [
            { id: 1, name: 'Data Encryption', status: 'Compliant' },
            { id: 2, name: 'Access Control', status: 'Non-Compliant' },
            { id: 3, name: 'Regular Audits', status: 'Compliant' }
        ];

        const rulesList = rules.map(rule => `
            <div class="rule-item">
                <span class="rule-name">${rule.name}</span>
                <span class="rule-status ${rule.status.toLowerCase()}">${rule.status}</span>
            </div>
        `).join('');

        rulesContainer.innerHTML = rulesList;
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const dashboard = new Dashboard();
    await dashboard.mount();
};

window.addEventListener('DOMContentLoaded', main);