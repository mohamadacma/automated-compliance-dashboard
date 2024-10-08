const webpack = require('webpack');
const path = require('path');
const CopyPlugin = require('copy-webpack-plugin');
const dotenv = require('dotenv');
const fs = require('fs');

const dotenvFile = process.env.API_LOCATION ? `.env.${process.env.API_LOCATION}` : '.env';

// Check if the dotenv file exists
const finalDotenvFile = fs.existsSync(dotenvFile) ? dotenvFile : '.env';

// Load environment variables from the dotenv file
const envResult = dotenv.config({ path: finalDotenvFile });

if (envResult.error) {
  console.error(`Failed to load ${finalDotenvFile}:`, envResult.error);
  throw envResult.error;
}

const env = envResult.parsed;

if (!env) {
  throw new Error(`Failed to parse environment variables from ${finalDotenvFile}`);
}

console.log(`Loaded environment variables from ${finalDotenvFile}:`, env);

const envKeys = Object.keys(env).reduce((prev, next) => {
  prev[`process.env.${next}`] = JSON.stringify(env[next]);
  return prev;
}, {});

module.exports = {
  mode: 'development',
  plugins: [
    new CopyPlugin({
      patterns: [
        {
          from: 'static_assets',
          to: '../',
          globOptions: {
            ignore: ['**/.DS_Store'],
          },
        },
      ],
    }),
    new webpack.DefinePlugin(envKeys),
  ],
  entry: {
   index: path.resolve(__dirname, 'src', 'pages', 'index.js'),
    addComplianceRule: path.resolve(__dirname, 'src', 'pages', 'addComplianceRule.js'),
    complianceStatus: path.resolve(__dirname, 'src', 'pages', 'complianceStatus.js'),
    dashboard: path.resolve(__dirname, 'src', 'pages', 'dashboard.js'),
  },
  output: {
    path: path.resolve(__dirname, 'build'),
    filename: 'src/pages/[name].js',
    publicPath: '/',
  },
  resolve: {
    extensions: ['.js'],
  },
  devServer: {

    static: {
      directory: path.join(__dirname, 'static_assets'),
    },
    port: 3000,
    client: {
      overlay: true,
    },
    devMiddleware: {
      writeToDisk: true,
    },
    headers: {
      "Access-Control-Allow-Origin": "*",
      "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, PATCH, OPTIONS",
      "Access-Control-Allow-Headers": "X-Requested-With, content-type, Authorization",
    },
  },
  devtool: 'source-map',
};
