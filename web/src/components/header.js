import ComplianceDashboardClient from '../api/complianceDashboardClient';
import BindingClass from "../util/bindingClass";

/**
 * The header component for the Compliance Dashboard website.
 */
export default class Header extends BindingClass {
    constructor() {
        super();

        const methodsToBind = [
            'addHeaderToPage', 'createSiteTitle', 'createUserInfoForHeader',
            'createLoginButton', 'createLogoutButton', 'createButton'
        ];
        this.bindClassMethods(methodsToBind, this);

        this.client = new ComplianceDashboardClient();
    }

    /**
     * Add the header to the page.
     */
    async addHeaderToPage() {
        const currentUser = await this.client.getIdentity();

        const siteTitle = this.createSiteTitle();
        const userInfo = this.createUserInfoForHeader(currentUser);

        const header = document.getElementById('header');
        header.appendChild(siteTitle);
        header.appendChild(userInfo);
    }

    createSiteTitle() {
        const homeButton = document.createElement('a');
        homeButton.classList.add('header_home');
        homeButton.href = 'dashboard.html';
        homeButton.innerText = 'Compliance Dashboard';

        const siteTitle = document.createElement('div');
        siteTitle.classList.add('site-title');
        siteTitle.appendChild(homeButton);

        return siteTitle;
    }

    createUserInfoForHeader(currentUser) {
        const userInfo = document.createElement('div');
        userInfo.classList.add('user-info');

        if (currentUser) {
            const userName = currentUser.name || 'User';
            const logoutButton = this.createLogoutButton(userName);
            userInfo.appendChild(logoutButton);
        } else {
            const loginButton = this.createLoginButton();
            userInfo.appendChild(loginButton);
        }

        return userInfo;
    }

    createLoginButton() {
        return this.createButton('Login', this.client.login);
    }

    createLogoutButton(userName) {
        return this.createButton(`Logout: ${userName}`, this.client.logout);
    }

    createButton(text, clickHandler) {
        const button = document.createElement('a');
        button.classList.add('button');
        button.href = '#';
        button.innerText = text;

        button.addEventListener('click', async (event) => {
            event.preventDefault();
            await clickHandler();
        });

        return button;
    }
}