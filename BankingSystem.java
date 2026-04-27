import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BankingSystem extends Application {

    double balance = 1000;

    // Default Login
    String savedUsername = "admin";
    String savedPassword = "1234";

    @Override
    public void start(Stage stage) {

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

        Button registerBtn = new Button("Register");
        registerBtn.setMinWidth(220);

        Button backBtn = new Button("Back to Login");
        backBtn.setMinWidth(220);

        Label createMsg = new Label();

        VBox createRoot = new VBox(18, createTitle, newUser, newPass,
                registerBtn, backBtn, createMsg);

        createRoot.setAlignment(Pos.CENTER);
        createRoot.setStyle(
                "-fx-background-color: linear-gradient(to bottom right,#0f2027,#203a43,#2c5364);" +
                "-fx-padding:30px;"
        );

        Scene createScene = new Scene(createRoot, 450, 550);

        // ================= DASHBOARD =================

        Label dashTitle = new Label("💳 Banking Dashboard");
        dashTitle.setFont(new Font("Arial", 26));
        dashTitle.setStyle("-fx-text-fill:white; -fx-font-weight:bold;");

        Label balanceLabel = new Label("Current Balance: ₹" + balance);
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

        VBox dashRoot = new VBox(18, dashTitle, balanceLabel, amountField,
                depositBtn, withdrawBtn, logoutBtn, msg);

        dashRoot.setAlignment(Pos.CENTER);
        dashRoot.setStyle(
                "-fx-background-color: linear-gradient(to bottom right,#232526,#414345);" +
                "-fx-padding:30px;"
        );

        Scene dashboardScene = new Scene(dashRoot, 500, 600);

        // ================= LOGIN ACTION =================

        loginBtn.setOnAction(e -> {

            if (username.getText().equals(savedUsername)
                    && password.getText().equals(savedPassword)) {

                loginMsg.setText("");
                stage.setScene(dashboardScene);

            } else {
                loginMsg.setText("❌ Invalid Username or Password");
                loginMsg.setStyle("-fx-text-fill:red;");
            }
        });

        // ================= GO TO CREATE PAGE =================

        createBtn.setOnAction(e -> stage.setScene(createScene));

        // ================= REGISTER ACTION =================

        registerBtn.setOnAction(e -> {

            if (newUser.getText().isEmpty() || newPass.getText().isEmpty()) {

                createMsg.setText("⚠ Fill all fields");
                createMsg.setStyle("-fx-text-fill:yellow;");

            } else {

                savedUsername = newUser.getText();
                savedPassword = newPass.getText();

                createMsg.setText("✅ Account Created Successfully");
                createMsg.setStyle("-fx-text-fill:#00ff99;");
            }
        });

        // ================= BACK TO LOGIN =================

        backBtn.setOnAction(e -> {
            newUser.clear();
            newPass.clear();
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
                    balance += amount;
                    balanceLabel.setText("Current Balance: ₹" + balance);
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

                if (amount > balance) {
                    msg.setText("❌ Insufficient Balance");
                } else if (amount <= 0) {
                    msg.setText("⚠ Enter valid amount");
                } else {
                    balance -= amount;
                    balanceLabel.setText("Current Balance: ₹" + balance);
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