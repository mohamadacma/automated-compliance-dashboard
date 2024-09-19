import ComplianceDashboardClient from '../api/complianceDashboardClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";

/**
 * Logic needed for the compliance status page of the Automated Compliance Dashboard.
 */
class ComplianceStatus extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'loadComplianceStatus', 'displayComplianceStatus'], this);
        this.header = new Header();
        this.client = new ComplianceDashboardClient();
    }

    /**
     * Add the header to the page and load the ComplianceStatus.
     */
    async mount() {
        await this.header.addHeaderToPage();
        await this.loadComplianceStatus();
    }

    /**
     * Load the compliance status data from the client.
     */
    async loadComplianceStatus() {
        try {
            const status = await this.client.getComplianceStatus();
            this.displayComplianceStatus(status);
        } catch (error) {
            console.error("Error loading compliance status:", error);
            // You might want to display an error message on the page here
        }
    }

    /**
     * Display the compliance status on the page.
     * @param {Object} status - The compliance status data
     */
    displayComplianceStatus(status) {
        const statusContainer = document.getElementById('compliance-status');

        // Example of how you might structure the status display
        let statusHtml = `
            <h2>Overall Compliance: ${status.overallCompliance}%</h2>
            <div class="status-details">
                <div class="status-item">
                    <h3>Compliant Rules: ${status.compliantRules}</h3>
                </div>
                <div class="status-item">
                    <h3>Non-Compliant Rules: ${status.nonCompliantRules}</h3>
                </div>
            </div>
            <h3>Compliance by Framework:</h3>
            <ul>
        `;

        // Add framework-specific compliance
        for (let framework in status.frameworkCompliance) {
            statusHtml += `
                <li>${framework}: ${status.frameworkCompliance[framework]}%</li>
            `;
        }

        statusHtml += `</ul>`;

        statusContainer.innerHTML = statusHtml;
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const complianceStatus = new ComplianceStatus();
    await complianceStatus.mount();
};

window.addEventListener('DOMContentLoaded', main);
