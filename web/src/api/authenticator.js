import BindingClass from "../util/bindingClass";
import { Auth } from 'aws-amplify';

export default class Authenticator extends BindingClass {
    constructor() {
        super();

        const methodsToBind = ['getCurrentUserInfo', 'isUserLoggedIn', 'getUserToken', 'login', 'logout'];
            this.bindClassMethods(methodsToBind, this);

            this.configureCognito();
        }

    async getCurrentUserInfo() {
        try {
            const cognitoUser = await Auth.currentAuthenticatedUser();
            const { email, name } = cognitoUser.signInUserSession.idToken.payload;
            return { email, name };
        } catch (error) {
            // User is not authenticated
            return null;
        }
    }


    async isUserLoggedIn() {
        try {
            await Auth.currentAuthenticatedUser();
            return true;
        } catch {
            return false;
        }
    }

    async getUserToken() {
        const cognitoSession = await Auth.currentSession();
        return cognitoSession.getIdToken().getJwtToken();
    }

    async login() {
      try {
        console.log('Attempting to log in...');
        await Auth.federatedSignIn();
        console.log('Login successful');
      } catch (error) {
        console.error('Login failed:', error);
      }
    }

    async logout() {
        await Auth.signOut();
    }

    configureCognito() {
    console.log('Configuring Cognito with:', {
            userPoolId: process.env.COGNITO_USER_POOL_ID,
            userPoolWebClientId: process.env.COGNITO_USER_POOL_CLIENT_ID,
            domain: process.env.COGNITO_DOMAIN,
            redirectSignIn: process.env.COGNITO_REDIRECT_SIGNIN,
            redirectSignOut: process.env.COGNITO_REDIRECT_SIGNOUT,
        });
        Auth.configure({
            userPoolId: process.env.COGNITO_USER_POOL_ID,
            userPoolWebClientId: process.env.COGNITO_USER_POOL_CLIENT_ID,
            oauth: {
                domain: process.env.COGNITO_DOMAIN,
                redirectSignIn: process.env.COGNITO_REDIRECT_SIGNIN,
                redirectSignOut: process.env.COGNITO_REDIRECT_SIGNOUT,
                region: 'us-east-2',
                scope: ['openid', 'email', 'profile', 'aws.cognito.signin.user.admin'],
                responseType: 'code'
            }
        });
    }
}
