import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashMap;

public class BankingSystem extends Application {

    // Store password for each user
    HashMap<String, String> accounts = new HashMap<>();

    // Store separate balance for each user
    HashMap<String, Double> balances = new HashMap<>();

    // Current logged in user
    String currentUser = "";

    @Override
    public void start(Stage stage) {

        // Default admin account
        accounts.put("admin", "1234");
        balances.put("admin", 5000.0);

        // ================= LOGIN PAGE =================

        Label loginTitle = new Label("🏦 Smart Bank Login");
        loginTitle.setFont(new Font("Arial", 28));
        loginTitle.setStyle("-fx-text-fill:white; -fx-font-weight:bold;");

        TextField username = new TextField();
        username.setPromptText("Username");
        username.setMaxWidth(250);

        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setMaxWidth(250);

        Button loginBtn = new Button("Login");
        loginBtn.setMinWidth(220);

        Button createBtn = new Button("Create Account");
        createBtn.setMinWidth(220);

        Label loginMsg = new Label();

        VBox loginRoot = new VBox(18, loginTitle, username, password,
                loginBtn, createBtn, loginMsg);

        loginRoot.setAlignment(Pos.CENTER);
        loginRoot.setStyle(
                "-fx-background-color: linear-gradient(to bottom right,#141e30,#243b55);" +
                "-fx-padding:30px;"
        );

        Scene loginScene = new Scene(loginRoot, 450, 550);

        // ================= CREATE ACCOUNT PAGE =================

        Label createTitle = new Label("📝 Create Account");
        createTitle.setFont(new Font("Arial", 26));
        createTitle.setStyle("-fx-text-fill:white; -fx-font-weight:bold;");

        TextField newUser = new TextField();
        newUser.setPromptText("New Username");
        newUser.setMaxWidth(250);

        PasswordField newPass = new PasswordField();
        newPass.setPromptText("New Password");
        newPass.setMaxWidth(250);

        TextField openingBalance = new TextField();
        openingBalance.setPromptText("Opening Balance");
        openingBalance.setMaxWidth(250);

        Button registerBtn = new Button("Register");
        registerBtn.setMinWidth(220);

        Button backBtn = new Button("Back to Login");
        backBtn.setMinWidth(220);

        Label createMsg = new Label();

        VBox createRoot = new VBox(18, createTitle, newUser, newPass,
                openingBalance, registerBtn, backBtn, createMsg);

        createRoot.setAlignment(Pos.CENTER);
        createRoot.setStyle(
                "-fx-background-color: linear-gradient(to bottom right,#0f2027,#203a43,#2c5364);" +
                "-fx-padding:30px;"
        );

        Scene createScene = new Scene(createRoot, 450, 600);

        // ================= DASHBOARD =================

        Label dashTitle = new Label("💳 Banking Dashboard");
        dashTitle.setFont(new Font("Arial", 26));
        dashTitle.setStyle("-fx-text-fill:white; -fx-font-weight:bold;");

        Label welcome = new Label();
        welcome.setStyle("-fx-text-fill:yellow; -fx-font-size:18px;");

        Label balanceLabel = new Label();
        balanceLabel.setFont(new Font("Arial", 22));
        balanceLabel.setStyle("-fx-text-fill:#00ff99;");

        TextField amountField = new TextField();
        amountField.setPromptText("Enter Amount");
        amountField.setMaxWidth(250);

        Button depositBtn = new Button("💰 Deposit");
        depositBtn.setMinWidth(220);

        Button withdrawBtn = new Button("💸 Withdraw");
        withdrawBtn.setMinWidth(220);

        Button logoutBtn = new Button("🚪 Logout");
        logoutBtn.setMinWidth(220);

        Label msg = new Label();

        VBox dashRoot = new VBox(18, dashTitle, welcome, balanceLabel,
                amountField, depositBtn, withdrawBtn, logoutBtn, msg);

        dashRoot.setAlignment(Pos.CENTER);
        dashRoot.setStyle(
                "-fx-background-color: linear-gradient(to bottom right,#232526,#414345);" +
                "-fx-padding:30px;"
        );

        Scene dashboardScene = new Scene(dashRoot, 500, 650);

        // ================= LOGIN =================

        loginBtn.setOnAction(e -> {

            String user = username.getText();
            String pass = password.getText();

            if (accounts.containsKey(user) &&
                    accounts.get(user).equals(pass)) {

                currentUser = user;

                welcome.setText("Welcome " + currentUser);
                balanceLabel.setText("Current Balance: ₹" +
                        balances.get(currentUser));

                loginMsg.setText("");
                stage.setScene(dashboardScene);

            } else {
                loginMsg.setText("❌ Invalid Username or Password");
                loginMsg.setStyle("-fx-text-fill:red;");
            }
        });

        // ================= GO CREATE ACCOUNT =================

        createBtn.setOnAction(e -> stage.setScene(createScene));

        // ================= REGISTER =================

        registerBtn.setOnAction(e -> {

            try {
                String user = newUser.getText();
                String pass = newPass.getText();
                double amount = Double.parseDouble(openingBalance.getText());

                if (user.isEmpty() || pass.isEmpty()) {

                    createMsg.setText("⚠ Fill all fields");

                } else if (accounts.containsKey(user)) {

                    createMsg.setText("❌ Username already exists");

                } else if (amount < 0) {

                    createMsg.setText("⚠ Invalid Balance");

                } else {

                    accounts.put(user, pass);
                    balances.put(user, amount);

                    createMsg.setText("✅ Account Created Successfully");
                }

            } catch (Exception ex) {
                createMsg.setText("❌ Invalid Input");
            }
        });

        // ================= BACK =================

        backBtn.setOnAction(e -> {
            newUser.clear();
            newPass.clear();
            openingBalance.clear();
            createMsg.setText("");
            stage.setScene(loginScene);
        });

        // ================= DEPOSIT =================

        depositBtn.setOnAction(e -> {

            try {
                double amount = Double.parseDouble(amountField.getText());

                if (amount <= 0) {
                    msg.setText("⚠ Enter valid amount");
                } else {

                    double bal = balances.get(currentUser);
                    bal += amount;
                    balances.put(currentUser, bal);

                    balanceLabel.setText("Current Balance: ₹" + bal);
                    msg.setText("✅ Deposit Successful");
                }

            } catch (Exception ex) {
                msg.setText("❌ Invalid Input");
            }
        });

        // ================= WITHDRAW =================

        withdrawBtn.setOnAction(e -> {

            try {
                double amount = Double.parseDouble(amountField.getText());

                double bal = balances.get(currentUser);

                if (amount <= 0) {
                    msg.setText("⚠ Enter valid amount");

                } else if (amount > bal) {
                    msg.setText("❌ Insufficient Balance");

                } else {

                    bal -= amount;
                    balances.put(currentUser, bal);

                    balanceLabel.setText("Current Balance: ₹" + bal);
                    msg.setText("✅ Withdrawal Successful");
                }

            } catch (Exception ex) {
                msg.setText("❌ Invalid Input");
            }
        });

        // ================= LOGOUT =================

        logoutBtn.setOnAction(e -> {
            username.clear();
            password.clear();
            amountField.clear();
            msg.setText("");
            stage.setScene(loginScene);
        });

        // ================= SHOW =================

        stage.setTitle("Smart Banking System");
        stage.setScene(loginScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}