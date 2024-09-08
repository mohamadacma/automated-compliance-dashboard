import ComplianceDashboardClient from '../api/complianceDashboardClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the add Compliance Rule page of the website.
 */
class AddComplianceRule extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToDashboard'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToDashboard);
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the ComplianceDashboardClient.
     */
    mount() {
        document.getElementById('save-btn').addEventListener('click', this.submit);
        this.header.addHeaderToPage();
        this.client = new ComplianceDashboardClient();
    }

    /**
     * Method to run when the add compliance rule submit button is pressed.
     * Calls the ComplianceDashboardService to create the rule.
     */
    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = '';
        errorMessageDisplay.classList.add('hidden');

        const saveButton = document.getElementById('save-btn');
        const origButtonText = saveButton.innerText;
        saveButton.innerText = 'Loading...';

        const ruleName = document.getElementById('rule-name').value;
        const description = document.getElementById('description').value;
        const framework = document.getElementById('framework').value;
        const threshold = document.getElementById('threshold').value;
        const action = document.getElementById('action').value;

        try {
            const rule = await this.client.addComplianceRule(
                ruleName,
                description,
                framework,
                threshold,
                action
            );
            this.dataStore.set('rule', rule);
        } catch (error) {
            saveButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        }
    }

    /**
     * When the rule is added to the datastore, redirect to the dashboard page.
     */
    redirectToDashboard() {
        const rule = this.dataStore.get('rule');
        if (rule != null) {
            window.location.href = '/dashboard.html';
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const addComplianceRule = new AddComplianceRule();
    addComplianceRule.mount();
};

window.addEventListener('DOMContentLoaded', main);