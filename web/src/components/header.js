import ComplianceDashboardClient from '../api/complianceDashboardClient';
import BindingClass from "../util/bindingClass";

export default class Header extends BindingClass {
    constructor(dataStore) {
        super();
        this.dataStore = dataStore;
        this.client = new ComplianceDashboardClient();
        this.bindClassMethods(['addHeaderToPage', 'createLoginButton', 'createLogoutButton'], this);
    }

    async addHeaderToPage() {
        const currentUser = await this.client.getIdentity();
        const header = document.getElementById('header');

        const title = document.createElement('h1');
        title.innerText = 'Compliance Dashboard';
        header.appendChild(title);

        if (currentUser) {
            header.appendChild(this.createLogoutButton(currentUser.name));
        } else {
            header.appendChild(this.createLoginButton());
        }
    }

    createLoginButton() {
        const button = document.createElement('button');
        button.innerText = 'Login';
        button.addEventListener('click', (e) => {
            e.preventDefault(); // Prevent default form submission
            this.client.login();
        });
        return button;
    }

    createLogoutButton(username) {
        return this.createButton(`Logout (${username})`, () => this.client.logout());
    }

    createButton(text, clickHandler) {
        const button = document.createElement('button');
        button.innerText = text;
        button.addEventListener('click', clickHandler);
        return button;
    }
}