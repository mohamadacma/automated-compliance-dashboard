<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Compliance Rule</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
    <header id="header">
        <!-- The header content will be dynamically added by the Header class -->
    </header>

    <main class="main-content">
        <h1>Add Compliance Rule</h1>
        <form id="add-compliance-rule-form">
            <div class="form-field">
                <label for="rule-name">Rule Name:</label>
                <input type="text" id="rule-name" name="rule-name" required>
            </div>
            <div class="form-field">
                <label for="description">Description:</label>
                <textarea id="description" name="description" required></textarea>
            </div>
            <div class="form-field">
                <label for="framework">Framework:</label>
                <input type="text" id="framework" name="framework" required>
            </div>
            <div class="form-field">
                <label for="threshold">Threshold:</label>
                <input type="number" id="threshold" name="threshold" required>
            </div>
            <div class="form-field">
                <label for="action">Action:</label>
                <input type="text" id="action" name="action" required>
            </div>
            <div class="form-field">
                <button type="submit" id="save-btn">Save Rule</button>
            </div>
        </form>
        <div id="error-message" class="error-message hidden"></div>
    </main>

    <script src="/assets/addComplianceRule.js"></script>
</body>
</html>